package Leetcode;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/#/description
 *
 * The read4 API is defined in the parent class Reader4.
 * int read4(char[] buf);
 *
 * Created by venkatamunnangi on 3/27/17.
 */

public class ReadNCharsRead4II {

    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) {
                break;
            }
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) {
                buffPtr = 0;
            }
        }
        return ptr;
    }

    int read4(char[] buf) {
        throw new NotImplementedException();
    }
}
