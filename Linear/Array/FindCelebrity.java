package Linear.Array;

import java.util.HashMap;

/**
 * Created by rliu on 2/15/17.
 */
public class FindCelebrity {
    static HashMap<Integer, Integer> set = new HashMap<>();

    FindCelebrity() {
        set.put(0, 1);
        set.put(1, 0);
    }

    public static void main(String[] args) {
        new FindCelebrity();
        System.out.println(findCelebrity(2));
    }

    public static int findCelebrity(int n) {
        int candidate = 0;
//        int[] knows=new int[n]; //how many people knows
//        int[] known=new int[n]; //record how many people known him
        for (int i = 1; i < n; i++)
            if (knows(candidate, i)) {
                candidate = i;
            }
        for (int i = 0; i < n; i++)
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        return candidate;
    }


    //not working code
    public static int findCelebrityN(int n) {
        int[] knows = new int[n]; //only record -1, 0, 1
        int[] known = new int[n]; //record the exactlly number


        for (int i = 0; i < n; i++) { //we ask each person i
            int notKnows = 0;
            for (int j = 0; j < n; j++) { //only ask a if she knows some potencial person j
                if (j != i && (known[j] >= 0 && knows[j] <= 0)) {
                    if (knows(i, j)) {
                        known[j]++;
                        knows[i]++;
                    } else {
                        notKnows++;
                        known[j]--;
                    }
                }
            }
            if (notKnows == n - 1) {
                for (int j = 0; j < n; j++) {
                    if (!knows(j, i))
                        return -1;
                }
                return i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (knows[i] == 0 && known[i] == n - 1)
                return i;
        }
        return -1;

    }


    public static boolean knows(int a, int b) {
        return b == set.get(a);
    }
}
