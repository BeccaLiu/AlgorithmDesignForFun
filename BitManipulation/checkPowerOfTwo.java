package BitManipulation;

/**
 * Created by rliu on 4/7/17.
 * power of 2 will be like 1, 10, 100, 1000, 10000....
 * Let, x = 4 = (100)2
 * x - 1 = 3 = (011)2
 * x & (x-1) = 4 & 3 = (100)2 & (011)2 = (000)2
 * Let, x = 6 = (110)2
 * x - 1 = 5 = (101)2
 * x & (x-1) = 6 & 5 = (110)2 & (101)2 = (100)2
 * If subtraction is acceptable then you can just use x & (x - 1), which gives 0 for power of 2, and >0 otherwise.
 */
public class checkPowerOfTwo {
    public static void main(String[] args) {
        System.out.print(checkPowerOf2(4));
        System.out.print(checkPowerOf2(-2147483648));
    }

    public static boolean checkPowerOf2(int n) {
        //if n is smaller than n, definitely it would not be power of 2
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

}
