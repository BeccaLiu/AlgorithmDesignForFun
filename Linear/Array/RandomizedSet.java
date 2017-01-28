package Linear.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by rliu on 1/27/17.
 * 380. Insert Delete GetRandom O(1)
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
public class RandomizedSet {
    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }

    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(randomSet.insert(1));

// Returns false as 2 does not exist in the set.
        System.out.println(randomSet.insert(0));

// Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(randomSet.remove(0));

// getRandom should return either 1 or 2 randomly.
        System.out.println(randomSet.getRandom());

// Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(randomSet.remove(1));

// 2 was already in the set, so return false.
        System.out.println(randomSet.insert(2));

// Since 2 is the only number in the set, getRandom always return 2.
        System.out.println(randomSet.getRandom());

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.get(val) != null)
            return false;
        else {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        Integer index = map.get(val);
        if (index != null) {
            map.remove(val);
            //Attention: this part is the key of the whole question, and separate into two condition: one is to be remove element happened to be the last value,
            //or we need to move the last element to fill the hole
            if (index == list.size() - 1)
                list.remove(list.size() - 1);
            else {
                int lastVal = list.get(list.size() - 1);
                list.set(index, lastVal);
                map.put(lastVal, index);
                list.remove(list.size() - 1);
            }
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
