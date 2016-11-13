package Tree;

import java.util.Random;

/**
 * Created by rliu on 11/13/16.
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {
    int badVersion;
    int size;

    public FirstBadVersion(int size) {
        this.size = size;
        Random random = new Random();
        badVersion = random.nextInt(size) + 1;
        System.out.println(badVersion);
    }

    public static void main(String[] args) {
        int size = 10;
        FirstBadVersion fbv = new FirstBadVersion(size);
        int start = 1;
        int end = size;
        while (start < end - 1) {
            int mid = start + (end - start) / 2;
            if (fbv.isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        System.out.println(fbv.isBadVersion(start) ? start : end);
    }

    public boolean isBadVersion(int n) {
        if (n > size)
            throw new IllegalStateException();
        return n >= badVersion;
    }

}
