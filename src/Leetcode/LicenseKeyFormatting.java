package Leetcode;

/**
 * https://leetcode.com/problems/license-key-formatting/#/description
 *
 * Created by venkatamunnangi on 1/25/17.
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String targetString, int K) {
        if(K <= 0 || targetString == null || targetString.isEmpty()) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(targetString.replaceAll("-","").toUpperCase());
        for(int i = stringBuilder.length() - K; i>0; i-=K) {
            stringBuilder.insert(i, "-");
        }
        return stringBuilder.toString();
    }

    public static void main(String... args) {
        LicenseKeyFormatting licenseKeyFormatting = new LicenseKeyFormatting();

        System.out.println(licenseKeyFormatting.licenseKeyFormatting("2-4A0r7-4k", 4));
        //Output: "24A0-R74K"
        System.out.println(licenseKeyFormatting.licenseKeyFormatting("2-4A0r7-4k", 3));
        //Output: "24-A0R-74K"
    }
}
