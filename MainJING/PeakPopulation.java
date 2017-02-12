package MainJING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by rliu on 2/11/17.
 */
public class PeakPopulation {
    static int startYear = 1900;
    static int endYear = 2000;

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<BirthDeath> lists = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            int by = startYear + rand.nextInt(endYear - startYear + 1);
            int diff = endYear - by;
            int dy = by + rand.nextInt(diff + 1);
            BirthDeath bd = new BirthDeath(by, dy);
            lists.add(bd);
        }
        System.out.println(lists);
        System.out.println(getPeakPopulation(lists));
    }

    public static int getPeakPopulation(ArrayList<BirthDeath> lists) {
        int[] bucket = new int[endYear - startYear + 1];

        for (int i = 0; i < lists.size(); i++) {
            BirthDeath curr = lists.get(i);
            if (curr.deathYear == curr.deathYear) {
                bucket[curr.deathYear - startYear]++;
            } else {
                for (int j = curr.birthYear; j <= curr.deathYear; j++) {
                    bucket[j - curr.birthYear]++;
                }
            }
        }
        int maxPopulation = bucket[0];
        int maxIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > maxPopulation) {
                maxPopulation = bucket[i];
                maxIndex = i;
            }
        }
        System.out.println(Arrays.toString(bucket));
        return startYear + maxIndex;
    }

    static class BirthDeath {
        int birthYear;
        int deathYear;

        BirthDeath(int by, int dy) {
            birthYear = by;
            deathYear = dy;
        }

        @Override
        public String toString() {
            return "(" + birthYear + "," + deathYear + ")";
        }
    }
}
