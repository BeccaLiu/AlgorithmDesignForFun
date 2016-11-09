import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by rliu on 11/8/16.
 */
public class TwoSum1 {
    public static void main(String[] args) {
        int size = 20;
        int[] arr = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> arr[i] = random.nextInt(10));
        int sum = 10;
        System.out.println(Arrays.toString(arr));

        //twoSum2Pass(arr,sum);

        twoSum1Pass(arr, sum);
        System.out.println(twoSumTwoPointer(arr, sum));

    }

    //return if the arr exist 1 pairs of sum
    public static boolean twoSumTwoPointer(int[] arr, int sum) { //nlogn
        if (arr == null || arr.length < 2)
            return false;

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] + arr[right] == sum)
                return true;
            else if (arr[left] + arr[right] < sum)
                left++;
            else
                right--;
        }
        return false;
    }

    public static void twoSum2Pass(int[] arr, int sum) {
        if (arr == null || arr.length < 2)
            throw new IllegalStateException();

        HashMap<Integer, Integer> map = new HashMap<>();  //key is sum-curr, value is index
        for (int i = 0; i < arr.length; i++) {
            map.put(sum - arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i]))
                System.out.println("[" + i + "]:" + arr[i] + " + [" + map.get(arr[i]) + "]:" + (sum - arr[i]));
        }
    }

    public static void twoSum1Pass(int[] arr, int sum) {
        if (arr == null || arr.length < 2)
            throw new IllegalStateException();

        HashMap<Integer, Integer> map = new HashMap<>();  //<remain, index>
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i]))
                System.out.println("[" + i + "]:" + arr[i] + " + [" + map.get(arr[i]) + "]:" + (sum - arr[i]));
            map.put(sum - arr[i], i);
        }
    }
}
