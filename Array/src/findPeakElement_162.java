public class findPeakElement_162 {

    /*
    方法一：遍历一遍数组，找最大值，最大值一定是峰值
     */


    /*
    方法二：查找元素 + O(log n)，想到二分查找，但本题不是有序，只能用类二分查找

    如果 mid大于左右元素，那么mid就是峰值
    若是 [mid] < [mid+1] ， 比如 7 8 9，说明mid处于上坡，9可能成为峰值，所以在右侧搜索
    若是 [mid] > [mid+1] ， 比如 9 8 7，说明mid处于下坡，9可能成为峰值，所以在左侧搜索
     */

    int res = 0;

    public int findPeakElement(int[] nums) {
        binary(nums, 0, nums.length-1);

        return res;
    }


    void binary(int[] nums, int left, int right){

        if(left > right){
            return;
        }

        int mid = (left+right)/2;

        int leftValue;

        if(mid-1 >= 0){
            leftValue = nums[mid-1];   // leftValue是mid左边的值
        }else{
            leftValue = Integer.MIN_VALUE;
        }

        int rightValue;

        if(mid+1 < nums.length){
            rightValue = nums[mid+1];
        }else{
            rightValue = Integer.MIN_VALUE;
        }

        // 判断(分四种情况)：大于还是小于leftValue？  大于还是小于rightValue？
        if(nums[mid] > leftValue && nums[mid]>rightValue){   // mid比左右值都大，mid就是峰值
            res = mid;
        }else if(nums[mid] <= leftValue && nums[mid]>rightValue){   // 递减趋势，峰值在左半边
            binary(nums, left, mid-1);
        }else if(nums[mid] > leftValue && nums[mid] <= rightValue){  // 递增趋势，峰值在右半边
            binary(nums, mid+1, right);
        }else{    //           <= leftVal 且 <=rightVal，mid就是低谷，左右半边都有可能
            binary(nums,left,mid-1);
            binary(nums,mid+1,right);
        }
    }









}
