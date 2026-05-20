public class MoveZeroesTest_283 {

    /*
    leetcode283 移动零
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    请注意 ，必须在不复制数组的情况下原地对数组进行操作。


    我的想法：用指针i扫描数组，i遇到0就停，然后用j扫描i后面的元素，找到一个非零的就停，交换ij

    老师：其实不用移动0，只需要让 非0数 占据0的位置就好，然后把之后的数组元素全置为0
         用i扫描数组，count记录数组中非0个数，i扫描到的非0数全部给nums[count] -> 巧妙之处：count计数器就是非0下标
         时间复杂度O(n)
     */

    public static void moveZeroes(int[] nums){

        int count = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                nums[count] = nums[i];  // 即使i与count一样 也这么操作
                count++;                // 注意顺序：count++在上一行操作后
            }
        }

        // 上面for循环后，所有非0的都到前面去了，把数组剩下的全部填充成0就行
        for(int i=count; i<nums.length; i++){
            nums[i] = 0;
        }

    }





}
