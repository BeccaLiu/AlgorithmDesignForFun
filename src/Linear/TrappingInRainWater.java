package Linear;

/**
 * Created by rliu on 11/12/16.
 * Trapping rain water
 * Given n non-negative integers to represent the histogram bar height, the width of each bar is 1
 * Calculate how much water it can trap after raining
 *
 */
public class TrappingInRainWater {
    public static void primitiveIdea(int[] a){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++){ //every iteration calculate the rain water start from i+1
            int boundary=i+1;
            while(a[boundary]<a[i])
                boundary++;


        }
    }
    public static void main(String[] args) {
        int[] b = {5, 3, 4, 6, 7, 3};

    }
}
