package cs5004.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * The TextView is a view implementation that turns a model of shapes and
 * animations into a human readable output.
 * 
 * @author Bram Ziltzer
 *
 */
public class TextView implements View {

  private Appendable output;
  private String viewType;

  /**
   * Creates an instance of a TextView.
   * 
   * @param output the object at which to output the data
   */
  public TextView(Appendable output) {
    this.viewType = "text";
    this.output = output;
  }

  @Override
  public void runTextView(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws IOException {
    output.append(getOutputText(shapes, animations));
  }

  @Override
  public String getOutputText(LinkedList<Shape> shapes, LinkedList<Animation> animations) {
    String outputString = "";

    // add shape type, location, and dimensions
    for (Shape shape : shapes) {
      if (shape.getShapeType().equals("rectangle")) {
        outputString += "Create rectangle " + shape.getName() + " with corner at "
            + shape.getLocationString() + " with color" + shape.getColor() + ", width "
            + shape.getXDimension() + " and height " + shape.getYDimension()
            + System.lineSeparator();
      } else if (shape.getShapeType().equals("oval")) {
        outputString += "Create oval " + shape.getName() + " with center at "
            + shape.getLocationString() + " with color" + shape.getColor() + ", x radius "
            + shape.getXDimension() + " and y radius " + shape.getYDimension()
            + System.lineSeparator();
      }
    }
    outputString += System.lineSeparator();

    // add shape appear and disappear times
    for (Shape shape : shapes) {
      outputString += shape.getName() + " appears at time t=" + shape.getAppearTime()
          + " and disappears at time t=" + shape.getDisappearTime() + System.lineSeparator();
    }
    outputString += System.lineSeparator();

    // add animation details
    for (Animation animation : animations) {
      switch (animation.getAnimationType()) {
        case "location":
          outputString += animation.getName() + " moves from " + "("
              + animation.getStartLocation().getX() + "," + animation.getStartLocation().getY()
              + ") to (" + animation.getEndLocation().getX() + "," 
              + animation.getEndLocation().getY()
              + ") from t=" + animation.getStartTime() + " to t=" + animation.getEndTime()
              + System.lineSeparator();
          break;
  
        case "color":
          outputString += animation.getName() + " changes color from " + animation.getStartColor()
              + " to " + animation.getEndColor() + " from t=" + animation.getStartTime() + " to t="
              + animation.getEndTime() + System.lineSeparator();
          break;
  
        case "scale":
          String xName = "";
          String yName = "";
          int startX = animation.getStartXDimension();
          int startY = animation.getStartYDimension();
          int endX = animation.getEndXDimension();
          int endY = animation.getEndYDimension();
  
          if (animation.getShape().getShapeType().equals("oval")) {
            xName = "x radius";
            yName = "y radius";
          } else if (animation.getShape().getShapeType().equals("rectangle")) {
            xName = "width";
            yName = "height";
          }
          
          // if x dimension changes
          if (startX != endX) {
            outputString += animation.getName() + " changes " + xName + " from " + startX + " to "
                + endX + " from t=" + animation.getStartTime() + " to t=" + animation.getEndTime()
                + System.lineSeparator();
          }
          // if y dimension changes
          if (startY != endY) {
            outputString += animation.getName() + " changes " + yName + " from " + startY + " to "
                + endY + " from t=" + animation.getStartTime() + " to t=" + animation.getEndTime()
                + System.lineSeparator();
          }
          break;
        default:
          // no default case
      }
    }
    return outputString;
  }

  @Override
  public String getViewType() {
    return this.viewType;
  }

  @Override
  public void initializeFrame() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method initializeFrame is unsupported for text view.");

  }

  @Override
  public void setViewCanvas(int x, int y, int width, int height)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method setViewCanvas is unsupported for text view.");

  }

  @Override
  public void update() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method update is unsupported for text view.");

  }

  @Override
  public void addShapesToFrame(ArrayList<Shape> shape) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method addShapesToFrame is unsupported for text view.");

  }

  @Override
  public int getSpeed() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method getSpeed is unsupported for text view.");
  }

  @Override
  public void runSVGView(LinkedList<Shape> shapes, Map<String, LinkedList<Animation>> shapeMap,
      int[] canvasSize, int duration) throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Method runSVGView is unsupported for text view.");

  }

  @Override
  public void setController(Controller controller) {
    throw new UnsupportedOperationException("Method setController is unsupported for text view.");

  }

  @Override
  public void changeSpeedDisplay(int newSpeed) {
    throw new UnsupportedOperationException(
        "Method changeSpeedDisplay is unsupported for text view.");

  }

  @Override
  public String getSvgOutputText(LinkedList<Shape> shapes,
      Map<String, LinkedList<Animation>> shapeMap, int[] canvas)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method changeSpeedDisplay is unsupported for text view.");
  }

}
