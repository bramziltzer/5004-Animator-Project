package cs5004.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * A View is an interface that defines methods for outputting data from an model
 * of animations about the animation's shapes and actions.
 * 
 * @author Bram Ziltzer
 *
 */
public interface View {

  /**
   * Uses the list of shapes, the map of shapes and their animations, and the
   * canvas size to create a string in the format of an SVG file.
   * 
   * @param shapes   the list of shapes in the animation
   * @param shapeMap the map of shape names whose keys are a list of the
   *                 animations associated with those shapes
   * @param canvas   the array containing the location and dimensions of the
   *                 drawing canvas
   * @return a String with the formated SVG text describing the animation
   * @throws UnsupportedOperationException when the view does not support an SVG
   *                                       output
   */
  String getSvgOutputText(LinkedList<Shape> shapes, Map<String, LinkedList<Animation>> shapeMap,
      int[] canvas) throws UnsupportedOperationException;

  /**
   * Uses a list of shape and a list of animations to create a string in a
   * human-readable format.
   * 
   * @param shapes     the list of shapes in the animation
   * @param animations the list of animation steps in the animation
   * @return a human-readable formatted String describing the animation
   * @throws UnsupportedOperationException when the view does not support a text
   *                                       output of the provided format
   */
  String getOutputText(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws UnsupportedOperationException;

  /**
   * Creates a text view of an animation. The text view can be output to the
   * console or to a file.
   * 
   * @param shapes     the list of shapes in this animation
   * @param animations the list of animations steps that occur in the animation
   * @throws UnsupportedOperationException when called in a non-text view
   * @throws IOException                   when there is an error appending the
   *                                       text data to the output
   */
  void runTextView(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws UnsupportedOperationException, IOException;

  /**
   * Outputs the animation in an SVG format. The SVG view can be output to the
   * console or to a file.
   * 
   * @param shapes   the list of shapes in this animation
   * @param shapeMap the map of animations steps that occur in the animation whose
   *                 key is the name of the shape being animated and whose value
   *                 is the list of animations for that shape
   * @throws UnsupportedOperationException when called in a non-SVG view
   * @throws IOException                   when there is an error appending the
   *                                       text data to the output
   */
  void runSVGView(LinkedList<Shape> shapes, Map<String, LinkedList<Animation>> shapeMap,
      int[] canvasSize, int duration) throws UnsupportedOperationException, IOException;

  /**
   * Gets the type of this view.
   * 
   * @return the type of this view
   */
  String getViewType();

  /**
   * Initializes the frame of a GUI for display to the user.
   * 
   * @throws UnsupportedOperationException when called in a non-graphical view
   */
  void initializeFrame() throws UnsupportedOperationException;

  /**
   * Sets the canvas location and dimensions for drawing the animation in a GUI.
   * 
   * @param x      the x coordinate of the drawing canvas
   * @param y      the y coordinate of the drawing canvas
   * @param width  the width of the drawing canvas
   * @param height the height of the drawing canvas
   * @throws UnsupportedOperationException when called in a view that does not
   *                                       support a canvas
   */
  void setViewCanvas(int x, int y, int width, int height) throws UnsupportedOperationException;

  /**
   * Updates the drawing canvas to display the most recent state of the shapes at
   * a moment in time.
   * 
   * @throws UnsupportedOperationException when called in a view that does not
   *                                       support updating a drawing canvas
   */
  void update() throws UnsupportedOperationException;

  /**
   * Adds the given list of shapes to the drawing canvas with updated animation
   * properties at a moment in time.
   * 
   * @param shape the list of shapes whose properties are displayed on the canvas
   * @throws UnsupportedOperationException when called in a view that does not
   *                                       support updating a drawing canvas with
   *                                       a list of shapes
   */
  void addShapesToFrame(ArrayList<Shape> shape) throws UnsupportedOperationException;

  /**
   * Gets the rate at which frames of the animation update in units of frames per
   * second.
   * 
   * @return the rate at which frames of the animation update in frames per second
   * @throws UnsupportedOperationException when called in a view that does not use
   *                                       a speed parameter to control the output
   */
  int getSpeed() throws UnsupportedOperationException;

  /**
   * Gives access to a Controller for a view that uses a Controller to handle real
   * time modification of a displayed animation.
   * 
   * @param controller the Controller that handles the data that the view displays
   * @throws UnsupportedOperationException when called in a view that does not
   *                                       access the Controller to modify its
   *                                       state
   */
  void setController(Controller controller) throws UnsupportedOperationException;

  /**
   * Updates the displayed speed of the animation in real time.
   * 
   * @param newSpeed the new speed to show to the user
   * @throws UnsupportedOperationException on a view whose speed does not get
   *                                       displayed in real time to the user
   */
  void changeSpeedDisplay(int newSpeed) throws UnsupportedOperationException;

}
