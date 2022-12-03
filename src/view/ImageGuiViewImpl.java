package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.ImageModel;
import model.Pixel;
import model.PixelRGB;

/**
 * The implements of ImageGuiView, class that show the user interface of the program.
 */
public class ImageGuiViewImpl implements ImageGuiView {

  private final int width;
  private final int height;
  private final JFrame baseFrame;

  private final JPanel rightPanel;
  private final JPanel leftPanel;
  private final JPanel centerPanel;

  private final java.util.List<ViewEvents> listeners;

  private String currentName;

  /**
   * Create a gui view to show user interface.
   */
  public ImageGuiViewImpl() {
    this.listeners = new ArrayList<>();
    this.width = 1920;
    this.height = 1080;
    this.baseFrame = new JFrame("Image Processor");

    this.centerPanel = new JPanel();
    this.leftPanel = new JPanel();
    this.rightPanel = new JPanel();

    this.currentName = null;
  }

  @Override
  public void initialize() {
    this.baseFrame.setSize(1920, 1080);

    JPanel mainPanel = new JPanel();
    this.initializePanel(mainPanel);

    baseFrame.add(mainPanel);

    this.baseFrame.setVisible(true);
  }

  @Override
  public void addViewListener(ViewEvents listener) {
    this.listeners.add(Objects.requireNonNull(listener));
  }

  private void initializePanel(JPanel mainPanel) {
    mainPanel.setSize(1920, 1080);

    mainPanel.setLayout(new BorderLayout());

    this.leftPanel.setPreferredSize(new Dimension(this.width / 7, this.height));
    this.initialLeftPanel(this.leftPanel);


    this.rightPanel.setPreferredSize(new Dimension(this.width / 7, this.height));
    this.initialRightPanel(this.rightPanel);


    this.centerPanel.setPreferredSize(new Dimension(this.width * 5 / 7, this.height * 3 / 2));
    this.initialCenterPanel(this.centerPanel);

    mainPanel.add(leftPanel, BorderLayout.WEST);
    mainPanel.add(rightPanel, BorderLayout.EAST);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
  }

  /**
   * This method creates the picture reveal panel(on the center), which is use to show the picture.
   *
   * @param centerPanel the center panel for placing the picture to modify
   */
  private void initialCenterPanel(JPanel centerPanel) {
    centerPanel.setBackground(Color.LIGHT_GRAY);
    centerPanel.setBorder(BorderFactory.createTitledBorder("Click \"Load\" Button to load"));

  }

  /**
   * This method creates the histogram panel(on the right), which is use to show the histogram.
   *
   * @param rightPanel the right panel for placing the picture to modify
   */
  private void initialRightPanel(JPanel rightPanel) {
    centerPanel.setBackground(Color.LIGHT_GRAY);
    rightPanel.setBorder(BorderFactory.createTitledBorder("Histograms: Red, Green, " +
            "Blue, Intensity"));
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
    makeHistograms(null);

  }

