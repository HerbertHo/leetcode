public class removeElement_27 {
    /* 题目：
    给你一个数组nums和一个值val，你需要原地移除所有数值等于val的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量
     */

    /* 说明：
    事实上，数组的元素不能”移除“，只能用其他元素”覆盖“它
     */


    // 方法一：暴力实现，两层循环 -> 遍历数组遇到val，用后面的元素覆盖它
    // 超出时间限制，可能有错
    public int removeElement1(int[] nums, int val) {

        int count = 0;
        int i = 0;

        while(i<nums.length){
            if(nums[i] == val){
                for(int j=i+1; j<nums.length; j++){
                    nums[j-1] = nums[j];
                }
            }else{
                i++;
                count++;
            }
        }

        return count;
    }


    /*
    方法二：双指针（快慢指针）
    fast指针指向新数组需要的元素（不等于val）      slow指针指向新数组的下标值
    fast找到非val值，赋给slow
     */

    public int removeElement(int[] nums, int val) {

        int fast = 0;
        int slow = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;   // slow只在满足条件时再后移
            }


            fast++;     // fast指针一直向后移
        }

        return slow;
    }




}
