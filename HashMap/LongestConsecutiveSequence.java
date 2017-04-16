package HashMap;

import java.util.HashMap;

/**
 * Created by rliu on 4/16/17.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 用hashmap，key是数字，value是包含这个数字的最大长度，但是这里由于我们只考虑边界，
 * 也就是比如1，4，2，3 只有<1,4><4,4>是对的，2的value和3的value不重要，因为我们拿到一个新的数，只比较newnum－1和value和newnum＋1的value
 * 如果newnum －1 在map中且处于某非边界连续sequence中，那么newnum必然已经出现在map中了
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public static int longestConsecutive(int[] num) {
        // write you code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < num.length; i++) {
            if (!map.containsKey(num[i])) {
                int left = map.getOrDefault(num[i] - 1, 0);
                int right = map.getOrDefault(num[i] + 1, 0);
                int curr = left + right + 1;
                map.put(num[i], curr);
                maxLen = Math.max(maxLen, curr);
                //如果newnum －1 在map中且处于某非边界连续sequence中，那么newnum必然已经出现在map中了 所以这里只需要更新－left和＋right的value不需要，更新所有在left边界和nums［i］之间的
                map.put(num[i] - left, curr);
                map.put(num[i] + right, curr);
            } else
                continue;
        }
        return maxLen;

    }
}

