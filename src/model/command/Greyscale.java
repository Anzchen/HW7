package model.command;

import model.ImageModel;
import model.PixelRGB;

/**
 * This class is used to greyscale images based on red, green, blue, value, intensity, or luma.
 */
public class Greyscale implements ImageFunctionObject {

  private final ImageModel.RGBVIL rgbvil;

  /**
   * This constructor decides what kind of greyscale we're doing.
   *
   * @param rgbvil either red green blue value intensity or luma to greyscale
   */
  public Greyscale(ImageModel.RGBVIL rgbvil) {
    this.rgbvil = rgbvil;
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) throws IllegalArgumentException {
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        // determines the greyscale color
        int newColor = setPixel(image[r][c]);

        image[r][c].setRed(newColor);
        image[r][c].setGreen(newColor);
        image[r][c].setBlue(newColor);
      }
    }

    return image;
  }

  // this helper method determines what value to set the pixel's RGB as based on this.rgbvil
  private int setPixel(PixelRGB pixel) {
    int greyValue = 0;
    switch (this.rgbvil) {
      case Red:
        greyValue = pixel.getRed();
        break;
      case Green:
        greyValue = pixel.getGreen();
        break;
      case Blue:
        greyValue = pixel.getBlue();
        break;
      case Value:
        if (pixel.getRed() >= pixel.getGreen() && pixel.getRed() >= pixel.getBlue()) {
          greyValue = pixel.getRed();
        }
        if (pixel.getGreen() >= pixel.getRed() && pixel.getGreen() >= pixel.getBlue()) {
          greyValue = pixel.getGreen();
        }
        if (pixel.getBlue() >= pixel.getGreen() && pixel.getBlue() >= pixel.getRed()) {
          greyValue = pixel.getBlue();
        }
        break;
      case Intensity:
        greyValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
        break;
      case Luma:
        greyValue = (int) (pixel.getRed() * 0.2126
                + pixel.getGreen() * 0.7152
                + pixel.getBlue() * 0.0722);
        break;
      default:
        throw new IllegalArgumentException("Not a valid RGBVIL.");
    }
    return greyValue;
  }
}
