package BitManipulation;

import java.util.BitSet;
import java.util.Scanner;

/**
 * Created by rliu on 5/4/17.
 */
public class JavaBitSet {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        BitSet b1 = new BitSet(m);
        BitSet b2 = new BitSet(m);

        for (int i = 0; i < n; i++) {
            String operation = scan.next();
            int operand1 = scan.nextInt();
            int operand2 = scan.nextInt();
            if (operation.equals("AND")) {
                if (operand1 == 1)
                    b1.and(b2);
                else
                    b2.and(b1);
            } else if (operation.equals("OR")) {
                if (operand1 == 1)
                    b1.or(b2);
                else
                    b2.or(b1);
            } else if (operation.equals("XOR")) {
                if (operand1 == 1)
                    b1.xor(b2);
                else
                    b2.xor(b1);
            } else if (operation.equals("SET")) {
                if (operand1 == 1)
                    b1.set(operand2);
                else
                    b2.set(operand2);

            } else if (operation.equals("FLIP")) {
                if (operand1 == 1)
                    b1.flip(operand2);
                else
                    b2.flip(operand2);
            }
            //cardinarlity: Returns the number of bits set to {@code true} in this {@code BitSet}.
            System.out.println(b1.cardinality() + " " + b2.cardinality());

        }
    }
}
