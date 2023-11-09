package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// https://leetcode.com/problems/exclusive-time-of-functions/solutions/153497/java-solution-using-stack-wrapper-class-and-calculation-when-pop-element-from-the-stack/
class ExclusiveFunctionCalls {


    // Parses a list of log strings to calculate the exclusive time spent in each function.
    public int[] exclusiveTime(int n, List<String> logs) {
        // Stack to keep track of function call start times
        Deque<Log> callStack = new ArrayDeque<>();
        // Array to hold the exclusive times of each function
        int[] result = new int[n];

        for (String content : logs) {
            // Parse the log string into a Log object
            Log log = new Log(content);
            if (log.isStart) {
                // If log is a start, push it onto the callStack
                callStack.push(log);
            } else {
                // If log is an end, pop the start log from the callStack
                Log startLog = callStack.pop();
                // Calculate the duration of the function call
                int duration = log.time - startLog.time + 1;
                // Add the duration to the function's exclusive time
                result[startLog.id] += duration;

                // If there's a function currently being executed, subtract the duration from its exclusive time
                if (!callStack.isEmpty()) {
                    result[callStack.peek().id] -= duration;
                }
            }
        }

        // Return the array containing exclusive times
        return result;
    }

    // Log class represents a parsed log entry
    public static class Log {
        public int id; // Function ID from the log
        public boolean isStart; // Flag indicating if the log is for a start or an end
        public int time; // Timestamp from the log

        // Constructor parses a single log string and initializes the Log object
        public Log(String content) {
            String[] parts = content.split(":");
            id = Integer.parseInt(parts[0]); // Parse the function ID
            isStart = "start".equals(parts[1]); // Determine if it's a start or end log
            time = Integer.parseInt(parts[2]); // Parse the timestamp
        }
    }

    // Main method for execution
    public static void main(String[] args) {
        ExclusiveFunctionCalls solver = new ExclusiveFunctionCalls();
        // Example log list
        List<String> logs = List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        // Calculate exclusive times
        int[] times = solver.exclusiveTime(2, logs);
        // Print exclusive times
        for (int i = 0; i < times.length; i++) {
            System.out.println("Function " + i + ": " + times[i] + " units");
        }
    }
}
