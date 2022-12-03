package model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for imageUtil class.
 */
public class ImageUtilTests {

  @Test
  public void testReadPPM() {
    PixelRGB[][] image = ImageUtil.readPPM("res/1black1white.ppm");
    assertEquals(0, image[0][0].getRed());
    assertEquals(0, image[0][0].getGreen());
    assertEquals(0, image[0][0].getBlue());
    assertEquals(255, image[0][0].getMax());
    assertEquals(255, image[0][1].getRed());
    assertEquals(255, image[0][1].getGreen());
    assertEquals(255, image[0][1].getBlue());
    assertEquals(255, image[0][1].getMax());
  }

  @Test
  public void testReadImage() throws NoSuchFileException {
    PixelRGB[][] image = ImageUtil.readImage("res/4Pixels_PNG.png");
    assertEquals(101, image[0][0].getRed());
    assertEquals(90, image[0][0].getGreen());
    assertEquals(58, image[0][0].getBlue());
    assertEquals(255, image[0][0].getMax());

    assertEquals(103, image[0][1].getRed());
    assertEquals(120, image[0][1].getGreen());
    assertEquals(62, image[0][1].getBlue());
    assertEquals(255, image[0][1].getMax());

    assertEquals(110, image[1][0].getRed());
    assertEquals(110, image[1][0].getGreen());
    assertEquals(110, image[1][0].getBlue());
    assertEquals(255, image[1][0].getMax());

    assertEquals(146, image[1][1].getRed());
    assertEquals(118, image[1][1].getGreen());
    assertEquals(150, image[1][1].getBlue());
    assertEquals(255, image[1][1].getMax());
  }

  // test writePPM method
  @Test
  public void testWritePPM() throws IOException {
    PixelRGB[][] image = ImageUtil.readPPM("res/TestImageWith4Pixels.ppm");
    ImageUtil.writePPM(image, "res/testWrite.ppm");
    PixelRGB[][] checkerImage = ImageUtil.readPPM("res/testWrite.ppm");
    assertEquals(image[0][0].getRed(), checkerImage[0][0].getRed());
    assertEquals(image[0][0].getGreen(), checkerImage[0][0].getGreen());
    assertEquals(image[0][0].getBlue(), checkerImage[0][0].getBlue());
    assertEquals(image[0][0].getMax(), checkerImage[0][0].getMax());

    assertEquals(image[0][1].getRed(), checkerImage[0][1].getRed());
    assertEquals(image[0][1].getGreen(), checkerImage[0][1].getGreen());
    assertEquals(image[0][1].getBlue(), checkerImage[0][1].getBlue());
    assertEquals(image[0][1].getMax(), checkerImage[0][1].getMax());

    assertEquals(image[1][0].getRed(), checkerImage[1][0].getRed());
    assertEquals(image[1][0].getGreen(), checkerImage[1][0].getGreen());
    assertEquals(image[1][0].getBlue(), checkerImage[1][0].getBlue());
    assertEquals(image[1][0].getMax(), checkerImage[1][0].getMax());

    assertEquals(image[1][1].getRed(), checkerImage[1][1].getRed());
    assertEquals(image[1][1].getGreen(), checkerImage[1][1].getGreen());
    assertEquals(image[1][1].getBlue(), checkerImage[1][1].getBlue());
    assertEquals(image[1][1].getMax(), checkerImage[1][1].getMax());

    File file = new File("res/testWrite.ppm");
    file.delete();
  }

  @Test
  public void testMakeImageOutput() throws IOException {
    PixelRGB[][] image = ImageUtil.readImage("res/4Pixels_PNG.png");
    ImageUtil.makeImageOutput(image, "res/testMakeOutput.bmp");
    PixelRGB[][] checkerImage = ImageUtil.readImage("res/testMakeOutput.bmp");
    assertEquals(image[0][0].getRed(), checkerImage[0][0].getRed());
    assertEquals(image[0][0].getGreen(), checkerImage[0][0].getGreen());
    assertEquals(image[0][0].getBlue(), checkerImage[0][0].getBlue());
    assertEquals(image[0][0].getMax(), checkerImage[0][0].getMax());

    assertEquals(image[0][1].getRed(), checkerImage[0][1].getRed());
    assertEquals(image[0][1].getGreen(), checkerImage[0][1].getGreen());
    assertEquals(image[0][1].getBlue(), checkerImage[0][1].getBlue());
    assertEquals(image[0][1].getMax(), checkerImage[0][1].getMax());

    assertEquals(image[1][0].getRed(), checkerImage[1][0].getRed());
    assertEquals(image[1][0].getGreen(), checkerImage[1][0].getGreen());
    assertEquals(image[1][0].getBlue(), checkerImage[1][0].getBlue());
    assertEquals(image[1][0].getMax(), checkerImage[1][0].getMax());

    assertEquals(image[1][1].getRed(), checkerImage[1][1].getRed());
    assertEquals(image[1][1].getGreen(), checkerImage[1][1].getGreen());
    assertEquals(image[1][1].getBlue(), checkerImage[1][1].getBlue());
    assertEquals(image[1][1].getMax(), checkerImage[1][1].getMax());

    File file = new File("res/testMakeOutput.bmp");
    file.delete();
  }

  @Test
  public void testMakeImageCopy() {
    PixelRGB[][] makeCopyOfMe = ImageUtil.readPPM("res/1black1white.ppm");
    PixelRGB[][] image = ImageUtil.makeImageCopy(makeCopyOfMe);
    assertEquals(0, image[0][0].getRed());
    assertEquals(0, image[0][0].getGreen());
    assertEquals(0, image[0][0].getBlue());
    assertEquals(255, image[0][0].getMax());

    assertEquals(255, image[0][1].getRed());
    assertEquals(255, image[0][1].getGreen());
    assertEquals(255, image[0][1].getBlue());
    assertEquals(255, image[0][1].getMax());
  }
}
