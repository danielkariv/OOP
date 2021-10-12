import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectHitLevel class.
 */
public class DirectHitLevel implements LevelInformation {
    @Override
    public List<Point> initialBalls() {
        List<Point> balls = new ArrayList<>();

        balls.add(new Point(400, 400));
        return balls;
    }

    @Override
    public int numberOfBalls() {
        return initialBalls().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, 3));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 50;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.RED);
                d.drawCircle(400, 100, 25);
                d.drawCircle(400, 100, 75);
                d.drawLine(325, 100, 475, 100);
                d.drawLine(400, 25, 400, 175);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        blocks.add(new Block(380, 80, 40, 40, Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
