import javax.swing.JFrame;

import java.awt.*;
/*
import java.awt.Dimension;
import java.awt.Color;
*/

public class Frame extends JFrame
{
  Panel panel;
  Dimension size = new Dimension(1000,780);
  boolean isDecorated = true;

  public Frame()
  {
    this.setTitle("Escaper");
    this.setSize((int)size.getWidth(),(int)size.getHeight());
    //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(!isDecorated);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    panel = new Panel(size, isDecorated);

    this.setContentPane(panel);
    this.setVisible(true);
  }

  // Getters

  public Panel getPanel()
  {
    return panel;
  }
}
