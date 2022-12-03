package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import model.command.Blur;
import model.command.BrightenDarken;
import model.command.FlipHorizontal;
import model.command.FlipVertical;
import model.command.Greyscale;
import model.command.Sharpen;
import model.command.Transform;
import model.command.ImageFunctionObject;
import model.ImageModel;


/**
 * This class is the implementation of the ImageController.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageModel model;
  private final Readable readable;
  private final Appendable appendable;

  /**
   * This constructor creates an instance of a controller.
   *
   * @param model      The model to work with
   * @param readable   The readable to read from
   * @param appendable The appendable to transmit outputs
   * @throws IllegalArgumentException If any of the fields are null
   */
  public ImageControllerImpl(ImageModel model, Readable readable, Appendable appendable)
          throws IllegalArgumentException {
    if (model == null || readable == null || appendable == null) {
      throw new IllegalArgumentException("Can't have null fields.");
    }

    this.model = model;
    this.readable = readable;
    this.appendable = appendable; //save for view in the future
  }

  @Override
  public void control() throws IOException {
    System.out.println("Welcome!");
    Scanner sc = new Scanner(this.readable);

    while (sc.hasNextLine()) {

      String command = sc.nextLine();

      // split command into some tokens, split by space
      String[] commandArray = command.split(" ");


      switch (commandArray[0]) {
        case ("load"):
          model.load(commandArray[1], commandArray[2]);
          this.appendable.append("Successfully load the image.\n");
          break;

        case ("save"):
          model.save(commandArray[1], commandArray[2]);
          this.appendable.append("Successfully save the image.\n");
          break;

        case ("red-component"):
          ImageFunctionObject redGreyscale = new Greyscale(ImageModel.RGBVIL.Red);
          model.process(redGreyscale, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully grayscale the image by red.\n");
          break;

        case ("green-component"):
          ImageFunctionObject greenGreyscale = new Greyscale(ImageModel.RGBVIL.Green);
          model.process(greenGreyscale, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully grayscale the image by green.\n");
          break;

        case ("blue-component"):
          ImageFunctionObject blueGreyscale = new Greyscale(ImageModel.RGBVIL.Blue);
          model.process(blueGreyscale, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully grayscale the image by blue.\n");
          break;

        case ("value-component"):
          ImageFunctionObject valueGreyscale = new Greyscale(ImageModel.RGBVIL.Value);
          model.process(valueGreyscale, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully grayscale the image by value.\n");
          break;

        case ("luma-component"):
          ImageFunctionObject lumaGreyscale = new Greyscale(ImageModel.RGBVIL.Luma);
          model.process(lumaGreyscale, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully grayscale the image by luma.\n");
          break;

        case ("intensity-component"):
          ImageFunctionObject intensityGreyscale = new Greyscale(ImageModel.RGBVIL.Value);
          model.process(intensityGreyscale, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully grayscale the image by intensity.\n");
          break;

        case ("horizontal-flip"):
          ImageFunctionObject horizontalFlip = new FlipHorizontal();
          model.process(horizontalFlip, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully flip horizontally the image.\n");
          break;

        case ("vertical-flip"):
          ImageFunctionObject verticalFlip = new FlipVertical();
          model.process(verticalFlip, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully flip vertically the image.\n");
          break;

        case ("brighten"):
          ImageFunctionObject brighten = new BrightenDarken(Integer.parseInt(commandArray[1]));
          model.process(brighten, commandArray[2], commandArray[3]);
          this.appendable.append("Successfully brighten the image.\n");
          break;

        case ("greyscale"):
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
          ImageFunctionObject greyscaleTrans = new Transform(greyscaleMatrix);
          model.process(greyscaleTrans, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully greyscale the image.\n");

          break;

        case ("sepia"):
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

          ImageFunctionObject sepiaTrans = new Transform(sepiaMatrix);
          model.process(sepiaTrans, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully sepia the image.\n");

          break;

        case ("blur"):
          ImageFunctionObject blur = new Blur();
          model.process(blur, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully blur the image.\n");
          break;

        case ("sharpen"):
          ImageFunctionObject sharpening = new Sharpen();
          model.process(sharpening, commandArray[1], commandArray[2]);
          this.appendable.append("Successfully sharpen the image.\n");
          break;

        case ("-file"):
          sc = new Scanner(new FileInputStream(commandArray[1]));
          this.appendable.append("Successfully load the file.\n");
          break;

        case ("mosaic"):
          ImageFunctionObject mosaic = new Mosaic(Integer.parseInt(commandArray[1]));
          model.process(mosaic, commandArray[2], commandArray[3]);
          this.appendable.append("Successfully mosaic the image.\n");
          break;

        default:
          this.appendable.append("Unknown command.\n");
      }
    }
  }


}
