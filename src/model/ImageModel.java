package model;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import model.command.ImageFunctionObject;

/**
 * This interface represents the operations offered by the image processing
 * model.
 */
public interface ImageModel {

  /**
   * This method takes in an Model.ImageFunctionObject and applies it to the image.
   * Then, it adds the modified image to the HashMap with a new name.
   */
  public void process(ImageFunctionObject modification, String imageName, String newName);

  /**
   * Some basic trait of a single image, use for grayscale.
   */
  enum RGBVIL { Red, Green, Blue, Value, Intensity, Luma }

  /**
   * Load an image from the specified path and refer it to henceforth
   * in the program by the given image name.
   *
   * @param filename the file path to load from
   * @param newName  the new image name for the program
   */
  public void load(String filename, String newName) throws NoSuchFileException;


  /**
   * Save the image with the given name to the specified path
   * which should include the name of the file.
   *
   * @param filenameToSave the file path to save to
   * @param imageName      the image name that user want to save
   * @throws IOException cannot save to the given file name
   */
  public void save(String filenameToSave, String imageName) throws IOException;

  public PixelRGB[][] getImage(String imageName);

}
