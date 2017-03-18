package MainJING;

/**
 * Created by rliu on 3/18/17.
 * rules with byte array, decode two byte if arr[index] is 1, decode one byte if arr[index] is 0
 */
public class DecodeNumber {
    byte[] byteArray;

    public DecodeNumber(byte[] input) {
        byteArray = input;
    }

    public static void main(String[] args) {
        byte[] input = new byte[]{1, 0, 0, 1, 1, 1, 0, 0, 1};
        DecodeNumber decodeFunc = new DecodeNumber(input);
        System.out.println(decodeFunc.decodeBefore(0)); //out of bound
        System.out.println(decodeFunc.decodeBefore(1)); //can not decode
        System.out.println(decodeFunc.decodeBefore(2)); //can not decode
        System.out.println(decodeFunc.decodeBefore(5));
    }

    public char decodeAfter(int index) {
        if (index < 0 || index >= byteArray.length)
            throw new IndexOutOfBoundsException("Index is out of Bounds");
        if (byteArray[index] == 0)
            return getCharApi(byteArray[index]);

        if (byteArray[index] == 1) {
            if (index + 1 >= byteArray.length)
                throw new IllegalArgumentException("Can not decode at current index");
            else
                return getCharApi(byteArray[index], byteArray[index + 1]);
        }
        //should not reach here
        throw new UnknownError("Unknown error");
    }

    //Analysis: for any two byte [index-2,index-1]
    //[0,0] always decode index-1, as this already create a valid input
    //[0,1] throw exception as no matter decode two byte [0,1] or one byte[1], it is not a valid input
    //[1,0] two option: decode two byte[1,0] or decode one byte[0]
    //[1,1] always decode index-2,index-1, as we already can give user a valid decoded char.
    public char decodeBefore(int idx) {
        //as we want to decode the byte in front of index, if index-1<=0, there are nothing at index-1 in the byteArray, throw index out of bounds exception
        if (idx <= 0 || idx > byteArray.length)
            throw new IndexOutOfBoundsException("Index is out of Bounds");

        //case [...0,0,idx,...] or [0,idx...]
        if (idx - 2 >= 0 && byteArray[idx - 2] == 0 && byteArray[idx - 1] == 0 || idx - 1 == 0 && byteArray[idx - 1] == 0)
            return getCharApi(byteArray[idx - 1]);
        //case [...0,1,idx,..] or [1,idx...]
        if (idx - 2 >= 0 && byteArray[idx - 2] == 0 && byteArray[idx - 1] == 1 || idx - 1 == 0 && byteArray[idx - 1] == 1)
            throw new IllegalArgumentException("Can not decode at current index");

        //case [...1,0,...]
        if (idx - 2 >= 0 && byteArray[idx - 2] == 1 && byteArray[idx - 1] == 0) {
            //case [...,0,1,0,idx....] and [1,0,idx...]
            if (idx - 3 >= 0 && byteArray[idx - 3] == 0 || idx - 2 == 0)
                return getCharApi(byteArray[idx - 2], byteArray[idx - 1]);
            //case [...,1,1,0,idx.....]
            if (idx - 3 >= 0 && byteArray[idx - 3] == 1)
                return getCharApi(byteArray[idx - 1]);
        }
        //case [....,1,1,idx....]
        else if (idx - 2 >= 0 && byteArray[idx - 2] == 1 && byteArray[idx - 1] == 1)
            return getCharApi(byteArray[idx - 2], byteArray[idx - 1]);

        throw new UnknownError("Unknown error");
    }

    public char getCharApi(byte b1) {
        return (char) b1;
    }

    public char getCharApi(byte b1, byte b2) {
        return (char) (b1 * 10 + b2);
    }

}
