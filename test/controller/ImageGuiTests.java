package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import model.ImageModel;
import model.ImageModelImpl;
import view.ImageGuiView;
import view.ImageGuiViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ImageGuiControllerImpl.
 */
public class ImageGuiTests {

  @Test
  public void testLoadEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/1black1whiteVertical.ppm");
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
  public void testSaveEvent() throws IOException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/TestImageWith4Pixels.ppm");
    controller.saveEvent("checker", "res/TestingSaveImage.ppm");
    controller.loadEvent("checkerO", "res/TestingSaveImage.ppm");

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
  public void testBlurEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/TestImageWith4Pixels.ppm");
    controller.blurEvent("checker", "blur");

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
  public void testBrightenEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/1black1whiteVertical.ppm");
    controller.brightenEvent(10, "checker", "checkerB");

    assertEquals(10, model.getImage("checkerB")[0][0].getRed());
    assertEquals(10, model.getImage("checkerB")[0][0].getGreen());
    assertEquals(10, model.getImage("checkerB")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[0][0].getMax());

    assertEquals(255, model.getImage("checkerB")[1][0].getRed());
    assertEquals(255, model.getImage("checkerB")[1][0].getGreen());
    assertEquals(255, model.getImage("checkerB")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerB")[1][0].getMax());
  }

  @Test
  public void testComponentEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/TestImageWith4Pixels.ppm");
    controller.componentEvent(ImageModel.RGBVIL.Luma,
            "checker", "GrayscaleLuma");

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
  public void testFlipHEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/1black1white.ppm");
    controller.flipHEvent("checker", "checkerH");

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
  public void testFlipVEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/1black1whiteVertical.ppm");
    controller.flipVEvent("checker", "checkerV");

    assertEquals(255, model.getImage("checkerV")[0][0].getRed());
    assertEquals(255, model.getImage("checkerV")[0][0].getGreen());
    assertEquals(255, model.getImage("checkerV")[0][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[0][0].getMax());

    assertEquals(0, model.getImage("checkerV")[1][0].getRed());
    assertEquals(0, model.getImage("checkerV")[1][0].getGreen());
    assertEquals(0, model.getImage("checkerV")[1][0].getBlue());
    assertEquals(255, model.getImage("checkerV")[1][0].getMax());
  }

  @Test
  public void testGreyscaleEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/TestImageWith4Pixels.ppm");
    controller.greyscaleEvent("checker", "GrayscaleLuma");

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
  }

  @Test
  public void testSharpenEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/TestImageWith4Pixels.ppm");
    controller.sharpenEvent("checker", "sharpen");

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
  public void testSepiaEvent() throws NoSuchFileException {
    ImageModel model = new ImageModelImpl();
    ImageGuiView view = new ImageGuiViewImpl();
    ImageGuiControllerImpl controller = new ImageGuiControllerImpl(model, view);
    controller.loadEvent("checker", "res/TestImageWith4Pixels.ppm");
    controller.sepiaEvent("checker", "sepia");

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

}
