package cs5004.animator.model;

import java.awt.Point;

/**
 * A MoveAnimation represents the change of a shape's location over time.
 * 
 * @author Bram Ziltzer
 *
 */
public class MoveAnimation extends AbstractAnimation {
  private Point startLocation;
  private Point destination;

  /**
   * {@inheritDoc}
   * 
   * @param startLocation the location of the Shape at the start of this animation
   * @param destination   the location of the Shape at the end of the animation
   */
  public MoveAnimation(Shape shape, int startTime, int endTime, Point startLocation,
      Point destination) {

    super(shape, startTime, endTime);
    this.startLocation = startLocation;
    this.destination = destination;

    this.animationType = "location";
  }

  @Override
  public Animation duplicate() {
    return new MoveAnimation(this.shape.duplicate(), this.startTime, this.endTime,
        new Point(this.startLocation.x, this.startLocation.y),
        new Point(this.destination.x, this.destination.y));
  }

  @Override
  public Shape updateShape(Shape shapeToUpdate, int t) {
    int xInstance = Animation.tween(startLocation.x, destination.x, startTime, endTime, t);
    int yInstance = Animation.tween(startLocation.y, destination.y, startTime, endTime, t);
    shapeToUpdate.setLocation(new Point(xInstance, yInstance));
    return shapeToUpdate;
  }

  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " moves from (" + this.startLocation.getX() + ","
        + this.startLocation.getY() + ") to (" + destination.getX() + "," + destination.getY()
        + ") from t=" + this.startTime + " to t=" + this.endTime;
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
  public Point getStartLocation() {
    return this.startLocation;
  }

  @Override
  public Point getEndLocation() {
    return this.destination;
  }
}