  /**
   * This method creates the command Panel(on the left), which is use to modify the picture.
   *
   * @param leftPanel the left panel for placing these panels
   */
  private void initialLeftPanel(JPanel leftPanel) {
    //for elements to be arranged vertically within this panel
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

    // This part is for Load & Save
    JPanel loadSavePanel = new JPanel();
    loadSavePanel.setBorder(BorderFactory.createTitledBorder("Load & Save"));
    loadSavePanel.setLayout(new GridLayout(1, 2));
    leftPanel.add(loadSavePanel);

    // This Button is for Load
    JButton loadButton = new JButton("Load");
    loadButton.addActionListener(e -> {
      JFileChooser fc = new JFileChooser();
      fc.setDialogTitle("Choose image to modify");
      fc.showOpenDialog(null); // what this means??
      //get the selected file
      File image = fc.getSelectedFile();

      if (image != null) {
        String imagePath = image.getAbsolutePath();
        String imageName = image.getName();
        this.currentName = imageName;
        for (ViewEvents listener : listeners) {
          try {
            listener.loadEvent(imageName, imagePath);
          } catch (NoSuchFileException ex) {
            throw new RuntimeException("Image not found.");
          }
        }
      } else {
        throw new IllegalArgumentException("Invalid image");
      }

    });

    // This Button is for Save
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose a place to save");
        fc.showSaveDialog(null);
        File file = fc.getSelectedFile();
        String imagePath = file.getAbsolutePath();
        for (ViewEvents listener : listeners) {
          try {
            listener.saveEvent(this.currentName, imagePath);
          } catch (IOException ie) {
            throw new IllegalStateException("Image not found.");
          }
        }

      }
    });
    loadSavePanel.add(loadButton);
    loadSavePanel.add(saveButton);

    // This part is for brighten & darken
    JPanel brightenPanel = new JPanel();
    brightenPanel.setBorder(BorderFactory.createTitledBorder("Brighten & Darken"));
    brightenPanel.setLayout(new GridLayout(2, 1));
    leftPanel.add(brightenPanel);

    // make a slider for brighten or darken
    JSlider brightenSlider = new JSlider(-255, 255, 0);

    brightenSlider.setPaintTicks(true);
    brightenSlider.setMajorTickSpacing(51);
    brightenSlider.setMinorTickSpacing(10);
    brightenSlider.setPaintLabels(true);
    brightenPanel.add(brightenSlider);

    JButton brightenButton = new JButton("Brighten");
    brightenButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        String newName = this.currentName.split("\\.")[0] + "Brighten";
        for (ViewEvents listener : listeners) {
          listener.brightenEvent(brightenSlider.getValue(), this.currentName, newName);
        }

        this.currentName = newName;
      }
    });
    brightenPanel.add(brightenButton);

    // This part is for GreyScale
    JPanel greyScalePanel = new JPanel();
    greyScalePanel.setBorder(BorderFactory.createTitledBorder("GreyScale"));
    greyScalePanel.setLayout(new GridLayout(2, 1));
    leftPanel.add(greyScalePanel);

    String[] options = {"Normal Greyscale", "Red Component", "Green Component", "Blue Component",
      "Value Component", "Intensity Component", "Luma Component"};
    JComboBox<String> greyScaleCombobox = new JComboBox<String>();
    greyScaleCombobox.setActionCommand("Greyscale options");
    for (int i = 0; i < options.length; i++) {
      greyScaleCombobox.addItem(options[i]);
    }
    greyScalePanel.add(greyScaleCombobox);

    JButton greyScaleButton = new JButton("Greyscale");
    greyScaleButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        if (greyScaleCombobox.getSelectedItem().equals("Normal Greyscale")) {
          String newName = this.currentName.split("\\.")[0] + "NormalGreyscale";
          for (ViewEvents listener : listeners) {
            listener.greyscaleEvent(this.currentName, newName);
          }
          this.currentName = newName;
        } else if (greyScaleCombobox.getSelectedItem().equals("Red Component")) {
          String newName = this.currentName.split("\\.")[0] + "RedComponent";
          for (ViewEvents listener : listeners) {
            listener.componentEvent(ImageModel.RGBVIL.Red, this.currentName, newName);
          }
          this.currentName = newName;
        } else if (greyScaleCombobox.getSelectedItem().equals("Green Component")) {
          String newName = this.currentName.split("\\.")[0] + "GreenComponent";
          for (ViewEvents listener : listeners) {
            listener.componentEvent(ImageModel.RGBVIL.Green, this.currentName, newName);
          }
          this.currentName = newName;
        } else if (greyScaleCombobox.getSelectedItem().equals("Blue Component")) {
          String newName = this.currentName.split("\\.")[0] + "BlueComponent";
          for (ViewEvents listener : listeners) {
            listener.componentEvent(ImageModel.RGBVIL.Blue, this.currentName, newName);
          }
          this.currentName = newName;
        } else if (greyScaleCombobox.getSelectedItem().equals("Value Component")) {
          String newName = this.currentName.split("\\.")[0] + "ValueComponent";
          for (ViewEvents listener : listeners) {
            listener.componentEvent(ImageModel.RGBVIL.Value, this.currentName, newName);
          }
          this.currentName = newName;
        } else if (greyScaleCombobox.getSelectedItem().equals("Intensity Component")) {
          String newName = this.currentName.split("\\.")[0] + "IntensityComponent";
          for (ViewEvents listener : listeners) {
            listener.componentEvent(ImageModel.RGBVIL.Intensity, this.currentName, newName);
          }
          this.currentName = newName;
        } else if (greyScaleCombobox.getSelectedItem().equals("Luma Component")) {
          String newName = this.currentName.split("\\.")[0] + "LumaComponent";
          for (ViewEvents listener : listeners) {
            listener.componentEvent(ImageModel.RGBVIL.Luma, this.currentName, newName);
          }
          this.currentName = newName;
        }
      }
    });

    greyScalePanel.add(greyScaleButton);

    // This part is for flip
    JPanel flipPanel = new JPanel();
    flipPanel.setBorder(BorderFactory.createTitledBorder("Flip"));
    flipPanel.setLayout(new GridLayout(2, 1));
    leftPanel.add(flipPanel);

    JButton verFlipButton = new JButton("Vertical Flip");
    verFlipButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        String newName = this.currentName.split("\\.")[0] + "FlipVertical";
        for (ViewEvents listener : listeners) {
          listener.flipVEvent(this.currentName, newName);
        }
        this.currentName = newName;
      }
    });

    JButton hoFlipButton = new JButton("Horizontal Flip");
    hoFlipButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        String newName = this.currentName.split("\\.")[0] + "FlipHorizontal";
        for (ViewEvents listener : listeners) {
          listener.flipHEvent(this.currentName, newName);
        }

        this.currentName = newName;
      }
    });

    flipPanel.add(verFlipButton);
    flipPanel.add(hoFlipButton);


    // This part is for Sepia
    JPanel sepiaPenal = new JPanel();
    sepiaPenal.setBorder(BorderFactory.createTitledBorder("Sepia"));
    sepiaPenal.setLayout(new GridLayout(1, 1));
    leftPanel.add(sepiaPenal);
    // create Button for sepia
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        String newName = this.currentName.split("\\.")[0] + "Sepia";
        for (ViewEvents listener : listeners) {
          listener.sepiaEvent(this.currentName, newName);

        }
        this.currentName = newName;
      }
    });
    sepiaPenal.add(sepiaButton);

    // This part is for Sharpen
    JPanel sharpenPenal = new JPanel();
    sharpenPenal.setBorder(BorderFactory.createTitledBorder("Sharpen"));
    sharpenPenal.setLayout(new GridLayout(1, 1));
    leftPanel.add(sharpenPenal);
    // create Button for sharpen
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        String newName = this.currentName.split("\\.")[0] + "Sharpen";
        for (ViewEvents listener : listeners) {
          listener.sharpenEvent(this.currentName, newName);
        }
        this.currentName = newName;
      }
    });
    sharpenPenal.add(sharpenButton);

    // This part is for Blur
    JPanel blurPanel = new JPanel();
    blurPanel.setBorder(BorderFactory.createTitledBorder("Blur"));
    blurPanel.setLayout(new GridLayout(1, 1));
    leftPanel.add(blurPanel);
    // create Button for sepia
    JButton blurButton = new JButton("Blur");
    blurButton.addActionListener(e -> {
      if (this.currentName == null) {
        throw new IllegalArgumentException("currently No image");
      } else {
        String newName = this.currentName.split("\\.")[0] + "Blur";
        for (ViewEvents listener : listeners) {
          listener.blurEvent(this.currentName, newName);

        }
        this.currentName = newName;
      }
    });
    blurPanel.add(blurButton);

  }


  @Override
  public void showCenterImage(String imageName, PixelRGB[][] image) {
    centerPanel.setBorder(BorderFactory.createTitledBorder("Image: " + imageName));
    if (imageName == null || image == null) {
      throw new IllegalArgumentException("Null name or cannot get a image");
    }
    try {
      BufferedImage showingImage = new BufferedImage(image[0].length, image.length,
              BufferedImage.TYPE_INT_RGB);
      for (int r = 0; r < image.length; r++) {
        for (int c = 0; c < image[0].length; c++) {
          Pixel pixel = image[r][c];
          Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
          showingImage.setRGB(c, r, color.getRGB());
        }
      }

      int newHeight = this.height * 3 / 5;
      double widthHeightRatio = 1.0 * image[0].length / image.length;
      int newWidth = (int) (newHeight * widthHeightRatio);
      Image scaledShowingImage = showingImage.getScaledInstance(newWidth, newHeight,
              Image.SCALE_SMOOTH);
      Icon icon = new ImageIcon(scaledShowingImage);
      JLabel imageLabel = new JLabel(icon, JLabel.CENTER);
      // remove all previous things on screen
      this.centerPanel.removeAll();
      this.centerPanel.add(imageLabel);
      this.baseFrame.repaint();
      this.baseFrame.revalidate();
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("cannot show the image");
    }
    return;
  }

  @Override
  public void makeHistograms(PixelRGB[][] image) {
    if (image == null) {
      // no need to display histograms if there is no image!
      return;
    }

    // all arrays initialized to 0
    int[] redData = new int[256];
    int[] greenData = new int[256];
    int[] blueData = new int[256];
    int[] intensityData = new int[256];


    // loop through the image to get the color data
    for (int r = 0; r < image.length - 1; r++) {
      for (int c = 0; c < image[0].length - 1; c++) {

        int redValue = image[r][c].getRed();
        redData[redValue] = redData[redValue] + 1;

        int greenValue = image[r][c].getGreen();
        greenData[greenValue] = greenData[greenValue] + 1;

        int blueValue = image[r][c].getBlue();
        blueData[blueValue] = blueData[blueValue] + 1;

        int intensityValue = (redValue + greenValue + blueValue) / 3;
        intensityData[intensityValue] = intensityData[intensityValue] + 1;
      }
    }


    // The red, green, and blue histograms will be of their respective colors.
    // The intensity histogram will be yellow for no particular reason.
    Histogram redHist = new Histogram(redData, Color.red);
    Histogram greenHist = new Histogram(greenData, Color.blue);
    Histogram blueHist = new Histogram(blueData, Color.green);
    Histogram intensityHist = new Histogram(intensityData, Color.yellow);

    this.rightPanel.removeAll();
    // finally, add the histograms to the panel for display
    this.rightPanel.add(redHist);
    this.rightPanel.add(greenHist);
    this.rightPanel.add(blueHist);
    this.rightPanel.add(intensityHist);
    this.baseFrame.repaint();
    this.baseFrame.revalidate();
  }


}

