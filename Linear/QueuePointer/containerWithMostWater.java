package Linear.QueuePointer;

/**
 * Created by rliu on 12/5/16.
 * Container with most water
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * the difference between this question and largest rectangle histogram is
 * The "container of water" solution will allow the water to rise above intermediate positions.
 * With the "largest rectangle" problem, the rectangle cannot rise above intermediate bars.
 */
public class containerWithMostWater {
    public static void main(String[] args) {
        int[] c1 = {2, 4, 3, 6, 3, 2, 4, 7, 3, 1};
        primitiveIdea(c1);
        improvedIdea(c1);

    }

    //primitive idea: list all possibility and calculate, Time: O(N^2)
    public static void primitiveIdea(int[] c) {
        if (c == null || c.length == 0 || c.length == 1)
            return;

        int left = 0;
        int right = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < c.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < c.length; j++) {
                int size = Math.min(c[i], c[j]) * (j - i);
                if (size > max) {
                    left = i;
                    right = j;
                    max = size;
                }
            }
        }
        System.out.println(left + "," + right + ":" + max);
    }

    //as the primitive idea is O(n^2) and if we want to improve it, will make assumption that the improved function will be O(n) but not o(nlogn), as we do not see any chances using binary deduction here
    //[I am stuck]: can not think of the following special character, next time, investigate the some undiscovered feature
    //for i,j, the size will be height * width=Math.min(a[i],a[j])*dist(i,j),
    //assume a[i]<a[j], then any k between i, j, the size will be Math.min(a[i],a[k])*dist(i,k)
    //as dist(i,k) is always smaller than dist(i,j), Math.min(a[i],a[k]) is also smaller or equal Math.min(a[i],a[j])
    //any container between i,j will definitely smaller than sizeCurr
    //key is every time move the smaller index of i,j
    public static void improvedIdea(int[] a) {
        //need two pointer
        int left = 0;
        int right = a.length - 1;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        while (left < right) {
            int size = Math.min(a[left], a[right]) * (right - left);
            if (size > max) {
                max = size;
                start = left;
                end = right;
            }
            if (a[left] < a[right])
                left++;
            else
                right--;
        }
        System.out.println(start + "," + end + ":" + max);
    }
}
