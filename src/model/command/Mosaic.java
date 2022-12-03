package model.command;

import model.PixelRGB;

/**
 * This class is used to mosaic images.
 */
public class Mosaic implements ImageFunctionObject {

  private final int value;

  /**
   * The constructor sets how many seeds we are using
   * @param value How many seeds to break down the image into
   * @throws IllegalArgumentException If value is not positive
   */
  public Mosaic(int value) {
    this.value = value;
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {
    for (int i = 0; i < value; i++) {
      int height = (int) Math.floor(Math.random() * image.length);
      int width = (int) Math.floor(Math.random() * image[0].length);
//      image[height][width]
    }

    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c ++) {

      }
    }
    return image;
  }

  public int distance() {
    return 0;
  }
}
