package Advance;

/**
 * Created by rliu on 11/24/16.
 * Edit Distance
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class EditDistance {
    public static void main(String[] args) {
        String a = "head";
        String b = "sad";
        int count[][] = new int[a.length()][b.length()];
        System.out.println(recursiveDis(a, b, 0, 0, count));
        System.out.println(dpDis(a, b));
    }

    //recursive idea is a bottom up idea
    //match[i][j] stands for the min steps wo match a(i,a.length) and b(j,b.length)
    public static int recursiveDis(String a, String b, int i, int j, int step[][]) {
        if (i == a.length())
            return b.length() - j;//b need to delete all the leftover character to match a like a="",b="sad"
        if (j == b.length())
            return a.length() - i;//vice verse previous statement
        if (step[i][j] != 0)
            return step[i][j];

        int res;
        if (a.charAt(i) == b.charAt(j)) {
            res = recursiveDis(a, b, i + 1, j + 1, step);
        } else {
            //insert: a=(head->shead) b=sad => head ad
            int insert = recursiveDis(a, b, i, j + 1, step);
            //replace: a=(head->sead) b=sad =>sed ad
            int replace = recursiveDis(a, b, i + 1, j + 1, step);
            //delete: a=(head->ead) b=sad
            int delete = recursiveDis(a, b, i + 1, j, step);
            //as the insert replace and delete all count for the next level return, in current level we need add one
            res = Math.min(Math.min(insert, replace), delete) + 1;
        }
        step[i][j] = res;
        return res;
    }

    //iterative dp idea is top down idea
    public static int dpDis(String a, String b) {
        if (a == null || a.length() == 0)
            return b.length();
        if (b == null || b.length() == 0)
            return a.length();
        //step[i][j] a{0,i},b{0,j} min steps to match, so step[len(a)][len(b)] will be the result to return
        //the step should be of size[a.length+1][b.length+1]
        //int[][] step = new int[a.length()][b.length()];
        //   "" s  a  d
        //"" 0  1  2  3
        //h  1  1
        //e  2
        //a  3
        //d  4
        int[][] step = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length(); i++)
            step[i][0] = i;

        for (int j = 0; j < b.length(); j++)
            step[0][j] = j;

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j))
                    step[i + 1][j + 1] = step[i][j];
                else
                    //a(i) and a(j) is matched, now the cursor moved to i+1,and j+1, replace i+1 to match j+1 :step[i][j]
                    //i+1 and j is matched, to match i+1 and j+1, we need to insert a(j+1) character after i :step[i+1][j]
                    //i,j+1 is matched, to match i+1, and j+1, we need to delete a(i+1): step[i][j+1]
                    step[i + 1][j + 1] = Math.min(step[i][j], Math.min(step[i][j + 1], step[i + 1][j])) + 1;
            }
        }
        return step[a.length()][b.length()];
    }
}
