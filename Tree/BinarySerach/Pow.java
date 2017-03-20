package Tree.BinarySerach;

/**
 * Created by rliu on 1/11/17.
 * Implement pow(x, n).
 * Key: n can be negative and positive, as well as x
 */
public class Pow {
    public static void main(String[] args) {
        System.out.println(myPow(34.00515, -3));
        // System.out.println(myPow(0.00001, 2147483647));
        //System.out.println(myPow(0,2147483647));
        System.out.println(3 >> 1);
        System.out.println((-3 / 2));
    }

    //x^4=x^2*x^2*x^0
    //x^5=x^2*x^2*x^1
    //
    //x^4=x^-2*x^-2*x^0
    //x^-5=x^-2*x^-2*x^-1
    //so base case will be n==0 n==1 or n==-1

    public static double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        if (n == -1)
            return 1.0 / x;

        //do not use n>>1 as for 3>>1=1 but for -3>>1 = -2
        int mid = n / 2;
        //this is not using the idea of binary split, cause what we want to do is find the clue and remove redudent work
        //return myPow(x,mid)*myPow(x,n-mid);
        double partRs = myPow(x, mid);
        //the return will be either partRs^2 or partRs^2*n
        if (mid == n - mid)
            return partRs * partRs;
        else {
            //for case x^-5=x^-2*x^-2*x^-1
            if (n < 0)
                return partRs * partRs * myPow(x, -1);
                //for case x^4=x^-2*x^-2*x^0
            else
                return partRs * partRs * x;
        }
    }
}
