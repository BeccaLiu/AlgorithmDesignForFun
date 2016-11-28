package Graph;

/**
 * Created by rliu on 11/27/16.
 * Find K Pairs with Smallest Sums
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */
public class KPairsWithSmallestSums {

    public static void main(String[] args) {
        int[] a = {-2, -1, 0, 1, 1, 2, 3};
        int[] b = {-1, 1, 2, 2, 3};
        bestFirstSearch(a, b, 10);
    }


    // as there are two sorted list
    // we can convert this one dimension problem to two dimension problem by lists all the sum into a m*n matrix, and this matrix is sorted from left to right, from top to down
    // we can use heap to get k th pairs or we can use binary search with the help of the right top corner(kSmallestInSortedMatirx)
    public static void bestFirstSearch(int[] a, int[] b, int k) {
        int[][] matrix = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                matrix[i][j] = a[i] + b[j];
                System.out.print(String.format("%3d", matrix[i][j]));
            }
            System.out.println();
        }
        System.out.print(KSmallestInSortedMatrix.kthSmallest(matrix, 10));
    }
}
