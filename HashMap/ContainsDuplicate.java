package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by rliu on 1/17/17.
 * 217. Contains Duplicate
 * initial idea is sort using nlogn and scan
 * but better solution is using hashset
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{2147483647, -2147483647}, 1, 2147483647));
        System.out.println(containsNearbyAlmostDuplicateTreeSet(new int[]{1, 3, 1}, 1, 1));
        System.out.println(containsNearbyAlmostDuplicateBucket(new int[]{1, 3, 1}, 1, 1));
    }

    //here we need consider one more factor: t than previous question,
    //a tricky part of this question is when input including 2147483647, -2147483647 which is the largest number for int range ->  using long
    //hint: 1. binary search tree 2.using bucket 3.hashmap+special process
    //first, t will be huge if the number is huge, but k seams to have a limitation, which is the input size
    //and this quesiton is focus on dealing with the relationship between each node.
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //primitive idea is check back for each node, so it is a kn solution, but will not pass the time limited, there must be a faster way
        for (int i = 1; i < nums.length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i - j >= 0 && Math.abs((long) nums[i] - (long) nums[i - j]) <= t)
                    return true;
            }
        }
        return false;

    }

    public static boolean containsNearbyAlmostDuplicateTreeSet(int[] nums, int k, int t) {
        if (k <= 0 || nums.length < 2) {
            return false;
        }
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long floor = treeSet.floor((long) nums[i]); //Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
            Long ceiling = treeSet.ceiling((long) nums[i]); //Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
            if (floor != null && nums[i] - floor <= t || ceiling != null && ceiling - nums[i] <= t)
                return true;
            if (i >= k) //true return first before the remove to avoid  [1, 50, 70, 90, 110, 1, 2] k = 5 t = 1 that nums[5]=nums[1]=1 and nums[6]=2 which should return true
                treeSet.remove((long) nums[i - k]);

            treeSet.add((long) nums[i]);
        }
        return false;
    }

    //nums[i] - nums[j] | <= t -> nums[i] / t - nums[j] / t | <= 1
    //still using hashmap, but key is the bucket number
    //[2, -40, 14, -10, 44, 60, 53] k=4 t= 10 -> bucket mapping[3, 0, 4, 2, 7, 9, 8] when we scan 53 which bucket# is 8 we check bucket 7 and bucket 9, and we found valid
    public static boolean containsNearbyAlmostDuplicateBucket(int[] nums, int k, int t) {
        if (k <= 0 || nums.length < 2) {
            return false;
        }

        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket) || map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t || map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)
                return true;
            if (i >= k) {
                long latestBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(latestBucket); //sliding window need to maintain the content of window, so we need to add and remove dynamically
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }

    //Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
    //when come up with duplicate, it will be intuitive to think about hashset
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!hs.add(nums[i]))
                return true;
        }
        return false;
    }

    //219. Contains Duplicate II
    //Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
    //if we want to know the index, we probably won't be able to sort
    //and only solution is hashmap
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(nums[i]);
            if (index != null && Math.abs(index - i) <= k)
                return true;
            else
                map.put(nums[i], i); //remember to update the map
        }
        return false;
    }


}
