import java.util.Random;

/**
 * Created by rliu on 2/21/17.
 * 398. Random Pick Index
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * <p>
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * Reservoir Sampling: for get sample from a large,streamflow like data input with the correct probability,
 * eg, if there are currently 5 a in input stream, return the index of on random a with probality of 1/5
 */
public class RandomPickIndex {
    int[] nums;
    Random rnd;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.rnd = new Random();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 3};
        RandomPickIndex obj = new RandomPickIndex(nums);
        int param_1 = obj.pick(3); //return2,3,4 with equal probability
        int param_2 = obj.pick(1); //return 0
    }

    public int pick(int target) {
        int index = -1; //choose to update the index or not
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {

                if (rnd.nextInt(++count) == 0) //count is how many arr[index]==target if rnd number is 0, and the probablity of this is 1/count, we update the index
                    index = i;
            }
        }

        return index;
    }
}
