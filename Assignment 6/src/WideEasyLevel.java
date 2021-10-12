import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasyLevel class.
 */
public class WideEasyLevel implements LevelInformation {
    @Override
    public List<Point> initialBalls() {
        List<Point> balls = new ArrayList<>();
        balls.add(new Point(300, 400));
        balls.add(new Point(320, 380));
        balls.add(new Point(340, 360));
        balls.add(new Point(360, 340));

        balls.add(new Point(460, 400));
        balls.add(new Point(440, 380));
        balls.add(new Point(420, 360));
        balls.add(new Point(400, 340));
        return balls;
    }

    @Override
    public int numberOfBalls() {
        return initialBalls().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-4, 2));
        velocities.add(new Velocity(-4, 2));
        velocities.add(new Velocity(-4, 2));
        velocities.add(new Velocity(-4, 2));

        velocities.add(new Velocity(4, 2));
        velocities.add(new Velocity(4, 2));
        velocities.add(new Velocity(4, 2));
        velocities.add(new Velocity(4, 2));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(50, 150, 230));
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

        blocks.add(new Block(20, 200, 60, 30, Color.RED));
        blocks.add(new Block(80, 200, 60, 30, Color.RED));
        blocks.add(new Block(140, 200, 60, 30, Color.ORANGE));
        blocks.add(new Block(200, 200, 60, 30, Color.ORANGE));
        blocks.add(new Block(260, 200, 60, 30, Color.YELLOW));
        blocks.add(new Block(320, 200, 60, 30, Color.YELLOW));
        blocks.add(new Block(380, 200, 60, 30, Color.GREEN));
        blocks.add(new Block(440, 200, 60, 30, Color.GREEN));
        blocks.add(new Block(500, 200, 60, 30, Color.BLUE));
        blocks.add(new Block(560, 200, 60, 30, Color.BLUE));
        blocks.add(new Block(620, 200, 60, 30, Color.PINK));
        blocks.add(new Block(680, 200, 60, 30, Color.PINK));
        blocks.add(new Block(740, 200, 60, 30, Color.CYAN));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
