package Leetcode.Stack;

/**
 * https://leetcode.com/problems/crawler-log-folder/description/
 */
public class CrawlerLogFolder {
    private static final String GO_BACK = "../";
    private static final String STAY = "./";

    public int minOperations(String[] logs) {
        int countToGoBack = 0;

        for (String op : logs) {
            switch (op) {
                case GO_BACK:
                    if (countToGoBack > 0) {
                        countToGoBack--;
                    }
                    break;
                case STAY:
                    // Do nothing
                    break;
                default:
                    countToGoBack++;
                    break;
            }
        }

        return countToGoBack;
    }
}
