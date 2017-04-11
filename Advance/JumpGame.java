package Advance;

/**
 * Created by rliu on 4/10/17.
 */
public class JumpGame {
    public static void main(String[] args) {
        canJump(new int[]{3, 2, 1, 0, 4});
        canJump(new int[]{2, 3, 1, 1, 4});
        canJump(new int[]{5, 4, 0, 0, 0, 0, 0});
    }

    public static boolean canJump(int[] A) {
        // wirte your code here
        if (A == null || A.length == 0)
            return false;

        //能到的最远的index，只要i在index内说明符合规格
        int index = 0;

        for (int i = 0; i < A.length; i++) {
            if (i > index)
                return false;
            index = Math.max(index, i + A[i]);
        }
        return true;
    }
}
