package Linear.QueuePointer;

/**
 * Gas Station
 * Created by rliu on 11/10/16.
 * gas[i]=[3,4,3,6,7,1,2]
 * cost[i]=[2,4,5,1,5,1,3]
 * suppose there is a circle with n petrol pumps on the circle
 * you are given two sets of data:
 * gas[i]: the amount of petrol that the ith pumps has
 * cost[i]: the distance to the i+1 pump
 * return the starting gas station index if you can travel the pump once, otherwise -1
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 * Follow-up: using queue or stack
 */
public class GasStation {
    public static int canCompleteCircle1(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0)
            return -1;
        if (gas.length != cost.length)
            return -1;
        int[] reminder = new int[gas.length * 2];
        int total = 0;
        int start = 0; //to return the start
        int end = 1;
        for (int i = 0; i < gas.length * 2; i++) {
            reminder[i] = gas[i % gas.length] - cost[i % gas.length];
            total += reminder[i];
            while (total < 0) {
                total -= reminder[start++];
            }
            end++;
            if ((end - start) == gas.length)
                return start;
        }
        return -1;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        if (gas == null || cost == null)
            return -1;
        if (gas.length != cost.length)
            return -1;

        int right = 0; //already including
        int left = gas.length - 1; //left-1 the boundary of range inclusively
        int tank = gas[right] - cost[right];

        while (left > right) {
            if (tank < 0) {
                tank += gas[left] - cost[left--];
            } else {
                tank += gas[++right] - cost[right];
            }

        }
        return tank < 0 ? -1 : (left + 1) % gas.length;

    }

    public static void main(String[] args) {
        int[] gas = {3, 4, 3, 6, 7, 1, 2};
        int[] cost = {4, 4, 6, 7, 5, 1, 3};
        System.out.println(canCompleteCircle1(gas, cost));

        int[] gas1 = {1};
        int[] cost1 = {2};
        System.out.println(canCompleteCircuit(gas1, cost1));

    }
}
