/**
 * @author Daniel Kariv
 * Task 1: SumDiv ( print numbers that divided by given args, and in range of given arg)
 */
public class SumDiv {
    static final int ARGSCOUNT = 3;
    static final int MINRANGE = 1;
    static final String INVALIDMSG = "Invalid input";
    /**
     * Main function of task 1 : SumDiv
     * receiving 3 positive numbers ( a,b,c).
     * print the numbers range from 1 to 'a' (includes) that divided by 'b' or 'c'.
     * @param args args given by program call.
     */
    public static void main(String[] args) {
        // receive 3 args which represent 'a,b,c'.
        int a = 0, b = 0, c = 0;
        // check if args count is correct.
        if (args.length != ARGSCOUNT) {
            System.out.println(INVALIDMSG);
            return;
        } else {
            // parse the numbers (String->int), if failed, input is invalid.
            try {
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
                c = Integer.parseInt(args[2]);
            } catch (NumberFormatException exception) {
                System.out.println(INVALIDMSG);
                return;
            }
        }
        // check if the given numbers are positive.
        if (a > 0 && b > 0 && c > 0) {
            int sum = 0;
            // run over all numbers from 1 to 'a' (includes)
            for (int i = MINRANGE; i <= a; i++) {
                if (i % b == 0 || i % c == 0) {
                    System.out.println(i);
                    sum += i;
                }
            }
            System.out.println(sum);
        } else {
            System.out.println(INVALIDMSG);
            return;
        }
    }
}
