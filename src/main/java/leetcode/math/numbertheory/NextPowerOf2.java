package leetcode.math.numbertheory;

public class NextPowerOf2 {

    public int nextPowerOf2(int num) {
        // If the number is 0, the next power of 2 is 1.
        if (num == 0) {
            return 1;
        }

        // If the number is a power of 2, then the number AND (number - 1) will be zero.
        // E.g. 8 (1000 in binary) & 7 (0111 in binary) = 0
        // So, if the number is already a power of 2, just return the number itself.
        if (num > 0 && (num & (num - 1)) == 0) {
            return num;
        }

        // If the number is not a power of 2, then keep resetting the rightmost set bit
        // until the number becomes a power of 2.
        // E.g. for num = 5 (101 in binary),
        //      1st iteration: 5 & 4 = 4 (100 in binary)
        // As the condition of the loop says, if (number AND (number - 1)) is not zero,
        // continue resetting the rightmost set bit.
        while ((num & (num - 1)) > 0) {
            num &= (num - 1);
        }

        // After the above loop, 'num' will be the nearest power of 2 which is less than
        // the given number. To get the next power of 2, we simply left-shift the number by 1.
        // E.g. for num = 4 (100 in binary), left shift will make it 1000 in binary which is 8.
        return num << 1;
    }

    public int nextPowerOf2Negative(int num) {
        // Convert the number to positive if it's negative.
        if (num < 0) {
            num = -num;
        }

        // If the number is 0, the next power of 2 is 1.
        if (num == 0) {
            return 1;
        }

        // If the number is a power of 2, then the number AND (number - 1) will be zero.
        if (num > 0 && (num & (num - 1)) == 0) {
            return num;
        }

        // Keep resetting the rightmost set bit until the number becomes a power of 2.
        while ((num & (num - 1)) > 0) {
            num &= (num - 1);
        }

        // Left shift the number to get the next power of 2.
        return num << 1;
    }


    public static void main(String[] args) {
        NextPowerOf2 np = new NextPowerOf2();
        System.out.println(np.nextPowerOf2(5));  // This will print 8, the next power of 2 after 5.
        System.out.println(np.nextPowerOf2Negative(5));  // This will print 8, the next power of 2 after 5.
        System.out.println(np.nextPowerOf2Negative(-5));  // This will print 8, the next power of 2 after 5.
    }
}
