package Int;

/**
 * Created by venkatamunnangi on 02/11/16.
 */
public class IsUnique {
    public boolean isUnique(String req) {
        int checker = 0;
        for (int i = 0;i <req.length();i++) {
            int val = req.charAt(i) - 'a';
            if((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= val;
        }
        return false;
    }
}
