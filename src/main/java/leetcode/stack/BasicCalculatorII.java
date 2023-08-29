package leetcode.stack;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/basic-calculator-ii/solutions/
public class BasicCalculatorII {

    public static int calculate(String source) {
        return makeAddEvaluator(makeLexemes(source)).eval();
    }

    enum LexemeType {
        NUMBER, PLUS, MINUS, STAR, SLASH
    }

    static class Lexeme {
        LexemeType type;
        String content;

        public Lexeme(LexemeType type, String content) {
            this.type = type;
            this.content = content;
        }
    }

    private static List<Lexeme> makeLexemes(String source) {
        List<Lexeme> out = new ArrayList<>();
        int i = 0;
        while (i < source.length()) {
            if (source.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(source.charAt(i))) {
                String s = readNumber(source.substring(i));
                out.add(new Lexeme(LexemeType.NUMBER, s));
                i += s.length();
                continue;
            }
            switch (source.charAt(i)) {
                case '+' -> out.add(new Lexeme(LexemeType.PLUS, "+"));
                case '-' -> out.add(new Lexeme(LexemeType.MINUS, "-"));
                case '*' -> out.add(new Lexeme(LexemeType.STAR, "*"));
                case '/' -> out.add(new Lexeme(LexemeType.SLASH, "/"));
                default -> throw new IllegalArgumentException("unreadable source");
            }
            i++;
        }
        return out;
    }

    private static String readNumber(String source) {
        StringBuilder sb = new StringBuilder();
        for (char ch : source.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    interface Evaluator {
        int eval();
    }

    static class MulEvaluator implements Evaluator {
        Evaluator left, right;

        @Override
        public int eval() {
            return left.eval() * right.eval();
        }
    }

    static class DivEvaluator implements Evaluator {
        Evaluator left, right;

        @Override
        public int eval() {
            return left.eval() / right.eval();
        }
    }

    static class AddEvaluator implements Evaluator {
        Evaluator left, right;

        @Override
        public int eval() {
            return left.eval() + right.eval();
        }
    }

    static class SubEvaluator implements Evaluator {
        Evaluator left, right;

        @Override
        public int eval() {
            return left.eval() - right.eval();
        }
    }

    static class NumEvaluator implements Evaluator {
        int number;

        @Override
        public int eval() {
            return number;
        }
    }

    private static Evaluator makeAddEvaluator(List<Lexeme> lexemes) {
        for (int i = lexemes.size() - 1; i >= 0; i--) {
            Lexeme lexeme = lexemes.get(i);
            if (lexeme.type == LexemeType.PLUS) {
                AddEvaluator evaluator = new AddEvaluator();
                evaluator.left = makeAddEvaluator(lexemes.subList(0, i));
                evaluator.right = makeAddEvaluator(lexemes.subList(i + 1, lexemes.size()));
                return evaluator;
            } else if (lexeme.type == LexemeType.MINUS) {
                SubEvaluator evaluator = new SubEvaluator();
                evaluator.left = makeAddEvaluator(lexemes.subList(0, i));
                evaluator.right = makeAddEvaluator(lexemes.subList(i + 1, lexemes.size()));
                return evaluator;
            }
        }
        return makeMulEvaluator(lexemes);
    }

    private static Evaluator makeMulEvaluator(List<Lexeme> lexemes) {
        for (int i = lexemes.size() - 1; i >= 0; i--) {
            Lexeme lexeme = lexemes.get(i);
            if (lexeme.type == LexemeType.STAR) {
                MulEvaluator evaluator = new MulEvaluator();
                evaluator.left = makeMulEvaluator(lexemes.subList(0, i));
                evaluator.right = makeMulEvaluator(lexemes.subList(i + 1, lexemes.size()));
                return evaluator;
            } else if (lexeme.type == LexemeType.SLASH) {
                DivEvaluator evaluator = new DivEvaluator();
                evaluator.left = makeMulEvaluator(lexemes.subList(0, i));
                evaluator.right = makeMulEvaluator(lexemes.subList(i + 1, lexemes.size()));
                return evaluator;
            }
        }
        return makeNumEvaluator(lexemes);
    }

    private static Evaluator makeNumEvaluator(List<Lexeme> lexemes) {
        for (Lexeme lexeme : lexemes) {
            if (lexeme.type == LexemeType.NUMBER) {
                NumEvaluator evaluator = new NumEvaluator();
                evaluator.number = Integer.parseInt(lexeme.content);
                return evaluator;
            }
        }
        throw new IllegalArgumentException("no numLexeme found");
    }

    public static void main(String[] args) {
        BasicCalculatorII calculator = new BasicCalculatorII();
        System.out.println(calculator.calculate("3+2*2"));    // Sample Test: Output 7
        System.out.println(calculator.calculate(" 3/2 "));    // Sample Test: Output 1
        System.out.println(calculator.calculate(" 3+5 / 2 "));// Sample Test: Output 5
    }
}

