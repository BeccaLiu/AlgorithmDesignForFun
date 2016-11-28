package Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 11/26/16.
 * Kth Smallest Element in a Sorted Matrix
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * Example:
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 * Best First Search -- Dijkstra's algorithm -- weighted breadth first search
 */
public class KSmallestInSortedMatrix {

    public static void main(String[] args) {
        int[][] a = {
                {0, 0, 2, 3},
                {1, 1, 2, 5},
                {2, 3, 3, 5},
                {2, 3, 4, 5}
        };
        System.out.println(kthSmallest(a, 13));
        System.out.println(kthSmallestWithoutVisited(a, 13));
        System.out.println(binarySearchKthSmallest(a, 13));
    }

    //If we just use heap add all m*n number into min-heap, and pop first k, the add will take O((m*n-k)*log(k)) and pop will take O(klog(m*n)), which is not good
    //using heap and hashSet
    //Time Complexity:O(klog(max(m,n)) 0,0,1,1,2,2,2,2,3,3| 3,3,4(pop the kth element in min-heap)
    //space Complexity: O(m*n) <-boolean[][] /the heap will take k space
    public static int kthSmallest(int[][] a, int k) {
        if (a == null || a.length == 0)
            return Integer.MAX_VALUE;
        if (k > a.length * a[0].length)
            return Integer.MAX_VALUE;

        PriorityQueue<Pointer> minHeap = new PriorityQueue<>(new Comparator<Pointer>() {
            @Override
            public int compare(Pointer o1, Pointer o2) {
                return o1.value - o2.value;
            }
        });
        boolean[][] visited = new boolean[a.length][a[0].length];
        minHeap.add(new Pointer(a[0][0], 0, 0));
        int count = 0;
        while (!minHeap.isEmpty()) {
            Pointer cur = minHeap.poll();
            count++;
            if (count == k) {
                System.out.println(cur);
                return cur.value;
            }
            if (cur.row + 1 < a.length && visited[cur.row + 1][cur.col] == false) {
                minHeap.add(new Pointer(a[cur.row + 1][cur.col], cur.row + 1, cur.col));
                visited[cur.row + 1][cur.col] = true;
            }
            if (cur.col + 1 < a[0].length && visited[cur.row][cur.col + 1] == false) {
                minHeap.add(new Pointer(a[cur.row][cur.col + 1], cur.row, cur.col + 1));
                visited[cur.row][cur.col + 1] = true;
            }
        }
        return Integer.MAX_VALUE;
    }

    //we need visited, is because in the previous attempt, we move both right and down, and conflict happens when the previous down meets current right
    //inorder to avoid this, we can define someway, that current only check right, or only check down
    //as the matrix is sorted from left to right and up to down, we can add all nodes at col 0, and poll and check right every time
    //space: O(m) m=array.length
    //time: O(klogm)
    public static int kthSmallestWithoutVisited(int[][] a, int k) {

        if (a == null || a.length == 0)
            return Integer.MAX_VALUE;
        if (k > a.length * a[0].length)
            return Integer.MAX_VALUE;

        PriorityQueue<Pointer> minHeap = new PriorityQueue<>(new Comparator<Pointer>() {
            @Override
            public int compare(Pointer o1, Pointer o2) {
                return o1.value - o2.value;
            }
        });
        for (int i = 0; i < a.length; i++)
            minHeap.add(new Pointer(a[i][0], i, 0));
        int count = 0;
        while (!minHeap.isEmpty()) {
            Pointer cur = minHeap.poll();
            count++;
            if (count == k)
                return cur.value;
            if (cur.col + 1 < a[0].length) {
                minHeap.add(new Pointer(a[cur.row][cur.col + 1], cur.row, cur.col + 1));
            }
        }
        return Integer.MAX_VALUE;
    }

    //Is there a way to do it without heap to save the space:
    //think about find target in a sorted matrix
    //using binary search idea and combine with the special character of the top right corner
    public static int binarySearchKthSmallest(int[][] a, int k) {
        if (a == null || a.length == 0)
            return Integer.MAX_VALUE;
        if (k > a.length * a[0].length)
            return Integer.MAX_VALUE;

        int start = a[0][0];
        int end = a[a.length - 1][a[0].length - 1];
        while (start < end) {
            int mid = (start + end) / 2;
            int i = 0;
            int j = a[0].length - 1;
            int count = 0;
            while (i >= 0 && i < a.length && j >= 0 && j < a[0].length) {
                if (a[i][j] > mid)
                    j--;
                else {
                    count += j + 1;
                    i++;
                }
            }
            if (count == k)
                return mid;
            else if (count < k) {
                start = mid + 1;
            } else
                end = mid - 1;

        }
        return start;
    }

    private static class Pointer {
        int value;
        int row;
        int col;

        public Pointer(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return value + ":" + row + "/" + col;
        }
    }
}
