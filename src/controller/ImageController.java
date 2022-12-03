package controller;

import java.io.IOException;

/**
 * This class represents the controller for the image editor.
 */
public interface ImageController {

  /**
   * The main method will call this method, which allows the ImageController to take control of
   * the program.
   * @throws IOException If any transmission fails
   */
  public void control() throws IOException;
}
