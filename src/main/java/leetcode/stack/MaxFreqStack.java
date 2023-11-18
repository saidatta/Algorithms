package leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode.com/problems/maximum-frequency-stack/description/
class MaxFreqStack {
    private final Map<Integer, Integer> elementFrequencyMap;
    private final Map<Integer, Stack<Integer>> groupStackMap;
    private int maxFrequency;

    public MaxFreqStack() {
        elementFrequencyMap = new HashMap<>();
        groupStackMap = new HashMap<>();
        maxFrequency = 0;
    }

    public void push(int val) {
        int frequency = elementFrequencyMap.getOrDefault(val, 0) + 1;
        elementFrequencyMap.put(val, frequency);
        maxFrequency = Math.max(maxFrequency, frequency);

        groupStackMap.computeIfAbsent(frequency, k -> new Stack<>()).push(val);
    }

    public int pop() {
        int val = groupStackMap.get(maxFrequency).pop();
        elementFrequencyMap.put(val, elementFrequencyMap.get(val) - 1);

        if (groupStackMap.get(maxFrequency).isEmpty()) {
            maxFrequency--;
        }

        return val;
    }

    public static void main(String[] args) {
        MaxFreqStack freqStack = new MaxFreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println(freqStack.pop()); // Returns 5
        System.out.println(freqStack.pop()); // Returns 7
        System.out.println(freqStack.pop()); // Returns 5
        System.out.println(freqStack.pop()); // Returns 4
    }
}

