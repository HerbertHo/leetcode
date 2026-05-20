public class rotate_189 {

    /*
    方法一：反转数组
     */

    public void rotate(int[] nums, int k) {

        // 先反转整数数组 7 6 5 4 3 2 1
        // 再反转前k个数字 5 6 7
        // 再反转后n-k个数组 1 2 3 4

        int length = nums.length;

        k = k % length;  // 易忽略！！

        reversePart(nums,0,length-1);

        reversePart(nums,0,k-1);

        reversePart(nums,k,length-1);
    }


    private void reversePart(int[] nums, int start, int end){

        int i = start;
        int j = end;

        while(i<j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

            i++;
            j--;
        }
    }












}
