package Linear.String;

/**
 * Created by rliu on 12/19/16.
 * 28. Implement strStr()
 * Implement strStr().
 * check if string is a part of another string
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {
    public static void main(String[] args) {
        System.out.println(strStrNaive("mississippi", "mississippi"));

    }

    //primitive idea with be search from each index of haystack, which will take O(n*k) k is the length of needle
    //take me long time to figure out the corner cases
    public static int strStrNaive(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0)
            return 0;
        if (needle.length() > haystack.length())
            return -1;
        for (int start = 0; start <= haystack.length() - needle.length(); start++) {
            boolean isMatch = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(start + j) != needle.charAt(j)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch)
                return start;
        }
        return -1;
    }

    //ex.haystack=="aaaaaacbd" and needle="aaaacbd" we kind of want to start from c, cause a are all the same
    public static int strStr(String haystack, String needle) {
        if (haystack == null | haystack.length() == 0)
            return 0;
        int start = 0;
        int sameCount = 0;
        while (start <= haystack.length() - needle.length()) {
            int j = start + sameCount;
            int i = 0;
            while (haystack.charAt(j) == needle.charAt(i)) {
                if (haystack.charAt(j) == haystack.charAt(start))
                    sameCount++;
                j++;
                i++;
            }
            if (j == needle.length())
                return start;
            else if (sameCount != 0 && start + sameCount == j) {
                while (haystack.charAt(j) == haystack.charAt(start)) {
                    j++;
                    start++;
                }
                // } else if (haystack.charAt(j) != haystack.charAt(start)) {
            } else {
                start = j + 1;
                sameCount = 0;

            }
        }

        return start;

    }
}
