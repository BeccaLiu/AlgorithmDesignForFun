package BitManipulation;

/**
 * Created by rliu on 2/20/17.
 * 461. Hamming Distance
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Note:
 * 0 ≤ x, y < 231.
 * <p>
 * Example:
 * Input: x = 1, y = 4
 * Output: 2
 * <p>
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ^   ^
 * <p>
 * eg:
 * position = 2
 * ID = 5 = 0000 0101 (in binary)
 * ID >> position = 0000 0001
 * 0000 0001 & 0000 0001( 1 in binary ) = 1, because the furthest right bit is set.
 */
public class HummingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }

    public static int hammingDistance(int x, int y) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            //System.out.print(Integer.toBinaryString(1<<i)+" ");
            if ((((x >> i) & 1) ^ ((y >> i) & 1)) == 1)
                res++;
        }
        return res;
    }

}
