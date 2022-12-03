package model.command;

import model.PixelRGB;

/**
 * This class represents a function object that can be used in the Model.
 * ImageModel to modify an image.
 */
public interface ImageFunctionObject {

  /**
   * This method modifies an image. This modification is based on the implementation.
   */
  public PixelRGB[][] apply(PixelRGB[][] image);
}
