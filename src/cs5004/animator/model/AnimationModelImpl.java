package cs5004.animator.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class is an implementation of an AnimationModel, which is used for
 * creating, storing, and returning data and data structures used for creating
 * an animation.
 * 
 * @author Bram Ziltzer
 *
 */
public final class AnimationModelImpl implements AnimationModel {

  private LinkedList<Animation> animationSteps;
  private Map<String, Shape> shapes;
  private Map<String, LinkedList<Animation>> animationsByShape;
  private LinkedList<Shape> shapesByOrderAdded;

  private int canvasXPoint = 0;
  private int canvasYPoint = 0;
  private int canvasWidth = 50;
  private int canvasHeight = 50;

  private int duration;

  /**
   * Creates an instance of an AnimationModelImpl.
   */
  public AnimationModelImpl() {
    this.animationSteps = new LinkedList<Animation>();

    this.shapes = new HashMap<String, Shape>();
    this.animationsByShape = new HashMap<String, LinkedList<Animation>>();
    this.shapesByOrderAdded = new LinkedList<Shape>();
    this.duration = 0;
  }

  /*
   * Returns true if the given shape name is a shape that already exists in the
   * model. Returns false otherwise.
   */
  private boolean shapeExists(String shapeName) {
    return shapes.containsKey(shapeName);
  }

  @Override
  public LinkedList<Shape> getShapesByOrderAdded() {
    LinkedList<Shape> shapeList = new LinkedList<Shape>();
    for (Shape shape : this.shapesByOrderAdded) {
      shapeList.add(shape.duplicate());
    }
    return shapeList;
  }

  /*
   * Adds the given animation to the list of animations in sorted order and to the
   * list corresponding to the relevant shape in the animationsByShape map.
   */
  private void addAnimationToLists(Animation animation) {
    // add shape to the shapes LinkedList in order
    int i = 0;
    String shapeName = animation.getShape().getName();
    if (!this.animationSteps.isEmpty()) {
      // increment until value is less than value at index i or until at end of list
      Iterator<Animation> iterator = animationSteps.iterator();
      while (iterator.hasNext()) {
        if (animation.compareTo(iterator.next()) <= 0) {
          break;
        }
        i++;
      }
    }
    this.animationSteps.add(i, animation);

    // add shape to the animationsByShape HashMap list value in order
    int j = 0;
    LinkedList<Animation> shapeAnimationsList = this.animationsByShape.get(shapeName);
    if (!shapeAnimationsList.isEmpty()) {
      // increment until value is less than value at index i or until at end of list
      Iterator<Animation> iterator = shapeAnimationsList.iterator();
      while (iterator.hasNext()) {
        if (animation.compareTo(iterator.next()) <= 0) {
          break;
        }
        i++;
      }
    }
    shapeAnimationsList.add(j, animation);
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    String shapeName = shape.getName();
    if (shapeExists(shapeName)) {
      throw new IllegalArgumentException("Cannot use the same name as an existing shape.");
    }
    this.shapes.put(shapeName, shape);

    // add shape to map of animations by shape, instantiate list of shapes
    this.animationsByShape.put(shapeName, new LinkedList<Animation>());
    this.shapesByOrderAdded.add(shape);
  }

  /*
   * Returns true if a given animation for a shape is valid. Returns false
   * otherwise. An animation is invalid if it is the same type of animation as an
   * already existing animation for a single shape in the same time frame as the
   * existing animation.
   */
  private boolean isValidAnimation(String shapeName, String type, int startTime, int endTime) {
    LinkedList<Animation> animationList = animationsByShape.get(shapeName);
    if (!(animationList.isEmpty())) {
      for (Animation existingAnimation : animationList) {
        if (existingAnimation.getAnimationType() == type
            && !(startTime >= existingAnimation.getEndTime()
                || endTime <= existingAnimation.getStartTime())) {
          return false;
        }
      }
    }
    return true;
  }

  /*
   * Changes the disappear time of the shape if the animation goes longer than the
   * shape exists. Also sets the duration of the entire animation if the animation
   * disappear time is longer than the current duration.
   */
  private void setNewDisappearTime(String shapeName, int endTime) {
    int infinity = Integer.MAX_VALUE;
    Shape shape = shapes.get(shapeName);
    int disappearTime = shape.getDisappearTime();
    if (endTime > disappearTime || disappearTime == infinity) {
      shape.setDisappearTime(endTime);
    }
    // update duration of the whole animation
    if (endTime > this.duration) {
      this.duration = endTime;
    }
  }

