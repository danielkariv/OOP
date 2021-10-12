import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Kariv.
 * Ass5Game class. Where the game starts.
 */
public class Ass6Game {

    /**
     * Main function.
     * @param args args given from calling the program.
     */
    public static void main(String[] args) {
        List<LevelInformation> levelInformations = new ArrayList<>();
        levelInformations.add(new DirectHitLevel());
        levelInformations.add(new WideEasyLevel());
        levelInformations.add(new GreenLevel());
        levelInformations.add(new FinalFourLevel());

        GameFlow gf = new GameFlow();
        gf.runLevels(levelInformations);

    }
}
