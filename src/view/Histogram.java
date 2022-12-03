package view;

//import java.awt.*;
//import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * Image histograms show the frequency values of red, green, blue, or intensity in the pixels
 * of an image. This frequency is represented by a line graph.
 */
public class Histogram extends JPanel {

  // colorAmounts (int[256]) is set in a away such that the number of
  // 0 value color pixels is in data[0], the number of 1 value color pixels is in data[1]... etc
  private final int[] colorAmounts;
  private final Color color;

  /**
   * Create a histogram that shows the color frequency.
   *
   * @param colorAmounts the amounts of color.
   * @param color the color for histogram
   * @throws IllegalArgumentException if color amounts is not 256
   */
  public Histogram(int[] colorAmounts, Color color) throws IllegalArgumentException {
    super();
    if (colorAmounts.length != 256) {
      throw new IllegalArgumentException("Must be 256 in length");
    }

    this.colorAmounts = colorAmounts;
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);


    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(color);


    // we loop through all the color values, determine the amount of each color value, and plot
    // them on a bar graph.
    for (int i = 0; i < this.colorAmounts.length; i++) {
      g2d.fillRect(i, 0, 1, this.colorAmounts[i] / 50);

    }


  }

}
