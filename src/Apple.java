import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class Apple
{
    int tileWidth, tileHeight;

    Pair applePosition = new Pair(0, 0);

    public Apple()
    {
        this.tileWidth = Settings.screenWidth / Settings.boardWidth;
        this.tileHeight = Settings.screenHeight / Settings.boardHeight;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(applePosition.p1 * tileWidth, applePosition.p2 * tileHeight, tileWidth, tileHeight);
    }

    public void generate(ArrayList<Pair> snakeArray)
    {
        // Filter out spaces that are occupied by the snake
        ArrayList<Pair> freeTiles = new ArrayList<Pair>();
        for (int i = 0; i < Settings.boardHeight; i++)
        {
            for (int j = 0; j < Settings.boardWidth; j++)
            {
                boolean isSnake = false;
                for (int k = 0; k < snakeArray.size(); k++)
                {
                    if (j == snakeArray.get(k).p1 && i == snakeArray.get(k).p2)
                    {
                        isSnake = true;
                        break;
                    }
                }
                if (!isSnake)
                {
                    freeTiles.add(new Pair(j, i));
                }
            }
        }

        // Generate random position
        Random rand = new Random();
        int randomIndex = rand.nextInt(freeTiles.size());
        applePosition.p1 = freeTiles.get(randomIndex).p1;
        applePosition.p2 = freeTiles.get(randomIndex).p2;
    }
}