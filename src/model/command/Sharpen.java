package model.command;

import model.PixelRGB;

/**
 * This class is used to sharpen images.
 */
public class Sharpen implements ImageFunctionObject {

  /**
   * The default constructor, since it needs no fields.
   */
  public Sharpen() {
    // there is no code as no fields are needed
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {

    PixelRGB[][] newImage = new PixelRGB[image.length][image[0].length];

    // this for loop scans all the pixels of an image, but it also has different cases for
    // each pixel on the "sides" of the image
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {

        // the logic behind this code is we set the values to be 0 at default if the pixel does not
        // exist (aka if the pixel is out of bounds)
        PixelRGB[] p = new PixelRGB[26];
        for (int i = 0; i < 26; i++) {
          p[i] = new PixelRGB(0, 0, 0, 255);
        }

        try {
          p[1] = image[r - 2][c - 2];
        } catch (IndexOutOfBoundsException e) {
          p[1] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[2] = image[r - 2][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p[2] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[3] = image[r - 2][c];
        } catch (IndexOutOfBoundsException e) {
          p[3] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[4] = image[r - 2][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p[4] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[5] = image[r - 2][c + 2];
        } catch (IndexOutOfBoundsException e) {
          p[5] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[6] = image[r - 1][c - 2];
        } catch (IndexOutOfBoundsException e) {
          p[6] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[7] = image[r - 1][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p[7] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[8] = image[r - 1][c];
        } catch (IndexOutOfBoundsException e) {
          p[8] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[9] = image[r - 1][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p[9] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[10] = image[r - 1][c + 2];
        } catch (IndexOutOfBoundsException e) {
          p[10] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[11] = image[r][c - 2];
        } catch (IndexOutOfBoundsException e) {
          p[11] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[12] = image[r][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p[12] = new PixelRGB(0, 0, 0, 255);
        }

        p[13] = image[r][c];

        try {
          p[14] = image[r][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p[14] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[15] = image[r][c + 2];
        } catch (IndexOutOfBoundsException e) {
          p[15] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[16] = image[r + 1][c - 2];
        } catch (IndexOutOfBoundsException e) {
          p[16] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[17] = image[r + 1][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p[17] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[18] = image[r + 1][c];
        } catch (IndexOutOfBoundsException e) {
          p[18] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[19] = image[r + 1][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p[19] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[20] = image[r + 1][c + 2];
        } catch (IndexOutOfBoundsException e) {
          p[20] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[21] = image[r + 2][c - 2];
        } catch (IndexOutOfBoundsException e) {
          p[21] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[22] = image[r + 2][c - 1];
        } catch (IndexOutOfBoundsException e) {
          p[22] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[23] = image[r + 2][c];
        } catch (IndexOutOfBoundsException e) {
          p[23] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[24] = image[r + 2][c + 1];
        } catch (IndexOutOfBoundsException e) {
          p[24] = new PixelRGB(0, 0, 0, 255);
        }
        try {
          p[25] = image[r + 2][c + 2];
        } catch (IndexOutOfBoundsException e) {
          p[25] = new PixelRGB(0, 0, 0, 255);
        }

        int newRed = (int) (-0.125 * (p[1].getRed() + p[2].getRed() + p[3].getRed()
                + p[4].getRed() + p[5].getRed()
                + p[6].getRed() + p[10].getRed() + p[11].getRed() + p[15].getRed() + p[16].getRed()
                + p[20].getRed() + p[21].getRed()
                + p[22].getRed() + p[23].getRed() + p[24].getRed())
                + 0.25 * (p[7].getRed() + p[8].getRed()
                + p[9].getRed() + p[12].getRed() + p[14].getRed()
                + p[17].getRed() + p[18].getRed() + p[19].getRed())) + image[r][c].getRed();

        if (newRed > image[r][c].getMax()) {
          newRed = image[r][c].getMax();
        } else if (newRed < 0) {
          newRed = 0;
        }

        int newGreen = (int) (-0.125 * (p[1].getGreen() + p[2].getGreen() + p[3].getGreen()
                + p[4].getGreen() + p[5].getGreen()
                + p[6].getGreen() + p[10].getGreen() + p[11].getGreen()
                + p[15].getGreen() + p[16].getGreen()
                + p[20].getGreen() + p[21].getGreen()
                + p[22].getGreen() + p[23].getGreen() + p[24].getGreen())
                + 0.25 * (p[7].getGreen() + p[8].getGreen()
                + p[9].getGreen() + p[12].getGreen() + p[14].getGreen()
                + p[17].getGreen() + p[18].getGreen() + p[19].getGreen()))
                + image[r][c].getGreen();

        if (newGreen > image[r][c].getMax()) {
          newGreen = image[r][c].getMax();
        } else if (newGreen < 0) {
          newGreen = 0;
        }

        int newBlue = (int) (-0.125 * (p[1].getBlue() + p[2].getBlue() + p[3].getBlue()
                + p[4].getBlue() + p[5].getBlue()
                + p[6].getBlue() + p[10].getBlue() + p[11].getBlue()
                + p[15].getBlue() + p[16].getBlue()
                + p[20].getBlue() + p[21].getBlue()
                + p[22].getBlue() + p[23].getBlue() + p[24].getBlue())
                + 0.25 * (p[7].getBlue() + p[8].getBlue() + p[9].getBlue()
                + p[12].getBlue() + p[14].getBlue()
                + p[17].getBlue() + p[18].getBlue() + p[19].getBlue())
                + image[r][c].getBlue());

        if (newBlue > image[r][c].getMax()) {
          newBlue = image[r][c].getMax();
        } else if (newBlue < 0) {
          newBlue = 0;
        }

        newImage[r][c] = new PixelRGB(newRed, newGreen, newBlue, image[r][c].getMax());


      }
    }
    return newImage;
  }
}
