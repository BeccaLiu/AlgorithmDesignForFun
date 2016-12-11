package Tree.BinarySerach;

/**
 * Created by rliu on 12/10/16.
 * primitive Idea: if input is 15, we will try from 0 to sqrt(15) to check if , i*i ==15 which take O(n^1/2)
 * improved: using binary search lgN
 */
public class Sqrt {
    public static void main(String[] args) {
        int input = 15;
        binary(25);
        bit(25);

    }

    //[I am stuck here] can not think of using binary search
    public static void binary(int input) {
        int i = 0;
        int j = input;
        int res = 0;
        while (i < j) {
            int mid = i + (j - i) / 2;
            int square = mid * mid;
            if (square == input) {
                res = mid;
                break;
            } else if (square < input) {
                res = mid;
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        System.out.println(res);
    }


    //[I am stuck here] do not know any 31 bits(integer is 32 bits, but the first bit is sign bit) binary would not be more than product of any two 16 bits binary
    //1001*1010=1011010
    //time complexity O(lg n)
    //TODO:
    public static void bit(int x) {
        int res = 0;
        int bit = 0;
        for (int first = 1 << 15; first != 0; first = first >>> 1) {
            bit = res | first;
            if (bit <= x / bit)
                res = bit;

        }
        System.out.println(res);
    }

}
