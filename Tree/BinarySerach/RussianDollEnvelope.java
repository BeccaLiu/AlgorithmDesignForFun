package Tree.BinarySerach;

/**
 * Created by rliu on 12/11/16.
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * height is 3 with
 * [2,3]
 * => [5,4]
 * => [6,7]
 */
public class RussianDollEnvelope {
    //[I am stuck here]: sort the array using width increasing, but sort height in same width decreasing
    //[[2,3],[5,4],[6,7],[6,4]]
    //problem become find the longest increasing sequence(not need to be adjacent)
    // previous example is [2,3],[5,4],[6,7], and key is, if height is larger than previous height, the width is definitely larger than previous width
    public static void main(String[] args) {
        //after sort, if the height became
        int[] height = {2, 5, 6, 7, 3, 4, 5, 7}; //longest increasing will be 2,3,4,5,7
        longestIncreasingSequenceDP(height);

    }


    public static void longestIncreasingSequenceDP(int[] arr) {

    }
}
