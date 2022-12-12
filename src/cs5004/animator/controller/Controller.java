package cs5004.animator.controller;

/**
 * The Controller interface defines methods for running an animation by
 * retrieving necessary data from the model and giving relevant data to the view
 * to output to the user. This interface has methods available for running a
 * live animation.
 * 
 * @author Bram Ziltzer
 *
 */
public interface Controller {

  /**
   * This method executes the view that is passed to the controller.
   */
  void run();

  /**
   * Starts a visual animation by starting a clock that updates drawings on the
   * screen.
   */
  void startAnimation();

  /**
   * Restarts an active animation to its first frame.
   */
  void restartAnimation();

  /**
   * Stops an animation by stopping the clock that controls updates to the
   * drawings on the screen.
   */
  void pauseAnimation();

  /**
   * Enables the animation to continuously loop back to its first frame once it
   * reaches its last frame.
   */
  void enableLoop();

  /**
   * Disables the animation from looping once it reaches its last frame.
   */
  void disableLoop();

  /**
   * Increases the speed of animation by a fixed amount.
   */
  void incrementSpeed();

  /**
   * Decreases the speed of animiation by a fixed amount.
   */
  void decrementSpeed();
}
