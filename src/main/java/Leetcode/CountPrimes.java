package Leetcode;

public class CountPrimes {
    public int countPrimes(int n) {
        if(n < 2) {
            return 0;
        }

        boolean[] notPrime = new boolean[n];
        int numOfPrimes = 0;
        for(int i = 2; i < n; i++) {
            if(notPrime[i]) {
                continue;
            }

            for(int k = 2; k * i < n; k++) {
                notPrime[k * i] = true;
            }
            numOfPrimes++;
        }
        return numOfPrimes;
    }
}
