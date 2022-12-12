package cs5004.animator.model;

import java.awt.Point;

/**
 * This class represents a geometric oval, or ellipse, with properties useful in
 * animation.
 * 
 * @author Bram Ziltzer
 *
 */
public class Oval extends AbstractShape {

  /**
   * Creates an instance of a two-dimensional oval for use in animations.
   * 
   * @param name          the name of the Oval
   * @param location      the coordinates of the center of the Oval
   * @param xDimension    the length of the x radius of the Oval
   * @param yDimension    the length of the y radius of the Oval
   * @param color         the color of the Oval
   * @param appearTime    the time that the OVal appears in an animation
   * @param disappearTime the time that the Oval disappears in an animation
   * @throws IllegalArgumentException when the Oval's name is an empty String,
   *                                  when the Oval's dimensions are less than or
   *                                  equal to zero, and when the Oval's disappear
   *                                  time is before its appear time
   */
  public Oval(String name, Point location, int xDimension, int yDimension, Color color,
      int appearTime, int disappearTime) throws IllegalArgumentException {

    super(name, location, xDimension, yDimension, color, appearTime, disappearTime);
    this.shapeType = "oval";
  }

  @Override
  public Shape duplicate() {
    return new Oval(this.name, new Point(this.location.x, this.location.y), this.xDimension,
        this.yDimension,
        new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()),
        this.appearTime, this.disappearTime);
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n" + "Type: oval\n" + "Center: " + this.getLocationString()
        + ", X radius: " + this.xDimension + ", Y radius: " + this.yDimension + ", Color: "
        + this.color.toString() + "\n" + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime + "\n";
  }

}
