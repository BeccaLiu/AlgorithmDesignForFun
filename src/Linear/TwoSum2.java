package Linear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rliu on 11/8/16.
 */
public class TwoSum2 {
    /* Prob Desc: Design and Implement a TwoSum class which support the following two operation, add and find
     * add(x): add x into the internal data structure
     * find(target): find if there are any pair sum up to the target
     * Data stream question
     * Interface: add       public void add(int toAdd)
     * Interface: find      public boolean find(int toFind)
     * Assumption:          you have enough memory to store the stream of data
     * Corner Case:         No data received-> return false when find()
     * Time Complexity:     add:O(1)    find: O(n)
     * Space Complexity:    O(2n)
     */
    private final List<Integer> list;
    private final HashMap<Integer, Integer> map; //<number, frequency>

    public TwoSum2() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        TwoSum2 ts = new TwoSum2();
        ts.add(1);
        ts.add(2);
        System.out.println(ts.find(3));
        ts.add(2);
        System.out.println(ts.find(4));
        System.out.println(ts.find(2));

    }

    public void add(int toAdd) { //O(1)
        list.add(toAdd);
        if (!map.containsKey(toAdd))
            map.put(toAdd, 1);
        else
            map.put(toAdd, map.get(toAdd) + 1);
    }

    public boolean find(int toFind) { //O(n)
        for (int i : list) {
            int remind = toFind - i;
            if ((remind != i && map.containsKey(remind)) || (remind == i) && map.get(remind) > 1)
                return true;
        }
        return false;
    }
}
