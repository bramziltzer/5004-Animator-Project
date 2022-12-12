package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.Animation;
import cs5004.animator.model.Shape;

/**
 * The SVGView is a view implementation that turns a model of shapes and
 * animations into an standard SVG file format.
 * 
 * @author Bram Ziltzer
 *
 */
public class SVGView implements View {

  private Appendable output;
  private int duration = 1000;
  private int speed = 10;
  private String viewType;

  /**
   * Creates an instance of an SVGView.
   * 
   * @param output the object at which to output the data
   * @param speed  the speed of the animation in frames per second
   */
  public SVGView(Appendable output, int speed) {
    this.viewType = "svg";
    this.output = output;
    this.speed = speed;
  }

  @Override
  public void runSVGView(LinkedList<Shape> shapes, Map<String, LinkedList<Animation>> shapeMap,
      int[] canvas, int duration) throws UnsupportedOperationException, IOException {
    this.duration = duration * (1000 / this.speed);
    output.append(getSvgOutputText(shapes, shapeMap, canvas));
    if (output instanceof FileWriter) {
      ((FileWriter) output).close();
    }
  }

  @Override
  public String getSvgOutputText(LinkedList<Shape> shapes,
      Map<String, LinkedList<Animation>> shapeMap, int[] canvas) {
    String outputString = "";
    int canvasWidth = canvas[2] + 200;
    int canvasHeight = canvas[3] + 100;

    outputString += "<svg width=\"" + canvasWidth + "\" height=\"" + canvasHeight
        + "\" version=\"1.1\"\n" + "xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    // set hidden faux rectangle for loopback, keeps reference time
    outputString += "<rect>\n" + "<animate id=\"base\" begin=\"0;base.end\" dur=\"" + (duration)
        + "ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" + "</rect>\n\n";

    for (Shape shape : shapes) {
      String shapeType = "";
      String xPointName = "";
      String yPointName = "";
      String xDimensionName = "";
      String yDimensionName = "";

      if (shape.getShapeType().equals("rectangle")) {
        shapeType = "rect";
        xPointName = "x";
        yPointName = "y";
        xDimensionName = "width";
        yDimensionName = "height";
      } else if (shape.getShapeType().equals("oval")) {
        shapeType = "ellipse";
        xPointName = "cx";
        yPointName = "cy";
        xDimensionName = "rx";
        yDimensionName = "ry";
      }

      outputString += "<" + shapeType + " id=\"" + shape.getName() + "\" " + xPointName + "=\""
          + (int) shape.getLocation().getX() + "\" " + yPointName + "=\""
          + (int) shape.getLocation().getY() + "\" " + xDimensionName + "=\""
          + (int) shape.getXDimension() + "\" " + yDimensionName + "=\""
          + (int) shape.getYDimension() + "\" fill=\"rgb" + shape.getColor().toString()
          + "\" visibility=\"hidden\" >\n";
      outputString += "<animate attributeType=\"xml\" begin=\"base.begin+"
          + (shape.getAppearTime() * 1000 / speed)
          + "ms\" dur=\"1ms\" attributeName=\"visibility\" from=\"hidden\" "
          + "to=\"visible\" fill=\"freeze\" />\n";

      for (Animation animation : shapeMap.get(shape.getName())) {
        int animationStart = animation.getStartTime() * 1000 / this.speed;
        int animationDuration = (animation.getEndTime() - animation.getStartTime()) * 1000
            / this.speed;

        switch (animation.getAnimationType()) {
          case "location":
            outputString += "<animate attributeType=\"xml\" begin=\"base.begin+" + animationStart
                + "ms\" dur=\"" + animationDuration + "ms\" attributeName=\"" + xPointName
                + "\" from=\"" + animation.getStartLocation().getX() + "\" to=\""
                + animation.getEndLocation().getX() + "\" fill=\"freeze\" />\n";
            outputString += "<animate attributeType=\"xml\" begin=\"base.begin+" + animationStart
                + "ms\" dur=\"" + animationDuration + "ms\" attributeName=\"" + yPointName
                + "\" from=\"" + animation.getStartLocation().getY() + "\" to=\""
                + animation.getEndLocation().getY() + "\" fill=\"freeze\" />\n";
            break;
          case "scale":
            outputString += "<animate attributeType=\"xml\" begin=\"base.begin+" + animationStart
                + "ms\" dur=\"" + animationDuration + "ms\" attributeName=\"" + xDimensionName
                + "\" from=\"" + animation.getStartXDimension() + "\" to=\""
                + animation.getEndXDimension() + "\" fill=\"freeze\" />\n";
            outputString += "<animate attributeType=\"xml\" begin=\"base.begin+" + animationStart
                + "ms\" dur=\"" + animationDuration + "ms\" attributeName=\"" + yDimensionName
                + "\" from=\"" + animation.getStartYDimension() + "\" to=\""
                + animation.getEndYDimension() + "\" fill=\"freeze\" />\n";
            break;
          case "color":
            outputString += "<animate attributeType=\"xml\" begin=\"base.begin+" + animationStart
                + "ms\" dur=\"" + animationDuration + "ms\" attributeName=\"fill\" from=\"rgb"
                + animation.getStartColor().toString() + "\" to=\"rgb"
                + animation.getEndColor().toString() + "\" fill=\"freeze\" />\n";
            break;
          default:
            // no default case
        }
      }

      // reset shape in order to loop
      outputString += "<animate attributeType=\"xml\" begin=\"base.end\" "
          + "dur=\"1ms\" attributeName=\""
          + xPointName + "\" to=\"" + (int) shape.getLocation().getX() + "\" fill=\"freeze\" />\n";
      outputString += "<animate attributeType=\"xml\" begin=\"base.end\" "
          + "dur=\"1ms\" attributeName=\""
          + yPointName + "\" to=\"" + (int) shape.getLocation().getY() + "\" fill=\"freeze\" />\n";
      outputString += "<animate attributeType=\"xml\" begin=\"base.end\" "
          + "dur=\"1ms\" attributeName=\""
          + xDimensionName + "\" to=\"" + (int) shape.getXDimension() + "\" fill=\"freeze\" />\n";
      outputString += "<animate attributeType=\"xml\" begin=\"base.end\" "
          + "dur=\"1ms\" attributeName=\""
          + yDimensionName + "\" to=\"" + (int) shape.getYDimension() + "\" fill=\"freeze\" />\n";
      outputString += "<animate attributeType=\"xml\" begin=\"base.end\" "
          + "dur=\"1ms\" attributeName=\"fill\" to=\"rgb"
          + shape.getColor().toString() + "\" fill=\"freeze\" />\n";
      outputString += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
          + "\"visibility\" to=\"hidden\" fill=\"freeze\" />\n";

      outputString += "</" + shapeType + ">\n\n";
    }

    outputString += "</svg>";
    return outputString;
  }

  @Override
  public String getViewType() {
    return this.viewType;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void runTextView(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Cannot run text view using SVG view.");
  }

  @Override
  public void initializeFrame() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot run text view using SVG view.");

  }

  @Override
  public void setViewCanvas(int x, int y, int width, int height)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method setViewCanvas is unsupported for this view.");

  }

  @Override
  public void update() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method update is unsupported for this view.");

  }

  @Override
  public void addShapesToFrame(ArrayList<Shape> shape) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method addShapesToFrame is unsupported for this view.");

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
  public String getOutputText(LinkedList<Shape> shapes, LinkedList<Animation> animations)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Method getOutputText is unsupported for SVG view.");
  }

}
