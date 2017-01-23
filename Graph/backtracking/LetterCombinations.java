package Graph.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rliu on 1/22/17.
 * 17. Letter Combinations of a Phone Number
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinations {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0)
            return res;
        String[] charMap = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            int size = res.size();
            for (int j = 0; j < size; j++) {
                String curr = res.remove(0);
                for (char c : charMap[digit].toCharArray()) {
                    res.add(curr + c);
                }
            }
        }
        return res;
    }
}
