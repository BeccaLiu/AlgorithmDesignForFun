package Tree.BinarySerach;

/**
 * Created by rliu on 11/20/16.
 * search a 2D matrix
 * given a 2D matrix : each row is sorted from left to right, first integer in each row is larger than the last integer in previous row
 */
public class SearchIn2DMatrix {
    public static void main(String[] args) {
        int m = 5;
        int n = 10;
        int[][] arr = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = index++;
            }
        }
        System.out.println(searchI(arr, 51));
        System.out.println(searchII(arr, 51));

    }

    public static boolean searchI(int[][] a, int target) {
        if (a == null || a.length == 0)
            return false;
        int start = 0;
        int end = a.length * a[0].length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midVal = a[mid / a[0].length][mid % a[0].length];
            if (midVal == target) {
                System.out.println(mid / a[0].length + "/" + mid % a[0].length);
                return true;
            } else if (midVal < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }

    //the matrix is each row is sorted from left to right, each column is sorted from top to bottom
    //Key: if we do the binary search, we find it is hard to get rid of part of the instance, cause if target>mid, we may go right, and go down
    // find a starting point with only one choice, rather than mid point
    //Time Complexity O(m+n)
    //TODO: why start from the right top corner is working?
    public static boolean searchII(int[][] a, int target) {
        if (a == null || a.length == 0)
            return false;
        int i = 0;
        int j = a[i].length - 1;
        while (i >= 0 && i < a.length && j >= 0 && j < a[0].length) {
            int curr = a[i][j];
            if (curr == target)
                return true;
            else if (curr < target)
                i++;
            else
                j--;
        }
        return false;
    }
}
