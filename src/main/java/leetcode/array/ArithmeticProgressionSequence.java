package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class ArithmeticProgressionSequence {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);

        int dif = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {

            if (arr[i] - arr[i - 1] != dif) {
                return false;
            }
        }

        return true;
    }

    //    Negative Marking -- TC: O(N), SC: O(1)
//    Now let's reduce the space complexity to Θ(1)\Theta(1)Θ(1). Because of the previous observation, we know what we
//    need to see. I've included a deeper explanation in the code breakdown. However, the trick is to notice that there
//    are exactly n values to check for. Because they differ by dx, it's pretty simple to map them array indices.
//    From there, negative marking is trivial.
//
//    This asymptomatically optimal as ANY element can invalidate the function and we cannot reduce constant time any
//    further. At least I hope so, I'm no algorithms/math expert ;)
    public boolean canMakeArithmeticProgression2(int [] input) {

        ArrayList<Integer> arr = Arrays.stream(input).boxed().collect(Collectors.toCollection(ArrayList::new));

        //Assume arr can be rearranged to form an arithemetic progression.
        //Then, the line formed by arr has slope mx-mn (or any two points!).
        int mx = Collections.max(arr);
        int mn = Collections.min(arr);
        // arthimetic formula a_min + (n-1)d = max
        int dx = (mx - mn) / (arr.size() - 1);

        //Correct for division errors (mx%arr.size()-1 != 0)
        //I chose this formula instead to highlight the edge cases!
        if (mn + dx * (arr.size() - 1) != mx) {
            return false;
        }

        //If the minimum and maximum are equal, all indices are equal --> the difference is always 0
        if (dx == 0) {
            return true;
        }

        //Because f'(x) deletes constants in x, we can change the x intercept to 0
        //We then increment the element by 1. This is a tool we're saving for later :p
        //Also notice, every element should now be POSITIVE
        arr.replaceAll(x -> x - mn + 1);

//        for(int i = 0; i < arr.size(); i++){
//            int value = arr.get(i);
//            value -= mn;
//            value++;
//            arr.set(i, value);
//        }

        //F: "If the array is valid, then each value from 1 to 1 + dx*(arr.size()-1) must exist in arr"
        //F and canMakeArithmeticProgression are equivalent (the proof is trivial)
        //If a point falls off the line or is repeated, then we CANNOT satisfy F as we cannot satisfy n
        //criteria using n-1 elements

        //Here's the trick you (probably) came for... NEGATIVE MARKING!
        //Because there are exactly arr.size() points on the line, we can represent seeing an element
        //by marking the value at index value/dx as negative!
        //This is also why we incremented arr[i]! (-0=0)
        for (int i = 0; i < arr.size(); i++) {
            //Find the value of arr[i] without negative marking AND remove the increment.
            int c = Math.abs(arr.get(i)) - 1;

            //If c isn't divisible by dx, it CANNOT be on our line. Proof by contradiction.
            if (c % dx != 0) {
                return false;
            }

            //If we have seen c before, return false. Proof by contradiction.
            if (arr.get(c / dx) < 0) {
                return false;
            }

            //Negative marking
            arr.set(c / dx, -arr.get(c / dx));
        }

        //F and canMakeArithmeticProgression are equivalent -- return True
        return true;
    }

}
