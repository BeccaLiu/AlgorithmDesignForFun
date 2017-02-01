package Linear.String;

/**
 * Created by rliu on 1/31/17.
 * 91. Decode Ways
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 */

//TODO: how to know that we can solve a problem in dp?
public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecodings("10234"));
    }

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] num = new int[s.length() + 1];
        num[0] = 1; //match for ""
        for (int i = 0; i < s.length(); i++) {
            //step 1, match s(0,i-1) aka for 1231|2
            // if curr==0, we can not match s(0,i-1)
            if (s.charAt(i) != '0')
                num[i + 1] = num[i];
            //step 2 match s(0,i-2) as s(i-1,i) may form a valid decode aka 123|12
            //if s(i)==0, and s(i-1) larger than 2 or 0 like 30, 00 , it is we can not match s(0,i-2), as we already can not match s(0,i-1)
            //return 0 as no conversion found
            if (s.charAt(i) == '0' && i - 1 >= 0 && (s.charAt(i - 1) > '2' || s.charAt(i - 1) <= '0'))
                return 0;
                //if for s(i-1,i) fall into range[11,26] this is a valid decode
            else if (i - 1 >= 0 && (s.charAt(i - 1) == '2' && s.charAt(i) <= '6' || s.charAt(i - 1) == '1'))
                num[i + 1] += num[i - 1];
        }
        return num[s.length()];
    }
}
