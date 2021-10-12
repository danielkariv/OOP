/**
 * BlockRemover class. deals with removing blocks when hitted, and counting remain blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel game
     * @param removedBlocks counter for remaining blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * called when blocks are getting hit.
     * the method is removing the block from game, and updating the counter.
     * @param beingHit the object getting hit.
     * @param hitter the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}