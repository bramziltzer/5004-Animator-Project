package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * A VisualView is a view implementation that turns a model of shapes and
 * animations into a Java Swing GUI output.
 * 
 * @author Bram Ziltzer
 *
 */
public class VisualView extends JFrame implements View {
  private static final long serialVersionUID = 1L;
  private String viewType;
  private int canvasX = 0;
  private int canvasY = 0;
  private int canvasWidth = 600;
  private int canvasHeight = 500;
  private int speed;

  private AnimationPanel ap;

  /**
   * Creates an instance of a VisualView at the given speed.
   * 
   * @param speed the speed of the animation in frames per second
   */
  public VisualView(int speed) {
    super("Easy Animator");
    this.speed = speed;
    this.viewType = "visual";
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public String getViewType() {
    return this.viewType;
  }

  @Override
  public void initializeFrame() {

    this.setPreferredSize(new Dimension(canvasWidth, canvasHeight + 50));
    this.setSize(getPreferredSize());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ap = new AnimationPanel();
    ap.setPreferredSize(new Dimension(canvasWidth + 150, canvasHeight + 100));
    ap.setLocation(-this.canvasX, -this.canvasY);

    JScrollPane scrollArea = new JScrollPane();
    scrollArea.setViewportView(ap);

    this.add(scrollArea, BorderLayout.CENTER);
    this.pack();

    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void setViewCanvas(int x, int y, int width, int height) {
    this.canvasX = x;
    this.canvasY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }

  @Override
  public void addShapesToFrame(ArrayList<Shape> shapes) {
    ap.setShapes(shapes);
  }

  @Override
  public void update() {
    ap.repaint();
  }

  @Override
  public void runTextView(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Method runTextView is unsupported for this view.");
  }

  @Override
  public void runSVGView(LinkedList<Shape> shapes, Map<String, LinkedList<Animation>> shapeMap,
      int[] canvasSize, int duration) throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Method runSVGView is unsupported for this view.");
  }

  @Override
  public void setController(Controller controller) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method setController is unsupported for this view.");
  }

  @Override
  public void changeSpeedDisplay(int newSpeed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method changeSpeedDisplay is unsupported for this view.");
  }

  @Override
  public String getSvgOutputText(LinkedList<Shape> shapes,
      Map<String, LinkedList<Animation>> shapeMap, int[] canvas)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method getSvgOutputText is unsupported for this view.");
  }

  @Override
  public String getOutputText(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method getOutputText is unsupported for this view.");
  }
}
