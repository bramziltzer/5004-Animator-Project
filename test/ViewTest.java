
import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.Color;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.View;

/**
 * JUnit test class of the text and SVG implementations of the View interface.
 * 
 * @author Bram Ziltzer
 *
 */
public class ViewTest {
  AnimationModel m;

  /**
   * Create a model for use in testing the views.
   */
  @Before
  public void setUp() {
    this.m = new AnimationModelImpl();

    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));
    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);
  }

  /**
   * Test of the text view output string.
   */
  @Test
  public void testTextViewOutput() {
    View v = new TextView(System.out);

    assertEquals(
        "Create rectangle R with corner at (0.0,0.0) with color(0,0,0), width 5 and height 5\n"
            + "Create oval o1 with center at (0.0,0.0) with color(0,0,0), x radius 5 and "
            + "y radius 5\n" + "\n" + "R appears at time t=0 and disappears at time t=50\n"
            + "o1 appears at time t=5 and disappears at time t=10\n" + "\n"
            + "R changes color from (255,0,0) to (0,0,0) from t=1 to t=10\n"
            + "R moves from (200.0,200.0) to (200.0,300.0) from t=1 to t=10\n"
            + "o1 changes color from (0,0,0) to (255,0,100) from t=5 to t=10\n"
            + "o1 changes x radius from 2 to 7 from t=5 to t=10\n"
            + "o1 changes y radius from 2 to 7 from t=5 to t=10\n"
            + "o1 moves from (2.0,2.0) to (12.0,12.0) from t=5 to t=10\n"
            + "R changes color from (255,0,0) to (0,0,0) from t=11 to t=14\n"
            + "R moves from (200.0,300.0) to (200.0,200.0) from t=11 to t=14\n",
        v.getOutputText(m.getShapeList(), m.getAnimations()));
  }

  /**
   * Test of the SVG view output string.
   */
  @Test
  public void testSvgViewOutput() {
    // test with different speed
    View v = new SVGView(System.out, 25);
    int[] borders = new int[] { 0, 0, 200, 200 };

    assertEquals("<svg width=\"400\" height=\"300\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n" + "\n" + "<rect>\n"
        + "<animate id=\"base\" begin=\"0;base.end\" dur=\"1000ms\" attributeName=\"visibility\" "
        + "from=\"hide\" to=\"hide\"/>\n" + "</rect>\n" + "\n"
        + "<rect id=\"R\" x=\"0\" y=\"0\" width=\"5\" height=\"5\" fill=\"rgb(0,0,0)\" "
        + "visibility=\"hidden\" >\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+0ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+440ms\" dur=\"120ms\" "
        + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+440ms\" dur=\"120ms\" "
        + "attributeName=\"x\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+440ms\" dur=\"120ms\" "
        + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+40ms\" dur=\"360ms\" "
        + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+40ms\" dur=\"360ms\" "
        + "attributeName=\"x\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+40ms\" dur=\"360ms\" "
        + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\" "
        + "to=\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" "
        + "to=\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"width\" to=\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"height\" to=\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"fill\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"visibility\" to=\"hidden\" fill=\"freeze\" />\n" + "</rect>\n" + "\n"
        + "<ellipse id=\"o1\" cx=\"0\" cy=\"0\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,0)\" "
        + "visibility=\"hidden\" >\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+200ms\" dur=\"1ms\" "
        + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+200ms\" dur=\"200ms\" "
        + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(255,0,100)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+200ms\" dur=\"200ms\""
        + " attributeName=\"rx\" from=\"2\" to=\"7\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+200ms\" dur=\"200ms\""
        + " attributeName=\"ry\" from=\"2\" to=\"7\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+200ms\" dur=\"200ms\" "
        + "attributeName=\"cx\" from=\"2.0\" to=\"12.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+200ms\" dur=\"200ms\" "
        + "attributeName=\"cy\" from=\"2.0\" to=\"12.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" "
        + "to=\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" "
        + "to=\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"rx\" "
        + "to=\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"ry\" "
        + "to=\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" "
        + "to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"visibility\" to=\"hidden\" fill=\"freeze\" />\n" + "</ellipse>\n" + "\n" + "</svg>",
        v.getSvgOutputText(m.getShapeList(), m.getAnimationsByShape(), borders));

    v = new SVGView(System.out, 50);
    assertEquals("<svg width=\"400\" height=\"300\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n" + "\n" + "<rect>\n"
        + "<animate id=\"base\" begin=\"0;base.end\" dur=\"1000ms\" attributeName=\"visibility\""
        + " from=\"hide\" to=\"hide\"/>\n" + "</rect>\n" + "\n"
        + "<rect id=\"R\" x=\"0\" y=\"0\" width=\"5\" height=\"5\" fill=\"rgb(0,0,0)\" visibility="
        + "\"hidden\" >\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+0ms\" dur=\"1ms\" attributeName="
        + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+220ms\" dur=\"60ms\" attributeName="
        + "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+220ms\" dur=\"60ms\" attributeName="
        + "\"x\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+220ms\" dur=\"60ms\" attributeName="
        + "\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+20ms\" dur=\"180ms\" attributeName="
        + "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+20ms\" dur=\"180ms\" attributeName="
        + "\"x\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+20ms\" dur=\"180ms\" attributeName="
        + "\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\" to="
        + "\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" to="
        + "\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"width\" "
        + "to=\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"height\""
        + " to=\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" "
        + "to=\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"visibility\" to=\"hidden\" fill=\"freeze\" />\n" + "</rect>\n" + "\n"
        + "<ellipse id=\"o1\" cx=\"0\" cy=\"0\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,0)\" "
        + "visibility=\"hidden\" >\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+100ms\" dur=\"1ms\" attributeName="
        + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+100ms\" dur=\"100ms\" attributeName="
        + "\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(255,0,100)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+100ms\" dur=\"100ms\" attributeName="
        + "\"rx\" from=\"2\" to=\"7\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+100ms\" dur=\"100ms\" attributeName="
        + "\"ry\" from=\"2\" to=\"7\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+100ms\" dur=\"100ms\" attributeName="
        + "\"cx\" from=\"2.0\" to=\"12.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.begin+100ms\" dur=\"100ms\" attributeName="
        + "\"cy\" from=\"2.0\" to=\"12.0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" to="
        + "\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" to="
        + "\"0\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"rx\" to="
        + "\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"ry\" to="
        + "\"5\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" to="
        + "\"rgb(0,0,0)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"visibility\" to=\"hidden\" fill=\"freeze\" />\n" + "</ellipse>\n" + "\n" + "</svg>",
        v.getSvgOutputText(m.getShapeList(), m.getAnimationsByShape(), borders));
  }

}
