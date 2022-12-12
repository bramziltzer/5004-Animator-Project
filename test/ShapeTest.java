
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;



import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Shape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Color;

/**
 * This is a test class of the implementations of the Shape interface.
 * 
 * @author Bram Ziltzer
 *
 */
public class ShapeTest {
  Shape r;
  Shape o;

  /**
   * Creates a rectangle and oval for testing.
   */
  @Before
  public void setUp() {
    this.r = new Rectangle("r", new Point(100, 0), 5, 5, new Color(5, 5, 5), 0, 8);
    this.o = new Oval("o", new Point(0, 6), 5, 5, new Color(5, 5, 5), 0, 10);
  }

  /**
   * Test of the constructors for a Rectangle and Oval on valid input.
   */
  @Test
  public void testConstructors() {
    Shape r = new Rectangle("r", new Point(100, 0), 5, 5, new Color(5, 5, 5), 0, 8);
    assertEquals("Name: r\n" + "Type: rectangle\n"
        + "Lower Left Corner: (100.0,0.0), Width: 5, Height: 5, Color: (5,5,5)\n"
        + "Appears at t=0\n" + "Disappears at t=8\n", r.toString());

    r = new Rectangle("r2", new Point(-100, 105), 300, 10, new Color(0, 255, 0), 50, 90);
    assertEquals("Name: r2\n" + "Type: rectangle\n"
        + "Lower Left Corner: (-100.0,105.0), Width: 300, Height: 10, Color: (0,255,0)\n"
        + "Appears at t=50\n" + "Disappears at t=90\n", r.toString());

    Shape o = new Oval("o", new Point(0, 6), 5, 5, new Color(5, 5, 5), 0, 10);
    assertEquals("Name: o\n" + "Type: oval\n"
        + "Center: (0.0,6.0), X radius: 5, Y radius: 5, Color: (5,5,5)\n" + "Appears at t=0\n"
        + "Disappears at t=10\n", o.toString());

    o = new Oval("o2", new Point(234, 1000), 14, 500, new Color(255, 100, 255), 100, 300);
    assertEquals("Name: o2\n" + "Type: oval\n"
        + "Center: (234.0,1000.0), X radius: 14, Y radius: 500, Color: (255,100,255)\n"
        + "Appears at t=100\n" + "Disappears at t=300\n", o.toString());
  }

