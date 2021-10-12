import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Sleeper sleeper = new Sleeper();
    private double numOfSeconds;
    private int counter;

    /**
     * Constructor.
     * @param numOfSeconds how long to run.
     * @param countFrom from what number to start.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom) {
        this.numOfSeconds = numOfSeconds;
        this.counter = countFrom;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + counter, 32);
        sleeper.sleepFor(1000);
        counter -= 1;
        if (counter <= 0) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}