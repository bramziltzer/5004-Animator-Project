import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cs5004.animator.model.Color;

/**
 * JUnit test class of the Color class.
 * 
 * @author Bram Ziltzer
 *
 */
public class ColorTest {

  /**
   * Test of the Color constructor on valid input. Ensures proper exception
   * handling.
   */
  @Test
  public void testConstructor() {
    Color c = new Color(0, 0, 0);
    assertEquals("(0,0,0)", c.toString());

    c = new Color(255, 255, 255);
    assertEquals("(255,255,255)", c.toString());

    c = new Color(100, 0, 50);
    assertEquals("(100,0,50)", c.toString());

    // test invalid values:
    try {
      c = new Color(-1, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      c = new Color(0, -1, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      c = new Color(0, 0, -1);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      c = new Color(0, 0, 256);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      c = new Color(256, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }

    try {
      c = new Color(0, 256, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // successfully caught exception
    }
  }

  /**
   * Tests of the getter methods.
   */
  @Test
  public void testGetters() {
    Color c = new Color(0, 0, 0);
    assertEquals(0, c.getRed());
    assertEquals(0, c.getGreen());
    assertEquals(0, c.getBlue());

    c = new Color(255, 255, 255);
    assertEquals(255, c.getRed());
    assertEquals(255, c.getGreen());
    assertEquals(255, c.getBlue());

    c = new Color(4, 55, 109);
    assertEquals(4, c.getRed());
    assertEquals(55, c.getGreen());
    assertEquals(109, c.getBlue());
  }

  /**
   * Test of the toString method.
   */
  @Test
  public void testToString() {
    Color c = new Color(0, 0, 0);
    assertEquals("(0,0,0)", c.toString());

    c = new Color(255, 255, 255);
    assertEquals("(255,255,255)", c.toString());

    c = new Color(100, 0, 50);
    assertEquals("(100,0,50)", c.toString());
  }

}
