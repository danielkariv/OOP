/**
 * DiscoverHypernym class.
 * Deals with getting a corpus folder and lemma and output the hypernyms with relation to the lemma.
 */
public class DiscoverHypernym {

    /**
     * main function, called by user with args.
     * @param args should contains the corpus path, and a lemma.
     */
    public static void main(String[] args) {
        HypernymDatabase hypernymDatabase = new HypernymDatabase();
        String corpusPath, lemma;
        // check we got two path in given args, and extract them to a separated variables.
        if (args.length != 2) {
            System.out.println("Not enough args given.");
            return;
        } else {
            corpusPath = args[0];
            lemma = args[1];
        }
        hypernymDatabase.compileDatabase(corpusPath);
        hypernymDatabase.searchLemma(lemma);
    }
}
