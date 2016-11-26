package Advance;

/**
 * Created by rliu on 11/25/16.
 * Unique Binary search tree
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class UniqueBST {
    public static void main(String[] args) {
        int n = 3;
        int[] count = new int[n + 1];
        System.out.println(recursionCntImp(n, count));
        System.out.println(count[n]);
        System.out.println(dpCnt(n));
    }

    public static int recursionCnt(int n) {
        if (n == 0 || n == 1)
            return 1;
        int rt = 0;
        for (int i = 0; i < n; i++) {
            rt += recursionCnt(i) * recursionCnt(n - 1 - i);
        }
        return rt;
    }

    //as the previous example has duplicate calculation, count(2) is process in the case n=3 with root 1, but we calculate count(2) again when root=3
    public static int recursionCntImp(int n, int[] cnt) {
        if (n == 0 || n == 1)
            return 1;
        if (cnt[n] != 0)
            return cnt[n];
        int rt = 0;
        for (int i = 0; i < n; i++) {
            rt += recursionCnt(i) * recursionCnt(n - 1 - i);
        }
        cnt[n] = rt;
        return rt;

    }

    //no need waste the space for recursion stack
    //induction route: count[i]=sum(count(j)*count(i-1-j)) for j=[0  i)
    public static int dpCnt(int n) {
        if (n == 0 || n == 1)
            return 1;
        int[] count = new int[n + 1];
        count[0] = count[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - 1 - j];
            }
        }
        return count[n];

    }
}
