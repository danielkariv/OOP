import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * GameoverAnimation class.
 */
public class GameoverAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean didWin;
    private int score;

    /**
     * Constructor.
     * @param k keyboard given from GUI object.
     * @param didWin status if the player win/lose.
     * @param score total score amount.
     */
    public GameoverAnimation(KeyboardSensor k, boolean didWin, int score) {
        this.keyboard = k;
        this.stop = false;
        this.didWin = didWin;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.didWin) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is  " + this.score, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}