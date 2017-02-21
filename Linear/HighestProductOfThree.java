package Linear;

/**
 * Created by rliu on 12/5/16.
 * Highest product of three
 * product will be either: 1. product of first three biggest number 5*10*100
 * 2. product of first biggest number and two smallest number 5*-100*-10
 */
public class HighestProductOfThree {
    public static void main(String[] args) {
        int[] arr = {1, 10, -5, Integer.MIN_VALUE + 5};
        System.out.println(hpt(arr));
    }

    public static int hpt(int[] arr) {
        //which store the first three maximum number and two smallest number
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = arr[i];
            } else if (arr[i] > secondMax) {
                thirdMax = secondMax;
                secondMax = arr[i];
            } else if (arr[i] > thirdMax)
                thirdMax = arr[i];
            if (arr[i] < firstMin) {
                secondMin = firstMin;
                firstMin = arr[i];
            } else if (arr[i] < secondMin)
                secondMin = arr[i];
        }

        int max1 = firstMax;
        if (inRange(max1, secondMax)) {
            max1 *= secondMax;
            if (inRange(max1, thirdMax)) {
                max1 *= thirdMax;
            } else
                throw new IllegalArgumentException("out of range");
        } else
            throw new IllegalArgumentException("out of range");

        int max2 = firstMax;
        if (inRange(max2, firstMin)) {
            max2 *= firstMin;
            if (inRange(max2, secondMin)) {
                max2 *= secondMin;
            } else
                throw new IllegalArgumentException("out of range");
        } else
            throw new IllegalArgumentException("out of range");


        return Math.max(max1, max2);
    }

    public static boolean inRange(int val, int nextVal) {
        int product = val * nextVal;
        if (nextVal != 0) {
            return product / nextVal == val;
        }
        return true;
    }
}
