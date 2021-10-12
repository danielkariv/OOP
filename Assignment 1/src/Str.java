/**
 * @author Daniel Kariv
 * Task 3: Str ( print groups of words based on if start by given query or include it)
 */
public class Str {
    static final int ARGSCOUNT = 2;
    static final String INVALIDMSG = "Invalid input";
    /**
     * Main function of task 3 : Str
     * receiving 2 strings ( query,sequence).
     * sequence is a list of words separated by '_' and query is any string.
     * prints the words in two groups:
     *      The words that start with the query.
     *      All words that include the query.
     * each group of words printed in the order they appear in the sequence.
     * @param args args given by program call.
     */
    public static void main(String[] args) {
        // receive 2 args which represent 'query,sequence'
        String query, sequence;
        if (args.length != ARGSCOUNT) {
            System.out.println(INVALIDMSG);
            return;
        } else {
            query = args[0];
            sequence = args[1];
        }
        // get array of words from sequence (separated by '_' ).
        String[] array = sequence.split("_");
        // print words that start with the query.
        for (int i = 0; i < array.length; i++) {
            if (array[i].startsWith(query)) {
                System.out.println(array[i]);
            }
        }
        // print words that include the query ( not including ones that already printed).
        for (int i = 0; i < array.length; i++) {
            if (array[i].contains(query) && !array[i].startsWith(query)) {
                System.out.println(array[i]);
            }
        }
    }
}
