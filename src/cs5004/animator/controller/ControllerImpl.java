package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.Timer;

import cs5004.animator.model.Animation;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.Shape;
import cs5004.animator.view.View;

/**
 * The ControllerImpl provides methods for retrieving animation data and giving
 * it to a view to output. It makes use of the Java Swing Timer class to run
 * live animations.
 * 
 * @author Bram Ziltzer
 *
 */
public class ControllerImpl implements Controller {
  private AnimationModel model;
  private View view;
  private Timer timer;
  private int speed = 1;
  private ActionListener animator;
  private LinkedList<Shape> shapeList = null;
  private boolean shouldReset = false;
  private boolean shouldLoop = false;
  private int delay;

  /**
   * Creates an instance of a controller with a given model and view.
   * 
   * @param model the model that controls and holds the main data on animations
   *              and shapes.
   * @param view  the view that is used to output data and, if applicable,
   *              interact with the user
   */
  public ControllerImpl(AnimationModel model, View view) {
    this.model = model;
    this.view = view;
    this.animator = null;
  }

  @Override
  public void run() {
    switch (view.getViewType()) {
      case "text":
        try {
          view.runTextView(model.getShapeList(), model.getAnimations());
        } catch (UnsupportedOperationException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
  
      case "svg":
        try {
          view.runSVGView(model.getShapesByOrderAdded(), model.getAnimationsByShape(),
              model.getCanvasBounds(), model.getDuration());
        } catch (UnsupportedOperationException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
  
      case "visual":
        this.speed = view.getSpeed();
        try {
          this.runVisualView();
        } catch (UnsupportedOperationException e) {
          e.printStackTrace();
        }
        break;
  
      case "playback":
        this.speed = view.getSpeed();
        this.delay = 1000 / speed;
        try {
          this.initializePlayback();
        } catch (UnsupportedOperationException e) {
          e.printStackTrace();
        }
        break;
      default:
        // no default case
    }
  }

  @Override
  public void startAnimation() {
    timer.start();
  }

  @Override
  public void restartAnimation() {
    this.shouldReset = true;
  }

  @Override
  public void pauseAnimation() {
    timer.stop();
  }

  @Override
  public void enableLoop() {
    this.shouldLoop = true;
  }

  @Override
  public void disableLoop() {
    this.shouldLoop = false;
  }

  @Override
  public void incrementSpeed() {
    if (this.speed < 5) {
      this.speed = 5;
    } else {
      this.speed += 5;
    }
    this.timer.setDelay(1000 / this.speed);
    this.view.changeSpeedDisplay(this.speed);
  }

  @Override
  public void decrementSpeed() {
    if (this.speed <= 5) {
      this.speed = 1;
    } else {
      this.speed -= 5;
    }
    this.timer.setDelay(1000 / this.speed);
    this.view.changeSpeedDisplay(this.speed);
  }

  /*
   * Computes and returns a list of Shapes and their properties at a snapshot in
   * time in the animation.
   * 
   * @param t         the moment in time to get the list of Shapes
   * @param shapeList the list of Shapes with their properties set to the given
   *                  moment in time
   * @return a list of Shapes and their properties at a snapshot in time in the
   *         animation.
   */
  private ArrayList<Shape> getShapeAtTick(int t, LinkedList<Shape> shapeList) {
    ArrayList<Shape> activeShapes = new ArrayList<Shape>();
    Map<String, LinkedList<Animation>> animationsByShape = model.getAnimationsByShape();

    for (Shape shape : shapeList) {
      if (shape.getAppearTime() <= t && t <= shape.getDisappearTime()) {
        for (Animation animation : animationsByShape.get(shape.getName())) {
          if (animation.getStartTime() <= t && t <= animation.getEndTime()) {
            shape = animation.updateShape(shape, t);
          }
        }
        activeShapes.add(shape);
      }
    }
    return activeShapes;
  }

  /*
   * Runs the visual view using a Java Swing timer to control the rate that
   * animations update on the screen. Stops the view on the last frame so the
   * images don't disappear from the screen.
   */
  private void runVisualView() {
    this.initializeFrame();
    this.resetShapeList();

    this.timer = new Timer(1000 / speed, new ActionListener() {
      int frame = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        view.addShapesToFrame(getShapeAtTick(frame, shapeList));
        view.update();
        frame++;
        // pause on last frame
        if (frame >= model.getDuration() - 1) {
          timer.stop();
        }
      }
    });
    timer.start();
  }

  /*
   * Initializes the GUI window with information about the drawing canvas size.
   */
  private void initializeFrame() {
    int[] bounds = this.model.getCanvasBounds();
    this.view.setViewCanvas(bounds[0], bounds[1], bounds[2], bounds[3]);
    this.view.initializeFrame();
  }

  /*
   * Initializes the playback view and creates the Swing Timer that controls the
   * animation's drawing updates.
   */
  private void initializePlayback() {
    view.setController(this);
    this.resetShapeList();
    this.initializeFrame();

    this.animator = new ActionListener() {
      int frame = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        view.update();
        view.addShapesToFrame(getShapeAtTick(frame, shapeList));
        frame++;
        if (shouldReset) {
          if (!shouldLoop) {
            pauseAnimation();
          }
          frame = 0;
          resetShapeList();
          shouldReset = false;
        }
        if (shouldLoop && frame >= model.getDuration()) {
          frame = 1;
          resetShapeList();
        }
      }
    };
    this.timer = new Timer(delay, this.animator);

  }

  /*
   * Resets the list of shapes to their initial values before they were mutated by
   * the running animation.
   */
  private void resetShapeList() {
    this.shapeList = model.getShapesByOrderAdded();
  }
}
