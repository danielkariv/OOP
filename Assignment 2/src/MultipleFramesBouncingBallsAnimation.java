
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

/**
 * @author Daniel Kariv
 * MultipleFramesBouncingBallsAnimation Class, for task 3.4.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final int SCREENWIDTH = 800, SCREENHEIGHT = 600;
    private static final int FRAMEX1 = 50, FRAMEY1 = 50, FRAMEWIDTH1 = 450, FRAMEHEIGHT1 = 450;
    private static final int FRAMEX2 = 450, FRAMEY2 = 450, FRAMEWIDTH2 = 150, FRAMEHEIGHT2 = 150;
    private static final Color FRAMECOLOR1 = Color.gray, FRAMECOLOR2 = Color.yellow;
    private static final int MAXSPEED = 50;

    /**
     * Draws balls animation, which separated to two groups, render on two different frames.
     * @param balls given array.
     */
    private static void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("title", SCREENWIDTH, SCREENHEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            surface.setColor(FRAMECOLOR1);
            surface.fillRectangle(FRAMEX1, FRAMEY1, FRAMEWIDTH1, FRAMEHEIGHT1);

            for (int i = 0; i < balls.length / 2; i++) {

                balls[i].moveOneStep(FRAMEX1, FRAMEY1, FRAMEWIDTH1, FRAMEHEIGHT1);
                balls[i].drawOn(surface);
            }

            surface.setColor(FRAMECOLOR2);
            surface.fillRectangle(FRAMEX2, FRAMEY2, FRAMEWIDTH2, FRAMEHEIGHT2);
            for (int i = balls.length / 2; i < balls.length; i++) {

                balls[i].moveOneStep(FRAMEX2, FRAMEY2, FRAMEWIDTH2, FRAMEHEIGHT2);
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
                if (i < args.length / 2) {
                    balls[i] = new Ball(new Point(rand.nextInt(FRAMEWIDTH1) + FRAMEX1,
                                                  rand.nextInt(FRAMEHEIGHT1) + FRAMEY1),
                                                     Integer.parseInt(args[i]), color);
                } else {
                    balls[i] = new Ball(new Point(rand.nextInt(FRAMEWIDTH2) + FRAMEX2,
                                                  rand.nextInt(FRAMEHEIGHT2) + FRAMEY2),
                                                     Integer.parseInt(args[i]), color);
                }
                // goes slower up to size 50.
                balls[i].setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360),
                        MAXSPEED / Math.min(balls[i].getSize(), 50)));
            }
            drawAnimation(balls);
        } else {
            System.out.println("Not enough args. Please add balls size.");
        }
    }
}
