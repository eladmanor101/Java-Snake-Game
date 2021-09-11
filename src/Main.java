import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.add(new MyPanel());
            f.setSize(Settings.screenWidth, Settings.screenHeight);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        });
    }
}

class MyPanel extends JPanel
{
    Snake snake = new Snake();
    Apple apple = new Apple();

    Timer timer;

    public MyPanel()
    {
        this.addKeyListener(snake);
        this.setFocusable(true);

        apple.generate(snake.snakeArray);

        timer = new Timer(400, e -> update());
        timer.start();
    }

    public void update()
    {
        if (snake.isSelfColliding())
        {
            timer.stop();
            this.repaint();
            return;
        }

        snake.update();

        // Check for apple collision
        if (snake.snakeArray.get(0).p1 == apple.applePosition.p1 && snake.snakeArray.get(0).p2 == apple.applePosition.p2)
        {
            // Grow snake
            snake.grow();

            // Generate new apple position
            apple.generate(snake.snakeArray);
        }

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        snake.draw(g);
        apple.draw(g);
    }
}