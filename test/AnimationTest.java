import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Animation;
import cs5004.animator.model.Color;
import cs5004.animator.model.ColorAnimation;
import cs5004.animator.model.MoveAnimation;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ScaleAnimation;
import cs5004.animator.model.Shape;

/**
 * JUnit test class of the implementations of the Animation interface.
 * 
 * @author Bram Ziltzer
 *
 */
public class AnimationTest {
  Shape r;
  Shape o;
  Animation scale;
  Animation move;
  Animation color;

  /**
   * Creates Shape and Animation instances for testing.
   */
  @Before
  public void setUp() {
    this.r = new Rectangle("r", new Point(100, 0), 5, 10, new Color(5, 5, 5), 10, 100);
    this.o = new Oval("o", new Point(0, 6), 5, 5, new Color(5, 5, 5), 10, 100);

    this.move = new MoveAnimation(this.r, 50, 60, r.getLocation(), new Point(50, 50));
    this.scale = new ScaleAnimation(this.r, 20, 31, r.getXDimension(), r.getYDimension(), 200, 200);
    this.color = new ColorAnimation(this.o, 40, 80, o.getColor(), new Color(255, 255, 255));
  }

  /**
   * Test of the MoveAnimation, ScaleAnimation, and ColorAnimation constructors on
   * valid input.
   */
  @Test
  public void testConstructors() {
    assertEquals("Shape r moves from (100.0,0.0) to (50.0,50.0) from t=50 to t=60",
        this.move.toString());

    assertEquals(
        "Shape r scales from Width: 5, Height: 10 to Width: 200, Height: 200 from t=20 to t=31",
        this.scale.toString());

    assertEquals("Shape o changes color from (5,5,5) to (255,255,255) from t=40 to t=80",
        this.color.toString());
  }

  /**
   * Test of the MoveAnimation, ScaleAnimation, and ColorAnimation constructors on
   * invalid input.
   */
  @Test
  public void testBadConstructors() {
    // bad time
    try {
      new MoveAnimation(this.r, 70, 60, r.getLocation(), new Point(50, 50));
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      new MoveAnimation(this.r, -10, 60, r.getLocation(), new Point(50, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    // bad color
    try {
      new ColorAnimation(this.r, 10, 60, r.getColor(), new Color(256, 0, 0));
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    // bad dimensions
    try {
      new ScaleAnimation(this.o, 10, 60, o.getXDimension(), o.getYDimension(), -10, 50);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      new ScaleAnimation(this.o, 10, 60, o.getXDimension(), o.getYDimension(), 10, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }
  }

  /**
   * Test of the getShape class.
   */
  @Test
  public void testGetShape() {
    assertEquals(this.r, this.move.getShape());
    assertEquals(this.r, this.scale.getShape());
    assertEquals(this.o, this.color.getShape());
  }

  /**
   * Test of the getters for the start and end color.
   */
  @Test
  public void testGetColors() {
    // start color
    assertEquals("(5,5,5)", this.color.getStartColor().toString());
    // end color
    assertEquals("(255,255,255)", this.color.getEndColor().toString());
  }

  /**
   * Test of the getters for the start and end dimensions.
   */
  @Test
  public void testGetDimensions() {
    // start dimensions
    assertEquals(5, this.scale.getStartXDimension());
    assertEquals(10, this.scale.getStartYDimension());

    // end dimensions
    assertEquals(200, this.scale.getEndXDimension());
    assertEquals(200, this.scale.getEndYDimension());
  }

  /**
   * Test of the getters for the start and end locations.
   */
  @Test
  public void testGetLocation() {

    // start location
    Point start = new Point(100, 0);
    assertEquals(start.toString(), this.move.getStartLocation().toString());

    // end location
    Point end = new Point(50, 50);
    assertEquals(end.toString(), this.move.getEndLocation().toString());
  }

  /**
   * Check exceptions for unsupported operations.
   */
  @Test
  public void testUnsupportedGetters() {
    // color animation
    try {
      this.color.getStartLocation();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.color.getEndLocation();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.color.getStartXDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.color.getEndXDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.color.getStartYDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.color.getEndYDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    // scale animation
    try {
      this.scale.getStartLocation();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.scale.getEndLocation();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.scale.getStartColor();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.scale.getEndColor();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    // move animation
    try {
      this.move.getStartColor();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.move.getEndColor();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.move.getStartXDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.move.getEndXDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.move.getStartYDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }

    try {
      this.move.getEndYDimension();
      fail();
    } catch (UnsupportedOperationException e) {
      // successfully caught exception
    }
  }

  /**
   * Test of the getters for the start and end times.
   */
  @Test
  public void testGetTime() {
    assertEquals(40, this.color.getStartTime());
    assertEquals(80, this.color.getEndTime());

    assertEquals(20, this.scale.getStartTime());
    assertEquals(31, this.scale.getEndTime());

    assertEquals(50, this.move.getStartTime());
    assertEquals(60, this.move.getEndTime());
  }

  /**
   * Test of the duplicate method, ensuring a deep copy is made.
   */
  @Test
  public void testDuplicate() {
    Animation scaleDuplicate = this.scale.duplicate();
    assertNotEquals(this.scale, scaleDuplicate);

    Animation moveDuplicate = this.move.duplicate();
    assertNotEquals(this.move, moveDuplicate);

    Animation colorDuplicate = this.color.duplicate();
    assertNotEquals(this.color, colorDuplicate);

  }

  /**
   * Test of the getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("o", this.color.getName());
    assertEquals("r", this.scale.getName());
    assertEquals("r", this.move.getName());
  }

  /**
   * Test of the getanimationType method.
   */
  @Test
  public void testGetAnimationType() {
    assertEquals("color", this.color.getAnimationType());
    assertEquals("scale", this.scale.getAnimationType());
    assertEquals("location", this.move.getAnimationType());
  }

  /**
   * Test of updateShape method, which mutates the given shape to get a snapshot
   * of its properties at a given time.
   */
  @Test
  public void testUpdateShape() {
    assertEquals("(5,5,5)", this.o.getColor().toString());
    this.color.updateShape(this.o, 80);
    assertEquals("(255,255,255)", this.o.getColor().toString());

    assertEquals(5, this.r.getXDimension());
    assertEquals(10, this.r.getYDimension());
    this.scale.updateShape(this.r, 25);
    assertEquals(92, this.r.getXDimension());
    assertEquals(95, this.r.getYDimension());

    assertEquals(100, this.r.getLocation().x);
    assertEquals(0, this.r.getLocation().y);
    this.move.updateShape(this.r, 58);
    assertEquals(60, this.r.getLocation().x);
    assertEquals(40, this.r.getLocation().y);

  }

  /**
   * Test of the static tween method.
   */
  @Test
  public void testTween() {
    for (int i = 0; i <= 50; i++) {
      assertEquals((2 * i), Animation.tween(0, 100, 0, 50, i));

    }
  }

  /**
   * Test of the toString method.
   */
  @Test
  public void testToString() {
    assertEquals("Shape r moves from (100.0,0.0) to (50.0,50.0) from t=50 to t=60",
        this.move.toString());

    assertEquals(
        "Shape r scales from Width: 5, Height: 10 to Width: 200, Height: 200 from t=20 to t=31",
        this.scale.toString());

    assertEquals("Shape o changes color from (5,5,5) to (255,255,255) from t=40 to t=80",
        this.color.toString());
  }
}
