package Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by rliu on 11/27/16.
 * 373. Find K Pairs with Smallest Sums
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */
public class KPairsWithSmallestSums {

    public static void main(String[] args) {
        int[] a = {-2, -1, 0, 1, 1, 2,};
        int[] b = {-1, 1, 2, 2,};
        bestFirstSearch(a, b, 10);
        List<int[]> rs = kSmallestPairs(a, b, 24);
        for (int i = 0; i < rs.size(); i++) {
            int[] curr = rs.get(i);
            System.out.print(curr[0] + "," + curr[1] + "/ ");
        }
    }


    // as there are two sorted list
    // we can convert this one dimension problem to two dimension problem by lists all the sum into a m*n matrix, and this matrix is sorted from left to right, from top to down
    // we can use heap to get k th pairs or we can use binary search with the help of the right top corner(kSmallestInSortedMatirx)
    //still a m*n solution ,just return the kth smallest value;
    public static void bestFirstSearch(int[] a, int[] b, int k) {
        int[][] matrix = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                matrix[i][j] = a[i] + b[j];
                System.out.print(String.format("%3d", matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println(KSmallestInSortedMatrix.kthSmallest(matrix, 10));
    }

    //faster: klogk
    //think it as a matrix of sum, we first add first column in pq, and then according to what we extracted, we add from row left to row right
    //For every numbers in nums1, its best partner(yields min sum) always start from nums2[0] since arrays are all sorted;
    //And for a specific number in nums1, its next candidate sould be [this specific number] + nums2[current_associated_index + 1], unless out of boundary;)
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return list;
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[0] + a[1] - b[0] - b[1]);

        //heapify O(k)
        for (int i = 0; i < k && i < nums1.length; i++) {
            //store nums1[i] nums2[0] and index of current nums[2];
            //you can see 1 as the fixed side, as all the paris contains every number in 1, and we just need increasing the number of index of 2
            //ex, pq contains nums1[0],nums1[1],nums1[2]...nums1[k] with nums2[0];
            pq.add(new int[]{nums1[i], nums2[0], 0});
        }

        while (list.size() < k && !pq.isEmpty()) {
            int[] cur = pq.poll();
            list.add(new int[]{cur[0], cur[1]});
            //index of nums2, if the fixed side index of current pop array is euqals to fixed side length; we have already offer all the possibility
            if (cur[2] == nums2.length - 1) continue;
            //the current min is [nums1[i],nums2[index2],index2], as for each same nums1[i], we need to add larger one nums[i],nums[index2+1],index2+1 to the queue;
            //and we also preserve the nums1, so that if next time we pop nums1[i],nums[index2+1], we can add nums[i],nums[index2+2]
            //in this case, we can scan all the possibility
            pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
            System.out.println(cur[0] + "," + nums2[cur[2] + 1] + "," + (cur[2] + 1));
        }

        return list;

    }
}
