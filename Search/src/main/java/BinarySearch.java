public class BinarySearch {

    // 前提：数组有序
    public int binarySearch(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;

        /*
        易错点一：while中的条件是 left<=right 还是left<right
        解答：   因为是左闭右闭写法，即区间包含left和right，所以left和right可以相等，所以是left<=right而不是left<right
         */
        while(left <= right){
            int mid = (left + right)/2;

            if(nums[mid] > target){
                /*
                易错点二：是 right=mid 还是 right = mid-1
                解答：根据if条件nums[mid]>target知 nums[mid]不是我们搜索的值，所以下一步搜索的区间无需包含mid
                */
                right = mid-1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }

        return -1;
    }



}
