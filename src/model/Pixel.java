package model;

/**
 * This interface represents a pixel in an image.
 */
public interface Pixel {

  /**
   * This method gets the red value of the pixel when called on.
   *
   * @return The value of red
   */
  public int getRed();

  /**
   * This method gets the green value of the pixel when called on.
   *
   * @return The value of green
   */
  public int getGreen();

  /**
   * This method gets the blue value of the pixel when called on.
   *
   * @return The value of blue
   */
  public int getBlue();

  /**
   * This method gets the max value of the pixel when called on.
   *
   * @return The value of the color most prominent
   */
  public int getMax();

  /**
   * This method set the red of pixel to give value.
   *
   * @param red the value of red
   */
  public void setRed(int red);

  /**
   * This method set the green of pixel to give value.
   *
   * @param green the value of green
   */
  public void setGreen(int green);

  /**
   * This method set the blue of pixel to give value.
   *
   * @param blue the value of blue
   */
  public void setBlue(int blue);
}
