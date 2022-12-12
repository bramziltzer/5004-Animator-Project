package cs5004.animator.model;

/**
 * An AbstractAnimation is a generic animation for a given shape that has a
 * start and an end time.
 * 
 * @author Bram Ziltzer
 *
 */
public abstract class AbstractAnimation implements Animation {

  protected Shape shape;
  protected int startTime;
  protected int endTime;
  protected String animationType;

  /**
   * Creates an animation.
   * 
   * @param shape     the Shape associated with this animation
   * @param startTime the time this animation starts
   * @param endTime   the time this animation ends
   * @throws IllegalArgumentException when the start time of the animation is
   *                                  before the shape appears and when the
   *                                  animation end time is before its start time
   */
  public AbstractAnimation(Shape shape, int startTime, int endTime)
      throws IllegalArgumentException {
    if (startTime < shape.getAppearTime()) {
      throw new IllegalArgumentException(
          "Animation start time can't be before the shape is created.");
    } else if (endTime < startTime) {
      throw new IllegalArgumentException("Animation end time can't be before the start time.");
    }

    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public Shape getShape() {
    return this.shape;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public String getAnimationType() {
    return this.animationType;
  }

  @Override
  public String getName() {
    return this.shape.getName();
  }

  @Override
  public int compareTo(Animation o) {
    return this.startTime - ((AbstractAnimation) o).startTime;
  }
}
