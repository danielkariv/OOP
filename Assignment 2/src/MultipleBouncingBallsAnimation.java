
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

/**
 * @author Daniel Kariv
 * MultipleBouncingBallsAnimation Class, for task 3.3.
 */
public class MultipleBouncingBallsAnimation {
    private static final int SCREENWIDTH = 200, SCREENHEIGHTGHT = 200;
    private static final int MAXSPEED = 50;

    /**
     * Draws ball animation.
     * @param balls given balls array.
     */
    private static void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("title", SCREENWIDTH, SCREENHEIGHTGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep(0, 0, SCREENWIDTH, SCREENHEIGHTGHT);
                balls[i].drawOn(surface);
            }
            gui.show(surface);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Main function, initialize program.
     * @param args args from terminal.
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int x, y, dx, dy;
        if (args.length > 0) {
            Ball[] balls = new Ball[args.length];
            for (int i = 0; i < args.length; i++) {
                Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
                balls[i] = new Ball(new Point(rand.nextInt(SCREENWIDTH) + 1, rand.nextInt(SCREENHEIGHTGHT) + 1),
                                    Integer.parseInt(args[i]), color);
                // goes slower up to size 50.
                double ballSpeed = MAXSPEED / Math.min(balls[i].getSize(), 50);
                balls[i].setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), ballSpeed));
            }

            drawAnimation(balls);
        } else {
            System.out.println("Not enough args. Please add balls size.");
        }
    }
}
