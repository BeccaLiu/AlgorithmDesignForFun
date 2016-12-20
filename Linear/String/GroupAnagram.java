package Linear.String;

import java.util.*;

/**
 * Created by rliu on 12/19/16.
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.
 * <p>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 */
public class GroupAnagram {
    public static void main(String[] args) {
        System.out.println(groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0)
            return null;
        List<List<String>> rt = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String temp = new String(chars);
            if (map.containsKey(temp)) {
                map.get(temp).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(temp, list);
            }

        }
        for (List<String> list : map.values())
            rt.add(list);
        return rt;
    }

    //[I am stuck here]: not be able to think of sorting the words to testing matching, the following code will never work out.
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return null;

        List<List<String>> rt = new ArrayList<>();
        HashMap<HashSet<Character>, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            if (s == "") {
                if (rt.isEmpty())
                    rt.add(new ArrayList<String>());
                rt.get(0).add(s);
                continue;
            }
            boolean existed = false;
            for (HashSet<Character> set : map.keySet()) {
                int count = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (set.contains(s.charAt(i)))
                        count++;
                }
                if (count == set.size()) {
                    existed = true;
                    map.get(set).add(s);
                    break;
                }

            }
            if (!existed) {
                HashSet<Character> tempSet = new HashSet<>();
                ArrayList<String> list = new ArrayList<>();

                for (int i = 0; i < s.length(); i++)
                    tempSet.add(s.charAt(i));
                list.add(s);

                map.put(tempSet, list);
            }
        }

        for (ArrayList list : map.values())
            rt.add(list);
        return rt;

    }

}
