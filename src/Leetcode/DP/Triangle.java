package Leetcode.DP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 3/4/17.
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.isEmpty()) {
            return -1;
        }

        int ans[] = new int[triangle.size() + 1];

        for(int i = triangle.size() -1 ; i >=0; i--) {
            for(int j = 0;j<triangle.get(i).size();j++) {
                ans[j] = Math.min(ans[j], ans[j+1]) + triangle.get(i).get(j);
            }
        }

        return ans[0];
    }

    public static void main(String [] args) {
        List<Integer> a1 = new ArrayList<>();
        a1.add(4);
        a1.add(1);
        a1.add(8);
        a1.add(3);

        List<Integer> a2 = new ArrayList<>();
        a2.add(6);
        a2.add(5);
        a2.add(7);

        List<Integer> a3 = new ArrayList<>();
        a3.add(3);
        a3.add(4);

        List<Integer> a4 = new ArrayList<>();
        a4.add(2);

        List<List<Integer>> x = new ArrayList<>();
        x.add(a4);
        x.add(a3);
        x.add(a2);
        x.add(a1);


        Triangle t = new Triangle();
        System.out.println(t.minimumTotal(x));
    }
}
