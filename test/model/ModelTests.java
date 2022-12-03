package model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import model.command.Blur;
import model.command.BrightenDarken;
import model.command.FlipHorizontal;
import model.command.FlipVertical;
import model.command.Greyscale;
import model.command.ImageFunctionObject;
import model.command.Sharpen;
import model.command.Transform;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests related to the model.
 */
public class ModelTests {

  // tests if every pixel in a 2x2 completely black image loaded correctly
  @Test
  public void testLoad() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/black.ppm", "black");
    for (int r = 0; r < model.getImage("black")[0].length; r++) {
      for (int c = 0; c < model.getImage("black").length; c++) {
        assertEquals(0, model.getImage("black")[r][c].getRed());
        assertEquals(0, model.getImage("black")[r][c].getGreen());
        assertEquals(0, model.getImage("black")[r][c].getBlue());
      }
    }
  }

  @Test
  public void testLoadPNG() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/4Pixels_PNG.png", "checker");
    assertEquals(101, model.getImage("checker")[0][0].getRed());
    assertEquals(90, model.getImage("checker")[0][0].getGreen());
    assertEquals(58, model.getImage("checker")[0][0].getBlue());
    assertEquals(255, model.getImage("checker")[0][0].getMax());

    assertEquals(103, model.getImage("checker")[0][1].getRed());
    assertEquals(120, model.getImage("checker")[0][1].getGreen());
    assertEquals(62, model.getImage("checker")[0][1].getBlue());
    assertEquals(255, model.getImage("checker")[0][1].getMax());

    assertEquals(110, model.getImage("checker")[1][0].getRed());
    assertEquals(110, model.getImage("checker")[1][0].getGreen());
    assertEquals(110, model.getImage("checker")[1][0].getBlue());
    assertEquals(255, model.getImage("checker")[1][0].getMax());

    assertEquals(146, model.getImage("checker")[1][1].getRed());
    assertEquals(118, model.getImage("checker")[1][1].getGreen());
    assertEquals(150, model.getImage("checker")[1][1].getBlue());
    assertEquals(255, model.getImage("checker")[1][1].getMax());
  }


  @Test(expected = NoSuchFileException.class)
  public void testLoadException() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("noSuchFile", "black");

  }

  // testing all the function objects

  @Test
  public void testBrightenDarken() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/checkerboardbw.ppm", "checker");

    // the after state for brightening
    ImageFunctionObject brighten = new BrightenDarken(1);
    model.process(brighten, "checker", "checker-bright");
    assertEquals(1, model.getImage("checker-bright")[0][0].getRed());
    assertEquals(1, model.getImage("checker-bright")[0][0].getGreen());
    assertEquals(1, model.getImage("checker-bright")[0][0].getBlue());
    assertEquals(255, model.getImage("checker-bright")[0][0].getMax());
    assertEquals(255, model.getImage("checker-bright")[0][1].getRed());
    assertEquals(255, model.getImage("checker-bright")[0][1].getGreen());
    assertEquals(255, model.getImage("checker-bright")[0][1].getBlue());
    assertEquals(255, model.getImage("checker-bright")[0][1].getMax());
    assertEquals(1, model.getImage("checker-bright")[1][0].getRed());
    assertEquals(1, model.getImage("checker-bright")[1][0].getGreen());
    assertEquals(1, model.getImage("checker-bright")[1][0].getBlue());
    assertEquals(255, model.getImage("checker-bright")[1][0].getMax());
    assertEquals(255, model.getImage("checker-bright")[1][1].getRed());
    assertEquals(255, model.getImage("checker-bright")[1][1].getGreen());
    assertEquals(255, model.getImage("checker-bright")[1][1].getBlue());
    assertEquals(255, model.getImage("checker-bright")[1][1].getMax());
    // the after state for darkening
    ImageFunctionObject darken = new BrightenDarken(-1);
    model.process(darken, "checker", "checker-dark");
    assertEquals(0, model.getImage("checker-dark")[0][0].getRed());
    assertEquals(0, model.getImage("checker-dark")[0][0].getGreen());
    assertEquals(0, model.getImage("checker-dark")[0][0].getBlue());
    assertEquals(255, model.getImage("checker-dark")[0][0].getMax());
    assertEquals(254, model.getImage("checker-dark")[0][1].getRed());
    assertEquals(254, model.getImage("checker-dark")[0][1].getGreen());
    assertEquals(254, model.getImage("checker-dark")[0][1].getBlue());
    assertEquals(255, model.getImage("checker-dark")[0][1].getMax());
    assertEquals(0, model.getImage("checker-dark")[1][0].getRed());
    assertEquals(0, model.getImage("checker-dark")[1][0].getGreen());
    assertEquals(0, model.getImage("checker-dark")[1][0].getBlue());
    assertEquals(255, model.getImage("checker-dark")[0][1].getMax());
    assertEquals(254, model.getImage("checker-dark")[1][1].getRed());
    assertEquals(254, model.getImage("checker-dark")[1][1].getGreen());
    assertEquals(254, model.getImage("checker-dark")[1][1].getBlue());
    assertEquals(255, model.getImage("checker-dark")[1][1].getMax());
  }

  @Test
  public void testFlipH() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/1black1white.ppm", "checker");
    ImageFunctionObject flipH = new FlipHorizontal();
    model.process(flipH, "checker", "checkerH");
    assertEquals(255, model.getImage("checkerH")[0][0].getRed());
    assertEquals(255, model.getImage("checkerH")[0][0].getGreen());
    assertEquals(255, model.getImage("checkerH")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerH")[0][0].getMax());
    assertEquals(0, model.getImage("checkerH")[0][1].getRed());
    assertEquals(0, model.getImage("checkerH")[0][1].getGreen());
    assertEquals(0, model.getImage("checkerH")[0][1].getBlue());
    assertEquals(255, model.getImage("checkerH")[0][1].getMax());
  }

  @Test
  public void testFlipV() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/1black1whiteVertical.ppm", "checker");
    ImageFunctionObject flipV = new FlipVertical();
    model.process(flipV, "checker", "checkerV");
    assertEquals(255, model.getImage("checkerV")[0][0].getRed());
    assertEquals(255, model.getImage("checkerV")[0][0].getGreen());
    assertEquals(255, model.getImage("checkerV")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[0][0].getMax());
    assertEquals(0, model.getImage("checkerV")[1][0].getRed());
    assertEquals(0, model.getImage("checkerV")[1][0].getGreen());
    assertEquals(0, model.getImage("checkerV")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[1][0].getMax());
  }

  /*
  TestImageWith4Pixels.ppm
  Width of image: 2
  Height of image: 2
  Maximum value of a color in this file (usually 255): 255
  Color of pixel (0,0): 101,90,58
  Color of pixel (1,0): 103,120,62
  Color of pixel (0,1): 110,110,110
  Color of pixel (1,1): 146,118,150
  */

  @Test
  public void testGreyscaleRed() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject redGrayscale = new Greyscale(ImageModel.RGBVIL.Red);
    model.process(redGrayscale, "checker", "GrayscaleRed");

    assertEquals(101, model.getImage("GrayscaleRed")[0][0].getRed());
    assertEquals(101, model.getImage("GrayscaleRed")[0][0].getGreen());
    assertEquals(101, model.getImage("GrayscaleRed")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleRed")[0][0].getMax());

    assertEquals(103, model.getImage("GrayscaleRed")[0][1].getRed());
    assertEquals(103, model.getImage("GrayscaleRed")[0][1].getGreen());
    assertEquals(103, model.getImage("GrayscaleRed")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleRed")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleRed")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleRed")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleRed")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleRed")[1][0].getMax());

    assertEquals(146, model.getImage("GrayscaleRed")[1][1].getRed());
    assertEquals(146, model.getImage("GrayscaleRed")[1][1].getGreen());
    assertEquals(146, model.getImage("GrayscaleRed")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleRed")[1][1].getMax());
  }

  @Test
  public void testGreyscaleGreen() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject greenGrayscale = new Greyscale(ImageModel.RGBVIL.Green);
    model.process(greenGrayscale, "checker", "GrayscaleGreen");

    assertEquals(90, model.getImage("GrayscaleGreen")[0][0].getRed());
    assertEquals(90, model.getImage("GrayscaleGreen")[0][0].getGreen());
    assertEquals(90, model.getImage("GrayscaleGreen")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleGreen")[0][0].getMax());

    assertEquals(120, model.getImage("GrayscaleGreen")[0][1].getRed());
    assertEquals(120, model.getImage("GrayscaleGreen")[0][1].getGreen());
    assertEquals(120, model.getImage("GrayscaleGreen")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleGreen")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleGreen")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleGreen")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleGreen")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleGreen")[1][0].getMax());

    assertEquals(118, model.getImage("GrayscaleGreen")[1][1].getRed());
    assertEquals(118, model.getImage("GrayscaleGreen")[1][1].getGreen());
    assertEquals(118, model.getImage("GrayscaleGreen")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleGreen")[1][1].getMax());
  }

  @Test
  public void testGreyscaleBlue() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject blueGrayscale = new Greyscale(ImageModel.RGBVIL.Blue);
    model.process(blueGrayscale, "checker", "GrayscaleBlue");

    assertEquals(58, model.getImage("GrayscaleBlue")[0][0].getRed());
    assertEquals(58, model.getImage("GrayscaleBlue")[0][0].getGreen());
    assertEquals(58, model.getImage("GrayscaleBlue")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleBlue")[0][0].getMax());

    assertEquals(62, model.getImage("GrayscaleBlue")[0][1].getRed());
    assertEquals(62, model.getImage("GrayscaleBlue")[0][1].getGreen());
    assertEquals(62, model.getImage("GrayscaleBlue")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleBlue")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleBlue")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleBlue")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleBlue")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleBlue")[1][0].getMax());

    assertEquals(150, model.getImage("GrayscaleBlue")[1][1].getRed());
    assertEquals(150, model.getImage("GrayscaleBlue")[1][1].getGreen());
    assertEquals(150, model.getImage("GrayscaleBlue")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleBlue")[1][1].getMax());
  }

  @Test
  public void testGreyscaleValue() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject valueGrayscale = new Greyscale(ImageModel.RGBVIL.Value);
    model.process(valueGrayscale, "checker", "GrayscaleValue");

    // when value of red is the biggest in single pixel
    assertEquals(101, model.getImage("GrayscaleValue")[0][0].getRed());
    assertEquals(101, model.getImage("GrayscaleValue")[0][0].getGreen());
    assertEquals(101, model.getImage("GrayscaleValue")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleValue")[0][0].getMax());

    // when value of green is the biggest in single pixel
    assertEquals(120, model.getImage("GrayscaleValue")[0][1].getRed());
    assertEquals(120, model.getImage("GrayscaleValue")[0][1].getGreen());
    assertEquals(120, model.getImage("GrayscaleValue")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleValue")[0][1].getMax());

    // when value of blue is the biggest in single pixel
    assertEquals(150, model.getImage("GrayscaleValue")[1][1].getRed());
    assertEquals(150, model.getImage("GrayscaleValue")[1][1].getGreen());
    assertEquals(150, model.getImage("GrayscaleValue")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleValue")[1][1].getMax());

    // when value three pixels are same
    assertEquals(110, model.getImage("GrayscaleValue")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleValue")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleValue")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleValue")[1][0].getMax());
  }

  //test for greyscale image based on intensity
  @Test
  public void testGreyscaleIntensity() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject intensityGrayscale = new Greyscale(ImageModel.RGBVIL.Intensity);
    model.process(intensityGrayscale, "checker", "GrayscaleIntensity");

    assertEquals(83, model.getImage("GrayscaleIntensity")[0][0].getRed());
    assertEquals(83, model.getImage("GrayscaleIntensity")[0][0].getGreen());
    assertEquals(83, model.getImage("GrayscaleIntensity")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[0][0].getMax());

    assertEquals(95, model.getImage("GrayscaleIntensity")[0][1].getRed());
    assertEquals(95, model.getImage("GrayscaleIntensity")[0][1].getGreen());
    assertEquals(95, model.getImage("GrayscaleIntensity")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleIntensity")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleIntensity")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleIntensity")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[1][0].getMax());

    assertEquals(138, model.getImage("GrayscaleIntensity")[1][1].getRed());
    assertEquals(138, model.getImage("GrayscaleIntensity")[1][1].getGreen());
    assertEquals(138, model.getImage("GrayscaleIntensity")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[1][1].getMax());
  }

  @Test
  public void testGreyscaleLuma() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject lumaGrayscale = new Greyscale(ImageModel.RGBVIL.Luma);
    model.process(lumaGrayscale, "checker", "GrayscaleLuma");
    assertEquals(90, model.getImage("GrayscaleLuma")[0][0].getRed());
    assertEquals(90, model.getImage("GrayscaleLuma")[0][0].getGreen());
    assertEquals(90, model.getImage("GrayscaleLuma")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[0][0].getMax());

    assertEquals(112, model.getImage("GrayscaleLuma")[0][1].getRed());
    assertEquals(112, model.getImage("GrayscaleLuma")[0][1].getGreen());
    assertEquals(112, model.getImage("GrayscaleLuma")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleLuma")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleLuma")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleLuma")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[1][0].getMax());

    assertEquals(126, model.getImage("GrayscaleLuma")[1][1].getRed());
    assertEquals(126, model.getImage("GrayscaleLuma")[1][1].getGreen());
    assertEquals(126, model.getImage("GrayscaleLuma")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[1][1].getMax());
  }

  @Test
  public void testSave() throws IOException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checkerOriginal");
    model.save("res/TestingSaveImage.ppm", "checkerOriginal");
    model.load("res/TestingSaveImage.ppm", "checkerAfterSave");

    assertEquals(model.getImage("checkerOriginal")[0][0].getRed(),
            model.getImage("checkerAfterSave")[0][0].getRed());
    assertEquals(model.getImage("checkerOriginal")[0][0].getGreen(),
            model.getImage("checkerAfterSave")[0][0].getGreen());
    assertEquals(model.getImage("checkerOriginal")[0][0].getBlue(),
            model.getImage("checkerAfterSave")[0][0].getBlue());
    assertEquals(model.getImage("checkerOriginal")[0][0].getMax(),
            model.getImage("checkerAfterSave")[0][0].getMax());

    assertEquals(model.getImage("checkerOriginal")[0][1].getRed(),
            model.getImage("checkerAfterSave")[0][1].getRed());
    assertEquals(model.getImage("checkerOriginal")[0][1].getGreen(),
            model.getImage("checkerAfterSave")[0][1].getGreen());
    assertEquals(model.getImage("checkerOriginal")[0][1].getBlue(),
            model.getImage("checkerAfterSave")[0][1].getBlue());
    assertEquals(model.getImage("checkerOriginal")[0][1].getMax(),
            model.getImage("checkerAfterSave")[0][1].getMax());

    assertEquals(model.getImage("checkerOriginal")[1][0].getRed(),
            model.getImage("checkerAfterSave")[1][0].getRed());
    assertEquals(model.getImage("checkerOriginal")[1][0].getGreen(),
            model.getImage("checkerAfterSave")[1][0].getGreen());
    assertEquals(model.getImage("checkerOriginal")[1][0].getBlue(),
            model.getImage("checkerAfterSave")[1][0].getBlue());
    assertEquals(model.getImage("checkerOriginal")[1][0].getMax(),
            model.getImage("checkerAfterSave")[1][0].getMax());

    assertEquals(model.getImage("checkerOriginal")[1][1].getRed(),
            model.getImage("checkerAfterSave")[1][1].getRed());
    assertEquals(model.getImage("checkerOriginal")[1][1].getGreen(),
            model.getImage("checkerAfterSave")[1][1].getGreen());
    assertEquals(model.getImage("checkerOriginal")[1][1].getBlue(),
            model.getImage("checkerAfterSave")[1][1].getBlue());
    assertEquals(model.getImage("checkerOriginal")[1][1].getMax(),
            model.getImage("checkerAfterSave")[1][1].getMax());

    File file = new File("res/TestingSaveImage.ppm");
    file.delete();
  }

  @Test
  public void testGreyscale() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    double[][] greyscaleMatrix = new double[3][3];
    greyscaleMatrix[0][0] = 0.2126;
    greyscaleMatrix[1][0] = 0.2126;
    greyscaleMatrix[2][0] = 0.2126;
    greyscaleMatrix[0][1] = 0.7152;
    greyscaleMatrix[1][1] = 0.7152;
    greyscaleMatrix[2][1] = 0.7152;
    greyscaleMatrix[0][2] = 0.0722;
    greyscaleMatrix[1][2] = 0.0722;
    greyscaleMatrix[2][2] = 0.0722;
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject lumaGrayscale = new Transform(greyscaleMatrix);
    model.process(lumaGrayscale, "checker", "GrayscaleLuma");
    assertEquals(90, model.getImage("GrayscaleLuma")[0][0].getRed());
    assertEquals(90, model.getImage("GrayscaleLuma")[0][0].getGreen());
    assertEquals(90, model.getImage("GrayscaleLuma")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[0][0].getMax());

    assertEquals(112, model.getImage("GrayscaleLuma")[0][1].getRed());
    assertEquals(112, model.getImage("GrayscaleLuma")[0][1].getGreen());
    assertEquals(112, model.getImage("GrayscaleLuma")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleLuma")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleLuma")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleLuma")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[1][0].getMax());

    assertEquals(126, model.getImage("GrayscaleLuma")[1][1].getRed());
    assertEquals(126, model.getImage("GrayscaleLuma")[1][1].getGreen());
    assertEquals(126, model.getImage("GrayscaleLuma")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleLuma")[1][1].getMax());
  }

  @Test
  public void testSepia() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    double[][] sepiaMatrix = new double[3][3];
    sepiaMatrix[0][0] = 0.393;
    sepiaMatrix[1][0] = 0.349;
    sepiaMatrix[2][0] = 0.272;
    sepiaMatrix[0][1] = 0.769;
    sepiaMatrix[1][1] = 0.686;
    sepiaMatrix[2][1] = 0.534;
    sepiaMatrix[0][2] = 0.189;
    sepiaMatrix[1][2] = 0.168;
    sepiaMatrix[2][2] = 0.131;
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject lumaGrayscale = new Transform(sepiaMatrix);
    model.process(lumaGrayscale, "checker", "sepia");
    assertEquals(119, model.getImage("sepia")[0][0].getRed());
    assertEquals(106, model.getImage("sepia")[0][0].getGreen());
    assertEquals(83, model.getImage("sepia")[0][0].getBlue());
    assertEquals(255, model.getImage("sepia")[0][0].getMax());

    assertEquals(144, model.getImage("sepia")[0][1].getRed());
    assertEquals(128, model.getImage("sepia")[0][1].getGreen());
    assertEquals(100, model.getImage("sepia")[0][1].getBlue());
    assertEquals(255, model.getImage("sepia")[0][1].getMax());

    assertEquals(148, model.getImage("sepia")[1][0].getRed());
    assertEquals(132, model.getImage("sepia")[1][0].getGreen());
    assertEquals(103, model.getImage("sepia")[1][0].getBlue());
    assertEquals(255, model.getImage("sepia")[1][0].getMax());

    assertEquals(176, model.getImage("sepia")[1][1].getRed());
    assertEquals(157, model.getImage("sepia")[1][1].getGreen());
    assertEquals(122, model.getImage("sepia")[1][1].getBlue());
    assertEquals(255, model.getImage("sepia")[1][1].getMax());
  }

  @Test
  public void testBlur() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject blur = new Blur();
    model.process(blur, "checker", "blur");
    assertEquals(61, model.getImage("blur")[0][0].getRed());
    assertEquals(58, model.getImage("blur")[0][0].getGreen());
    assertEquals(45, model.getImage("blur")[0][0].getBlue());
    assertEquals(255, model.getImage("blur")[0][0].getMax());

    assertEquals(63, model.getImage("blur")[0][1].getRed());
    assertEquals(62, model.getImage("blur")[0][1].getGreen());
    assertEquals(48, model.getImage("blur")[0][1].getBlue());
    assertEquals(255, model.getImage("blur")[0][1].getMax());

    assertEquals(64, model.getImage("blur")[1][0].getRed());
    assertEquals(61, model.getImage("blur")[1][0].getGreen());
    assertEquals(57, model.getImage("blur")[1][0].getBlue());
    assertEquals(255, model.getImage("blur")[1][0].getMax());

    assertEquals(69, model.getImage("blur")[1][1].getRed());
    assertEquals(63, model.getImage("blur")[1][1].getGreen());
    assertEquals(62, model.getImage("blur")[1][1].getBlue());
    assertEquals(255, model.getImage("blur")[1][1].getMax());
  }

  @Test
  public void testSharpen() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    ImageFunctionObject sharp = new Sharpen();
    model.process(sharp, "checker", "sharpen");
    assertEquals(190, model.getImage("sharpen")[0][0].getRed());
    assertEquals(177, model.getImage("sharpen")[0][0].getGreen());
    assertEquals(138, model.getImage("sharpen")[0][0].getBlue());
    assertEquals(255, model.getImage("sharpen")[0][0].getMax());

    assertEquals(192, model.getImage("sharpen")[0][1].getRed());
    assertEquals(199, model.getImage("sharpen")[0][1].getGreen());
    assertEquals(141, model.getImage("sharpen")[0][1].getBlue());
    assertEquals(255, model.getImage("sharpen")[0][1].getMax());

    assertEquals(197, model.getImage("sharpen")[1][0].getRed());
    assertEquals(192, model.getImage("sharpen")[1][0].getGreen());
    assertEquals(177, model.getImage("sharpen")[1][0].getBlue());
    assertEquals(255, model.getImage("sharpen")[1][0].getMax());

    assertEquals(224, model.getImage("sharpen")[1][1].getRed());
    assertEquals(198, model.getImage("sharpen")[1][1].getGreen());
    assertEquals(207, model.getImage("sharpen")[1][1].getBlue());
    assertEquals(255, model.getImage("sharpen")[1][1].getMax());
  }

  @Test
  public void testSavePNG() throws IOException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checkerOriginal");
    model.save("res/TestingSaveImage.png", "checkerOriginal");
    model.load("res/TestingSaveImage.png", "checkerAfterSave");

    assertEquals(model.getImage("checkerOriginal")[0][0].getRed(),
            model.getImage("checkerAfterSave")[0][0].getRed());
    assertEquals(model.getImage("checkerOriginal")[0][0].getGreen(),
            model.getImage("checkerAfterSave")[0][0].getGreen());
    assertEquals(model.getImage("checkerOriginal")[0][0].getBlue(),
            model.getImage("checkerAfterSave")[0][0].getBlue());
    assertEquals(model.getImage("checkerOriginal")[0][0].getMax(),
            model.getImage("checkerAfterSave")[0][0].getMax());

    assertEquals(model.getImage("checkerOriginal")[0][1].getRed(),
            model.getImage("checkerAfterSave")[0][1].getRed());
    assertEquals(model.getImage("checkerOriginal")[0][1].getGreen(),
            model.getImage("checkerAfterSave")[0][1].getGreen());
    assertEquals(model.getImage("checkerOriginal")[0][1].getBlue(),
            model.getImage("checkerAfterSave")[0][1].getBlue());
    assertEquals(model.getImage("checkerOriginal")[0][1].getMax(),
            model.getImage("checkerAfterSave")[0][1].getMax());

    assertEquals(model.getImage("checkerOriginal")[1][0].getRed(),
            model.getImage("checkerAfterSave")[1][0].getRed());
    assertEquals(model.getImage("checkerOriginal")[1][0].getGreen(),
            model.getImage("checkerAfterSave")[1][0].getGreen());
    assertEquals(model.getImage("checkerOriginal")[1][0].getBlue(),
            model.getImage("checkerAfterSave")[1][0].getBlue());
    assertEquals(model.getImage("checkerOriginal")[1][0].getMax(),
            model.getImage("checkerAfterSave")[1][0].getMax());

    assertEquals(model.getImage("checkerOriginal")[1][1].getRed(),
            model.getImage("checkerAfterSave")[1][1].getRed());
    assertEquals(model.getImage("checkerOriginal")[1][1].getGreen(),
            model.getImage("checkerAfterSave")[1][1].getGreen());
    assertEquals(model.getImage("checkerOriginal")[1][1].getBlue(),
            model.getImage("checkerAfterSave")[1][1].getBlue());
    assertEquals(model.getImage("checkerOriginal")[1][1].getMax(),
            model.getImage("checkerAfterSave")[1][1].getMax());

    File file = new File("res/TestingSaveImage.png");
    file.delete();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSaveException() throws IOException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checkerOriginal");
    model.save("res/TestingSaveImage", "checkerOriginal");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTransformException() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    model.load("res/TestImageWith4Pixels.ppm", "checker");
    double[][] array = new double[1][1];
    array[0][0] = 1.0;
    ImageFunctionObject badTransform = new Transform(array);
  }
}
