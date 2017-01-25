package CCI.Chp1;

/**
 * Created by venkatamunnangi on 1/23/17.
 */
public class OneAway {
    public boolean isOneEditAway(String corr, String rep){

        if(Math.abs(corr.length() - rep.length()) > 1) {
            return false;
        }

        boolean hasEdited = false;


        if(corr.length() == rep.length()) { // replace a char
            for(int i = 0; i< corr.length();i++) {
                if(corr.charAt(i) != rep.charAt(i)) {
                    if(hasEdited) {
                        return false;
                    }
                    hasEdited = true;
                }
            }
        } else if(corr.length() > rep.length()) { // insert a char
            for (int i = 0; i < rep.length(); i++) {
                if (corr.charAt(i) != rep.charAt(i)) {
                    if (hasEdited) {
                        return false;
                    }
                    hasEdited = true;
                }
            }
        } else {
            for (int i = 0; i < corr.length(); i++) {
                if (corr.charAt(i) != rep.charAt(i)) {
                    if (hasEdited) {
                        return false;
                    }
                    hasEdited = true;
                }
            }
        }
        return true;
    }
}
