public class BinarySearch_704 {
    /*
    给定一个n个元素有序的（升序）整型数组nums和一个目标值target ，
    写一个函数搜索nums中的target，如果目标值存在返回下标，否则返回 -1
     */

    // 方法一最常规写法：遍历数组，但这没有利用上数组”有序“
    public int search1(int[] nums, int target) {
        for(int i=0; i<nums.length; i++){
            if(nums[i] == target){
                return i;
            }
        }
        return -1;
    }


    // 方法二：二分查找（左闭右闭） from 代码随想录    -> 推荐
    /*
      二分查找の前提：（1）有序数组  （2）数组中无重复元素，因为一旦有重复元素，使用二分查找法返回的元素下标可能不是唯一的
     */
    public int search2(int[] nums, int target) {

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


    // 方法三：二分查找（左闭右开） from 代码随想录
    public int search3(int[] nums, int target) {

        int left = 0;
        int right = nums.length;  // 不一样

        /*
        易错点一：while中的条件是 left<=right 还是left<right
        解答：因为是左闭右开写法，即区间包含left、不包含right，所以left和right不可以相等，所以是left<right而不是left<=right
         */
        while(left < right){
            int mid = (left + right)/2;

            if(nums[mid] > target){
                /*
                易错点二：是 right=mid 还是 right = mid-1
                */
                right = mid;   // 右开
            }else if(nums[mid] < target){
                left = mid + 1;  // 左闭
            }else{
                return mid;
            }
        }

        return -1;
    }


    /*
    方法四 from 我，多定义一个binaruSearch()函数 --- 为了跟其他题目通用框架
     */
    int resIndex = -1;

    public int search(int[] nums, int target) {

        binarySearch(nums, 0, nums.length-1, target);

        return resIndex;
    }

    void binarySearch(int[] nums, int left, int right, int target){

        if(left > right){
            return;
        }

        int mid = (left + right)/2;

        if(nums[mid] == target){
            resIndex = mid;
        }else if(nums[mid] > target){
            binarySearch(nums,left,mid-1,target);
        }else{
            binarySearch(nums,mid+1,right,target);
        }
    }







}
