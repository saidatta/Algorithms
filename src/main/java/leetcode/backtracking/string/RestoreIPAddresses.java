package leetcode.backtracking.string;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/
// The task is to restore all possible valid IP addresses from a string
public class RestoreIPAddresses {
    // Main method to restore IP addresses from the input string
    public List<String> restoreIpAddresses(String input) {
        List<String> validIPs = new ArrayList<>();
        backtrackForValidIPs(input, 0, new ArrayList<>(), validIPs);
        return validIPs;
    }

    // Helper method that backtracks and constructs IPs using dots
    private void backtrackForValidIPs(String input, int startIndex, List<Integer> segmentLengths, List<String> validIPs) {
        // Calculate the remaining characters to be processed
        final int remainingChars = input.length() - startIndex;
        // Calculate the remaining segments required to form the IP
        final int remainingSegments = 4 - segmentLengths.size();

        // Check if forming an IP is possible given the constraints
        if (remainingChars > remainingSegments * 3 || remainingChars < remainingSegments) {
            return;
        }

        // If we have found three segments, the last segment is the remaining input
        if (segmentLengths.size() == 3) {
            if (isValidSegment(input, startIndex, remainingChars)) {
                StringBuilder ip = new StringBuilder();
                int lastSegmentEnd = 0;
                for (Integer length : segmentLengths) {
                    ip.append(input, lastSegmentEnd, lastSegmentEnd + length).append('.');
                    lastSegmentEnd += length;
                }
                ip.append(input.substring(startIndex));
                validIPs.add(ip.toString());
            }
            return;
        }

        // Explore the possibilities of segment lengths (1, 2, or 3)
        for (int length = 1; length <= 3 && length <= remainingChars; ++length) {
            segmentLengths.add(length);
            if (isValidSegment(input, startIndex, length)) {
                backtrackForValidIPs(input, startIndex + length, segmentLengths, validIPs);
            }
            // Backtrack by removing the last segment length
            segmentLengths.remove(segmentLengths.size() - 1);
        }
    }

    // Validates whether a segment of the string is a valid IP segment
    private boolean isValidSegment(String s, int start, int length) {
        // Single-digit segments are always valid
        if (length == 1) return true;
        // Segments shouldn't start with 0
        if (s.charAt(start) == '0') return false;
        // For 3-digit segments, the value should be <= 255
        return length != 3 || s.substring(start, start + length).compareTo("255") <= 0;
    }

    public static void main(String[] args) {
        RestoreIPAddresses generator = new RestoreIPAddresses();
//        List<String> ips = generator.generate("25525511135");
//        System.out.println(ips);
    }
}
