public class canPartition_416 {

    /*
    方法一 ： 背包法 from 随想录

     所有元素之和为22， 要找一个子集之和为11

     可以使用回溯算法暴力输出所有情况，找子集之和为11的

      转化为背包问题：选取物品，能不能装满容量为11的背包 (本题元素不可以重复使用，所以是01背包)

       01背包问题的dp[j] : 容量为j的背包、最大价值为dp[j]
      本题的元素 1、5、11 既是 物品的重量、也是物品的价值 ， 所以要找dp[11]==11

       递推公式（状态转移方程）： dp[j] = max(dp[j], dp[j-weight[i]] + value(i)) = max(dp[j] + dp[j-nums[i]] + nums[i])

       初始化：理论课讲过，初始化为0
     */
    public boolean canPartition1(int[] nums) {
        int sum = 0;

        for(int i=0; i<nums.length; i++){
            sum += nums[i];
        }

        if(sum % 2 == 1){   // 如果sum为奇数，除以2后target有小数，不可能
            return false;
        }

        int target = sum / 2;

        int[] dp = new int[target+1];  // 最后要判断dp[target]==target， 所以dp中target这个下标要合法

        for(int i=0; i<nums.length; i++){
            for(int j=target; j>=nums[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }

        return dp[target]==target;
    }


    /*
    方法二，比较好理解
    https://www.bilibili.com/video/BV1oZ4y1G7QY/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */
    public boolean canPartition2(int[] nums) {

        // dp[i][j]:在原数组中"下标"[0,i]中 任选一些元素，能不能组成和为j ？
        // 最终返回dp[length-1][sum/2]：在原数组所有元素中任选元素，能不能组合和为sum/2

        int length = nums.length;

        int sum = 0;

        for(int x:nums){
            sum += x;
        }

        if(sum % 2 == 1){
            return false;
        }

        int target = sum/2;

        // 要返回dp[length-1][sum/2]
        boolean[][] dp = new boolean[length][target+1];

        // 递归：可以不选第i个元素dp[i-1][j]，也可以选第i个元素dp[i-1][j-nums[i]]
        // 递归函数：dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]

        // 初始化:dp[0][x]: 只选第0个元素，那么只能组成和为nums[0]
        if(nums[0] < target+1){   // 防止dp越界
            dp[0][nums[0]] = true;
        }

        // 递归，尝试填充dp数组
        for(int i=1; i<dp.length; i++){
            for(int j=0; j<dp[0].length; j++){

                if(nums[i]==j){
                    dp[i][j] = true;  // 第i个元素就能满足和为j
                }else if(nums[i]>j){  // 第i个元素>j，所以不能取第i个元素
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }

        return dp[length-1][target];
    }










}
