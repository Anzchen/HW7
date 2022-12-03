package model;
// may or may not need this class

/**
 * This class represents a single pixel of an image, represented as RGB.
 */
public class PixelRGB implements Pixel {

  protected int red;
  protected int green;
  protected int blue;
  private final int maxValue; // the max value that RGB can get

  /**
   * The constructor which takes in a red, green, and blue value.
   *
   * @param red   The red value from 0-255
   * @param green The green value from 0-255
   * @param blue  The blue value from 0-255
   * @throws IllegalArgumentException If any given value is out of range
   */
  public PixelRGB(int red, int green, int blue, int maxValue) throws IllegalArgumentException {

    if (red < 0 || green < 0 || blue < 0 || red > maxValue || green > maxValue || blue > maxValue) {
      throw new IllegalArgumentException("Colors must be 0-255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public int getMax() {
    return this.maxValue;
  }

  @Override
  public void setRed(int red) {
    if (red > this.maxValue) {
      red = this.maxValue;
    } else if (red < 0) {
      red = 0;
    }
    this.red = red;
  }

  @Override
  public void setGreen(int green) {
    if (green > this.maxValue) {
      green = this.maxValue;
    } else if (green < 0) {
      green = 0;
    }
    this.green = green;
  }

  @Override
  public void setBlue(int blue) {
    if (blue > this.maxValue) {
      blue = this.maxValue;
    } else if (blue < 0) {
      blue = 0;
    }

    this.blue = blue;
  }


}