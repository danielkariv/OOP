import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator class. Prints score base on given counter.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructor.
     * @param scoreCounter score counter;
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.drawText(375, 16, "Score: " + scoreCounter.getValue(), 16);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add score indicator to given game.
     * @param g given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
