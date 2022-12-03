package view;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import model.ImageModel;

/**
 * View Events interface for all events called in user interface.
 */
public interface ViewEvents {

  /**
   * An event that saves the image.
   *
   * @param path    The image path
   * @param newName The image name
   * @throws IOException If saving doesn't work
   */
  public void saveEvent(String path, String newName) throws IOException;

  /**
   * An event that loads the image.
   *
   * @param path    The image path
   * @param newName The image's new name
   * @throws NoSuchFileException If the file doesn't exist
   */
  public void loadEvent(String path, String newName) throws NoSuchFileException;

  /**
   * An event that brightens the image.
   *
   * @param amount    The degree of brightening
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void brightenEvent(int amount, String imageName, String newName);

  /**
   * An event that greyscale the image.
   *
   * @param rgbvil either red green blue value intensity and luma for greyscale
   * @param imageName the existing image name.
   * @param newName the changed image name.
   */
  public void componentEvent(ImageModel.RGBVIL rgbvil, String imageName, String newName);

  /**
   * An event that blurs the image.
   *
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void blurEvent(String imageName, String newName);

  /**
   * An event that flips the image horizontally.
   *
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void flipHEvent(String imageName, String newName);

  /**
   * An event that flips the image vertically.
   *
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void flipVEvent(String imageName, String newName);

  /**
   * An event that sharpens the image.
   *
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void sharpenEvent(String imageName, String newName);

  /**
   * An event that makes the image sepia tone.
   *
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void sepiaEvent(String imageName, String newName);

  /**
   * An event that grey scales the image.
   *
   * @param imageName The existing image name
   * @param newName   The changed image name
   */
  public void greyscaleEvent(String imageName, String newName);


}
