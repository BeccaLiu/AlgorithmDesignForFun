package Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 2/15/17.
 * 282. Expression Add Operators
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        System.out.println(addOperators("123", 6));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> re = new ArrayList<>();
        if (num == null || num.length() == 0)
            return re;
        List<Info> combination = divide(num, target, 0, num.length() - 1);
        for (int i = 0; i < combination.size(); i++) {
            if (combination.get(i).val == target)
                re.add(combination.get(i).pattern);
        }
        return re;
    }

    public static List<Info> divide(String num, int target, int start, int end) {
        if (start == end) {
            List<Info> list = new ArrayList<>();
            list.add(new Info(num.substring(start, end + 1), start, true));
            return list;
        }
        int mid = start + (end - start) / 2;
        List<Info> left = divide(num, target, start, mid);
        List<Info> right = divide(num, target, mid + 1, end);
        List<Info> re = new ArrayList<>();
        for (int i = 0; i < left.size(); i++) {
            for (int j = 0; j < right.size(); j++) {
                Info plus = new Info(left.get(i).pattern + "+" + right.get(j).pattern, left.get(i).val + right.get(j).val, false);
                Info multi = new Info(left.get(i).pattern + "*" + right.get(j).pattern, left.get(i).val * right.get(j).val, false);
                Info minus = new Info(left.get(i).pattern + "-" + right.get(j).pattern, left.get(i).val - right.get(j).val, false);
                re.add(plus);
                re.add(multi);
                re.add(minus);
                if (left.get(i).isNumeric && right.get(j).isNumeric) {
                    String number = left.get(i).pattern + right.get(j).pattern;
                    Info combine = new Info(number, Integer.parseInt(number), true);
                    re.add(combine);
                }
            }
        }
        return re;
    }

    public static class Info {
        String pattern;
        int val;
        boolean isNumeric;

        Info(String pattern, int val, boolean isNum) {
            this.pattern = pattern;
            this.val = val;
            isNumeric = isNum;
        }
    }


}
