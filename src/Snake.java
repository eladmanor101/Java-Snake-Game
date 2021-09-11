import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Snake implements KeyListener
{
    int tileWidth, tileHeight;

    ArrayList<Pair> snakeArray = new ArrayList<Pair>();
    Pair direction = new Pair(0, -1);

    public Snake()
    {
        this.tileWidth = Settings.screenWidth / Settings.boardWidth;
        this.tileHeight = Settings.screenHeight / Settings.boardHeight;
        System.out.println(tileWidth + " " + tileHeight);

        snakeArray.add(new Pair(Settings.boardWidth / 2, Settings.boardHeight / 2));
        snakeArray.add(new Pair(Settings.boardWidth / 2, Settings.boardHeight / 2 + 1));
    }

    public void update()
    {
        for (int i = snakeArray.size() - 1; i > 0; i--) // Move Body
        {
            snakeArray.set(i, new Pair(snakeArray.get(i - 1).p1, snakeArray.get(i - 1).p2));
        }
        snakeArray.set(0, new Pair(snakeArray.get(0).p1 + direction.p1, snakeArray.get(0).p2 + direction.p2)); // Move Head
        snakeArray.set(0, getWrappedCoordinate(snakeArray.get(0))); // Wrap Coordinates
    }

    public void draw(Graphics g)
    {
        // Draw Background
        for (int i = 0; i < Settings.boardHeight; i++)
        {
            for (int j = 0; j < Settings.boardWidth; j++)
            {
                g.setColor(Color.GRAY);
                g.fillRect(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
            }
        }

        // Draw Snake
        g.setColor(new Color(40, 160, 60));
        g.fillRect(snakeArray.get(0).p1 * tileWidth, snakeArray.get(0).p2 * tileHeight, tileWidth, tileHeight);
        g.setColor(Color.GREEN);
        for (int i = 1; i < snakeArray.size(); i++)
        {
            g.fillRect(snakeArray.get(i).p1 * tileWidth, snakeArray.get(i).p2 * tileHeight, tileWidth, tileHeight);
        }
    }

    public boolean isSelfColliding()
    {
        for (int i = 1; i < snakeArray.size(); i++)
        {
            if (wrapX(snakeArray.get(0).p1 + direction.p1) == snakeArray.get(i).p1 && wrapY(snakeArray.get(0).p2 + direction.p2) == snakeArray.get(i).p2)
            {
                return true;
            }
        }

        return false;
    }

    public void grow()
    {
        snakeArray.add(new Pair(snakeArray.get(snakeArray.size() - 1).p1, snakeArray.get(snakeArray.size() - 1).p2));
    }

    private Pair getWrappedCoordinate(Pair position)
    {
        if (position.p1 >= Settings.boardWidth)
        {
            return new Pair(position.p1 - Settings.boardWidth, position.p2);
        }
        if (position.p1 < 0)
        {
            return new Pair(position.p1 + Settings.boardWidth, position.p2);
        }
        if (position.p2 >= Settings.boardHeight)
        {
            return new Pair(position.p1, position.p2 - Settings.boardHeight);
        }
        if (position.p2 < 0)
        {
            return new Pair(position.p1, position.p2 + Settings.boardHeight);
        }

        return position;
    }

    private int wrapX(int x)
    {
        if (x >= Settings.boardWidth)
        {
            return x - Settings.boardWidth;
        }
        if (x < 0)
        {
            return x + Settings.boardWidth;
        }

        return x;
    }
    private int wrapY(int y)
    {
        if (y >= Settings.boardHeight)
        {
            return y - Settings.boardHeight;
        }
        if (y < 0)
        {
            return y + Settings.boardHeight;
        }

        return y;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W && direction.p2 != 1 && wrapY(snakeArray.get(0).p2 - 1) != snakeArray.get(1).p2 && snakeArray.get(0).p1 != snakeArray.get(1).p1)
        {
            direction =  new Pair(0, -1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_A && direction.p1 != 1 && wrapX(snakeArray.get(0).p1 - 1) != snakeArray.get(1).p1 && snakeArray.get(0).p2 != snakeArray.get(1).p2)
        {
            direction =  new Pair(-1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S && direction.p2 != -1 && wrapY(snakeArray.get(0).p2 + 1) != snakeArray.get(1).p2 && snakeArray.get(0).p1 != snakeArray.get(1).p1)
        {
            direction =  new Pair(0, 1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_D && direction.p1 != -1 && wrapX(snakeArray.get(0).p1 + 1) != snakeArray.get(1).p1 && snakeArray.get(0).p2 != snakeArray.get(1).p2)
        {
            direction =  new Pair(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}