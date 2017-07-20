package Skiena.Chp8_DynamicProgramming;

/**
 *
 * Created by venkatamunnangi on 2/21/17.
 */
public class LongestCommonSubsequence {

    public int lcsDynamic(char str1[], char str2[]) {
        if(str1 == null || str2 == null) {
            return 0;
        }

        int [][] result = new int[str1.length+1][str2.length+1];
        int max = 0;

        for(int i = 1; i< result.length;i++) {
            for(int j = 1; j< result[0].length;j++) {
                if(str1[i-1] == str2[j-1]) {
                    result[i][j] = result[i-1][j-1] + 1;
                } else {
                    result[i][j] = Math.max(result[i-1][j], result[i][j-1]);
                }

                if(result[i][j] > max) {
                    max = result[i][j];
                }
            }
        }

        return max;
    }

    public static void main(String args[]){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";

        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }

}
