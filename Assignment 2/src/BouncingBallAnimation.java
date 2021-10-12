
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Daniel Kariv
 * BouncingBallAnimation Class, for task 3.2.
 */
public class BouncingBallAnimation {
    private static final int SCREENWIDTH = 200, SCREENHEIGHT = 200;

    /**
     * Draws animation of a ball with given starting point and velocity.
     * @param start starting point of the ball.
     * @param dx x coordination of velocity.
     * @param dy y coordination of velocity.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", SCREENWIDTH, SCREENHEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start, 30, Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(0, 0, SCREENWIDTH, SCREENHEIGHT);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Main function, initialize program.
     * @param args args from terminal.
     */
    public static void main(String[] args) {
        int x, y, dx, dy;
        if (args.length == 4) {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            dx = Integer.parseInt(args[2]);
            dy = Integer.parseInt(args[3]);

            drawAnimation(new Point(x, y), dx, dy);
        } else {
            System.out.println("Not enough args. For example: 'x y dx dy'.");

        }

    }
}
