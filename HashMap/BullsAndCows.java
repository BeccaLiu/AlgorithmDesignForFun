package HashMap;

import java.util.ArrayList;

/**
 * Created by rliu on 3/26/17.
 * 299. Bulls and Cows
 */
public class BullsAndCows {
    public static void main(String[] args) {
        System.out.println(getHint("1122", "0001"));

    }

    public static String getHint(String secret, String guess) {
        int bull = 0;
        int cow = 0;
        ArrayList<Integer>[] map = new ArrayList[10];
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i))
                bull++;
            else {
                int number = guess.charAt(i) - '0';
                if (map[number] == null)
                    map[number] = new ArrayList<Integer>();
                map[number].add(i);
            }
        }

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                ArrayList<Integer> indexs = map[secret.charAt(i) - '0'];
                if (indexs != null && indexs.size() != 0) {
                    cow++;
                    indexs.remove(indexs.size() - 1);
                }
            }
        }
        return bull + "A" + cow + "B";

    }
}
