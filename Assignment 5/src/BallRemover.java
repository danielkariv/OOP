/**
 * BallRemover class. deals with removing balls when reach bottom, and counting remain blocks.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game game
     * @param removedBlocks counter for remaining blocks.
     */
    public BallRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * called when blocks are getting hit.
     * the method is removing the block from game, and updating the counter.
     * @param beingHit the object getting hit.
     * @param hitter the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}