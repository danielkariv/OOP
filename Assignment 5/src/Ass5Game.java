/**
 * @author Daniel Kariv.
 * Ass5Game class. Where the game starts.
 */
public class Ass5Game {

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
