/**
 * @author Daniel Kariv.
 * Ass3Game class. Where the game starts.
 */
public class Ass3Game {

    /**
     * Main function.
     * @param args args given from calling the program.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
