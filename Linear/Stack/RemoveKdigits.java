package Linear.Stack;

import java.util.ArrayDeque;

/**
 * Created by rliu on 12/7/16.
 * Remove K digits
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 */
public class RemoveKdigits {
    public static void main(String[] args) {
        String num = "5413219";
        int k = 3;
        removeDigits(num, k);
        String num2 = "1111111";
        removeDigits(num2, k);
    }

    //when problem is about scan and check back, we will think of stack
    public static void removeDigits(String num, int k) {
        if (num == null || num.length() < 0 || k > num.length())
            return;

        int[] nums = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            nums[i] = num.charAt(i) - '0';
        }

        //[I am stuck here]
        //maintain an increasing order in stack,(递增序列),so that can solve this problem in O(n) time by just scan once.
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int left = 1;
        stack.push(nums[0]);
        while (left < nums.length) {
            if (!stack.isEmpty() && nums[left] < stack.peek() && left < k + stack.size())
                stack.pop();
            else
                stack.push(nums[left++]);
        }
        while (stack.size() > num.length() - k) {
            stack.pop();
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.removeLast());
        }
        System.out.println();
    }

}
