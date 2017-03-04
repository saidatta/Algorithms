package CCI.Chp1;

/**
 * CCI algo doesnt succeed in case of cab and ab
 *
 *
 * Created by venkatamunnangi on 1/23/17.
 */
public class OneAway {
    public boolean isOneEditAway(String s, String t) {
        if(s == null || t == null) {
            return false;
        }

        for(int i = 0; i< Math.min(s.length(), t.length()); i++) {
            if(s.charAt(i) != t.charAt(i)) {
                if(s.length() == t.length()) {
                    return s.substring(i+1).equals(t.substring(i+1));
                } else if(s.length() > t.length()) {
                    return s.substring(i+1).equals(t.substring(i));
                } else {
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }

        return Math.abs(s.length() - t.length()) == 1;
    }
    public static void main(String [] args) {
        OneAway oa = new OneAway();

        System.out.println(oa.isOneEditAway("cab", "ab"));
    }

}
