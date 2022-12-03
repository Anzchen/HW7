package controller;


import java.io.IOException;
import java.nio.file.NoSuchFileException;

import model.ImageModel;
import model.command.Blur;
import model.command.BrightenDarken;
import model.command.FlipHorizontal;
import model.command.FlipVertical;
import model.command.Greyscale;
import model.command.ImageFunctionObject;
import model.command.Sharpen;
import model.command.Transform;
import view.ImageGuiView;
import view.ViewEvents;

/**
 * This class is the implementation of the ImageController.
 */
public class ImageGuiControllerImpl implements ViewEvents {

  private final ImageModel model;

  private final ImageGuiView view;

  /**
   * This constructor creates an instance of a controller.
   *
   * @param model The model to work with
   * @param view the user interface view
   * @throws IllegalArgumentException If any of the fields are null
   */
  public ImageGuiControllerImpl(ImageModel model, ImageGuiView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Can't have null fields.");
    }

    this.model = model;
    this.view = view;
    this.view.addViewListener(this);
    this.view.initialize();
  }


  @Override
  public void saveEvent(String name, String path) throws IOException {
    this.model.save(path, name);
    this.view.makeHistograms(this.model.getImage(name));
  }

  @Override
  public void loadEvent(String name, String path) throws NoSuchFileException {
    this.model.load(path, name);
    this.view.showCenterImage(name, this.model.getImage(name));
    this.view.makeHistograms(this.model.getImage(name));

  }

  @Override
  public void brightenEvent(int amount, String imageName, String newName) {
    ImageFunctionObject functionObject = new BrightenDarken(amount);
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));

  }

  @Override
  public void componentEvent(ImageModel.RGBVIL rgbvil, String imageName, String newName) {
    ImageFunctionObject functionObject = new Greyscale(rgbvil);
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }

  @Override
  public void blurEvent(String imageName, String newName) {
    ImageFunctionObject functionObject = new Blur();
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }

  @Override
  public void flipHEvent(String imageName, String newName) {
    ImageFunctionObject functionObject = new FlipHorizontal();
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }

  @Override
  public void flipVEvent(String imageName, String newName) {
    ImageFunctionObject functionObject = new FlipVertical();
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }

  @Override
  public void sharpenEvent(String imageName, String newName) {
    ImageFunctionObject functionObject = new Sharpen();
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }

  @Override
  public void sepiaEvent(String imageName, String newName) {
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
    ImageFunctionObject functionObject = new Transform(sepiaMatrix);
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }

  @Override
  public void greyscaleEvent(String imageName, String newName) {
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
    ImageFunctionObject functionObject = new Transform(greyscaleMatrix);
    this.model.process(functionObject, imageName, newName);
    this.view.showCenterImage(newName, this.model.getImage(newName));
    this.view.makeHistograms(this.model.getImage(newName));
  }


}
