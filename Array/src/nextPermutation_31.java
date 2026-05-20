import java.util.Arrays;

public class nextPermutation_31 {


    /*
    思想见 leetcode官方题解， 笔记见ipad

    因为要让新数比现在的数大，但又要尽可能地小，所有从个位（从右向左找）

    特殊情况：321，因为它是递减的（或者说从后像前看是递增的），我们是希望这样"从后往前是递增的"，于是要找违反这规则的数
     */

    public void nextPermutation(int[] nums) {

        public void nextPermutation(int[] nums) {

            int length = nums.length;

            if(length==1){
                return;
            }

            // 1、从后往前找第一个 '左边小于右边'的数 （破坏从后向前递增的数）
            int low=0;
            for(int i=length-2;i>=0;i--){
                if(nums[i] < nums[i+1]){
                    low = i;
                    break;
                }
            }

            if(low==0 && nums[0] > nums[1]){  // 特殊情况：整个数组递减
                Arrays.sort(nums);
                return;
            }

            // 2、找到low后，从右向左找只比nums[low]大一点点的数
            int high=0;

            for(int i=length-1; i>=0; i--){
                if(nums[i] > nums[low]){
                    high = i;
                    break;
                }
            }

            swap(nums, low, high);

            // 3、将low之后的数组改为升序、使整个数组最小（只需要将其逆序即可）
            reversePart(nums, low+1);
        }


        void swap(int[] nums, int low, int high){
            int temp = nums[low];

            nums[low] = nums[high];
            nums[high] = temp;
        }

        void reversePart(int[] nums, int index){
            int left = index;
            int right = nums.length-1;

            while(left < right){
                swap(nums, left, right);

                left++;
                right--;
            }
        }


}
