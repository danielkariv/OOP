/**
 * ScoreTrackingListener. deals with incressing score based on blocks hitted.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Called when block have been hit by ball.
     * increase score by 5 per block destroyed.
     * @param beingHit the object getting hit.
     * @param hitter the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}