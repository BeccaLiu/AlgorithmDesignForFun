package Linear.QueuePointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by rliu on 12/28/16.
 * 454. 4Sum II
 * Given four lists A, B, C, D of integer values, compute how many *tuples* (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero. if tuples is different, not matter the content, it will be valid
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 */
public class FourSum2 {
    public static void main(String[] args) {
        fourSumSplit(new int[]{1, 1}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{2, 2});

    }

    //using binary search to find the target number still too slow, can not pass the huge input test
    //n^3logn
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int m = 0; m < C.length; m++) {
                    int target = -(A[i] + B[j] + C[m]);
                    int left = 0;
                    int right = D.length - 1;
                    while (left <= right) {
                        int mid = (left + right) >> 1;
                        if (D[mid] == target) {
                            count++;
                            int l = mid - 1;
                            int r = mid + 1;
                            while (l >= 0 && D[l--] == D[mid])
                                count++;
                            while (r < D.length && D[r++] == D[mid])
                                count++;
                            break;
                        } else if (D[mid] > target)
                            right = mid - 1;
                        else
                            left = mid + 1;
                    }
                }
            }
        }
        return count;
    }

    //think of hashmap n^3
    public static int fourSumCountHash(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                HashMap<Integer, Integer> map = new HashMap<>();//key is the value, value is the count of duplicate input
                int sum = A[i] + B[j];
                for (int m = 0; m < C.length; m++) {
                    int target = 0 - sum - C[m];
                    if (!map.containsKey(target))
                        map.put(target, 1);
                    else
                        map.put(target, map.get(target) + 1);
                }
                for (int n = 0; n < D.length; n++) {
                    if (map.containsKey(D[n]))
                        count += map.get(D[n]);
                }
            }
        }
        return count;
    }

    //faster way: split 4 array to two 2 array and using hashmap to solve the problem like 2sum
    //memory limited
    public static int fourSumSplit(int[] A, int[] B, int[] C, int[] D) {
        //2 n size array will have n^2 combination
        //utilize the size of two array is same
        //here we only need return counts, so we can use the hashmap<sum, count> directly
        //but if we want to return all the tuples, we need using hashmap<sum,arraylist<tuple>> instead
        HashMap<Integer, Integer> sum1 = new HashMap<>();
        HashMap<Integer, Integer> sum2 = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sumOne = A[i] + B[j];
                int sumTwo = C[i] + D[j];
                //to make it faster, using map.getOrDefault(sum, 0) instead of if and else
                if (sum1.containsKey(sumOne))
                    sum1.put(sumOne, sum1.get(sumOne) + 1);
                else
                    sum1.put(sumOne, 1);
                if (sum2.containsKey(sumTwo))
                    sum2.put(sumTwo, sum2.get(sumTwo) + 1);
                else
                    sum2.put(sumTwo, 1);
            }
        }

        int count = 0;
        for (Iterator ite = sum1.entrySet().iterator(); ite.hasNext(); ) {
            Map.Entry entry = (Map.Entry) ite.next();
            int sum = (int) entry.getKey();
            int tempCount = (int) entry.getValue();
            if (sum2.containsKey(-sum))
                count += tempCount * sum2.get(-sum);
        }
        return count;

    }

    /*faster way online
    Map<Integer, Integer> map = new HashMap<>();

    for(int i=0; i<C.length; i++) {
        for(int j=0; j<D.length; j++) {
            int sum = C[i] + D[j];
            map.put(sum, map.getOrDefault(sum, 0) + 1); <-using getOrDefault
        }
    }

    int res=0;
    for(int i=0; i<A.length; i++) { <-do not using two hashset, no big different, but cost space, and everytime need to compute hashcode
        for(int j=0; j<B.length; j++) {
            res += map.getOrDefault(-1 * (A[i]+B[j]), 0);
        }
    }

    return res;
     */
}
