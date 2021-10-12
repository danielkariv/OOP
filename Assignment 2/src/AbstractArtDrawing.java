
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Daniel Kariv
 * AbstractArtDrawing Class, for task 2.
 */
public class AbstractArtDrawing {
    private static final int SCREENWIDTH = 400, SCREENHEIGHT = 300, LINECOUNT = 10, CIRCLERADIUS = 3;

    /**
     * Draws AbstractArt (lines,middle points, and intersection points).
     */
    public void drawAbstractArt() {
        Random rand = new Random(); // create a random-number generator
        Line[] lines = new Line[LINECOUNT];
        for (int i = 0; i < LINECOUNT; i++) {
            lines[i] = new Line(rand.nextInt(SCREENWIDTH), rand.nextInt(SCREENHEIGHT),
                                rand.nextInt(SCREENWIDTH), rand.nextInt(SCREENHEIGHT));
        }
        // Create a window with the title.
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("AbstractArtDrawing", SCREENWIDTH, SCREENHEIGHT);
        DrawSurface surface = gui.getDrawSurface();
        for (int i = 0; i < LINECOUNT; i++) {
            surface.setColor(Color.BLACK);
            surface.drawLine((int) lines[i].start().getX(), (int) lines[i].start().getY(),
                            (int) lines[i].end().getX(), (int) lines[i].end().getY());
            surface.setColor(Color.BLUE);
            surface.fillCircle((int) lines[i].middle().getX(), (int) lines[i].middle().getY(), CIRCLERADIUS);
        }
        surface.setColor(Color.RED);
        for (int i = 0; i < LINECOUNT; i++) {
            for (int j = i + 1; j < LINECOUNT; j++) {
                Point p = lines[i].intersectionWith(lines[j]);
                if (p != null) {
                    surface.fillCircle((int) p.getX(), (int) p.getY(), CIRCLERADIUS);
                }
            }
        }
        gui.show(surface);
    }

    /**
     * Main function, initialize program.
     * @param args args from terminal.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawAbstractArt();
    }
}
