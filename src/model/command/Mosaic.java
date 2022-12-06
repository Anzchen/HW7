package model.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.Pixel;
import model.PixelRGB;

/**
 * This class is used to mosaic images.
 */
public class Mosaic implements ImageFunctionObject {
  private final int value;
  private int holder;
  private int lowestDistance;
  private ArrayList<List<Integer>> seedArray;
  private ArrayList<ArrayList<Pixel>> clusters;
  private Random random;

  /**
   * The constructor sets how many seeds we are using.
   * @param value How many seeds to break down the image into
   * @throws IllegalArgumentException If value is not positive
   */
  public Mosaic(int value) {
    this.value = value;
    this.holder = 0;
    this.lowestDistance = 0;
    this.seedArray = new ArrayList<>();
    this.clusters = new ArrayList<>();
    this.random = new Random();
  }

  /**
   * The constructor sets how many seeds we are using,
   * using a known-seed Random for testing purposes.
   *
   * @param value How many seeds to break down the image into
   * @param seed known-seed for Random
   * @throws IllegalArgumentException If value is not positive
   */
  public Mosaic(int value, int seed) {
    this.value = value;
    this.holder = 0;
    this.lowestDistance = 0;
    this.seedArray = new ArrayList<>();
    this.clusters = new ArrayList<>();
    this.random = new Random(seed);
  }

  @Override
  public PixelRGB[][] apply(PixelRGB[][] image) {
    int height;
    int width;
    for (int i = 0; i < value; i++) {
      do {
        height = (int) Math.floor(random.nextDouble() * image.length);
        width = (int) Math.floor(random.nextDouble() * image[0].length);
      }
      while (seedArray.contains(Arrays.asList(height, width)));
      seedArray.add(Arrays.asList(height,width));
    }

    for (int i = 0; i < value; i++) {
      this.clusters.add(new ArrayList<Pixel>());
      this.clusters.get(i).add(image[seedArray.get(i).get(0)][seedArray.get(i).get(1)]);
    }

    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c ++) {
        this.lowestDistance = this.distance(seedArray.get(0).get(0), seedArray.get(0).get(1), r, c);
        for (int i = 1; i < value; i++) {
          if (this.distance(seedArray.get(i).get(0), seedArray.get(i).get(1), r, c)
                  < lowestDistance) {
            this.lowestDistance =
                    this.distance(seedArray.get(i).get(0), seedArray.get(i).get(1), r, c);
            this.holder = i;
          }
        }
        this.clusters.get(this.holder).add(image[r][c]);

        for (ArrayList<Pixel> cluster : this.clusters) {
          int avgRed = 0;
          int avgBlue = 0;
          int avgGreen = 0;
          for (int i = 0; i < cluster.size(); i++) {
            avgRed += cluster.get(i).getRed();
            avgBlue += cluster.get(i).getBlue();
            avgGreen += cluster.get(i).getGreen();
          }
          avgRed = avgRed / cluster.size();
          avgBlue = avgBlue / cluster.size();
          avgGreen = avgGreen / cluster.size();
          for (int i = 0; i < cluster.size(); i++) {
            cluster.get(i).setRed(avgRed);
            cluster.get(i).setBlue(avgBlue);
            cluster.get(i).setGreen(avgGreen);
          }
        }

      }
    }
    return image;
  }

  /**
   * Helper method for finding distance between a given pixel and given seed pixel.
   *
   * @param seedx the x location of the seed.
   * @param seedy the y location of the seed.
   * @param pixelx the x location of the pixel.
   * @param pixely the y location of the pixel.
   * @return the distance between the seed and the pixel.
   */
  private int distance(int seedx, int seedy, int pixelx, int pixely) {
    return (int) Math.round(
            Math.sqrt(
            Math.pow(seedx - pixelx, 2) +
            Math.pow(seedy - pixely, 2)));
  }
}
