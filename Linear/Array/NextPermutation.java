package Linear.Array;

import java.util.Arrays;

/**
 * Created by rliu on 4/10/17.
 * 31. Next Permutation
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 1. 从低位向高位（从右向左）找第一个递减的数：s[i]<s[i+1]。如果不存在，则表明该permutation已经最大，next permutation为当前序列的逆序。 //找到要swap的位置
 * 2. 在s[i+1:n-1]中找一个j，使s[j]>s[i]>=s[j+1]，swap(s[i], s[j]) 找到另一个要swap得数
 * 3. 将s[i+1:n-1]排序，从低位到高位单调递减。 剩下的sort
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 5, 3, 2, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        //find left
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1])
                break;
            i--;
        }
        //when eg 4321, i==-1
        if (i == -1) {
            Arrays.sort(nums);
            return;
        }
        //find right
        int j = nums.length - 1;
        while (j > i) {
            if (nums[j] > nums[i])
                break;
            j--;
        }

        //swap
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        //sort i+1 to end
        Arrays.sort(nums, i + 1, nums.length);
        return;
    }

}
