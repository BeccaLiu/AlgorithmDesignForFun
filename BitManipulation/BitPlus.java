package BitManipulation;

/**
 * Created by rliu on 4/27/17 11:41 AM.
 */
public class BitPlus {
    public static void main(String[] args) {
        System.out.println(aplusb(-1, 3));
    }

    public static int aplusb(int a, int b) {
        // write your code here, try to do it without arithmetic operators.
        //can only works for positive number
//        int idx=0;
//        int res=0;
//        int add=0;
//        while(a>0||b>0){
//            int i=a&1;
//            int j=b&1;
//            int sum=i+j+add;
//            res+=(sum&1)<<idx;
//            add=sum>>1;
//            a=a>>>1;
//            b=b>>>1;
//            idx++;
//        }
//        res+=add<<idx;
//        return res;

        // 主要利用异或运算来完成
        // 异或运算有一个别名叫做：不进位加法
        // 那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
        // 然后下面考虑哪些地方要进位，自然是a和b里都是1的地方
        // a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
        // 之后的结果。所以：a + b = (a ^ b) + (a & b << 1)
        // 令a' = a ^ b, b' = (a & b) << 1
        // 可以知道，这个过程是在模拟加法的运算过程，进位不可能
        // 一直持续，所以b最终会变为0。因此重复做上述操作就可以
        // 求得a + b的值。
        while (b != 0) {
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;


    }
}
