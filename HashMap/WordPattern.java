package HashMap;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rliu on 1/6/17.
 * 290. Word Pattern
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false. same pattern, different string
 * pattern = "abba", str = "dog dog dog dog" should return false. different pattern, same string
 * <p>
 * key: not only using one hashmap, but also a hashset
 */
public class WordPattern {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat fish"));
    }

    public static boolean wordPattern(String pattern, String str) {
        if (pattern == null || pattern.length() == 0) //no pattern at all, definitely false
            return false;
        if (pattern.length() == 1 && str.length() == 1)// "a" " " space is trate as a string
            return true;
        String[] arr = str.split(" ");
        if (arr.length != pattern.length())
            return false;
        HashMap<String, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char patterChar = pattern.charAt(i);
            set.add(patterChar);
            if (!map.containsKey(arr[i]))
                map.put(arr[i], patterChar);
            else if (!map.get(arr[i]).equals(patterChar))
                //else if(map.get(patterChar)!=(arr[i]))
                // should using equals, as == tests for reference equality (whether they are the same object), but equals test the content
                //although you might think arr[i] with same content will be in the shared string pool
                //from source code, split of java is using an ArrayList.toArray()
                //for future reference, most of the time, using equals
                return false;
        }
        return set.size() == map.size();

    }
}
