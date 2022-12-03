package model.command;

import model.PixelRGB;

/**
 * This class is used to flip images horizontally.
 */
public class FlipHorizontal implements ImageFunctionObject {

  /**
   * The default constructor, since it needs no fields.
   */
  public FlipHorizontal() {
    // there is no code as no fields are needed
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length / 2; c++) {
        PixelRGB copy = new PixelRGB(image[r][c].getRed(), image[r][c].getGreen(),
                image[r][c].getBlue(), image[r][c].getMax());
        image[r][c] = image[r][image[0].length - 1 - c];
        image[r][image[0].length - 1 - c] = copy;
      }
    }

    return image;
  }
}
