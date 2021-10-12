/**
 * CreateHypernymDatabase class.
 * Deals with given corpus folder and an export file path.
 * Create a databse of Hypernym and their relations.
 * Export it in the end to a file.
 */
public class CreateHypernymDatabase {

    /**
     * main function. given args from user, compile a database and export it to a file.
     * @param args should contains the corpus folder path, and export file path.
     */
    public static void main(String[] args) {

        HypernymDatabase hypernymDatabase = new HypernymDatabase();
        String corpusPath, exportPath;
        // check we got two path in given args, and extract them to a separated variables.
        if (args.length != 2) {
            System.out.println("Not enough args given.");
            return;
        } else {
            corpusPath = args[0];
            exportPath = args[1];
        }
        hypernymDatabase.compileDatabase(corpusPath);
        hypernymDatabase.saveFile(exportPath);
    }
}
