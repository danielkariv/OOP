import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Game object. The main class that deals with initializing, updating and drawing objects.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter scoreCounter;
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
        gui  = new GUI("Game", 800, 600);
        remainedBlocks = new Counter();
        remainedBalls = new Counter();
        scoreCounter = new Counter();

        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(new Point(300, 400), 5, Color.CYAN, this.environment);
            ball.setVelocity(new Velocity(2 + i, 4 - i));
            ball.addToGame(this);
            remainedBalls.increase(1);
        }

        Block blockLeft = new Block(0, 0, 10, 600, Color.gray);
        blockLeft.addToGame(this);
        Block blockRight = new Block(800 - 10, 0, 10, 600, Color.gray);
        blockRight.addToGame(this);
        Block blockTop = new Block(0, 0, 800, 20, Color.gray);
        blockTop.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, remainedBalls);
        Block deathRegion = new Block(0, 600, 800, 100, Color.gray);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        Color[] colors = {Color.red, Color.green, Color.orange, Color.blue, Color.pink, Color.cyan};
        PrintingHitListener phl = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, remainedBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 12 - y; x++) {
                Block block = new Block(800 - 10 - 50 * (x + 1), 100 + 30 * y, 50, 30, colors[y]);
                block.addToGame(this);
                block.addHitListener(phl);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                remainedBlocks.increase(1);
            }
        }

        Paddle paddle = new Paddle(350, 550, 80, 20, gui.getKeyboardSensor());
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);
    }
    /**
     * Run the game.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            long startTime = System.currentTimeMillis(); // timing

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            if (remainedBlocks.getValue() == 0) {
                scoreCounter.increase(100);
                gui.close();
                return;
            } else if (remainedBalls.getValue() == 0) {
                gui.close();
                return;
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {

                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
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
}