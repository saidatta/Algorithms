package leetcode.math.string;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/integer-to-english-words/#/description
 *
 * If the primary goal is maintainability and ease of changing the language, the Abstract Factory design pattern is
 * well-suited.
 *
 * Created by venkatamunnangi on 3/25/17.
 */
interface NumberWordFactory {
    String[] getLessThan20();

    String[] getTens();

    String[] getThousands();
}

class EnglishNumberWordFactory implements NumberWordFactory {

    private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};

    private static final String[] TENS =
            {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    @Override
    public String[] getLessThan20() {
        return LESS_THAN_20;
    }

    @Override
    public String[] getTens() {
        return TENS;
    }

    @Override
    public String[] getThousands() {
        return THOUSANDS;
    }
}

public class IntegerToEnglishWords {

    private final NumberWordFactory factory;

    public IntegerToEnglishWords(NumberWordFactory factory) {
        this.factory = factory;
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder englishTranslatedNumber = new StringBuilder();
        int i = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                String temp = constructNumberTranslation(num % 1000) + factory.getThousands()[i] + " ";
                englishTranslatedNumber.insert(0, temp);
            }

            num /= 1000;
            i++;
        }

        return englishTranslatedNumber.toString().trim();
    }

    private String constructNumberTranslation(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return factory.getLessThan20()[num] + " ";
        } else if (num < 100) {
            return factory.getTens()[num / 10] + " " + constructNumberTranslation(num % 10);
        } else {
            return factory.getLessThan20()[num / 100] + " Hundred " + constructNumberTranslation(num % 100);
        }
    }

    public static void main(String... args) {
        NumberWordFactory factory = new EnglishNumberWordFactory();
        IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords(factory);
        out.println(integerToEnglishWords.numberToWords(1_234_567_890));
    }
}
