import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
/*
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
*/

public class Panel extends JPanel
{
  private final int W;
  private final int H;

  private int x = 0;
  private int y = 0;

  public Panel(Dimension size, boolean isDecorated)
  {
    W = (int)size.getWidth();

    H = (isDecorated)?((int)size.getHeight()-30) : ((int)size.getHeight());

    System.out.println(W+" "+H);
  }
  public void display()
  {
    Graphics g = getGraphics();

    drawBackground(g, Color.red);
  }

  public void antiLag()
  {
    Toolkit.getDefaultToolkit().sync();
  }

  public int getWidth()
  {
    return W;
  }

  // Drawing map

  public void drawBackground(Graphics g, Color color)
  {
    g.setColor(color);
    g.fillRect(0,0,W,H);
  }

  public void drawMap(BufferedImage image, int i, int j, int size)
  {
    Graphics g = getGraphics();

    g.drawImage(image, j*size, i*size, this);
  }

  public void drawPlayer(Color color, int x, int y, int size)
  {
    Graphics g = getGraphics();

    g.setColor(color);
    g.fillOval(x,y,size,size);
  }
}
