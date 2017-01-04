package Linear.QueuePointer;

/**
 * Created by rliu on 1/2/17.
 * 287. Find the Duplicate Number
 * Binary Serach/Two Pointer
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Note:
 * 1.You must not modify the array (assume the array is read only).
 * 2.You must use only constant, O(1) extra space.
 * 3.Your runtime complexity should be less than O(n2).
 * 4.There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindDuplicate {
    //Analysis:
    //solution 0: native, iterate the list using 2 for loop, check if arr[i]==arr[j], which take O(n2) space, against third requirement
    //solution 1: sort the array, and scan once to find the duplicate, take O(nlogn)time, and take O(1) space, However against the first requirement: can not modified the array
    //solution 2: use a O(n) space array, set initial with True, scan the array, if arr[i]=False, set it to True, if it is already True, find the duplicate, however, against second requirement
    //requirement 3: if the runtime is less than O(n2), we may guess the running time is O(nlogn) or O(n) with O(1) space, however, I think O(nlogn) is more possible
    //a common algorithm with O(nlogn) is binary search

    //O(nlogn) think it as logn*n
    //for the range [1,n] count the number of appear less than mid, and more than mid
    public static int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int leftCount = 0;
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid && nums[i] >= left)
                    leftCount++;
            }
            if (leftCount > (mid - left + 1))
                right = mid;
            else
                left = mid + 1;
            leftCount = 0;
        }
        return left;
    }

    //like linkedlistCycle2
    //linked first with last, loop the array for every, till slow and fast meet
    //1. matching index and number like for 213 0->2, 1->1, 2->3
    //2. if no duplicate, the number is matching one by one, and using generated number as new index 213: 0->2->3 until the index 3 is out of bound
    //3. if has duplicate, there will be loop and endless list: 2131, matching: 0->2, {1，3}->1, 2->3 ;list is 0->2->3->1->1->1->1->... and the start of the loop will be the duplicate number
    public static int findDuplicateTwoPointer(int[] nums) {
        int slow = 0; //index of slow pointer
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
            //if input has no dup
//            if(fast>=nums.length)
//                break;
        } while (slow != fast);
        //return slow;
        //can not directly return slow as for case: 1 3 4 2 2, the list is 0,3,2,4,2,4,2,4..., just return slow, only return somewhere in loop and return 4, however answer is 2
        //using the idea of linked list cycle 2 which try the find the start point of loop, here we use this idea to find the duplicate number
        //why duplicate number is starting of the loop?
        //meaning of slow and fast is index
        //take previous example 1, 3, 4, 2, 2 to like will be 0 ->1-> 3-> 2-> 4-> 2  so 3->2 and 4->2 which means nums[3]=2 nums[4]=2, 2 is the duplicate number
        int find = 0;
        do {
            slow = nums[slow];
            find = nums[find];
        }
        while (slow != find);
        return slow;

    }

    public static void main(String[] args) {
        System.out.print(findDuplicateTwoPointer(new int[]{1, 3, 4, 2, 2}));
    }


}
