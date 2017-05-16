package Leetcode.Design;

import java.util.*;

/**
 * https://leetcode.com/problems/logger-rate-limiter/#/description
 * <p>
 * Created by venkatamunnangi on 5/15/17.
 */
public class LoggerRateLimiter {

    /**
     * I keep a heap to get rid of the old message and set of String to keep the recent messages only.
     * This approach would make sense when the number of logs within 10 minutes time window is not too large and
     * when we have lots of different messages.
     */
    PriorityQueue<Log> recentLogs;
    Set<String> recentMessages;

    /**
     * Initialize your data structure here.
     */
    public LoggerRateLimiter() {
        recentLogs = new PriorityQueue<>(10, (l1, l2) -> l1.timestamp - l2.timestamp);
        recentMessages = new HashSet<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!recentLogs.isEmpty()) {
            Log log = recentLogs.peek();
            // discard the logs older than 10 minutes
            if (timestamp - log.timestamp >= 10) {
                recentLogs.poll();
                recentMessages.remove(log.message);
            } else {
                break;
            }
        }
        boolean resultStatus = !recentMessages.contains(message);
        if (resultStatus) {
            recentLogs.add(new Log(timestamp, message));
            recentMessages.add(message);
        }
        return resultStatus;
    }

    class Log {
        int timestamp;
        String message;

        public Log(int aTimestamp, String aMessage) {
            timestamp = aTimestamp;
            message = aMessage;
        }
    }

//    /***
//     * A typical (accepted) solution is to keep a hash map of String that maps
//     * to the recent time stamp.
//     * But this way, it needs to keep the record of the entire messages, even when the
//     * message is rare.
//     */
//    Map<String, Integer> dict;
//
//    /** Initialize your data structure here. */
//    public LoggerRateLimiter() {
//        dict = new HashMap<>();
//    }
//
//    /** Returns true if the message should be printed in the given timestamp,
//     * otherwise returns false.
//     If this method returns false, the message will not be printed.
//     The timestamp is in seconds granularity. */
//    public boolean shouldPrintMessage(int timestamp, String message) {
//        if(timestamp < dict.getOrDefault(message, 0)) {
//            return false;
//        }
//
//        dict.put(message, timestamp+10);
//        return true;
//    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */