package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by rliu on 2/4/17.
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 */
public class RandomizedCollection {
    ArrayList<Integer> list;
    HashMap<Integer, HashSet> map; //number and index list

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, HashSet>();
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();

        System.out.println(collection.insert(0));
        System.out.println(collection.remove(0));
        System.out.println(collection.insert(-1));
        System.out.println(collection.remove(0));
        System.out.println(collection.getRandom());

        System.out.println(collection.getRandom());
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        HashSet<Integer> indexes = map.get(val);
        if (indexes != null && indexes.size() != 0) {
            indexes.add(list.size());
            list.add(val);
            return false;
        } else {
            if (indexes == null)
                indexes = new HashSet<>();
            indexes.add(list.size());
            list.add(val);
            map.put(val, indexes);
            return true;
        }
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        HashSet<Integer> removeIdxs = map.get(val);
        if (removeIdxs != null && removeIdxs.size() != 0) {
            int removeIdx = removeIdxs.iterator().next();
            //remove hole index from val list
            removeIdxs.remove(removeIdx);
            if (removeIdx != list.size() - 1) {
                //remove last index from last val list
                HashSet<Integer> endIdxs = map.get(list.get(list.size() - 1));
                endIdxs.remove(list.size() - 1);
                //adding hole index to last val list, and reset list hole value to last index value
                endIdxs.add(removeIdx);
                list.set(removeIdx, list.get(list.size() - 1));
            }
            list.remove(list.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a random element from the collection.
     */

    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
