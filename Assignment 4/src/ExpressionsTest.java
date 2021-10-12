
import java.util.Map;
import java.util.TreeMap;

/**
 * Test class.
 */
public class ExpressionsTest {

    /**
     * Main function.
     * @param args args given when called by system/user.
     */
    public static void main(String[] args) {
        Expression exp = new Xnor(new Xor(new Var("x"), new Var("y")), new Var("z"));
        System.out.println(exp.toString());
        Map<String, Boolean> ass = new TreeMap<>();
        ass.put("x", true);
        ass.put("y", false);
        ass.put("z", true);
        try {
            System.out.println(exp.evaluate(ass));
        } catch (Exception exception) {

        }
        System.out.println(exp.nandify().toString());
        System.out.println(exp.norify().toString());
        System.out.println(exp.simplify().toString());
    }
}
