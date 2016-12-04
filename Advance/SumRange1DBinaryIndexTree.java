package Advance;

/**
 * Created by rliu on 12/4/16.
 * Sum range Query
 * get clue from segment tree, we will think of using binary index tree
 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/#2d
 * We often need some sort of data structure to make our algorithms faster, this structure was first used for data compression.
 * Now it is often used for storing frequencies and manipulating cumulative frequency tables.
 */
public class SumRange1DBinaryIndexTree {
    int[] arr;
    int[] biTree;

    public SumRange1DBinaryIndexTree(int[] arr) {
        this.arr = arr;
        this.biTree = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            init(i, arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        SumRange1DBinaryIndexTree bit = new SumRange1DBinaryIndexTree(arr);
        System.out.println(bit.sum(1, 6));
        bit.update(2, 0);
        System.out.println(bit.sum(1, 6));
    }

    //update, j+=(j&-j) to get parent
    public void update(int i, int val) {
        int diff = arr[i] - val;
        arr[i] = val;
        init(i + 1, -diff);
    }

    //getSum j-=(j&-j) to get previous stored sum
    public int sum(int i, int j) {
        return getSum(j) - getSum(i - 1);

    }

    public void init(int i, int val) {
        //j&-j: when j=1, j&-j=1 ,so 1+1=2 is the parent of 1;
        //      when j=2, j&-j=2 ,so 2+2=4 is the parent of 2;
        //      when j=4, j&-j=4, so 4+4=8 is the parent of 4;
        //j&-j record the step that current index need to move to the right to get the parent which store the sum of current index
        for (int j = i + 1; j < biTree.length; j += (j & (-j))) {
            biTree[j] += val;
        }
    }

    public int getSum(int j) {
        //ex: when i=5, using i&-i we get 1, so the index 5-1=4 is the where sum of all the previous sum stored
        //    when i=6, we can get 2, so index 6-2=4 is also where the sum of all the previous sum stored;
        int sum = 0;
        for (int i = j + 1; i > 0; i -= (i & -i)) {
            sum += biTree[i];
        }
        return sum;
    }
}
