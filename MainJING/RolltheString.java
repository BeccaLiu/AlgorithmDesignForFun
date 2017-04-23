package MainJING;

/**
 * Created by rliu on 4/22/17 12:06 PM.
 */
public class RolltheString {
    public static void main(String[] args) {
        System.out.println(rollTheString("vwxyz", new int[]{5, 1, 2, 3, 4, 5}));
    }

    static String rollTheString(String s, int[] roll) {
        char[] arrays = s.toCharArray();
        int[] times = new int[s.length()];

        for (int i = 1; i < roll.length; i++) {
            for (int j = 0; j < roll[i]; j++) {
                times[j]++;
            }
        }

        for (int i = 0; i < arrays.length; i++) {
            char ori = arrays[i];
            arrays[i] = (char) ('a' + ((ori - 'a') + times[i]) % 26);
        }
        return String.valueOf(arrays);
    }

}
