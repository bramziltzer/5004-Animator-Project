package cs5004.animator.model;

import java.awt.Point;

/**
 * The Shape interface provides definitions for a geometric shape. The shape has
 * properties that allow it to be used for animation.
 * 
 * @author Bram Ziltzer
 *
 */
public interface Shape extends Comparable<Shape> {

  /**
   * Sets the length of the x dimension of the Shape.
   * 
   * @param x the length of the Shape's x dimension
   * @throws IllegalArgumentException when the new dimension is less than or equal
   *                                  to zero
   */
  void setXDimension(int x) throws IllegalArgumentException;

  /**
   * Sets the length of the y dimension of the Shape.
   * 
   * @param y the length of the Shape's y dimension
   * @throws IllegalArgumentException when the new dimension is less than or equal
   *                                  to zero
   */
  void setYDimension(int y) throws IllegalArgumentException;

  /**
   * Returns the length of the Shape's x dimension.
   * 
   * @return the length of the Shape's x dimension
   */
  int getXDimension();

  /**
   * Returns the length of the Shape's y dimension.
   * 
   * @return the length of the Shape's y dimension
   */
  int getYDimension();

  /**
   * Returns the time the Shape appears in an animation.
   * 
   * @return the time the Shape appears in an animation
   */
  int getAppearTime();

  /**
   * Returns the time the Shape disappears in an animation.
   * 
   * @return the time the Shape disappears in an animation
   */
  int getDisappearTime();

  /**
   * Sets the coordinates of the location of the Shape.
   * 
   * @param destination the Point to move the Shape to
   */
  void setLocation(Point destination);

  /**
   * Returns the current Point at which the Shape is located.
   * 
   * @return the current Point at which the Shape is located.
   */
  Point getLocation();

  /**
   * Returns a string representing the coordinates of the Shape's current
   * location.
   * 
   * @return a string representing the coordinates of the Shape's current location
   */
  String getLocationString();

  /**
   * Sets the color of the Shape.
   * 
   * @param color the Color to set the Shape to
   */
  void setColor(Color color);

  /**
   * Sets the time that the Shape disappears.
   * 
   * @param time the time that the Shape disappears
   * @throws IllegalArgumentException when the new disappear time is before the
   *                                  shape's appear time
   */
  void setDisappearTime(int time);

  /**
   * Sets the time that the Shape appears.
   * 
   * @param time the time that the Shape appears
   * @throws IllegalArgumentException when the new appear time is below zero and
   *                                  when the new appear time is after the
   *                                  shape's disappear time
   */
  void setAppearTime(int time);

  /**
   * Returns the current color of the Shape.
   * 
   * @return the Color of the Shape
   */
  Color getColor();

  /**
   * Returns the name of the Shape.
   * 
   * @return the name of the Shape
   */
  String getName();

  /**
   * Returns the type of geometric shape of this Shape.
   * 
   * @return the type of geometric shape of this Shape
   */
  String getShapeType();

  /**
   * Returns a deep copy of the shape.
   * 
   * @return a deep copy of the Shape
   */
  Shape duplicate();
}