  @Override
  public void addAnimation(String name, int startTime, int x1, int y1, int startXDimension,
      int startYDimension, int r1, int g1, int b1, int endTime, int x2, int y2, int endXDimension,
      int endYDimension, int r2, int g2, int b2) throws IllegalArgumentException {
    if (!shapeExists(name)) {
      throw new IllegalArgumentException(
          "Animation cannot be added: shape name does not match an existing shape.");
    }
    Shape shape = shapes.get(name);
    if (shape.getShapeType().equals("oval")) {
      // adjust dimensions to radius instead of diameter
      startXDimension /= 2;
      startYDimension /= 2;
      endXDimension /= 2;
      endYDimension /= 2;

      // adjust center location for oval
      x1 += startXDimension;
      x2 += endXDimension;
      y1 += startYDimension;
      y2 += endYDimension;

    }

    // set initial shape location
    if (shape.getLocation() == null) {
      shape.setLocation(new Point(x1, y1));
    }

    // set initial shape color
    if (shape.getColor() == null) {
      shape.setColor(new Color(r1, g1, b1));
    }

    int infinity = Integer.MAX_VALUE;

    // set initial shape dimensions
    if (shape.getXDimension() == infinity) {
      shape.setXDimension(startXDimension);
    }
    if (shape.getYDimension() == infinity) {
      shape.setYDimension(startYDimension);
    }

    // set appear and disappear times
    if (shape.getAppearTime() == infinity) {
      shape.setAppearTime(startTime);
    }
    this.setNewDisappearTime(name, endTime);

    // add animations to model:
    // move animation
    if ((x1 != x2 || y1 != y2) && isValidAnimation(name, "location", startTime, endTime)) {
      addAnimationToLists(
          new MoveAnimation(shape, startTime, endTime, new Point(x1, y1), new Point(x2, y2)));
    }
    // scale animation
    if ((startXDimension != endXDimension || startYDimension != endYDimension)
        && isValidAnimation(name, "scale", startTime, endTime)) {
      addAnimationToLists(new ScaleAnimation(shape, startTime, endTime, startXDimension,
          startYDimension, endXDimension, endYDimension));
    }
    // color animation
    if ((r1 != r2 || g1 != g2 || b1 != b2) && isValidAnimation(name, "color", startTime, endTime)) {
      addAnimationToLists(new ColorAnimation(shape, startTime, endTime, new Color(r1, g1, b1),
          new Color(r2, g2, b2)));
    }
  }

  @Override
  public LinkedList<Animation> getAnimations() {
    LinkedList<Animation> animationsList = new LinkedList<Animation>();
    for (Animation a : animationSteps) {
      animationsList.add(a.duplicate());
    }
    return animationsList;
  }

  @Override
  public LinkedList<Shape> getShapeList() {
    LinkedList<Shape> shapesList = new LinkedList<Shape>();
    // get shapes from HashMap and add to new LinkedList
    for (Shape s : this.shapes.values()) {
      shapesList.add(s.duplicate());
    }
    // sort the list
    shapesList.sort((a, b) -> a.compareTo(b));
    return shapesList;
  }

  @Override
  public Map<String, LinkedList<Animation>> getAnimationsByShape() {
    Map<String, LinkedList<Animation>> mapCopy = new HashMap<String, LinkedList<Animation>>();
    // for each animation list in the map, create copy of list and add to map copy
    for (Map.Entry<String, LinkedList<Animation>> entry : animationsByShape.entrySet()) {
      LinkedList<Animation> listCopy = new LinkedList<Animation>();
      for (Animation animation : entry.getValue()) {
        listCopy.add(animation.duplicate());
      }

      mapCopy.put(entry.getKey(), listCopy);
    }

    return mapCopy;
  }

  @Override
  public void setCanvasBounds(int x, int y, int width, int height) {
    this.canvasXPoint = x;
    this.canvasYPoint = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }

  @Override
  public int[] getCanvasBounds() {
    return new int[] { this.canvasXPoint, this.canvasYPoint, this.canvasWidth, this.canvasHeight };
  }

  @Override
  public int getDuration() {
    return this.duration;
  }

  @Override
  public String toString() {
    String result = "";
    // list of shapes
    for (Shape s : this.getShapeList()) {
      result += s.toString() + "\n";
    }

    // list of animations
    for (Animation a : animationSteps) {
      result += a.toString() + "\n";
    }

    return result;
  }

}
