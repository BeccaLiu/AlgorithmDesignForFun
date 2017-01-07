package HashMap;

import java.util.*;

/**
 * Created by rliu on 1/6/17.
 * 451. Sort Characters By Frequency
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * 1.highest frequency is appear first;
 * 2.if when frequency is same, the character treated equality
 * 3.A and a is treated differently
 * Analysis: we need know two things, the char, and the frequency of the char,-> HashMap<char, frequency>
 * we can using char array with size 256 to replace hashmap to make it faster, but noted, here we need to deal with lower case and upper case,
 */
public class SortCharByFrequency {
    public static void main(String[] args) {
        System.out.print(frequencySort("cccaaa"));
    }

    public static String frequencySort(String s) {
        //record char and frequency
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (map.containsKey(curr))
                map.put(curr, map.get(curr) + 1);
            else
                map.put(curr, 1);
        }


        PriorityQueue<String> arr = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        //iterate the hashmap and sort
        for (Iterator ite = map.entrySet().iterator(); ite.hasNext(); ) {
            Map.Entry<Character, Integer> entry = (Map.Entry) ite.next();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < entry.getValue(); i++)
                sb.append(entry.getKey());
            arr.add(sb.toString());
        }

        //change result to string
        StringBuilder sb = new StringBuilder();
        while (!arr.isEmpty())
            sb.append(arr.poll());

        return sb.toString();

    }

    public static String frequencySortCharArray(String s) {
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }

        PriorityQueue<String> arr = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        for (int i = 0; i < map.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < map[i]; j++)
                sb.append((char) i);
            arr.add(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        while (!arr.isEmpty())
            sb.append(arr.poll());

        return sb.toString();

    }
}
