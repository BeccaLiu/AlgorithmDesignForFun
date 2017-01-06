package Linear.Array;

/**
 * Created by rliu on 1/5/17.
 * 414. Third Maximum Number
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
 * [I am stuck here]: the requirement is O(n) solution, so the sort is not working, and I am think of some high level of datastructer to store the info, but failed
 * can only using 3 variable, sometimes, do not think to much
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{1, 2, -2147483648}));

    }

    public static int thirdMax(int[] nums) {
        //[pitfall]: when input has Integer.MIN_VALUE, will fail, so we need the set the initial value of first, second and third as Long.MIN_VALUE
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > third) {
                if (num > first) {
                    third = second;
                    second = first;
                    first = num;
                } else if (num < first && num > second) {
                    third = second;
                    second = num;
                } else if (num < second && num > third)
                    third = num;
            }
        }
        if (third <= Long.MIN_VALUE)
            return (int) first;
        else
            return (int) third;

    }
}
