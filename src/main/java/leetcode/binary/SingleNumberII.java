package leetcode.binary;

// https://leetcode.com/problems/single-number-ii/
public class SingleNumberII {
    public int singleNumber(int... nums) {
        // This will hold the unique number in the end
        int uniqueNumber = 0;

        // Iterate over each bit position (0 to 31) for a 32-bit integer
        for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
            int bitCount = 0; // Count the number of times a bit is set in this position
            int mask = (1 << bitPosition); // Create a mask to isolate the bit at bitPosition

            // Iterate through each number in the array
            for (int num : nums) {
                // If the bit at bitPosition is set in num, increase the bitCount
                if ((num & mask) != 0) {
                    bitCount++;
                }
            }

            // If the count of this bit is not a multiple of 3,
            // it means this bit is set in the unique number.
            if (bitCount % 3 != 0) {
                uniqueNumber |= mask;
            }
        }

        return uniqueNumber;
    }

    public static void main(String [] args) {
        SingleNumberII singleNumberII = new SingleNumberII();
        System.out.println(singleNumberII.singleNumber(2,2,3,2));
    }
}

/*
 * Explanation:
 * The algorithm works by checking each bit position across all numbers.
 * If a bit is set in a position that is not divisible by 3, it must belong to the unique number,
 * because each other number appears exactly three times and thus will contribute either 0 or 3
 * set bits in each position.
 * We use bitwise AND (&) to check if a bit is set and bitwise OR (|=) to set the corresponding bit in the result.
 */
