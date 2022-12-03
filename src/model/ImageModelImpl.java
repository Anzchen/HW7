package model;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;

import model.command.ImageFunctionObject;

/**
 * This class represents the loaded images, where images can be stored and modified.
 */
public class ImageModelImpl implements ImageModel {

  private final Map<String, PixelRGB[][]> loadedImages;

  /**
   * This constructor takes in an image and a function object.
   */
  public ImageModelImpl() {
    this.loadedImages = new HashMap<>();
  }


  @Override
  public void process(ImageFunctionObject modification, String imageName, String newName) {
    PixelRGB[][] copy = ImageUtil.makeImageCopy(loadedImages.get(imageName));
    this.loadedImages.put(newName, modification.apply(copy));
  }

  @Override
  public void load(String filename, String newName) throws NoSuchFileException {
    String imageFormat = "";
    try {
      imageFormat = filename.split("\\.")[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new NoSuchFileException("Filename" + filename + "not found.");
    }

    if (imageFormat.equals("ppm")) {
      if (ImageUtil.readPPM(filename) == null) {
        throw new NoSuchFileException("Invalid: no such file exist");
      } else {
        this.loadedImages.put(newName, ImageUtil.readPPM(filename));
      }
    } else {
      if (ImageUtil.readImage(filename) == null) {
        throw new NoSuchFileException("Invalid: no such file exist");
      } else {
        this.loadedImages.put(newName, ImageUtil.readImage(filename));
      }
    }

  }

  @Override
  public void save(String filenameToSave, String imageName) throws IOException {
    String imageFormat = "";
    try {
      imageFormat = filenameToSave.split("\\.")[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid: invalid file name.");
    }
    if (imageFormat.equals("ppm")) {
      ImageUtil.writePPM(this.loadedImages.get(imageName), filenameToSave);
    } else {
      ImageUtil.makeImageOutput(this.loadedImages.get(imageName), filenameToSave);
    }

  }

  @Override
  public PixelRGB[][] getImage(String imageName) {
    return ImageUtil.makeImageCopy(loadedImages.get(imageName));
  }
}
