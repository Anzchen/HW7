package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;


/**
 * Test class for testing image controller.
 */
public class ImageControllerImplTest {

  @Test
  public void testControllerLoadPPM() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/1black1whiteVertical.ppm checker");

    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();

    assertEquals(0, model.getImage("checker")[0][0].getRed());
    assertEquals(0, model.getImage("checker")[0][0].getGreen());
    assertEquals(0, model.getImage("checker")[0][0].getBlue());
    assertEquals(255, model.getImage("checker")[0][0].getMax());

    assertEquals(255, model.getImage("checker")[1][0].getRed());
    assertEquals(255, model.getImage("checker")[1][0].getGreen());
    assertEquals(255, model.getImage("checker")[1][0].getBlue());
    assertEquals(255, model.getImage("checker")[1][0].getMax());
  }

  @Test
  public void testControllerLoadImage() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/4Pixels_PNG.png checker");

    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();

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

  // test for controller load
  @Test
  public void testControllerSavePPM() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checkerO" +
            System.lineSeparator() + "save res/TestingSaveImage.ppm checkerO" +
            System.lineSeparator() + "load res/TestingSaveImage.ppm checker");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();

    assertEquals(model.getImage("checkerO")[0][0].getRed(),
            model.getImage("checker")[0][0].getRed());
    assertEquals(model.getImage("checkerO")[0][0].getGreen(),
            model.getImage("checker")[0][0].getGreen());
    assertEquals(model.getImage("checkerO")[0][0].getBlue(),
            model.getImage("checker")[0][0].getBlue());
    assertEquals(model.getImage("checkerO")[0][0].getMax(),
            model.getImage("checker")[0][0].getMax());

    assertEquals(model.getImage("checkerO")[0][1].getRed(),
            model.getImage("checker")[0][1].getRed());
    assertEquals(model.getImage("checkerO")[0][1].getGreen(),
            model.getImage("checker")[0][1].getGreen());
    assertEquals(model.getImage("checkerO")[0][1].getBlue(),
            model.getImage("checker")[0][1].getBlue());
    assertEquals(model.getImage("checkerO")[0][1].getMax(),
            model.getImage("checker")[0][1].getMax());

    assertEquals(model.getImage("checkerO")[1][0].getRed(),
            model.getImage("checker")[1][0].getRed());
    assertEquals(model.getImage("checkerO")[1][0].getGreen(),
            model.getImage("checker")[1][0].getGreen());
    assertEquals(model.getImage("checkerO")[1][0].getBlue(),
            model.getImage("checker")[1][0].getBlue());
    assertEquals(model.getImage("checkerO")[1][0].getMax(),
            model.getImage("checker")[1][0].getMax());

    assertEquals(model.getImage("checkerO")[1][1].getRed(),
            model.getImage("checker")[1][1].getRed());
    assertEquals(model.getImage("checkerO")[1][1].getGreen(),
            model.getImage("checker")[1][1].getGreen());
    assertEquals(model.getImage("checkerO")[1][1].getBlue(),
            model.getImage("checker")[1][1].getBlue());
    assertEquals(model.getImage("checkerO")[1][1].getMax(),
            model.getImage("checker")[1][1].getMax());

    File file = new File("res/TestingSaveImage.ppm");
    file.delete();
  }

  @Test
  public void testControllerSaveImage() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checkerO" +
            System.lineSeparator() + "save res/TestingSaveImagePNG.png checkerO" +
            System.lineSeparator() + "load res/TestingSaveImagePNG.png checker");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();

    assertEquals(model.getImage("checkerO")[0][0].getRed(),
            model.getImage("checker")[0][0].getRed());
    assertEquals(model.getImage("checkerO")[0][0].getGreen(),
            model.getImage("checker")[0][0].getGreen());
    assertEquals(model.getImage("checkerO")[0][0].getBlue(),
            model.getImage("checker")[0][0].getBlue());
    assertEquals(model.getImage("checkerO")[0][0].getMax(),
            model.getImage("checker")[0][0].getMax());

    assertEquals(model.getImage("checkerO")[0][1].getRed(),
            model.getImage("checker")[0][1].getRed());
    assertEquals(model.getImage("checkerO")[0][1].getGreen(),
            model.getImage("checker")[0][1].getGreen());
    assertEquals(model.getImage("checkerO")[0][1].getBlue(),
            model.getImage("checker")[0][1].getBlue());
    assertEquals(model.getImage("checkerO")[0][1].getMax(),
            model.getImage("checker")[0][1].getMax());

    assertEquals(model.getImage("checkerO")[1][0].getRed(),
            model.getImage("checker")[1][0].getRed());
    assertEquals(model.getImage("checkerO")[1][0].getGreen(),
            model.getImage("checker")[1][0].getGreen());
    assertEquals(model.getImage("checkerO")[1][0].getBlue(),
            model.getImage("checker")[1][0].getBlue());
    assertEquals(model.getImage("checkerO")[1][0].getMax(),
            model.getImage("checker")[1][0].getMax());

    assertEquals(model.getImage("checkerO")[1][1].getRed(),
            model.getImage("checker")[1][1].getRed());
    assertEquals(model.getImage("checkerO")[1][1].getGreen(),
            model.getImage("checker")[1][1].getGreen());
    assertEquals(model.getImage("checkerO")[1][1].getBlue(),
            model.getImage("checker")[1][1].getBlue());
    assertEquals(model.getImage("checkerO")[1][1].getMax(),
            model.getImage("checker")[1][1].getMax());

    File file = new File("res/TestingSaveImagePNG.png");
    file.delete();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testControllerSaveImageException() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checkerO" +
            System.lineSeparator() + "save res/TestingSaveImagePNG checkerO" +
            System.lineSeparator() + "load res/TestingSaveImagePNG checker");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
  }

  @Test
  public void testFlipH() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/1black1white.ppm checker "
            + System.lineSeparator() + "horizontal-flip checker checkerH");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
    assertEquals(255, model.getImage("checkerH")[0][0].getRed());
    assertEquals(255, model.getImage("checkerH")[0][0].getGreen());
    assertEquals(255, model.getImage("checkerH")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerH")[0][0].getMax());

    assertEquals(0, model.getImage("checkerH")[0][1].getRed());
    assertEquals(0, model.getImage("checkerH")[0][1].getGreen());
    assertEquals(0, model.getImage("checkerH")[0][1].getBlue());
    assertEquals(255, model.getImage("checkerH")[0][1].getMax());

    assertEquals("Successfully load the image."
                    + "\nSuccessfully flip horizontally the image.\n",
            output.toString());
  }

  @Test
  public void testFlipV() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/1black1whiteVertical.ppm checker"
            + System.lineSeparator() + "vertical-flip checker checkerV");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
    assertEquals(255, model.getImage("checkerV")[0][0].getRed());
    assertEquals(255, model.getImage("checkerV")[0][0].getGreen());
    assertEquals(255, model.getImage("checkerV")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[0][0].getMax());

    assertEquals(0, model.getImage("checkerV")[1][0].getRed());
    assertEquals(0, model.getImage("checkerV")[1][0].getGreen());
    assertEquals(0, model.getImage("checkerV")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[1][0].getMax());
    assertEquals("Successfully load the image.\nSuccessfully flip vertically the image.\n",
            output.toString());
  }

  @Test
  public void testBrighten() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/1black1whiteVertical.ppm checker"
            + System.lineSeparator() + "brighten 10 checker checkerB");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
    assertEquals(10, model.getImage("checkerB")[0][0].getRed());
    assertEquals(10, model.getImage("checkerB")[0][0].getGreen());
    assertEquals(10, model.getImage("checkerB")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[0][0].getMax());

    assertEquals(255, model.getImage("checkerB")[1][0].getRed());
    assertEquals(255, model.getImage("checkerB")[1][0].getGreen());
    assertEquals(255, model.getImage("checkerB")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[1][0].getMax());
    assertEquals("Successfully load the image.\nSuccessfully brighten the image.\n",
            output.toString());
  }

  @Test
  public void testDarken() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/1black1whiteVertical.ppm checker"
            + System.lineSeparator() + "brighten -10 checker checkerB");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
    assertEquals(0, model.getImage("checkerB")[0][0].getRed());
    assertEquals(0, model.getImage("checkerB")[0][0].getGreen());
    assertEquals(0, model.getImage("checkerB")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[0][0].getMax());

    assertEquals(245, model.getImage("checkerB")[1][0].getRed());
    assertEquals(245, model.getImage("checkerB")[1][0].getGreen());
    assertEquals(245, model.getImage("checkerB")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[1][0].getMax());
    assertEquals("Successfully load the image.\nSuccessfully brighten the image.\n",
            output.toString());
  }

  @Test
  public void testGreyscaleRed() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "red-component checker GrayscaleRed");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the image.\n"
                    + "Successfully grayscale the image by red.\n",
            output.toString());
  }

  @Test
  public void testGreyscaleGreen() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "green-component checker GrayscaleGreen");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the image."
                    + "\nSuccessfully grayscale the image by green.\n",
            output.toString());
  }

  @Test
  public void testGreyscaleBlue() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "blue-component checker GrayscaleBlue");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the image."
                    + "\nSuccessfully grayscale the image by blue.\n",
            output.toString());
  }

  @Test
  public void testGreyscaleValue() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "value-component checker GrayscaleValue");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the image."
                    + "\nSuccessfully grayscale the image by value.\n",
            output.toString());
  }

  @Test
  public void testGreyscaleIntensity() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "intensity-component checker GrayscaleIntensity");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
    assertEquals(101, model.getImage("GrayscaleIntensity")[0][0].getRed());
    assertEquals(101, model.getImage("GrayscaleIntensity")[0][0].getGreen());
    assertEquals(101, model.getImage("GrayscaleIntensity")[0][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[0][0].getMax());

    assertEquals(120, model.getImage("GrayscaleIntensity")[0][1].getRed());
    assertEquals(120, model.getImage("GrayscaleIntensity")[0][1].getGreen());
    assertEquals(120, model.getImage("GrayscaleIntensity")[0][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[0][1].getMax());

    assertEquals(110, model.getImage("GrayscaleIntensity")[1][0].getRed());
    assertEquals(110, model.getImage("GrayscaleIntensity")[1][0].getGreen());
    assertEquals(110, model.getImage("GrayscaleIntensity")[1][0].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[1][0].getMax());

    assertEquals(150, model.getImage("GrayscaleIntensity")[1][1].getRed());
    assertEquals(150, model.getImage("GrayscaleIntensity")[1][1].getGreen());
    assertEquals(150, model.getImage("GrayscaleIntensity")[1][1].getBlue());
    assertEquals(255, model.getImage("GrayscaleIntensity")[1][1].getMax());
    assertEquals("Successfully load the image.\nSuccessfully "
            + "grayscale the image by intensity.\n", output.toString());
  }

  @Test
  public void testGreyscaleLuma() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "luma-component checker GrayscaleLuma");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the image."
                    + "\nSuccessfully grayscale the image by luma.\n",
            output.toString());

    File file = new File("res/TestingSaveImage.ppm");
    file.delete();
  }

  @Test
  public void testModifyTwice() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/1black1whiteVertical.ppm checker"
            + System.lineSeparator() + "vertical-flip checker checkerV"
            + System.lineSeparator() + "brighten 10 checker checkerB");

    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
    assertEquals(255, model.getImage("checkerV")[0][0].getRed());
    assertEquals(255, model.getImage("checkerV")[0][0].getGreen());
    assertEquals(255, model.getImage("checkerV")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[0][0].getMax());

    assertEquals(0, model.getImage("checkerV")[1][0].getRed());
    assertEquals(0, model.getImage("checkerV")[1][0].getGreen());
    assertEquals(0, model.getImage("checkerV")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[1][0].getMax());

    assertEquals(10, model.getImage("checkerB")[0][0].getRed());
    assertEquals(10, model.getImage("checkerB")[0][0].getGreen());
    assertEquals(10, model.getImage("checkerB")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[0][0].getMax());

    assertEquals(255, model.getImage("checkerB")[1][0].getRed());
    assertEquals(255, model.getImage("checkerB")[1][0].getGreen());
    assertEquals(255, model.getImage("checkerB")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[1][0].getMax());

    assertEquals("Successfully load the image.\nSuccessfully flip vertically the image."
            + "\nSuccessfully brighten the image.\n", output.toString());
  }

  @Test
  public void testGreyscale() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "greyscale checker GrayscaleLuma");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the image."
                    + "\nSuccessfully greyscale the image.\n",
            output.toString());
  }


  @Test
  public void testSepia() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "sepia checker sepia");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
  public void testBlur() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "blur checker blur");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
  public void testSharpen() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("load res/TestImageWith4Pixels.ppm checker"
            + System.lineSeparator() + "sharpen checker sharpen");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
  public void testFileScript() throws IOException {
    ImageModel model = new ImageModelImpl();
    Readable input = new StringReader("-file res/testScript.txt");
    Appendable output = new StringBuilder("");
    ImageController controller = new ImageControllerImpl(model, input, output);
    controller.control();
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
    assertEquals("Successfully load the file.\n" +
            "Successfully load the image.\n" +
            "Successfully brighten the image.\n" +
            "Successfully grayscale the image by red.\n" +
            "Successfully grayscale the image by green.\n" +
            "Successfully grayscale the image by blue.\n" +
            "Successfully grayscale the image by value.\n" +
            "Successfully grayscale the image by intensity.\n" +
            "Successfully grayscale the image by luma.\n" +
            "Successfully flip horizontally the image.\n" +
            "Successfully flip vertically the image.\n" +
            "Successfully greyscale the image.\n" +
            "Successfully sepia the image.\n" +
            "Successfully blur the image.\n" +
            "Successfully sharpen the image.\n" +
            "Successfully save the image.\n", output.toString());

    File file = new File("res/TestFileScriptImage.png");
    file.delete();
  }


}