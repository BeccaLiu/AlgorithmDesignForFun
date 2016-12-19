package Linear.String;

/**
 * Created by rliu on 12/18/16.
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "110"
 * b = "1000"
 * Return "1110".
 */
public class AddBinary {
    public static void main(String[] args) {
        System.out.println(addBinary("110", "1111"));
        System.out.println(addBinary("1111", "1"));

    }

    public static String addBinary(String a, String b) {
        int add = 0;
        StringBuilder sb = new StringBuilder();
        int aLast = a.length() - 1;
        int bLast = b.length() - 1;
        while (aLast >= 0 && bLast >= 0) {
            int aVal = a.charAt(aLast--) - '0';
            int bVal = b.charAt(bLast--) - '0';
            int curr = aVal + bVal + add;
            sb.insert(0, curr % 2);
            add = curr / 2;
        }
        while (aLast >= 0) {
            int aVal = a.charAt(aLast--) - '0';
            int curr = aVal + add;
            sb.insert(0, curr % 2);
            add = curr / 2;
        }

        while (bLast >= 0) {
            int bVal = b.charAt(bLast--) - '0';
            int curr = bVal + add;
            sb.insert(0, curr % 2);
            add = curr / 2;
        }
        if (add != 0)
            sb.insert(0, add);
        return sb.toString();
    }
}