  /**
   * Tests proper exception handling for a Rectangle and Oval on invalid input.
   */
  @Test
  public void testInvalidArguments() {
    // empty name
    try {
      new Rectangle("", new Point(100, 0), 5, 5, new Color(5, 5, 5), 0, 8);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    // bad dimensions
    try {
      new Rectangle("r", new Point(100, 0), 0, 5, new Color(5, 5, 5), 0, 8);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      new Rectangle("", new Point(100, 0), 10, -10, new Color(5, 5, 5), 0, 8);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    // bad appear time
    try {
      new Rectangle("r2", new Point(-100, 105), 300, 10, new Color(0, 255, 0), -10, 90);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    // bad disappear time
    try {
      new Rectangle("r", new Point(100, 0), 10, 10, new Color(5, 5, 5), 10, 8);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }
  }

  /**
   * Test of setting and retrieving the dimensions of a shape.
   */
  @Test
  public void testDimensions() {
    // getters
    assertEquals(5, this.r.getXDimension());
    assertEquals(5, this.r.getYDimension());
    assertEquals(5, this.o.getXDimension());
    assertEquals(5, this.o.getYDimension());

    // setters
    this.r.setXDimension(200);
    this.r.setYDimension(30);
    assertEquals(200, this.r.getXDimension());
    assertEquals(30, this.r.getYDimension());

    this.o.setXDimension(89);
    this.o.setYDimension(1000);
    assertEquals(89, this.o.getXDimension());
    assertEquals(1000, this.o.getYDimension());

    // try setting to bad dimensions
    try {
      this.r.setXDimension(0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      this.o.setXDimension(-10);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      this.r.setYDimension(-50);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      this.o.setYDimension(0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

  }

  /**
   * Test of setting and retrieving the appear and disappear times of a shape.
   */
  @Test
  public void testAppearDisappearTimes() {
    // test getters
    assertEquals(0, this.r.getAppearTime());
    assertEquals(8, this.r.getDisappearTime());

    assertEquals(0, this.o.getAppearTime());
    assertEquals(10, this.o.getDisappearTime());

    // test setters
    this.r.setAppearTime(5);
    assertEquals(5, this.r.getAppearTime());
    this.r.setDisappearTime(30);
    assertEquals(30, this.r.getDisappearTime());

    this.o.setAppearTime(5);
    assertEquals(5, this.o.getAppearTime());
    this.o.setDisappearTime(30);
    assertEquals(30, this.o.getDisappearTime());

    // test bad input for setters

    // appear time after disappear time
    try {
      this.r.setAppearTime(100);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      this.r.setDisappearTime(0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    // negative appear time
    try {
      this.r.setAppearTime(-10);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }
  }

  /**
   * Test of setting and retrieving the location of a shape.
   */
  @Test
  public void testLocation() {
    // test getters
    assertEquals(new Point(100, 0).toString(), this.r.getLocation().toString());
    assertEquals(new Point(0, 6).toString(), this.o.getLocation().toString());

    // test setters
    r.setLocation(new Point(-100, -50));
    assertEquals(new Point(-100, -50).toString(), this.r.getLocation().toString());
    o.setLocation(new Point(-14, 608));
    assertEquals(new Point(-14, 608).toString(), this.o.getLocation().toString());

    // test getLocationString
    assertEquals("(-100.0,-50.0)", r.getLocationString());
    assertEquals("(-14.0,608.0)", o.getLocationString());
  }

  /**
   * Tests setting and retrieving the color of a shape.
   */
  @Test
  public void testColor() {
    // test getters
    assertEquals("(5,5,5)", this.r.getColor().toString());
    assertEquals("(5,5,5)", this.o.getColor().toString());

    // test setters
    this.r.setColor(new Color(255, 0, 0));
    this.o.setColor(new Color(1, 2, 3));

    assertEquals("(255,0,0)", this.r.getColor().toString());
    assertEquals("(1,2,3)", this.o.getColor().toString());

    // test bad color
    try {
      this.r.setColor(new Color(-10, -10, -10));
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }
  }

  /**
   * Test getName method for a shape.
   */
  @Test
  public void testGetName() {
    assertEquals("r", r.getName());
    assertEquals("o", o.getName());

  }

  /**
   * Test of the getShapeType method.
   */
  @Test
  public void testShapeType() {
    assertEquals("rectangle", r.getShapeType());
    assertEquals("oval", o.getShapeType());
  }

  /**
   * Test of the duplicate method, ensuring a proper deep copy is made.
   */
  @Test
  public void testDuplicate() {
    Shape rDuplicate = r.duplicate();
    Shape oDuplicate = o.duplicate();

    assertEquals(r.toString(), rDuplicate.toString());
    assertEquals(o.toString(), oDuplicate.toString());

    assertNotEquals(r, rDuplicate);
    assertNotEquals(o, oDuplicate);

    // make sure mutating the copy doesn't mutate the original
    rDuplicate.setLocation(new Point(1000, 1000));
    assertNotEquals(r.toString(), rDuplicate.toString());

    oDuplicate.setColor(new Color(0, 5, 10));
    assertNotEquals(o.toString(), oDuplicate.toString());

  }

  /**
   * Test of the toString method.
   */
  @Test
  public void testToString() {
    assertEquals("Name: r\n" + "Type: rectangle\n"
        + "Lower Left Corner: (100.0,0.0), Width: 5, Height: 5, Color: (5,5,5)\n"
        + "Appears at t=0\n" + "Disappears at t=8\n", r.toString());

    assertEquals("Name: o\n" + "Type: oval\n"
        + "Center: (0.0,6.0), X radius: 5, Y radius: 5, Color: (5,5,5)\n" + "Appears at t=0\n"
        + "Disappears at t=10\n", o.toString());
  }
}
