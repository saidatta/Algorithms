package Leetcode.Design;

/**
 * https://leetcode.com/problems/design-hashset/description/
 *
 * Intuition
 * If you are ever asked such question in interview then intention of interviewer is to know if you understand the
 * internals of HashMap/HashSet and not making those one liners or use of List.contains would not make that good of
 * impression. There are few part you must mention/understand while implementing this.
 *
 * Collision
 * Hash function
 * Load Factor (α), in this case its 100.
 * What other type of hashing is there? we are using a simple "Single hash" function.
 * How can you optimise space complexity and search complexity based on different usecase?
 *
 * I hope you read and learn about these concept for few hours atleast while doing this easy problem.
 *
 * Here lot of things can be done to make it optimum, but that will make implemention very large which is not required
 * for this demostration.
 *
 * If you learned anything new, please upvote.
 *
 * Complexity
 * Time complexity: O(1 + α) for search, if we convert List into a BST then it should be O(log(α)).
 * Space complexity:
 */

import java.util.*;

class MyHashSet {
    public static int _MAX_LENGTH = 10000;
    int[][] store = new int[_MAX_LENGTH][100];
    public MyHashSet() {
        for (int[] row : store) {
            Arrays.fill(row, -1);
        }
    }

    public int computeHash(int key){
        return key % _MAX_LENGTH;
    }

    public void add(int key) {
        int row = computeHash(key);

        if(!contains(key)){
            for(int i = 0; i < 100; i++){
                if(store[row][i] == -1){
                    store[row][i] = key;
                    break;
                }
            }
        }
    }

    public void remove(int key) {
        int row = computeHash(key);
        for(int i = 0; i < 100; i++){
            if(store[row][i] == key){
                store[row][i] = -1;
                break;
            }
        }
    }

    public boolean contains(int key) {
        int row = computeHash(key);
        for(int i = 0; i < 100; i++){
            if(store[row][i] == key){
                return true;
            }
        }
        return false;
    }
}

