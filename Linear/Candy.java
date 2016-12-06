package Linear;

/**
 * Created by rliu on 12/5/16.
 * Candy
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * rates={0,3,2,1,4}
 * Primitive Idea: first assign 1 to each kid, and check every kid with its neighbors, which take O(n), but the result {1,2,2,1,2} is invalid, which should be {1,3,2,1,2}
 * we find the problem is where decrease rates for continuously few kids
 * [I am stuck]: I notice invalid is happened when the rank is continuously decrease, but can not think of check the decrease rank in reverse way, so it became valid increasing
 * first in order left to right, make sure every kids and its left kids is valid
 * second in order right to left, make sure every kids and its right kids is valid
 * the max will the number of candy that kids need
 */
public class Candy {
    public static void main(String[] args) {
        int[] rateOfKids = {0, 3, 2, 1, 4};
        System.out.println(candy(rateOfKids));
    }

    public static int candy(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        if (arr.length == 1)
            return 1;
        int[] candy = new int[arr.length];
        candy[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1])
                candy[i] = candy[i - 1] + 1;
            else if (candy[i] == candy[i - 1])
                candy[i] = candy[i - 1];
            else
                candy[i] = 1;
        }
        int candyCount = candy[candy.length - 1];
        for (int i = candy.length - 2; i >= 0; i--) {
            int newCount = 0;
            if (arr[i] > arr[i + 1])
                newCount = candy[i + 1] + 1;
            else if (candy[i] == candy[i + 1])
                newCount = candy[i + 1];
            else
                newCount = 1;
            candy[i] = Math.max(newCount, candy[i]);
            candyCount += candy[i];
        }
        return candyCount;
    }
}
