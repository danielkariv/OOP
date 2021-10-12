/**
 * @author Daniel Kariv
 * Task 2: Fermat ( print pythagorean from given args)
 */
public class Fermat {
    static final int ARGSCOUNT = 2;
    static final int MINRANGE = 1;
    static final String INVALIDMSG = "Invalid input";
    /**
     * Main function of task 2 : Fermat
     * receiving 2 positive numbers ( n,range).
     * print all “a,b,c” when (Pythagorean) a^n + b^n = c^n is respected ( when a,b,c are between 1 to 'range').
     * if no equation is found, program prints "no".
     * @param args args given by program call.
     */
    public static void main(String[] args) {
        // receive 2 args which represent 'n,range'.
        int n = 0, range = 0;
        // check if args count is correct.
        if (args.length != ARGSCOUNT) {
            System.out.println(INVALIDMSG);
            return;
        } else {
            // parse the numbers (String->int), if failed, input is invalid.
            try {
                n = Integer.parseInt(args[0]);
                range = Integer.parseInt(args[1]);
            } catch (NumberFormatException exception) {
                System.out.println(INVALIDMSG);
                return;
            }
        }
        boolean isPrintOnce = false;
        // check if 'n','range' are positive numbers.
        if (n > 0 && range > 0) {
            // run over all numbers between 2 and 'range'.
            for (int a = MINRANGE; a < range; a++) {
                for (int b = a; b < range; b++) {
                    for (int c = b; c < range; c++) {
                        // calculate power of 'a','b','c' by 'n'.
                        int an = (int) Math.pow(a, n);
                        int bn = (int) Math.pow(b, n);
                        int cn = (int) Math.pow(c, n);
                        // print the values of 'a','b','c' if Pythagorean equation respected.
                        if (an + bn == cn) {
                            isPrintOnce = true;
                            System.out.println(a + "," + b + "," + c);
                        }
                    }
                }
            }
            // if it didn't find even one equation, print 'no'.
            if (!isPrintOnce) {
                System.out.println("no");
            }
        } else {
            System.out.println(INVALIDMSG);
            return;
        }
    }
}
