package Linear;

/**
 * Created by rliu on 12/5/16.
 * Highest product of three
 * product will be either: 1. product of first three biggest number 5*10*100
 * 2. product of first biggest number and two smallest number 5*-100*-10
 */
public class HighestProductOfThree {
    public static void main(String[] args) {
        int[] arr = {1, 10, -5, 5, 10, -100};
        System.out.println(hpt(arr));
    }

    public static int hpt(int[] arr) {
        //which store the first three maximum number and two smallest number
        int firstMax = arr[0];
        int secondMax = arr[0];
        int thirdMax = arr[0];
        int firstMin = arr[0];
        int secondMin = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = arr[i];
            } else if (arr[i] > secondMax) {
                thirdMax = secondMax;
                secondMax = arr[i];
            } else if (arr[i] > thirdMax)
                thirdMax = arr[i];
            else if (arr[i] < firstMin) {
                secondMin = firstMin;
                firstMin = arr[i];
            } else if (arr[i] < secondMin)
                secondMin = arr[i];
        }
        return Math.max(firstMax * secondMax * thirdMax, firstMax * firstMin * secondMin);
    }
}
