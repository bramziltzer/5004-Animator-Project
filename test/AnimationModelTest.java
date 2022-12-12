import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.util.LinkedList;

import org.junit.Test;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.Color;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * JUnit test class of the AnimationModelImpl class.
 * 
 * @author Bram Ziltzer
 *
 */
public class AnimationModelTest {

  /**
   * Tests adding shapes and animations to the model and ensures proper exception
   * handling.
   */
  @Test
  public void testAddShapesAndAnimations() {
    AnimationModel m = new AnimationModelImpl();
    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));
    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));

    // try adding a shape with a name that already exists
    try {
      m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);

    // try adding animation to a nonexistent shape
    try {
      m.addAnimation("test", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    assertEquals("Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + "\n" + "Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + "\n"
        + "Shape R changes color from (255,0,0) to (0,0,0) from t=1 to t=10\n"
        + "Shape R moves from (200.0,200.0) to (200.0,300.0) from t=1 to t=10\n"
        + "Shape o1 changes color from (0,0,0) to (255,0,100) from t=5 to t=10\n"
        + "Shape o1 scales from X Radius: 2, Y Radius: 2 to X Radius: 7, Y Radius: 7 "
        + "from t=5 to t=10\n"
        + "Shape o1 moves from (2.0,2.0) to (12.0,12.0) from t=5 to t=10\n"
        + "Shape R changes color from (255,0,0) to (0,0,0) from t=11 to t=14\n"
        + "Shape R moves from (200.0,300.0) to (200.0,200.0) from t=11 to t=14\n", m.toString());

  }

  /**
   * Tests setting and retrieving the canvas dimensions and locations.
   */
  @Test
  public void testCanvasBounds() {
    AnimationModel m = new AnimationModelImpl();
    m.setCanvasBounds(5, 10, 15, 20);
    int[] arr = new int[] { 5, 10, 15, 20 };
    int[] bounds = m.getCanvasBounds();
    assertArrayEquals(bounds, arr);

    m.setCanvasBounds(-5, -10, -15, -20);
    arr = new int[] { -5, -10, -15, -20 };
    bounds = m.getCanvasBounds();

    assertArrayEquals(bounds, arr);
  }

  /**
   * Tests the getAnimations method.
   */
  @Test
  public void testGetAnimations() {
    AnimationModel m = new AnimationModelImpl();
    assertEquals("[]", m.getAnimations().toString());

    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));
    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);

    assertEquals(
        "[Shape R changes color from (255,0,0) to (0,0,0) from t=1 to t=10, Shape R moves from "
            + "(200.0,200.0) to (200.0,300.0) from t=1 to t=10, Shape o1 changes color "
            + "from (0,0,0) to (255,0,100) from t=5 to t=10, Shape o1 scales from X Radius:"
            + " 2, Y Radius: 2 to X Radius: 7, Y Radius: 7 from t=5 to t=10, Shape o1 moves from"
            + " (2.0,2.0) to (12.0,12.0) from t=5 to t=10, Shape R changes color from (255,0,0) "
            + "to (0,0,0) from t=11 to t=14, Shape R moves from (200.0,300.0) to (200.0,200.0) "
            + "from t=11 to t=14]",
        m.getAnimations().toString());
  }

  /**
   * Tests the methods getShapeList and getshapesByOrderAdded. Checks that
   * mutating a shape doesn't change the deep copy.
   */
  @Test
  public void testGetShapeList() {
    AnimationModel m = new AnimationModelImpl();

    assertEquals("[]", m.getShapeList().toString());
    assertEquals("[]", m.getShapesByOrderAdded().toString());

    Shape r = new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50);
    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));
    m.addShape(r);

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);

    LinkedList<Shape> s1 = m.getShapeList();
    // test getShapeList
    assertEquals("[Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + ", Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + "]", s1.toString());

    LinkedList<Shape> s2 = m.getShapesByOrderAdded();
    // test getShapesByOrderAdded method
    assertEquals("[Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + ", Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + "]", s2.toString());

    // check that deep copies don't get mutated when changing the original
    r.setLocation(new Point(1000, 1000));
    r.setDisappearTime(10000);

    assertEquals("[Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + ", Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + "]", s1.toString());

    assertEquals("[Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + ", Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + "]", s2.toString());
  }

  /**
   * Test of the getDuration method.
   */
  @Test
  public void testGetDuration() {
    AnimationModel m = new AnimationModelImpl();
    assertEquals(0, m.getDuration());

    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));
    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    assertEquals(10, m.getDuration());

    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);

    assertEquals(14, m.getDuration());

  }

  /**
   * Test of the getAnimationsByShape method.
   */
  @Test
  public void testGetAnimationsByShape() {
    AnimationModel m = new AnimationModelImpl();
    assertEquals("{}", m.getAnimationsByShape().toString());

    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));
    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));
    assertEquals("{o1=[], R=[]}", m.getAnimationsByShape().toString());

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);

    assertEquals(
        "{o1=[Shape o1 changes color from (0,0,0) to (255,0,100) from t=5 to t=10, Shape o1 "
            + "scales from X Radius: 2, Y Radius: 2 to X Radius: 7, Y Radius: 7 from t=5 to t=10"
            + ", Shape o1 moves from (2.0,2.0) to (12.0,12.0) from t=5 to t=10], R=[Shape R "
            + "changes color from (255,0,0) to (0,0,0) from t=11 to t=14, Shape R moves from "
            + "(200.0,300.0) to (200.0,200.0) from t=11 to t=14, Shape R changes color from "
            + "(255,0,0) to (0,0,0) from t=1 to t=10, Shape R moves from (200.0,200.0) to "
            + "(200.0,300.0) from t=1 to t=10]}",
        m.getAnimationsByShape().toString());

  }

  /**
   * Test of the model's toString method.
   */
  @Test
  public void testToString() {
    AnimationModel m = new AnimationModelImpl();
    assertEquals("", m.toString());

    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));
    assertEquals("Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n\n", m.toString());

    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));

    assertEquals("Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + "\n" + "Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n\n", m.toString());

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    assertEquals("Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + "\n" + "Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + "\n"
        + "Shape R changes color from (255,0,0) to (0,0,0) from t=1 to t=10\n"
        + "Shape R moves from (200.0,200.0) to (200.0,300.0) from t=1 to t=10\n", m.toString());

    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);
    assertEquals("Name: R\n" + "Type: rectangle\n"
        + "Lower Left Corner: (0.0,0.0), Width: 5, Height: 5, Color: (0,0,0)\n" + "Appears at t=0\n"
        + "Disappears at t=50\n" + "\n" + "Name: o1\n" + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 5, Y radius: 5, Color: (0,0,0)\n" + "Appears at t=5\n"
        + "Disappears at t=10\n" + "\n"
        + "Shape R changes color from (255,0,0) to (0,0,0) from t=1 to t=10\n"
        + "Shape R moves from (200.0,200.0) to (200.0,300.0) from t=1 to t=10\n"
        + "Shape o1 changes color from (0,0,0) to (255,0,100) from t=5 to t=10\n"
        + "Shape o1 scales from X Radius: 2, Y Radius: 2 to X Radius: 7, Y Radius: 7 "
        + "from t=5 to t=10\n"
        + "Shape o1 moves from (2.0,2.0) to (12.0,12.0) from t=5 to t=10\n"
        + "Shape R changes color from (255,0,0) to (0,0,0) from t=11 to t=14\n"
        + "Shape R moves from (200.0,300.0) to (200.0,200.0) from t=11 to t=14\n", m.toString());
  }
}
