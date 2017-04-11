package Linear;

import java.util.ArrayDeque;

/**
 * Created by rliu on 4/10/17.
 * 402. Remove K Digits
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 */
public class RemoveKDigits {
    public static void main(String[] args) {
        System.out.println(DeleteDigits("10", 2));
    }

    public static String removeKdigits(String num, int k) {
        int remain = num.length() - k; //how many number need to be remain
        char[] numArray = num.toCharArray();
        char[] res = new char[remain]; //result char
        int index = 0;
        for (int i = 0; i < numArray.length; i++) {
            // (1)  (n - i > remain - index): have enough remaining digits to be compared
            // (2)  (res[index - 1] > nums[i]): at this time, the (index-1) is the newest added digit,
            //      compare this digit with the current num, if the res is greater and you have enough
            //      remaining digits to be compared, decrease the index(it ensures that the future added digit is
            //      always smaller than before and the size is remain) until get the right and 'safe' position
            while ((numArray.length - i > remain - index) && (index > 0 && numArray[i] < res[index - 1]))
                index--;
            if (index < remain)
                res[index++] = numArray[i];
        }

        // check leading zeroes
        index = -1;
        while (++index < remain) {
            if (res[index] != '0') break;
        }
        String s = new String(res).substring(index);

        return s.length() == 0 ? "0" : s;

    }

    public static String DeleteDigits(String A, int k) {
        if (k == A.length())
            return "";
        // write your code here
        int remain = A.length() - k; //how many number need to be remain

        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        stack.push(A.charAt(0));

        for (int i = 1; i < A.length(); i++) {
            char curr = A.charAt(i);
            //178542,2 when curr=5, stack[178] pop when size is ok to pop, aka size > remain(4)-(A.length(6)-3)
            //here A.length-i is the length of 542, we can at most pop with remain size 1+542 1+3=4 which is the remain
            while (stack != null && stack.size() != 0 && stack.size() > remain - (A.length() - i) && stack.peek() > curr) {
                stack.pop();
            }
            //eg. 123456789 ,1 when stack[12345678] will not push 9 inside
            if (stack != null && stack.size() < remain)
                stack.push(curr);
        }

        //eg.10200,1 => 0200 remove the first few 0;
        //eg 10,1 =>0 not remove
        while (stack.size() > 1 && stack.peekLast() == '0') {
            stack.removeLast();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }
}
