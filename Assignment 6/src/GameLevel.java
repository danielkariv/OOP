import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.List;

/**
 * Game object. The main class that deals with initializing, updating and drawing objects.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private boolean isFailed;

    /**
     * Constructor.
     * @param levelInformation level info.
     * @param keyboardSensor keyboard from GUI object.
     * @param ar animation runner.
     * @param scoreCounter score counter.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner ar, Counter scoreCounter) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboardSensor;
        this.runner = ar;
        this.scoreCounter = scoreCounter;

    }

    /**
     * add new collidable.
     * @param c new collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add new sprite.
     * @param s new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game.
     */
    public void initialize() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();



        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);

        initLevel();
        running = true;
    }
    /**
     * Run the game.
     */
    public void run() {

        this.isFailed = false;
        this.running = true;

        this.runner.run(new CountdownAnimation(3, 3));

        this.runner.run(this);
    }

    /**
     * remove given Collidable object from GameEnvironment.
     * @param c object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove given Sprite object from SpriteCollection.
     * @param s object to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.drawText(75, 16, "Lives : " + remainedBalls.getValue(), 16);
        d.drawText(350, 16, "Scores : " + scoreCounter.getValue(), 16);
        d.drawText(550, 16, "Level Name : " + levelInformation.levelName(), 16);
        if (remainedBlocks.getValue() == 0) {
            scoreCounter.increase(100);
            this.running = false;
        } else if (remainedBalls.getValue() == 0) {
            this.running = false;
        }
        if (keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                            new PauseScreen(keyboard)));
        }
    }

    /**
     * initialize a level based on given level data when constructing the level.
     */
    public void initLevel() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        remainedBlocks = new Counter();
        remainedBalls = new Counter();

        if (levelInformation.getBackground() != null) {
            sprites.addSprite(levelInformation.getBackground());
        }
        List<Point> ballsPos = levelInformation.initialBalls();
        List<Velocity> ballsVelocity = levelInformation.initialBallVelocities();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(ballsPos.get(i), 5, Color.white, this.environment);
            ball.setVelocity(ballsVelocity.get(i));
            ball.addToGame(this);
            remainedBalls.increase(1);
        }

        Block blockLeft = new Block(-100, 0, 110, 600, Color.gray);
        blockLeft.addToGame(this);
        Block blockRight = new Block(800 - 10, 0, 100, 600, Color.gray);
        blockRight.addToGame(this);
        Block blockTop = new Block(0, -100, 800, 120, Color.gray);
        blockTop.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, remainedBalls);
        Block deathRegion = new Block(0, 600, 800, 100, Color.gray);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        PrintingHitListener phl = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, remainedBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        List<Block> blocks = levelInformation.blocks();
        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = blocks.get(i);
            block.addToGame(this);
            block.addHitListener(phl);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            remainedBlocks.increase(1);
        }

        Paddle paddle = new Paddle(400 - levelInformation.paddleWidth() / 2, 550,
                levelInformation.paddleWidth(), 20, keyboard);
        paddle.setSpeed(levelInformation.paddleSpeed());
        paddle.addToGame(this);

    }

    /**
     * return if there are balls left.
     * @return true if there are balls left.
     */
    public boolean hasBallsLeft() {
        return remainedBalls.getValue() != 0;
    }
}