package Linear.Array;

/**
 * Created by rliu on 4/26/17 11:59 PM.
 * rotate String
 * bit manipulation
 * Given "abcdefg".
 * offset=0 => "abcdefg"
 * offset=1 => "gabcdef"
 * offset=2 => "fgabcde"
 * offset=3 => "efgabcd"
 */
public class RotateString {
    public static void main(String[] args) {
        rotateString("abcdefg".toCharArray(), 3);
        //ArrayDeque
        //head = (head - 1) & (elements.length - 1)
        //-3 ->101
        //-2 ->110
        //-1 ->111
    }

    public static void rotateString(char[] str, int offset) {
        // write your code here
        if (offset == 0)
            return;

        int end = str.length - 1;
        int idx = end;
        char saved = str[end];
        do {
            int pre = idx;
            idx = idx - offset;
            if (idx < 0)
                idx = -1 & (str.length - 1) + idx + 1;//from 0 to str.length-1,end idex moving left idx position
            if (idx != end)
                str[pre] = str[idx];
            else
                str[pre] = saved;

        } while (idx != end);

    }
}
