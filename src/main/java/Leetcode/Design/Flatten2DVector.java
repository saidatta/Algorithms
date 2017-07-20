package Leetcode.Design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-2d-vector/#/description
 *
 * Created by venkatamunnangi on 10/11/16.
 */
public class Flatten2DVector implements Iterator<Integer> {

    private List<List<Integer>> vector2d;
    private List<Integer> tempList;
    private int currentIndex;
    private int tempIndex;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        vector2d = vec2d;
        if (vector2d == null || vector2d.isEmpty()) {
            tempList = new ArrayList<>();
        } else {
            tempList = vector2d.get(currentIndex);
        }
        currentIndex = 0;
        tempIndex = 0;
    }

    @Override
    public Integer next() {
        if (tempIndex < tempList.size()) {
            return tempList.get(tempIndex++);
        } else {
            tempIndex = 0;
            tempList = vector2d.get(currentIndex);
            return tempList.get(tempIndex++);
        }
    }

    @Override
    public boolean hasNext() {
        if (tempIndex < tempList.size()) {
            return true;
        } else {
            do {
                currentIndex++;
                // if the last arrays are just empty. Then, we cannot return any number from them.
            } while ((currentIndex < vector2d.size()) && (vector2d.get(currentIndex).isEmpty()));
        }
        return currentIndex < vector2d.size();

    }
}