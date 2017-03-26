package Leetcode;

import static java.lang.System.out;

/**
 * Created by venkatamunnangi on 3/25/17.
 */
public class IntegerToEnglishWords {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        int numToParse = num;
        if(numToParse == 0) {
            return "Zero";
        }

        StringBuilder englishTranslatedNumber = new StringBuilder();
        int i = 0;

        while(numToParse > 0) {

            if(numToParse % 1000 != 0) {
                englishTranslatedNumber.insert(0, constructNumberTranslation(numToParse % 1000)+ THOUSANDS[i]+" ");
            }

            numToParse /= 1000;
            i++;
        }

        return englishTranslatedNumber.toString().trim();
    }

    private String constructNumberTranslation(int num) {
        if(num == 0) {
            return "";
        }

        if(num < 20) {
            return LESS_THAN_20[num]+" ";
        } else if (num < 100) {
            return TENS[num / 10] +" "+ constructNumberTranslation(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred "+ constructNumberTranslation(num % 100);
        }
    }

    public static void main(String [] args) {
        IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();
        out.println(integerToEnglishWords.constructNumberTranslation(1001));
    }

}
