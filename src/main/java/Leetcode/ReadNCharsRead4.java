package Leetcode;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * https://leetcode.com/problems/read-n-characters-given-read4/#/description
 *
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class ReadNCharsRead4 {

    private char[] buf4 = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {

        int ptr = 0;
        int bufPtr = 0, buffTotal = 0;

        while(ptr < n) {
            if(bufPtr == 0) {
                buffTotal = read4(buf4);
            }

            if(buffTotal == 0) {
                break;
            }

            while(ptr < n && bufPtr < buffTotal) {
                buf[ptr++] = buf4[bufPtr++];
            }

            if(bufPtr >= buffTotal) {
                bufPtr = 0;
            }
        }

        return ptr;
    }

    private int read4(char[] buff) {
        throw new NotImplementedException();
    }

}
