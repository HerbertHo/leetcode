public class search_33 {

    /*
    方法一：暴力法，遍历数组，但不满足时间复杂度 O(log n)
     */


    /*
    方法二：二分搜索

    二分查找的核心：舍弃一半

    mid的左右两边总有一边是完全排序的，可以比较一边的端点和mid的大小、判断该条边是否有序
    从而判断是否舍弃这一边
     */
    int targetIndex = -1;

    public int search(int[] nums, int target) {

        binarySearch(nums, target, 0, nums.length-1);

        return targetIndex;
    }


    void binarySearch(int[] nums, int target, int left, int right){

        if(left > right){
            return;
        }

        int mid = (left+right)/2;

        if(nums[mid] == target){
            targetIndex = mid;
            return;
        }


        if(nums[mid] >= nums[left]){   // 左边有序，注意：必须有=，因为存在left和mid重合的情况  ！！！ 没有=的话[3,1]、target=1时出错
            if(target>=nums[left] && target<=nums[mid]){   // target在左边   // 只能是mid，不能是mid-1，考虑只有一个元素的情况！！！
                binarySearch(nums,target,left,mid-1);
            }else{
                binarySearch(nums,target,mid+1,right);
            }
        }else{   // 右边有序
            if(target>=nums[mid] && target<=nums[right]){   // target在右边
                binarySearch(nums,target,mid+1,right);
            }else{
                binarySearch(nums,target,left,mid-1);
            }
        }
    }








}
