package leetcode.backtracking.string;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/
// The task is to restore all possible valid IP addresses from a string
public class RestoreIPAddressesBuilder {
    public List<String> restoreIpAddresses(String input) {
        List<String> validIPs = new ArrayList<>();
        backtrackForValidIPs(new IPAddressBuilder(input), 0, validIPs);
        return validIPs;
    }

    private void backtrackForValidIPs(IPAddressBuilder builder, int startIndex, List<String> validIPs) {
        if (!builder.isValidState(startIndex)) {
            return;
        }

        if (builder.build().split("\\.").length == 4) {
            validIPs.add(builder.build());
            return;
        }

        for (int length = 1; length <= 3 && startIndex + length <= builder.getLength(); ++length) {
            if (builder.canAddSegment(startIndex, length)) {
                builder.addSegment(startIndex, length);
                backtrackForValidIPs(builder, startIndex + length, validIPs);
                builder.removeLastSegment();
            }
        }
    }

    static class IPAddressBuilder {

        private final String input;
        private final List<String> segments = new ArrayList<>();

        public IPAddressBuilder(String input) {
            this.input = input;
        }

        public IPAddressBuilder addSegment(int start, int length) {
            this.segments.add(input.substring(start, start + length));
            return this;
        }

        public boolean isValidState(int startIndex) {
            int remainingChars = input.length() - startIndex;
            int remainingSegments = 4 - segments.size();
//            remainingChars <= remainingSegments * 3:
//            This ensures that even if we use the maximum number of
//            characters (3) for each of the remaining segments, we won't run out of characters. For instance,
//            if there are 2 segments remaining, they can take up a maximum of 6 characters.

//            remainingChars >= remainingSegments:
//            This ensures that we have at least 1 character for each of the remaining segments. For instance, if there
//            are 2 segments remaining, we need at least 2 characters.
            return remainingChars <= remainingSegments * 3 && remainingChars >= remainingSegments;
        }

        public boolean canAddSegment(int start, int length) {
            if (length == 1) {
                return true;
            }
            if (input.charAt(start) == '0') {
                return false;
            }
            return length != 3 || input.substring(start, start + length).compareTo("255") <= 0;
        }

        public int getLength() {
            return this.input.length();
        }

        public void removeLastSegment() {
            if (!segments.isEmpty()) {
                segments.remove(segments.size() - 1);
            }
        }

        public String build() {
            return String.join(".", segments);
        }
    }
}

