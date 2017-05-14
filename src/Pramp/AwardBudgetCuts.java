package Pramp;

/**
 * Created by venkatamunnangi on 5/8/17.
 */
public class AwardBudgetCuts {

    public static void main(String... args) {
        int aa = 2;
        if (xx(aa)) {
            System.out.println("HELLO");
        }
    }

    public static boolean xx(int aa) {
        if (aa % 2 == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }
}
