package Tree.BinarySerach;

/**
 * Created by rliu on 11/14/16.
 * Search in Rotated Sorted Array
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 1.You may assume no duplicate exists in the array.
 * <p>
 * Step 1: which part is mid in? monotone part or rotated part
 * Step 2: which part may target be in?
 * <p>
 * 2.if there is duplicates
 * Step 1: duplicates may affect the mid
 * * when this is a follow-up, try to think about what assumption the rules is breaking and how to fix it
 */
public class SearchRotatedArray {
    public static int searchRotatedNoDup(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target)
                return mid;
            //no matter what, the array is split by mid in two kind of format, one is increasing, and the other hash pivot point
            //what's more, for these is a case: mid ==start as mid=start+(end-start)/2;
            //step 1: which part mid is
            //inside step 1: which part my target is
            if (arr[mid] >= arr[start]) { //left part is increasing part
                //        ^- is >= not >, just > can not fix the input like[2,1] find 1, while mid=start;
                if (arr[start] <= target && target < arr[mid])  //target is in the increasing part
                    end = mid - 1;
                else
                    start = mid + 1;
            } else { //right part is increasing part
                if (arr[mid] < target && target <= arr[end]) //target is in the increasing part
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            //2. compare mid with target, these method will not work, be aware
            //ex, while target>nums[mid],  and next we want to eliminate half part by if(target>nums[right])
            //if nums=[3 4 5 6 7 0 1 ] target=7 mid=6 here target is larger than nums[right]=1 and 7 is at right side
            //if nums=[5 6 7 0 1 3 4 ] target=7 mid=0 here target is also larger than nums[right]=4 and 7 is at left side
            //the condition is not genetic to all cases.
            //the key here is to know that, no matter how far you binary go shrink the solution space, there is only two possibilities
            //a. the whole array is sorted, aka arr[start]<arr[end];
            //b. the array is still rotated, aka arr[start]>arr[end];
        }

        //another more straightforward solution:
        //while(start+1<end) ->the last iteration in array is start mid end three spot
        //and inside while loop you can if (arr[mid] > arr[start]) as mid will never duplicate with start
        //and at the end of each iteration
        //check if(start==target) or end==target, we can cover the check start mid and end ==target all three cases;
        return -1;
    }


    //why if there are duplicates, the above code will not work?
    //previous question:
    //step 1: which part mid is
    //inside step 1: which part my target is
    //for example 1120111111 we want to find 2, however, mid is equals to start, you still can not define, 2 is in left side or right side.
    public static int searchRotatedDup(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] > arr[start]) {
                if (arr[start] <= target && target < arr[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else if (arr[mid] < arr[start]) {
                if (arr[mid] < target && target <= arr[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            } else { //mid==start
                //reset solution space, while start++ until mid!=start
                start++;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 0, 1, 2, 3, 4, 5};
        System.out.println(searchRotatedNoDup(arr, 6));
        int[] a = {6, 6, 6, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5};
        System.out.println(searchRotatedDup(a, 6));

    }
}
