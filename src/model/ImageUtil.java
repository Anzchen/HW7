package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static PixelRGB[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    // new
    PixelRGB[][] pixels = new PixelRGB[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);

        // new
        pixels[i][j] = new PixelRGB(r, g, b, maxValue);
      }
    }

    // new
    return pixels;
  }

  /**
   * Write a image from PixelRGB[][] format to PPM format.
   *
   * @param image    the image to convert to PPM format
   * @param filename the file name of the PPM format file
   * @throws IOException if image cannot be converted to ppm
   */
  public static void writePPM(PixelRGB[][] image, String filename) throws IOException {
    File newImage = new File(filename);
    FileWriter filewriter = new FileWriter(newImage);

    filewriter.write("P3" + System.lineSeparator());
    filewriter.write(image[0].length + " " + image.length + System.lineSeparator());
    filewriter.write(image[0][0].getMax() + System.lineSeparator());

    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        filewriter.write(image[r][c].getRed() + System.lineSeparator());
        filewriter.write(image[r][c].getGreen() + System.lineSeparator());
        filewriter.write(image[r][c].getBlue() + System.lineSeparator());
      }
    }
    filewriter.close();
  }

  /**
   * Read an image file in the format other than PPM (png/bmp/jpg), and print the colors.
   *
   * @param filename the filename of the image to read
   * @return a image for ImageModel in PixelRGB[][] format
   * @throws NoSuchFileException if the file is not found
   */
  public static PixelRGB[][] readImage(String filename) {
    FileInputStream inputFile = null;
    BufferedImage input = null;

    try {
      inputFile = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }

    try {
      input = ImageIO.read(inputFile);
      inputFile.close();
    } catch (IOException e) {
      System.out.println("Invalid: cannot read file.");
      return null;
    }

    int width = input.getWidth();
    System.out.println("Width of image: " + width);
    int height = input.getHeight();
    System.out.println("Height of image: " + height);


    PixelRGB[][] pixels = new PixelRGB[height][width];

    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        pixels[i][j] = new PixelRGB(c.getRed(), c.getGreen(), c.getBlue(), 255);
      }
    }

    return pixels;

  }


  /**
   * Write a image from PixelRGB[][] format to png/jpg/bmp format.
   *
   * @param image    image to output as a file
   * @param filename the filename of the png/jpg/bmp file
   */
  public static void makeImageOutput(PixelRGB[][] image, String filename) {

    BufferedImage outputImage = new BufferedImage(image[0].length, image.length,
            BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        Pixel pixel = image[r][c];
        Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        outputImage.setRGB(c, r, color.getRGB());
      }
    }
    try {
      ImageIO.write(outputImage, filename.split("\\.")[1], new File(filename));

    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid: " + e.getMessage());
    }
  }


  /**
   * This method makes a copy of an image.
   *
   * @param image The image to copy
   * @return A copy of an image
   */
  public static PixelRGB[][] makeImageCopy(PixelRGB[][] image) {
    PixelRGB[][] copy = new PixelRGB[image.length][image[0].length];
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        copy[r][c] = new PixelRGB(image[r][c].getRed(), image[r][c].getGreen(),
                image[r][c].getBlue(), image[r][c].getMax());
      }
    }
    return copy;
  }


  /**
   * This is a demo main from the starter code. We decided to keep this method for now.
   *
   * @param args The inputs
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

