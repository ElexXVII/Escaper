import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Map
{
  private Panel panel;

  private int NB_CELLS_X = 0, NB_CELLS_Y = 0, CELLSIZE = 0;
  private int LENX, LENY;

  private int nbOfPlayer;

  private File mapFile;
  private int[][] mapGrid;

  private BufferedImage grass, stone;

  public Map(Panel p, String mapName, int nbOfCellsX, int nbOfCellsY, int cellSize)
  {
    panel = p;

    //NB_CELLS_X = nbOfCellsX;
    //NB_CELLS_Y = nbOfCellsY;
    //CELLSIZE = cellSize;

    //LENX = NB_CELLS_X*CELLSIZE;
    //LENY = NB_CELLS_Y*CELLSIZE;

    mapFile = new File("../ressources/"+mapName+"");
    readMap();
    initImage();

    /*
    for (int i=0; i<NB_CELLS_Y; i++)
    {
      for (int j=0; j<NB_CELLS_X; j++)
      {
        System.out.print(Character.toString(mapGrid[i][j]));
      }
      System.out.println();
    }
    */
  }

  private void readMap()
  {
    try
    {
      BufferedReader reader = new BufferedReader(
        new FileReader(mapFile));

      int c;
      while((c = reader.read()) != -1)
      {
        char character = (char) c;
        //System.out.print(Character.toString(c));

        if (NB_CELLS_Y == 0 && c != '\n')
        {
          NB_CELLS_X++;
        }
        if (c == '\n')
        {
          NB_CELLS_Y++;
        }
      }

      CELLSIZE = panel.getWidth()/NB_CELLS_X;
      mapGrid = new int[NB_CELLS_Y][NB_CELLS_X];
      System.out.print(NB_CELLS_Y + " " + NB_CELLS_X +"\n");
      reader.close();

      reader = new BufferedReader(
        new FileReader(mapFile));
      for (int i=0; i<NB_CELLS_Y; i++)
      {
        for (int j=0; j<NB_CELLS_X+1; j++)
        {
          c = reader.read();
          char character = (char) c;
          if (c != -1 && c != '\n')
          {
            mapGrid[i][j] = c;
            //System.out.print(Character.toString(c));
          }
        }
      }
      reader.close();
    }
    catch(FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  private void initImage()
  {
    try
    {
      grass = ImageIO.read(new File("../ressources/grass.png"));
      stone = ImageIO.read(new File("../ressources/stone.png"));
    }
    catch(FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  // Getters

  public int getCellSize()
  {
    return CELLSIZE;
  }

  // Usable functions

  public void displayAll()
  {
    for (int i=0; i<NB_CELLS_Y; i++)
    {
      for (int j=0; j<NB_CELLS_X; j++)
      {
        switch(Character.toString(mapGrid[i][j]))
        {
          case "*":
            panel.drawMap(stone, i, j, CELLSIZE);
            break;
          default:
            panel.drawMap(grass, i, j, CELLSIZE);
            break;
        }
      }
    }
  }

  public void displayArround(Entity player)
  {
    int xCell = (int)player.getX()/CELLSIZE;
    int yCell = (int)player.getY()/CELLSIZE;

    System.out.println((xCell)+" "+(yCell));

    for (int i=-1;i<2;i++)
    {
      for (int j=-1;j<2;j++)
      {
        switch(Character.toString(mapGrid[yCell+i][xCell+j]))
        {
          case "*":
            panel.drawMap(stone, yCell+i, xCell+j, CELLSIZE);
            break;
          default:
            panel.drawMap(grass, yCell+i, xCell+j, CELLSIZE);
            break;
        }
      }
    }

  }
}
