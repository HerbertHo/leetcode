public class sortColors_75 {

    /*
    思想 from 我：本题本质上就是一个三路快排，由于不需要递归，所以空间复杂度是O(1)
     */
    public void sortColors(int[] nums) {

        // left指向0的末尾，right指向2的开头

        int left = -1;
        int right = nums.length;
        int i = 0;

        while(i < right){   // 跟三路快排一样

            if(nums[i] == 0){
                left++;
                swap(nums, left, i);
                i++;
            }else if(nums[i] == 1){

                i++;
            }else{   // == 2
                right--;
                swap(nums, right, i);   // 此时i不++，因为还没判断新的nums[i]的值
            }
        }
    }


    void swap(int[] nums, int i, int j){
        int temp = nums[i];

        nums[i] = nums[j];
        nums[j] = temp;
    }






}
