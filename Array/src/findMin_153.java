public class findMin_153 {

    /*
    方法一 from 我
    排序 + 查找 + O(log n)  =  二分
    类似leet33的旋转排序数组
     */
    int minValue = Integer.MAX_VALUE;

    public int findMin(int[] nums) {

        // 跟leet33的旋转排序数组一样，总有半边有序
        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        binarySearch(nums, 0, length-1);

        return minValue;
    }


    void binarySearch(int[] nums, int left, int right){
        if(left>right){
            return;
        }

        int mid = (left+right)/2;

        if(nums[mid]>=nums[left]){  // 左边有序
            minValue = Math.min(minValue, nums[left]);

            binarySearch(nums, mid+1, right);   // 右边无序，可能有更小的
        }else{   // 右边有序
            minValue = Math.min(minValue, nums[mid]);

            binarySearch(nums, left,mid-1);   // 搜索左边
        }
    }













}
