package model.command;

import model.PixelRGB;

/**
 * This class is used to flip images vertically.
 */
public class FlipVertical implements ImageFunctionObject {
  /**
   * The default constructor, since it needs no fields.
   */
  public FlipVertical() {
    // there is no code as no fields are needed
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {
    for (int r = 0; r < image.length / 2; r++) {
      for (int c = 0; c < image[0].length; c ++) {
        PixelRGB copy = new PixelRGB(image[r][c].getRed(), image[r][c].getGreen(),
                image[r][c].getBlue(), image[r][c].getMax());
        image[r][c] = image[image.length - 1 - r][c];
        image[image.length - 1 - r][c] = copy;
      }
    }

    return image;
  }
}
