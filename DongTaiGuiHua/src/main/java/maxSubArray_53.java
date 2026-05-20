public class maxSubArray_53 {

    /*
    解法一 from 我

    dp[i] : 以nums[i]结尾的、连续子数组的和(一定包含nums[i])
     */
    public int maxSubArray1(int[] nums) {

        // 连续子数组、拥有最大和

        // dp[i]： 以nums[i]结尾的、连续子数组的最大和

        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        int[] dp = new int[length];

        dp[0] = nums[0];

        int max = dp[0];

        for(int i=1; i<length; i++){

            dp[i] = Math.max(dp[i-1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }

        return max;
    }



    /*
    方法二 from leetcode， 即简单、又高效
     */
    public int maxSubArray2(int[] nums) {
        int cursum = 0;         // 当前子数组的累加和（当前运行状态）
        int ans = nums[0];      // 最终答案，初始化为第一个元素（因为全是负数也要返回一个数）

        for (int i = 0; i < nums.length; i++) {
            cursum += nums[i];  // 累加当前元素

            ans = Math.max(ans, cursum); // 用当前子数组和更新最大值

            if (cursum < 0) {    // 如果当前子数组和为负，舍弃它，从下一个元素重新开始
                cursum = 0;
            }
        }

        return ans;  // 返回最大子数组和
    }










}
