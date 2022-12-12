package cs5004.animator.model;

import java.util.LinkedList;
import java.util.Map;

/**
 * This interface defines methods useful for creating a model representing an
 * animation. It provides methods for adding shapes and animations to the model,
 * as well as retrieving copies of that data.
 * 
 * @author Bram Ziltzer
 *
 */
public interface AnimationModel {

  /**
   * Adds a Shape to the animation model. This allows animations to be added for
   * this Shape.
   * 
   * @param shape the Shape to add to the animation
   * @throws IllegalArgumentException when trying to add a shape with the same
   *                                  name as a shape already in the model
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Adds an animation to the model with the given parameters. The shape to be
   * animated must be declared and added to the model before adding an animation
   * for it.
   * 
   * @param name            the name of the shape to be animated
   * @param startTime       the start time of the animation
   * @param x1              the x coordinate of the shape at the beginning of the
   *                        animation
   * @param y1              the y coordinate of the shape at the beginning of the
   *                        animation
   * @param startXDimension the x dimension of the shape at the beginning of the
   *                        animation
   * @param startYDimension the y dimension of the shape at the beginning of the
   *                        animation
   * @param r1              the red color value between 0 and 255 of the shape at
   *                        the beginning of the animation
   * @param g1              the green color value between 0 and 255 of the shape
   *                        at the beginning of the animation
   * @param b1              the blue color value between 0 and 255 of the shape at
   *                        the beginning of the animation
   * @param endTime         the end time of the animation
   * @param x2              the x coordinate of the shape at the end of the
   *                        animation
   * @param y2              the y coordinate of the shape at the end of the
   *                        animation
   * @param endXDimension   the x dimension of the shape at the end of the
   *                        animation
   * @param endYDimension   the y dimension of the shape at the end of the
   *                        animation
   * @param r2              the red color value between 0 and 255 of the shape at
   *                        the end of the animation
   * @param g2              the green color value between 0 and 255 of the shape
   *                        at the end of the animation
   * @param b2              the blue color value between 0 and 255 of the shape at
   *                        the end of the animation
   * @throws IllegalArgumentException when the specified shape to animate does not
   *                                  exist in the mode.
   */
  void addAnimation(String name, int startTime, int x1, int y1, int startXDimension,
      int startYDimension, int r1, int g1, int b1, int endTime, int x2, int y2, int endXDimension,
      int endYDimension, int r2, int g2, int b2) throws IllegalArgumentException;

  /**
   * Returns an array specifying the location and dimensions of the drawing canvas
   * formatted as [canvasX, canvasY, canvasWidth, canvasHeight].
   * 
   * @return an array specifying the location and dimensions of the drawing canvas
   *         formatted as [canvasX, canvasY, canvasWidth, canvasHeight].
   */
  int[] getCanvasBounds();

  /**
   * Specify the bounding box to be used for the animation display panel.
   * 
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   **/
  void setCanvasBounds(int x, int y, int width, int height);

  /**
   * Returns a deep copy of the list of animations.
   * 
   * @return a deep copy of the list of animations
   */
  LinkedList<Animation> getAnimations();

  /**
   * Returns a deep copy of the list of Shapes.
   * 
   * @return a deep copy of the list of Shapes
   */
  LinkedList<Shape> getShapeList();

  /**
   * Returns the time that the final animation step ends.
   * 
   * @return the time that the final animation step ends
   */
  int getDuration();

  /**
   * Returns a deep copy of the Map whose key is an unique name of a shape and a
   * LinkedList that shape's animations sorted by the order they begin.
   * 
   * @return a deep copy of the Map whose key is an unique name of a shape and a
   *         LinkedList that shape's animations sorted by the order they begin.
   */
  Map<String, LinkedList<Animation>> getAnimationsByShape();

  /**
   * Returns a deep copy of the list of Shapes sorted by the order they were added
   * to the model from the original input.
   * 
   * @return a deep copy of the list of Shapes sorted by the order they were added
   *         to the model from the original input.
   */
  LinkedList<Shape> getShapesByOrderAdded();
}
