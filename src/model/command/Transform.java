package model.command;

import model.PixelRGB;

/**
 * This class takes in a 3x3 matrix and uses it to alter each pixel of an image.
 */
public class Transform implements ImageFunctionObject {

  double[][] calculateMatrix;

  /**
   * This constructor takes in a 3x3 matrix and enforces it.
   * @param calculateMatrix A 3x3 matrix, represented as a double[][]
   * @throws IllegalArgumentException If the matrix is not 3x3
   */
  public Transform(double[][] calculateMatrix) throws IllegalArgumentException {

    if (calculateMatrix.length != 3 || calculateMatrix[0].length != 3) {
      throw new IllegalArgumentException("Must be a 3x3 matrix.");
    }

    this.calculateMatrix = calculateMatrix;
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        // determines the greyscale color
        double[] pixelResult = new double[3];
        pixelResult[0] = this.calculateMatrix[0][0] * image[r][c].getRed() +
                this.calculateMatrix[0][1] * image[r][c].getGreen() +
                this.calculateMatrix[0][2] * image[r][c].getBlue();
        pixelResult[1] = this.calculateMatrix[1][0] * image[r][c].getRed() +
                this.calculateMatrix[1][1] * image[r][c].getGreen() +
                this.calculateMatrix[1][2] * image[r][c].getBlue();
        pixelResult[2] = this.calculateMatrix[2][0] * image[r][c].getRed() +
                this.calculateMatrix[2][1] * image[r][c].getGreen() +
                this.calculateMatrix[2][2] * image[r][c].getBlue();

        image[r][c].setRed((int) pixelResult[0]);
        image[r][c].setGreen((int) pixelResult[1]);
        image[r][c].setBlue((int) pixelResult[2]);
      }
    }

    return image;
  }
}
