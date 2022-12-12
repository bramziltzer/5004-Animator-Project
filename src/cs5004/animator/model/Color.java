package cs5004.animator.model;

/**
 * A Color represents a color that is the combination of three colors: red,
 * green, and blue.
 * 
 * @author Bram Ziltzer
 *
 */
public class Color {
  private int red;
  private int green;
  private int blue;

  /**
   * Creates an instance of a Color.
   * 
   * @param red   the value of the red component of the color between 0 and 255,
   *              inclusive
   * @param green the value of the green component of the color between 0 and 255,
   *              inclusive
   * @param blue  the value of the blue component of the color between 0 and 255,
   *              inclusive
   * @throws IllegalArgumentException when the value of red, green, or blue are
   *                                  outside the range of 0 to 255, inclusive
   */
  public Color(int red, int green, int blue) throws IllegalArgumentException {
    if ((0 <= red && red < 256) && (0 <= green && green < 256) && (0 <= blue && blue < 256)) {
      this.red = red;
      this.green = green;
      this.blue = blue;
    } else {
      throw new IllegalArgumentException(
          "Red, green, and blue values must be between 0 and 255, inclusive.");
    }
  }

  /**
   * Gets the value of the red component of the color.
   * 
   * @return the value of the red component of the color.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the value of the green component of the color.
   * 
   * @return the value of the green component of the color.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the value of the blue component of the color.
   * 
   * @return the value of the blue component of the color.
   */
  public int getBlue() {
    return this.blue;
  }

  @Override
  public String toString() {
    return "(" + this.red + "," + this.green + "," + this.blue + ")";
  }
}
