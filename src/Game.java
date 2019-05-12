import java.awt.event.*;

public class Game// implements KeyListener//, MouseListener
{
  private Frame frame;
  private Panel panel;

  private Map map;
  private String mapName = "Level1";

  private Entity player;

  private boolean[] activeKey = {false, false, false, false};

  public Game()
  {
    openFrame();
    initGame();

    wait(100);

    start();
  }

  // Frame functions

  private void openFrame()
  {
    frame = new Frame();
    frame.addKeyListener(new InputHandler());

    initPanel();
    //initPanelSize();
  }

  // Panel functions

  private void initPanel()
  {
    panel = frame.getPanel();
  }

  // Game functions

  private void initGame()
  {
    map = new Map(panel, mapName, 40, 30, 25);
    player = new Entity(panel, map, 200, 100, 1);
  }

  private void wait(int time)
  {
    try
    {
      Thread.sleep(time);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  private void start()
  {
    boolean isFinished = false;

    panel.display();
    map.displayAll();

    while(!isFinished)
    {
      move();
      display();

      wait(10);
    }
  }

  // Movements

  private void move()
  {
    player.move(activeKey);
  }

  // Display

  private void display()
  {
    map.displayArround(player);
    player.display();

    panel.antiLag();
  }

  // KeyAdapter

  private class InputHandler extends KeyAdapter
  {
    @Override
    public void keyPressed(KeyEvent e)
    {
      int key = e.getKeyCode();
      switch(key)
      {
        case  KeyEvent.VK_Z:
        case  KeyEvent.VK_UP:
          activeKey[0] = true;
          break;
        case  KeyEvent.VK_D:
        case  KeyEvent.VK_RIGHT:
          activeKey[1] = true;
          break;
        case  KeyEvent.VK_S:
        case  KeyEvent.VK_DOWN:
          activeKey[2] = true;
          break;
        case  KeyEvent.VK_Q:
        case  KeyEvent.VK_LEFT:
          activeKey[3] = true;
          break;
      }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
      int key = e.getKeyCode();
      switch(key)
      {
        case KeyEvent.VK_Z:
        case KeyEvent.VK_UP:
          activeKey[0] = false;
          break;
        case KeyEvent.VK_D:
        case KeyEvent.VK_RIGHT:
          activeKey[1] = false;
          break;
        case KeyEvent.VK_S:
        case KeyEvent.VK_DOWN:
          activeKey[2] = false;
          break;
        case KeyEvent.VK_Q:
        case KeyEvent.VK_LEFT:
          activeKey[3] = false;
          break;
      }
    }
  }
}
