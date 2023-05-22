package Leetcode;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
                // if buffptr is at start of reading or reading 4 characters have been read.
                // re-read it.
                buffTotal = read4(buf4);
            }

            if(buffTotal == 0) {
                // exception as read4 method gave no characters.
                break;
            }

            while(ptr < n && bufPtr < buffTotal) {
                // Translate the buffer.
                buf[ptr++] = buf4[bufPtr++];
            }

            if(bufPtr >= buffTotal) {
                // end of the 4 char read buffer total.
                bufPtr = 0;
            }
        }

        return ptr;
    }

    private int read4(char[] buff) {
        return 4;
    }

}
