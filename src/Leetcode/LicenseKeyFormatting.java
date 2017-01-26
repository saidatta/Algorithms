package Leetcode;

/**
 * Created by venkatamunnangi on 1/25/17.
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        if(K <= 0 || S == null || S.isEmpty()) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(S.replaceAll("-","").toUpperCase());
        for(int i= stringBuilder.length() - (K); i>0;i-=K) {
            stringBuilder.insert(i, "-");
        }
        return stringBuilder.toString();
    }
}
