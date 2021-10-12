import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter scoreCounter;
    private GUI gui;

    /**
     * Constructor.
     */
    public GameFlow() {
        gui  = new GUI("Game", 800, 600);
        animationRunner = new AnimationRunner(gui);
        keyboardSensor = gui.getKeyboardSensor();
        scoreCounter = new Counter();
    }

    /**
     * Runs levels one after another, till the game is ending.
     * @param levels given list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean didWin = true;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner,
                    this.scoreCounter);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (!level.hasBallsLeft()) {
                didWin = false;
                break;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                            new GameoverAnimation(keyboardSensor, didWin, scoreCounter.getValue())));
        gui.close();
    }
}
