package model.command;

import model.PixelRGB;

/**
 * This class is used to blur images.
 */
public class Blur implements ImageFunctionObject {

  /**
   * The default constructor, since it needs no fields.
   */
  public Blur() {
    // there is no code as no fields are needed
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {

    PixelRGB[][] newImage = new PixelRGB[image.length][image[0].length];
    // this for loop scans all the pixels of an image, but it also has different cases for
    // each pixel on the "sides" of the image
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {

        // the logic behind this code is we set the values to be 0 at
        // default if the pixel does not exist (aka if the pixel is out of bounds)
        PixelRGB p1 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p2 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p3 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p4 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p5 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p6 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p7 = new PixelRGB(0, 0, 0, 255);
        PixelRGB p8 = new PixelRGB(0, 0, 0, 255);

        try {
          p1 = image[r - 1][c - 1];

        } catch (IndexOutOfBoundsException e) {
          p1 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p2 = image[r - 1][c];
        } catch (IndexOutOfBoundsException e) {
          p2 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p3 = image[r - 1][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p3 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p4 = image[r][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p4 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p5 = image[r][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p5 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p6 = image[r + 1][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p6 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p7 = image[r + 1][c];
        } catch (IndexOutOfBoundsException e) {
          p7 = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p8 = image[r + 1][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p8 = new PixelRGB(0, 0, 0, 255);
        }

        newImage[r][c] = new PixelRGB(
                (int) (0.0625 * p1.getRed()
                        + 0.125 * p2.getRed() + 0.0625 * p3.getRed()
                        + 0.125 * p4.getRed() + 0.125 * p5.getRed()
                        + 0.0625 * p6.getRed() + 0.125 * p7.getRed()
                        + 0.0625 * p8.getRed() + 0.25 * image[r][c].getRed()),
                (int) (0.0625 * p1.getGreen()
                        + 0.125 * p2.getGreen() + 0.0625 * p3.getGreen()
                        + 0.125 * p4.getGreen() + 0.125 * p5.getGreen()
                        + 0.0625 * p6.getGreen() + 0.125 * p7.getGreen()
                        + 0.0625 * p8.getGreen() + 0.25 * image[r][c].getGreen()),
                (int) (0.0625 * p1.getBlue()
                        + 0.125 * p2.getBlue() + 0.0625 * p3.getBlue()
                        + 0.125 * p4.getBlue() + 0.125 * p5.getBlue()
                        + 0.0625 * p6.getBlue() + 0.125 * p7.getBlue()
                        + 0.0625 * p8.getBlue() + 0.25 * image[r][c].getBlue()),
                image[r][c].getMax());
      }
    }
    return newImage;
  }
}
