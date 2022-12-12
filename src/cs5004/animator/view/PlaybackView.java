package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * A VisualView is a view implementation that turns a model of shapes and
 * animations into a Java Swing GUI output. The playback view has interactive
 * buttons that allow a user to pause, play, reset, loop, and change the speed
 * of speed.
 * 
 * @author Bram Ziltzer
 *
 */
public class PlaybackView extends JFrame implements View, ActionListener, ItemListener {
  private static final long serialVersionUID = 1L;
  private Controller controller;
  private String viewType;
  private int canvasX = 0;
  private int canvasY = 0;
  private int canvasWidth = 600;
  private int canvasHeight = 500;
  private int speed;
  private boolean isPaused;

  private AnimationPanel ap;
  private JLabel speedLabel;

  /**
   * Creates an instance of a PlaybackView at the given speed.
   * 
   * @param speed the initial speed of the animation in frames per second
   */
  public PlaybackView(int speed) {
    super("Easy Animator");
    this.speed = speed;
    this.viewType = "playback";
    this.controller = null;
    
    // start the playback paused
    this.isPaused = true;
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
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

    // create panel and buttons
    this.setPreferredSize(new Dimension(canvasWidth, canvasHeight + 50));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ap = new AnimationPanel();
    ap.setPreferredSize(new Dimension(canvasWidth + 150, canvasHeight + 100));
    ap.setLocation(-this.canvasX, -this.canvasY);

    JScrollPane scrollArea = new JScrollPane();
    scrollArea.setViewportView(ap);

    JPanel buttonPanel = new JPanel(true);
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setLayout(new GridLayout());

    JButton startButton = new JButton("Start");
    startButton.setName("start");
    startButton.addActionListener(this);

    JButton pauseButton = new JButton("Pause/Play");
    pauseButton.setName("pause");
    pauseButton.addActionListener(this);

    this.speedLabel = new JLabel("Speed: " + speed + " fps");

    JButton resetButton = new JButton("Reset");
    resetButton.setName("reset");
    resetButton.addActionListener(this);

    JCheckBox loopButton = new JCheckBox("Loop");
    loopButton.setName("loop");
    loopButton.addItemListener(this);

    JButton increaseSpeedButton = new JButton("Speed Up");
    increaseSpeedButton.setName("speedup");
    increaseSpeedButton.addActionListener(this);

    JButton decreaseSpeedButton = new JButton("Slow Down");
    decreaseSpeedButton.setName("slowdown");
    decreaseSpeedButton.addActionListener(this);

    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(resetButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(decreaseSpeedButton);
    buttonPanel.add(speedLabel);
    buttonPanel.add(increaseSpeedButton);

    this.add(scrollArea, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.pack();

    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void changeSpeedDisplay(int newSpeed) {
    this.speedLabel.setText("Speed: " + newSpeed + " fps");
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
  public void itemStateChanged(ItemEvent e) {
    // if loop button is selected
    if (((Component) e.getSource()).getName().equals("loop")) {
      if (e.getStateChange() == 1) {
        this.controller.enableLoop();
      } else {
        this.controller.disableLoop();
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();

    switch (c.getName()) {

      case "start":
        this.controller.startAnimation();
        this.isPaused = false;
        break;
  
      case "pause":
        if (this.isPaused) {
          this.controller.startAnimation();
          this.isPaused = false;
        } else {
          this.controller.pauseAnimation();
          this.isPaused = true;
        }
        break;
  
      case "reset":
        this.controller.restartAnimation();
        break;
  
      case "speedup":
        this.controller.incrementSpeed();
        break;
  
      case "slowdown":
        this.controller.decrementSpeed();
        break;
      default:
        // no default case
    }
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
