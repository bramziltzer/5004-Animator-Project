package cs5004.animator.model;

import java.awt.Point;

/**
 * An AbstractShape is a generic geometric 2D shape suitable for use in
 * animations.
 * 
 * @author Bram Ziltzer
 *
 */
public abstract class AbstractShape implements Shape {

  protected String name;
  protected String shapeType;
  protected Color color;
  protected Point location;
  protected int xDimension;
  protected int yDimension;
  protected int appearTime;
  protected int disappearTime;

  /**
   * Creates an instance of an AbstractShape with the given parameters for use in
   * animations.
   * 
   * @param name          the unique name of the shape
   * @param location      the coordinates of the shape
   * @param xDimension    the length of the shape's x dimension
   * @param yDimension    the length of the shape's y dimension
   * @param color         the shape's color
   * @param appearTime    the time the shape appears in an animation
   * @param disappearTime the time the shape disappears in an animation
   * @throws IllegalArgumentException when the Shape's name is empty, when the x
   *                                  and y dimensions are less than 1, when the
   *                                  appear time is negative, and when the
   *                                  disappear time is before the appear time
   */
  public AbstractShape(String name, Point location, int xDimension, int yDimension, Color color,
      int appearTime, int disappearTime) throws IllegalArgumentException {

    if (name.equals("")) {
      throw new IllegalArgumentException("The shape's name cannot be empty.");
    } else if (xDimension <= 0 || yDimension <= 0) {
      throw new IllegalArgumentException("The x and y dimensions must be greater than 0.");
    } else if (appearTime < 0) {
      throw new IllegalArgumentException("Shape cannot appear at a negative time.");
    } else if (disappearTime < appearTime) {
      throw new IllegalArgumentException("The shape can't disappear before it appears.");
    }

    this.name = name;
    this.color = color;
    this.xDimension = xDimension;
    this.yDimension = yDimension;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    this.location = location;
  }

  @Override
  public void setXDimension(int x) throws IllegalArgumentException {
    if (x <= 0) {
      throw new IllegalArgumentException("The x dimension must be greater than 0.");
    }
    this.xDimension = x;

  }

  @Override
  public void setYDimension(int y) throws IllegalArgumentException {
    if (y <= 0) {
      throw new IllegalArgumentException("The y dimension must be greater than 0.");
    }
    this.yDimension = y;
  }

  @Override
  public int getXDimension() {
    return this.xDimension;
  }

  @Override
  public int getYDimension() {
    return this.yDimension;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setLocation(Point destination) {
    this.location = destination;
  }

  @Override
  public void setDisappearTime(int time) {
    if (time < this.appearTime) {
      throw new IllegalArgumentException("The shape can't disappear before it appears.");
    }
    this.disappearTime = time;
  }

  @Override
  public void setAppearTime(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("The shape can't appear at negative time.");
    } else if (time > this.disappearTime) {
      throw new IllegalArgumentException("The shape can't disappear before it appears.");
    }
    this.appearTime = time;
  }

  @Override
  public Point getLocation() {
    return this.location;
  }

  @Override
  public String getLocationString() {
    return "(" + location.getX() + "," + location.getY() + ")";
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  @Override
  public int compareTo(Shape o) {
    return this.appearTime - ((AbstractShape) o).appearTime;
  }

}
