package Advance;

import java.util.Arrays;

/**
 * Created by rliu on 11/24/16.
 */
public class CountPrime {
    public static void main(String[] args) {
        System.out.print(countPrime(13));

    }

    public static int countPrime(int n) {
        if (n <= 3)
            return 0;

        int bound = (int) Math.sqrt(n);
        int count = 0;
        boolean[] notPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!notPrime[i]) {
                count++;
                if (i <= bound) {
                    for (int j = i; i * j <= n; j++) {
                        notPrime[j * i] = true;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(notPrime));
        return count;
    }
}

