package Tree.BinarySerach;

/**
 * Created by rliu on 4/7/17.
 * Given n pieces of wood with length L[i] (integer array).
 * Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. (you do not need to cut all of them)
 * What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
 */
public class WoodCut {
    public static void main(String[] args) {
        System.out.println(woodCut(new int[]{232, 124, 456, 124, 232, 456}, 5));
    }

    public static int woodCut(int[] L, int k) {
        // write your code here
        //get mini length;
        int maxlen = Integer.MIN_VALUE;
        for (int i : L)
            maxlen = Math.max(maxlen, i);

        int max = Integer.MIN_VALUE;
        int l = 1;
        int r = maxlen;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            for (int wood : L)
                count += wood / mid;
            if (count >= k) {

                if (mid > max)
                    max = mid;
                //to maximize the length, here is l=mid+1 but not r=mid-1
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return max < 0 ? 0 : max;

    }
}
