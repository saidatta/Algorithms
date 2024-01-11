package leetcode.array.counting;

// https://leetcode.com/problems/number-of-laser-beams-in-a-bank
public class NumBeamsBank {
    public int numberOfBeams(String[] bank) {
        int totalBeams = 0;
        int prevDeviceCount = 0;  // Count of devices in the previous row with devices

        for (String row : bank) {
            int deviceCount = 0;  // Count devices in the current row
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == '1') {
                    deviceCount++;
                }
            }

            // If the current row has devices and the previous row had devices
            if (deviceCount > 0 && prevDeviceCount > 0) {
                // Calculate the beams between these rows
                totalBeams += deviceCount * prevDeviceCount;
            }

            // Update prevDeviceCount if the current row has devices
            if (deviceCount > 0) {
                prevDeviceCount = deviceCount;
            }
        }

        return totalBeams;
    }
}
