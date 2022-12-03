import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.ImageGuiControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageGuiView;
import view.ImageGuiViewImpl;

/**
 * The main class.
 */
public class Main {
  /**
   * The main method.
   *
   * @param args input from the user
   */
  public static void main(String[] args) throws IOException {

    ImageGuiView view;
    ImageModel model = new ImageModelImpl();
    ImageController controller;
    ImageGuiControllerImpl guiController;
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;


    // I don't know if this if statement is needed, can remove and adjust after

    switch (args.length) {
      case (0):
        // equivalent to opening a jar file
        view = new ImageGuiViewImpl();
        // Start the guiController, initialize the main frame in side the constructor
        guiController = new ImageGuiControllerImpl(model, view);

        break;
      case (1):
        if (args[0].equals("-text")) {
          // interactive text mode
          model = new ImageModelImpl();
          controller = new ImageControllerImpl(model, rd, ap);
          controller.control();
        } else {
          throw new IllegalArgumentException("Invalid command");
        }
        break;
      case (2):
        if (args[0].equals("-file") && args[1] != null) {
          String inputString = "";
          Scanner sc = new Scanner(new FileInputStream(args[1]));
          while (sc.hasNextLine()) {
            inputString = inputString + sc.nextLine() + "\n";
          }

          rd = new StringReader(inputString);
          controller = new ImageControllerImpl(model, rd, ap);
          controller.control();
        } else {
          throw new IllegalArgumentException("Invalid file");
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid command");
    }


    controller = new ImageControllerImpl(model, rd, ap);
    controller.control();

  }
}
