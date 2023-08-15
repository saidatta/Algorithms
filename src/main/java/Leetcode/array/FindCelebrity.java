package Leetcode.array;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The knows API is defined in the parent class Relation.
 *
 * boolean knows(int a, int b);
 *
 * https://leetcode.com/problems/find-the-celebrity/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class FindCelebrity {
    public int findCelebrity(int n) {
        if(n <= 0) {
            return -1;
        }

        int celeb = 0;
        for(int i = 1; i < n; i++){
            if(knows(celeb, i)) {
                celeb = i;
            }
        }

        for(int i = 0; i < n; i++){
            if((i < celeb && knows(celeb, i)) || !knows(i, celeb)) {
                return -1;
            }
            if(i > celeb && !knows(i, celeb)) {
                return -1;
            }
        }
        return celeb;
    }

    private boolean knows(int a, int celeb) {
        // LEETCODE API
        return false;
//        throw new NotImplementedException();
    }
}
