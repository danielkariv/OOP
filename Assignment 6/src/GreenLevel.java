import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * GreenLevel class.
 */
public class GreenLevel implements LevelInformation {
    @Override
    public List<Point> initialBalls() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(300, 400));
        points.add(new Point(500, 400));

        return points;
    }

    @Override
    public int numberOfBalls() {
        return initialBalls().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            velocities.add(new Velocity(2 + i * 2, 4 - i));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(180, 240, 100));
                d.fillRectangle(0, 0, 800, 600);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Color[] colors = {Color.red, Color.green, Color.orange, Color.blue, Color.pink, Color.cyan};
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 12 - y; x++) {
                Block block = new Block(800 - 10 - 50 * (x + 1), 100 + 30 * y, 50, 30, colors[y]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
