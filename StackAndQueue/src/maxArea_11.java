public class maxArea_11 {

    /*
    方法一
    思想 from B站 https://www.bilibili.com/video/BV1Qg411q7ia/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */
    public int maxArea1(int[] nums) {

        // 双指针left和right 从首尾遍历数组，  更新时移动较短的那个坐标

        int length = nums.length;

        int left = 0;
        int right = length-1;
        int max = 0;

        while(left<right){

            int curArea = Math.min(nums[left], nums[right]) * (right-left);

            max = Math.max(max,curArea);

            if(nums[left]>nums[right]){    // 哪个短就移动哪个
                right--;
            }else{
                left++;
            }
        }

        return max;
    }










}
