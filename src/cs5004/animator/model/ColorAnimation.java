package cs5004.animator.model;

import java.awt.Point;

/**
 * A ColorAnimation represents the change of a shape's color over time.
 * 
 * @author Bram Ziltzer
 *
 */
public class ColorAnimation extends AbstractAnimation {
  private Color startColor;
  private Color endColor;

  /**
   * {@inheritDoc}
   * 
   * @param startColor the Color at the beginning of this animation
   * @param endColor   the Color at the end of this animation
   */
  public ColorAnimation(Shape shape, int startTime, int endTime, Color startColor, Color endColor) {
    super(shape, startTime, endTime);
    this.startColor = startColor;
    this.endColor = endColor;
    this.animationType = "color";
  }

  @Override
  public Animation duplicate() {
    return new ColorAnimation(this.shape.duplicate(), this.startTime, this.endTime,
        new Color(this.startColor.getRed(), this.startColor.getGreen(), this.startColor.getBlue()),
        new Color(this.endColor.getRed(), this.endColor.getGreen(), this.endColor.getBlue()));
  }

  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " changes color from " + this.startColor + " to "
        + this.endColor + " from t=" + this.startTime + " to t=" + this.endTime;
  }

  @Override
  public Color getStartColor() {
    return this.startColor;
  }

  @Override
  public Color getEndColor() {
    return this.endColor;
  }

  @Override
  public Shape updateShape(Shape shapeToUpdate, int t) {
    int rInstance = Animation.tween(startColor.getRed(), endColor.getRed(), startTime, endTime, t);
    int gInstance = Animation.tween(startColor.getGreen(), endColor.getGreen(), startTime, endTime,
        t);
    int bInstance = Animation.tween(startColor.getBlue(), endColor.getBlue(), startTime, endTime,
        t);
    shapeToUpdate.setColor(new Color(rInstance, gInstance, bInstance));
    return shapeToUpdate;
  }

  @Override
  public int getStartXDimension() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "getStartXDimension not supported for this animation type");
  }

  @Override
  public int getEndXDimension() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "getEndXDimension not supported for this animation type");
  }

  @Override
  public int getStartYDimension() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "getStartYDimension not supported for this animation type");
  }

  @Override
  public int getEndYDimension() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "getEndYDimension not supported for this animation type.");
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
