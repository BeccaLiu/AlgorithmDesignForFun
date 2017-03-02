package Design;

/**
 * Created by rliu on 3/1/17.
 * 362. Design Hit Counter
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * <p>
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 * <p>
 * It is possible that several hits arrive roughly at the same time.
 */
public class HitCounter {
    int count = 0;
    int timelaspe = 300;
    int lastUpdateTime = 0;
    int lastPosition = 0;
    int[] hitCounter;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        hitCounter = new int[timelaspe];
    }

    public static void main(String[] args) {
        HitCounter obj = new HitCounter();
        obj.hit(1);
        obj.hit(2);
        obj.hit(3);
        obj.hit(4);
        System.out.println(obj.getHits(300));
        obj.hit(300);
        System.out.println(obj.getHits(300));
        System.out.println(obj.getHits(301));

    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        moving(timestamp);
        hitCounter[lastPosition]++;
        count++;
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        moving(timestamp);
        return count;

    }

    public void moving(int timestamp) {
        int gap = Math.min(timestamp - lastUpdateTime, timelaspe);
        for (int i = 0; i < gap; i++) {
            lastPosition = (lastPosition + 1) % timelaspe;
            count -= hitCounter[lastPosition];
            hitCounter[lastPosition] = 0;
        }
        lastUpdateTime = timestamp;
    }
}
