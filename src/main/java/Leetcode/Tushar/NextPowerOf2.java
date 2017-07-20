package Leetcode.Tushar;

/**
 * http://www.geeksforgeeks.org/next-power-of-2/
 */
public class NextPowerOf2 {

    public int nextPowerOf2(int num){
        if(num ==0){
            return 1;
        }
        if(num > 0 && (num & (num-1)) == 0){
            return num;
        }
        while((num & (num-1)) > 0){
            num &= (num-1);
        }
        return num<<1;
    }
    public static void main(String args[]){
        NextPowerOf2 np = new NextPowerOf2();

        char[] x = new char[12];
        String s = "abcdefg";
        
        for(int i = 0; i<s.length();i++) {
            x[s.charAt(i) - 'a']++;
        }
        String ll = new String(x);

        System.out.println(ll.toCharArray());

        System.out.println(np.nextPowerOf2(4));
    }
}
