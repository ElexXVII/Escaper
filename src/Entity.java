import java.awt.Color;

public class Entity
{
  private Panel panel;
  private Map map;

  private float x, y, speed=1f;
  private int size;

  public Entity(Panel p, Map currentMap, float x, float y, float s)
  {
    panel = p;
    map = currentMap;

    this.x = x;
    this.y = y;
    this.speed = s;
    this.size = map.getCellSize();
  }

  public void move(boolean[] key)
  {
    //0-1-2-3 => N-E-S-W
    if (key[0])
    {
      y -= speed;
    }
    if (key[1])
    {
      x += speed;
    }
    if (key[2])
    {
      y += speed;
    }
    if (key[3])
    {
      x -= speed;
    }
  }

  public float getX()
  {
    return x;
  }

  public float getY()
  {
    return y;
  }

  public void display()
  {
    panel.drawPlayer(Color.black, (int)x, (int)y, size);
  }
}
