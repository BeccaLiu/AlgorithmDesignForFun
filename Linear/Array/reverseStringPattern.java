package Linear.Array;

/**
 * Created by rliu on 2/5/17.
 * 459. Repeated Substring Pattern
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 */
public class reverseStringPattern {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("b"));
    }

    public static boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0)
            return false;
        int len = str.length();

        for (int i = 1; i <= len / 2; i++) {
            if (len % i == 0) {
                int sep = len / i;
                boolean temp = true;
                for (int j = 1; j < sep; j++) {
                    for (int k = 0; k < i; k++) {
                        if (str.charAt(k) != str.charAt(i * j + k)) {
                            temp = false;
                            break;
                        }
                    }
                    if (!temp)
                        break;
                }
                if (temp)
                    return true;
            }
        }
        return false;
    }
}
