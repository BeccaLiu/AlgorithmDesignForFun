package MainJING;

/**
 * Created by rliu on 3/2/17.
 */
public class HungryRabbit {
    int[][] garden;
    int startRow;
    int endRow;

    HungryRabbit(int[][] garden) {
        if (garden == null || garden.length == 0)
            throw new IllegalArgumentException("you gave me garden with nothing");
        this.garden = garden;
        startRow = getCenter(garden)[0];
        endRow = getCenter(garden)[1];
        //System.out.println(garden[startRow][endRow]);
    }

    public static void main(String[] args) {
        int[][] garden = new int[][]{
                {5, 7, 8, 6, 3},
                {0, 0, 7, 0, 5},
                {4, 6, 3, 4, 9},
                {3, 1, 0, 5, 8}};
        int[][] garden2 = new int[][]{};
        HungryRabbit hr = new HungryRabbit(garden2);
        System.out.println(hr.ateCarrot());
    }

    public static int[] getCenter(int[][] garden) {
        int row = garden.length / 2;
        int col = garden[0].length / 2;
        int rowMod = garden.length % 2;
        int colMod = garden[0].length % 2;
        if (rowMod == 1 || colMod == 0) {
            if (garden[row][col - 1] > garden[row][col])
                col--;
        } else if (rowMod == 0 || colMod == 1) {
            if (garden[row - 1][col] > garden[row][col])
                row--;
        } else if (rowMod == 0 || colMod == 0) {
            int tempCol = col;
            int tempCol2 = col;
            if (garden[row - 1][col] > garden[row - 1][col - 1])
                tempCol = col;
            else
                tempCol = col - 1;

            if (garden[row][col] > garden[row][col - 1])
                tempCol2 = col;
            else
                tempCol2 = col - 1;

            if (garden[row - 1][tempCol] > garden[row][tempCol2]) {
                row = row - 1;
                col = tempCol;
            } else {
                col = tempCol2;
            }
        }
        return new int[]{row, col};
    }

    public int ateCarrot() {
        int i = startRow;
        int j = endRow;
        int count = ate(garden, startRow, endRow);
        garden[startRow][endRow] = 0;
        //if can not get carrot, rabbit will sleep
        while (ate(garden, i - 1, j) != 0 || ate(garden, i + 1, j) != 0 || ate(garden, i, j - 1) != 0 || ate(garden, i, j + 1) != 0) {
            int tempRow = i - 1;
            int tempCol = j;
            int max = ate(garden, i - 1, j);
            if (ate(garden, i + 1, j) > max) {
                tempRow = i + 1;
                tempCol = j;
                max = ate(garden, i + 1, j);
            }
            if (ate(garden, i, j - 1) > max) {
                tempRow = i;
                tempCol = j - 1;
                max = ate(garden, i, j - 1);
            }
            if (ate(garden, i, j + 1) > max) {
                tempRow = i;
                tempCol = j + 1;
                max = ate(garden, i, j + 1);
            }
            garden[tempRow][tempCol] = 0;
            count += max;
            i = tempRow;
            j = tempCol;
        }
        return count;
    }

    public int ate(int[][] garden, int i, int j) {
        if (i < 0 || j < 0 || i >= garden.length || j >= garden[0].length)
            return 0;
        return garden[i][j];
    }
}
