import java.util.List;

/**
 * LevelInformation class.
 */
public interface LevelInformation {
    /**
     * initialize balls data.
     * @return list of Point for balls.
     */
    List<Point> initialBalls();

    /**
     * get number of balls the level has.
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * initialize balls velocity data.
     * @return list velocity balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * get paddle speed.
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * get paddle width.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * get the name of the level.
     * @return name of level.
     */
    String levelName();

    /**
     * get background sprite.
     * @return background sprite.
     */
    Sprite getBackground();

    /**
     * get blocks data which has size,color, and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * get number of blocks in the level.
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
}