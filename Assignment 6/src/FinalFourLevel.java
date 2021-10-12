import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalFourLevel class.
 */
public class FinalFourLevel implements LevelInformation {
    @Override
    public List<Point> initialBalls() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(300, 400));
        points.add(new Point(500, 400));
        points.add(new Point(400, 450));
        return points;
    }

    @Override
    public int numberOfBalls() {
        return initialBalls().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            velocities.add(new Velocity(2 + i, 4 - i));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(150, 200, 150));
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

        Color[] colors = new Color[6];
        colors[0] = Color.gray;
        colors[1] = Color.red;
        colors[2] = Color.yellow;
        colors[3] = Color.green;
        colors[4] = Color.white;
        colors[5] = Color.pink;
        colors[6] = Color.cyan;
        for (int x = 0; x < 790; x += 60) {
            for (int y = 0; y < 180; y += 30) {
                blocks.add(new Block(10 + x, 100 + y, 60, 30, colors[y / 30]));
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
