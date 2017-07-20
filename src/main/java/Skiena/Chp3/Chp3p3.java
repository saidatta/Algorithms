package Skiena.Chp3;

/**
 * 3-3. We have seen how dynamic arrays enable arrays to grow while still achieving constant-time amortized performance.
 * This problem concerns extending dynamic arrays to let them both grow and shrink on demand.
 * 1. Consider an underflow strategy that cuts the array size in half whenever the array falls below half full.
 *    Give an example sequence of insertions and deletions where this strategy gives a bad amortized cost
 *
 * 2. Then, give a better underflow strategy than that suggested above, one that achieves constant amortized cost
 *    per deletion.
 *
 * Created by venkatamunnangi on 12/28/16.
 */
public class Chp3p3 {

    /**
     * Solution for 1
     *
     * 1. size 4 array with 3 elements. remove 1, insert 1, and so forth.
     */

    /**
     * Solution for 2
     *
     * 2. when the array is one-fourth full, shrink its size to half of what it was.
     */

}
