public class searchRange_34 {

    /*
       解法一                   不推荐！！！

    排序 + O(log n)：二分

    误区：
    使用二分查找方法看到的处在中间位置的元素的值nums[mid]恰好等于目标元素target的时候，还需要继续查找下去；
    此时比较容易陷入的误区是线性查找，正确的做法是继续二分查找

    要分别用两个函数找target第一次出现、最后一次出现的位置，因为只用一个函数的话无法在nums[mid]==target时再二分
     */

    public int[] searchRange(int[] nums, int target) {

        // 排序 + O(log n)：二分

        int[] result = new int[]{-1,-1};

        // 要分别用两个函数找target第一次出现、最后一次出现的位置，因为只用一个函数的话无法在nums[mid]==target时再二分
        searchFirst(nums,0,nums.length-1,target,result);
        searchLast(nums,0,nums.length-1,target,result);

        return result;
    }


    private void searchFirst(int[] nums, int left, int right, int target, int[] result){

        if(left>right){
            return;
        }

        int mid = (left+right)/2;

        if(nums[mid] > target){
            right = mid-1;
            searchFirst(nums,left,right,target,result);
        }else if(nums[mid] < target){
            left = mid+1;
            searchFirst(nums,left,right,target,result);
        }else{    // nums[mid]==target，这时再向左边找target第一次出现的位置
            if(result[0]==-1 || mid<result[0]){
                result[0] = mid;
            }

            right = mid-1;
            searchFirst(nums,left,right,target,result);
        }
    }

    private void searchLast(int[] nums, int left, int right, int target, int[] result){

        if(left>right){
            return;
        }

        int mid = (left+right)/2;

        if(nums[mid] > target){
            right = mid-1;
            searchLast(nums,left,right,target,result);
        }else if(nums[mid] < target){
            left = mid+1;
            searchLast(nums,left,right,target,result);
        }else{    // nums[mid]==target，这时再向右边找target最后一次出现的位置
            if(result[1]==-1 || mid>result[1]){
                result[1] = mid;
            }

            left = mid+1;
            searchLast(nums,left,right,target,result);
        }
    }


    /*
    解法二 from 我，先通过二分法找到一个符合题意的，然后向两边扩展
     */
    int startIndex = -1;
    int endIndex = -1;


    public int[] searchRange2(int[] nums, int target) {

        // 找到一个后，向左右扩展
        binarySearch(nums, 0, nums.length-1, target);

        int[] res = new int[2];

        res[0] = startIndex;
        res[1] = endIndex;

        return res;

    }


    void binarySearch(int[] nums, int left, int right,int target){

        if(left > right){
            return;
        }

        int mid = (left + right)/2;

        if(nums[mid] == target){  // 找到了一个target，向左右两边扩展
            startIndex = mid;
            endIndex = mid;

            int i=mid-1;

            while(i>=0 && nums[i]==target){
                startIndex = i;

                i--;
            }

            int j = mid+1;

            while(j<nums.length && nums[j]==target){
                endIndex = j;

                j++;
            }

            return;
        }else if(nums[mid] > target){

            binarySearch(nums, left, mid-1, target);
        }else{
            binarySearch(nums, mid+1, right, target);
        }
    }











}
