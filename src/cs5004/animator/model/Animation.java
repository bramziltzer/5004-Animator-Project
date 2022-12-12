package cs5004.animator.model;

import java.awt.Point;

/**
 * An Animation represents the change of a shape's properties over a period of
 * time. It is one step in what can be a larger animation. An Animation does not
 * automatically mutate its associated Shape; rather, it defines the properties
 * of that shape over a period of time.
 * 
 * @author Bram Ziltzer
 *
 */
public interface Animation extends Comparable<Animation> {

  /**
   * The Shape associated with this Animation.
   * 
   * @return the Shape associated with this Animation
   */
  Shape getShape();

  /**
   * Gets the color of the shape at the start of this animation.
   * 
   * @return the color of the shape at the start of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation whose color does not change
   */
  Color getStartColor() throws UnsupportedOperationException;

  /**
   * Gets the color of the shape at the end of this animation.
   * 
   * @return the color of the shape at the start of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation whose color does not change
   */
  Color getEndColor() throws UnsupportedOperationException;

  /**
   * Gets the x dimension of the shape at the start of this animation.
   * 
   * @return the x dimension of the shape at the start of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation that does not change the
   *                                       shape's dimensions
   */
  int getStartXDimension() throws UnsupportedOperationException;

  /**
   * Gets the x dimension of the shape at the end of this animation.
   * 
   * @return the x dimension of the shape at the end of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation that does not change the
   *                                       shape's dimensions
   */
  int getEndXDimension() throws UnsupportedOperationException;

  /**
   * Gets the y dimension of the shape at the start of this animation.
   * 
   * @return the y dimension of the shape at the start of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation that does not change the
   *                                       shape's dimensions
   */
  int getStartYDimension() throws UnsupportedOperationException;

  /**
   * Gets the y dimension of the shape at the end of this animation.
   * 
   * @return the y dimension of the shape at the end of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation that does not change the
   *                                       shape's dimensions
   */
  int getEndYDimension() throws UnsupportedOperationException;

  /**
   * Gets the coordinates of the shape at the start of this animation.
   * 
   * @return the coordinates of the shape at the start of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation that does not change the
   *                                       shape's location
   */
  Point getStartLocation() throws UnsupportedOperationException;

  /**
   * Gets the coordinates of the shape at the end of this animation.
   * 
   * @return the coordinates of the shape at the end of this animation.
   * @throws UnsupportedOperationException when this method is called on an
   *                                       animation that does not change the
   *                                       shape's location
   */
  Point getEndLocation() throws UnsupportedOperationException;

  /**
   * Gets the start time of this animation.
   * 
   * @return the start time of this animation.
   */
  int getStartTime();

  /**
   * Gets the end time of this animation.
   * 
   * @return the end time of this animation.
   */
  int getEndTime();

  /**
   * Returns a deep copy of this animation.
   * 
   * @return a deep copy of this animation.
   */
  Animation duplicate();

  /**
   * Gets the name of the Shape associated with this animation.
   * 
   * @return the name of the Shape associated with this animation.
   */
  String getName();

  /**
   * Gets the type of this animation.
   * 
   * @return the type of this animation.
   */
  String getAnimationType();

  /**
   * Updates the given Shape to the properties specified by the animation at a
   * moment in time, represented by a tick.
   * 
   * @param shape the Shape whose properties to change
   * @param tick  the unitless moment in time at which to determine the properties
   *              of the Shape
   * @return the mutated Shape with properties at a snapshot in time
   */
  Shape updateShape(Shape shape, int tick);

  /**
   * Calculates a value at a moment in time using the start value, start time, end
   * value, and end time. Assumes a constant rate of transition from the start
   * value to the end value.
   * 
   * @param a  the start value
   * @param b  the end value
   * @param tA the start time
   * @param tB the end time
   * @param t  the time at which to get the value between tA and tB
   * @return the value between a and b at the specified moment in time t
   */
  public static int tween(int a, int b, int tA, int tB, int t) {
    return (a * (tB - t) / (tB - tA)) + (b * (t - tA) / (tB - tA));
  }
}
