package model.command;

import model.PixelRGB;

/**
 * This class is used to darken images.
 */
public class BrightenDarken implements ImageFunctionObject {

  private final int value;

  /**
   * The constructor sets how much we're darkening the image by.
   * @param value How much we're darkening the image by
   * @throws IllegalArgumentException If value is not positive
   */
  public BrightenDarken(int value) {
    this.value = value;
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c ++) {
        image[r][c].setRed(image[r][c].getRed() + this.value);
        image[r][c].setGreen(image[r][c].getGreen() + this.value);
        image[r][c].setBlue(image[r][c].getBlue() + this.value);

        // the following code make sures that brightening caps at the maxValue (usually 255)
        // and also the darkening doesn't go lower than 0
        if (image[r][c].getRed() < 0) {
          image[r][c].setRed(0);
        } else if (image[r][c].getRed() > image[r][c].getMax()) {
          image[r][c].setRed(image[r][c].getMax());
        }
        if (image[r][c].getGreen() < 0) {
          image[r][c].setGreen(0);
        } else if (image[r][c].getGreen() > image[r][c].getMax()) {
          image[r][c].setGreen(image[r][c].getMax());
        }
        if (image[r][c].getBlue() < 0) {
          image[r][c].setBlue(0);
        } else if (image[r][c].getBlue() > image[r][c].getMax()) {
          image[r][c].setBlue(image[r][c].getMax());
        }
      }
    }
    return image;
  }
}
