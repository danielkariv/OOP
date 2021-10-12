/**
 * HitListener interface.
 */
public interface HitListener {
    /**
     * call whenever the beingHit object (Block) is hit.
     * @param beingHit the object getting hit.
     * @param hitter the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
