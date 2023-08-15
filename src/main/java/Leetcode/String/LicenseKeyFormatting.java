package Leetcode.String;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/license-key-formatting/#/description
 *
 * Created by venkatamunnangi on 1/25/17.
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String targetString, int K) {
        if (K <= 0 || targetString == null || targetString.isEmpty()) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(targetString.replaceAll("-", "").toUpperCase());
        for (int i = stringBuilder.length() - K; i > 0; i -= K) {
            stringBuilder.insert(i, "-");
        }
        return stringBuilder.toString();
    }

    public String licenseKeyFormatting2(String inputString, int groupSize) {
        StringBuilder formattedKey = new StringBuilder();
        char[] inputChars = inputString.toCharArray();

        // Counter for characters added in current group
        int charCount = 0;

        // Process characters from the end
        for (int i = inputString.length()-1; i >= 0;) {
            // Add characters until group is filled
            if (charCount < groupSize) {
                // Skip dashes
                if (inputChars[i] != '-') {
                    // Convert lowercase to uppercase
                    if (inputChars[i] >= 'a' && inputChars[i] <= 'z') {
                        formattedKey.append(Character.toUpperCase(inputChars[i]));
                    } else {
                        // Add character as is
                        formattedKey.append(inputChars[i]);
                    }
                    charCount++;
                }
                i--;
            } else { // Reset counter and append a dash
                charCount = 0;
                formattedKey.append('-');
            }
        }
        // Remove trailing dash if it exists
        removeTrailingDash(formattedKey);

        // The built string is in reverse, so reverse it back
        return formattedKey.reverse().toString();
    }

    // Helper function to remove trailing dash
    private void removeTrailingDash(StringBuilder sb) {
        int lastIndex = sb.length() - 1;
        if (lastIndex >= 0 && sb.charAt(lastIndex) == '-') {
            sb.deleteCharAt(lastIndex);
        }
    }

    public String licenseKeyFormatting3(String inputString, int groupSize) {
        String cleanString = inputString.replace("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder();

        int firstGroupSize = cleanString.length() % groupSize;
        int fullGroupCount = cleanString.length() / groupSize;

        if (firstGroupSize > 0) {
            sb.append(cleanString, 0, firstGroupSize);
            if (fullGroupCount > 0) {
                sb.append('-');
            }
        }

        IntStream.iterate(firstGroupSize, i -> i + groupSize)
                .limit(fullGroupCount)
                .forEach(i -> {
                    sb.append(cleanString, i, i + groupSize);
                    sb.append('-');
                });

        // Remove the trailing dash if it exists
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }



    public static void main(String... args) {
        LicenseKeyFormatting licenseKeyFormatting = new LicenseKeyFormatting();

        System.out.println(licenseKeyFormatting.licenseKeyFormatting("2-4A0r7-4k", 4));
        //Output: "24A0-R74K"
        System.out.println(licenseKeyFormatting.licenseKeyFormatting("2-4A0r7-4k", 3));
        //Output: "24-A0R-74K"
    }
}
