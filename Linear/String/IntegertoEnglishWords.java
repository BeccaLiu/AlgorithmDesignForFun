package Linear.String;

import java.util.HashMap;

/**
 * Created by rliu on 2/14/17.
 * 273. Integer to English Words
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegertoEnglishWords {
    public static void main(String[] args) {
        System.out.println(numberToWords(1234));
    }

    public static String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        StringBuilder rt = new StringBuilder();
        HashMap<Integer, String> map = buildMap();
        String[] sec = new String[]{"Billion", "Million", "Thousand"};
        StringBuilder[] re = new StringBuilder[4];
        int i = re.length - 1;
        while (num != 0) {
            int res = num % 1000; //if you want to get three digit ,you need divide by 1*10^3
            if (res != 0)
                re[i] = numberToWordsUnder1000(res, map);
            i--;
            num = num / 1000;
        }

        for (int j = 0; j < sec.length; j++) {
            if (re[j] != null) {
                rt.append(re[j]).append(" ").append(sec[j]).append(" ");
            }
        }
        if (re[re.length - 1] != null)
            rt.append(re[re.length - 1]).append(" ");
        return rt.substring(0, rt.length() - 1).toString(); //using substring to remove the last " "
    }

    public static StringBuilder numberToWordsUnder1000(int num, HashMap<Integer, String> map) {
        //can be separate by less than 20, less than 100

        int[] digit = new int[3];
        int i = 1;
        while (num != 0) {
            digit[digit.length - i++] = num % 10;
            num = num / 10;
        }
        StringBuilder sb = new StringBuilder();
        if (digit[0] >= 1)
            sb.append(map.get(digit[0])).append(" ").append("Hundred");

        if (digit[1] >= 2) {
            if (digit[0] >= 1)
                sb.append(" ");
            sb.append(map.get(digit[1] * 10));
            if (digit[2] != 0)
                sb.append(" ").append(map.get(digit[2]));

        } else if (digit[1] == 1) {
            if (digit[0] >= 1)
                sb.append(" ");
            sb.append(map.get(digit[1] * 10 + digit[2]));
        } else if (digit[1] == 0) {
            if (digit[2] != 0) {
                if (digit[0] >= 1)
                    sb.append(" ");
                sb.append(map.get(digit[2]));
            }
        }

        return sb;

    }

    //can using array instead of map
    public static HashMap buildMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        return map;
    }

}
