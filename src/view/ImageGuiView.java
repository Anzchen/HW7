package view;

import model.PixelRGB;

/**
 * The interface for the view of user interface of the program.
 */
public interface ImageGuiView {

  /**
   * Use to Initialize the gui frame.
   */
  public void initialize();


  /**
   * Set the controller of the image gui.
   *
   * @param listener listeners for respond to the clicks
   */
  public void addViewListener(ViewEvents listener);

  /**
   * Reveal image at the center to user.
   *
   * @param imageName The name of the image to show.
   * @param image the image to show to user.
   */
  public void showCenterImage(String imageName, PixelRGB[][] image);

  /**
   * This method makes and displays the histograms for red, green, blue, and intensity.
   *
   * @param image The image to make histograms of
   */
  public void makeHistograms(PixelRGB[][] image);
}