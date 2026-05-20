import java.util.Arrays;

public class coinChange_322 {


    /*
    方法一 from 我 ， 错误

    错误想法：target / maxValue = count1    取满最大元素会导致错误
           eg  2 5 想得到 11 ， 按我的方法11/5=2...1    1/2=0...1   会找不到解法，但实际上取3个2和1个5可以找到解法
     */
    public int coinChange1(int[] coins, int target) {

        // 硬币最少个数，所以尽量取大的面额

        Arrays.sort(coins);

        int[] count = new int[coins.length];  // 存储每种面额的硬币取了几个
        int countNum = 0;

        int i = coins.length-1;

        while(i>=0 && target>0){
            count[i] = target/coins[i];

            target -= count[i]*coins[i];

            countNum += count[i];
            i--;
        }

        if(target==0){
            return countNum;
        }else{
            return -1;
        }
    }


    /*
    方法二： 完全背包，一维数组
     */
    public int coinChange2_1(int[] coins, int amount) {

        // 完全背包问题
        // dp[j]: 装满容量的j的背包的最小硬币数
        // (1)选i：dp[j-coins[i]]+1   (2)不选i：dp[j]

        if(amount==0){
            return 0;
        }

        // 返回 dp[amount]
        int[] dp = new int[amount+1];

        // 易错点1：初始化dp[j]为Integer.MAX_VALUE   这样遍历时用min找最小值时才会更新dp[j]
        for(int j=0; j<dp.length; j++){
            dp[j] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for(int i=0; i<coins.length; i++){
            for(int j=coins[i]; j<dp.length; j++){

                // 易错点2：必须要有此if判断，否则在下面dp[j-coins[i]]+1会导致Integer溢出 （可以自定义maxValue避免这个问题）
                if (dp[j - coins[i]] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j-coins[i]]+1, dp[j]);
                }
            }
        }

        //  dp[amount]==Integer.MAX_VALUE 说明dp[amount]没有得到更新，即没有找到可以装满容量为amount背包的方法
        return dp[amount]==Integer.MAX_VALUE ? -1 : dp[amount];
    }



    /*
    方法二：对上面溢出问题的改进：自定义maxValue
     */
    public int coinChange2_2(int[] coins, int amount) {

        final int MAX = 20000;   // 自定义MAX

        if(amount==0){
            return 0;
        }

        // 返回 dp[amount]
        int[] dp = new int[amount+1];

        for(int j=0; j<dp.length; j++){
            dp[j] = MAX;
        }

        dp[0] = 0;

        for(int i=0; i<coins.length; i++){
            for(int j=coins[i]; j<dp.length; j++){

                dp[j] = Math.min(dp[j-coins[i]]+1, dp[j]);

            }
        }

        return dp[amount]==MAX ? -1 : dp[amount];
    }


    /*
    leetcode官方题解 好理解
     */
    public int coinChange(int[] nums, int amount) {

        // dp[i]:和为i，所需要使用的最小硬币数
        // 递归：选第j个数：dp[i-nums[j]]+1      不选第j个数字：dp[i]   dp[i] = Math.min( dp[i-nums[j]]+1, dp[i] )
        // 返回dp[amount]

        int length = nums.length;

        int[] dp = new int[amount+1];

        // 初始化
        Arrays.fill(dp, 100000);  // 要让dp[i] = Math.min( dp[i-nums[j]]+1, dp[i] )更新dp[i]

        dp[0] = 0;

        // 递归
        for(int i=0; i<dp.length;i++){   // 硬币和为i
            for(int j=0; j<nums.length; j++){   // 第i个硬币
                if(i-nums[j] >= 0){
                    dp[i] = Math.min(dp[i-nums[j]]+1,dp[i]);
                }
            }
        }

        if(dp[amount]==100000){
            return -1;
        }else{
            return dp[amount];
        }


    }













}
