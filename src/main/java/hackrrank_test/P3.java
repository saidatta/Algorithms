package hackrrank_test;

import java.util.*;

/**
 * Created by venkatamunnangi on 9/23/17.
 */
public class P3 {
    public static void main(String args[] ) throws Exception {
        List<List<Integer>> arrays = new ArrayList<List<Integer>>();
        Scanner scanner = null;
        try {
            scanner = new Scanner (System.in);
            long max = 0;

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] tokens = str.split(" ");
                List<Integer> array = new ArrayList<>(tokens.length);
                for (int i = 0; i < tokens.length; i++) {
                    array.add(Integer.parseInt(tokens[i]));
                }

                arrays.add(array);
            }

            int[] result = merge(convertIntegers(arrays.get(0)), convertIntegers(arrays.get(1)), convertIntegers(arrays.get(2)));

            String ans = "";
            for(int i = 0; i < result.length; i++) {
                ans += result[i] + " ";
            }
            System.out.println(ans.trim());

        } finally {
            if (scanner != null) scanner.close();
        }
    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    public static int[] merge(int[] sA, int[] sB, int[] sC) {
        PriorityQueue<ArrayNumber> queue = new PriorityQueue<>();
        int total=0;

        queue.add(new ArrayNumber(sA, 0));
        queue.add(new ArrayNumber(sB, 0));
        queue.add(new ArrayNumber(sC, 0));

        total = sA.length + sB.length + sC.length;

        int m=0;
        int[] result = new int[total];

        //while heap is not empty
        while(!queue.isEmpty()){
            ArrayNumber ac = queue.poll();
            result[m++]=ac.arr[ac.index];

            if(ac.index < ac.arr.length-1){
                queue.add(new ArrayNumber(ac.arr, ac.index+1));
            }
        }

        return result;
    }


    static class ArrayNumber implements Comparable<ArrayNumber> {
        int[] arr;
        int index;

	public ArrayNumber(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayNumber o) {
            return this.arr[this.index] - o.arr[o.index];
        }
    }
}
