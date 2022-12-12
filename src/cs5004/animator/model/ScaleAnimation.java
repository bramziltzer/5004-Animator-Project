package cs5004.animator.model;

import java.awt.Point;

/**
 * A ScaleAnimation represents a change of a Shape's dimensions over time.
 * 
 * @author Bram Ziltzer
 *
 */
public class ScaleAnimation extends AbstractAnimation {

  private int xDimensionStart;
  private int yDimensionStart;
  private int xDimensionEnd;
  private int yDimensionEnd;

  /**
   * {@inheritDoc}
   * 
   * @param xDimensionStart the x dimension at the start of the animation
   * @param yDimensionStart the y dimension at the start of the animation
   * @param xDimensionEnd   the x dimension at the end of the animation
   * @param yDimensionEnd   the y dimension at the end of the animation
   * @throws IllegalArgumentException when either the x or y dimensions are less
   *                                  than or equal to zero
   */
  public ScaleAnimation(Shape shape, int startTime, int endTime, int xDimensionStart,
      int yDimensionStart, int xDimensionEnd, int yDimensionEnd) throws IllegalArgumentException {
    super(shape, startTime, endTime);

    if (xDimensionEnd <= 0 || yDimensionEnd <= 0) {
      throw new IllegalArgumentException("The new x and y dimensions must be greater than 0.");
    }

    this.xDimensionStart = xDimensionStart;
    this.yDimensionStart = yDimensionStart;
    this.xDimensionEnd = xDimensionEnd;
    this.yDimensionEnd = yDimensionEnd;

    this.animationType = "scale";
  }

  @Override
  public Animation duplicate() {
    return new ScaleAnimation(this.shape.duplicate(), this.startTime, this.endTime,
        this.xDimensionStart, this.yDimensionStart, this.xDimensionEnd, this.yDimensionEnd);
  }

  @Override
  public Shape updateShape(Shape shapeToUpdate, int t) {
    int xInstance = Animation.tween(xDimensionStart, xDimensionEnd, startTime, endTime, t);
    int yInstance = Animation.tween(yDimensionStart, yDimensionEnd, startTime, endTime, t);
    shapeToUpdate.setXDimension(xInstance);
    shapeToUpdate.setYDimension(yInstance);
    return shapeToUpdate;
  }

  @Override
  public String toString() throws IllegalArgumentException {
    switch (this.shape.getShapeType()) {
      case "rectangle":
        return "Shape " + this.shape.getName() + " scales from Width: " + this.xDimensionStart
            + ", Height: " + this.yDimensionStart + " to Width: " + this.xDimensionEnd 
            + ", Height: " + this.yDimensionEnd + " from t=" + this.startTime + " to t=" 
            + this.endTime;
      case "oval":
        return "Shape " + this.shape.getName() + " scales from X Radius: " + this.xDimensionStart
            + ", Y Radius: " + this.yDimensionStart + " to X Radius: " + this.xDimensionEnd
            + ", Y Radius: " + this.yDimensionEnd + " from t=" + this.startTime + " to t="
            + this.endTime;
      default:
        throw new IllegalArgumentException("Error producing toString: unrecognized shape.");
    }

  }

  @Override
  public Color getStartColor() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("getStartcolor not supported for this animation type");

  }

  @Override
  public Color getEndColor() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("getEndColor not supported for this animation type");
  }

  @Override
  public int getStartXDimension() throws UnsupportedOperationException {
    return this.xDimensionStart;
  }

  @Override
  public int getEndXDimension() throws UnsupportedOperationException {
    return this.xDimensionEnd;
  }

  @Override
  public int getStartYDimension() throws UnsupportedOperationException {
    return this.yDimensionStart;
  }

  @Override
  public int getEndYDimension() throws UnsupportedOperationException {
    return this.yDimensionEnd;
  }

  @Override
  public Point getStartLocation() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "getStartLocation not supported for this animation type.");
  }

  @Override
  public Point getEndLocation() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "getEndLocation not supported for this animation type.");
  }

}
