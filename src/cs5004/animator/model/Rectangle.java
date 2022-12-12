package cs5004.animator.model;

import java.awt.Point;

/**
 * This class represents a geometric rectangle with properties useful in
 * animation.
 * 
 * @author Bram Ziltzer
 *
 */
public class Rectangle extends AbstractShape {

  /**
   * Creates an instance of a two-dimensional Rectangle for use in animations.
   * 
   * @param name          the name of the Rectangle
   * @param location      the coordinates of the lower left corner of the
   *                      Rectangle
   * @param xDimension    the width of the Rectangle
   * @param yDimension    the height of the Rectangles
   * @param color         the color of the Rectangle
   * @param appearTime    the time that the Rectangle appears in an animation
   * @param disappearTime the time that the Rectangle disappears in an animation
   * @throws IllegalArgumentException when the Rectangle's name is an empty
   *                                  String, when the Rectangle's dimensions are
   *                                  less than or equal to zero, and when the
   *                                  Rectangle's disappear time is before its
   *                                  appear time
   */
  public Rectangle(String name, Point location, int xDimension, int yDimension, Color color,
      int appearTime, int disappearTime) throws IllegalArgumentException {

    super(name, location, xDimension, yDimension, color, appearTime, disappearTime);
    this.shapeType = "rectangle";
  }

  @Override
  public Shape duplicate() {
    return new Rectangle(this.name, new Point(this.location.x, this.location.y), this.xDimension,
        this.yDimension,
        new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()),
        this.appearTime, this.disappearTime);
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n" + "Type: rectangle\n" + "Lower Left Corner: "
        + this.getLocationString() + ", Width: " + this.xDimension + ", Height: " + this.yDimension
        + ", Color: " + this.color.toString() + "\n" + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime + "\n";
  }
}
