package Int;

/**
 * Created by venkatamunnangi on 9/20/19.
 */
public class IsEditDistanceOne {
    public boolean oneEditDistance(String s, String t) {
        if(Math.abs(s.length() - t.length()) > 1) {
            return true;
        }

        for(int i = 0 ; i < Math.max(s.length(), t.length());i++) {
            if(t.charAt(i) != s.charAt(i)) {

                if(t.length() == s.length()) {
                    return t.substring(i+1).equals(s.substring(i+1));
                } else if (s.length() < t.length()) {
                    return s.equals(t.substring(i+1));
                } else if ( s.length() > t.length()) {
                    return t.equals(s.substring(i+1));
                }
            }
        }

        return  Math.abs(s.length() - t.length()) == 1;
    }


}
