package Linear.Array;

/**
 * Created by rliu on 4/5/17.
 */
public class FirstMissingNumber {
    public static void main(String[] args) {
        firstMissingPositive(new int[]{9, 4, -1, 2, 1, 3, 6, 5, 7, 8, 8, 9});
    }

    public static int firstMissingPositive(int[] A) {
        // write your code here

        if (A == null)
            return 1;
        for (int i = 0; i < A.length; i++) {
            while (A[i] <= A.length && A[i] > 0 && A[i] != i + 1) {
                int index = A[i] - 1;

                //swap the number to it is correct location
                int temp = A[index];
                if (A[i] == temp)
                    break;
                A[index] = A[i];
                A[i] = temp;

            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1)
                return i + 1;
        }
        return A.length + 1;

    }
}
