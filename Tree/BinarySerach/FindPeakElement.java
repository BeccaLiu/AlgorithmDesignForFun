package Tree.BinarySerach;

/**
 * Created by rliu on 1/11/17.
 * 162. Find Peak Element
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        System.out.println(findPeakElementHelper(new int[]{1, 2, 3, 4, 2}));
    }

    //as there are many peak, we only need find one, so the rule of binary no need to be that strict
    public static int findPeakElementHelper(int[] nums) {
        if (nums.length <= 1) return 0;
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else if (nums[mid] < nums[mid + 1])
                l = mid + 1;
        }

        //[I am stuck here]: always try to falls everything under the typical binary search code template, that is wrong
        //the below code I wrote is complicated and hard to understand
//        if(right<left)
//            return -1;
//        int mid=left+(right-left)/2;
//        if((mid==0||mid==nums.length-1)||(mid>0&&mid<nums.length-1&&nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1]))
//            return mid;
//        else if(mid>0&&mid<nums.length-1&&nums[mid]>nums[mid-1]&&nums[mid]<nums[mid+1]){
//            int index=findPeakElementHelper(nums,mid+1,right);
//            if(index!=-1)
//                return index;
//        }
//        else if(mid>0&&mid<nums.length-1&&nums[mid]<nums[mid-1]&&nums[mid]>nums[mid+1]){
//            int index=findPeakElementHelper(nums,left,mid-1);
//            if(index!=-1)
//                return index;
//        }
//        else{
//            int index=findPeakElementHelper(nums,left,mid-1);
//            if(index!=-1)
//                return index;
//            else
//                return findPeakElementHelper(nums,mid+1,right);
//        }
//        return -1;

        return l;
    }
}
