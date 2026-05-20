public class change_518 {

    /*
    方法一：多重背包，二维数组

    完全背包问题
    dp[i][j]: 使用物品0~i，在背包容量为j时，能装满背包的方案数

    递推公式
    (1)不使用当前硬币，装满容量为j的背包的方法数： dp[i-1][j]
    (2)使用当前硬币，则容量背包为j-nums[i]，的方法数dp[i][j-nums[i]]
                                       为什么是dp[i][j-nums[i]]而不是dp[i][j] ? 因为dp[i][j]无法确保一定使用了当前硬币
    所以 dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]];

    返回：dp[length-1][amount]
     */
    public int change1(int amount, int[] nums) {
        int length = nums.length;

        int[][] dp = new int[length][amount+1];   // 因为最终要返回dp[length-1][amount]

        // 初始化,
        // j=0时，即背包容量为0，装满背包的方式都是1种：什么都取
        for(int i=0; i<dp.length; i++){
            dp[i][0] = 1;
        }

        // i=0时，只使用物品0  dp[0][j]：使用物品0装满容量为j的背包的方法数
        for (int j = nums[0]; j <= amount; j++) {
            if (j % nums[0] == 0) {
                dp[0][j] = 1;
            }
        }

        // 递推
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){

                if(j >= nums[i]){
                    dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]];
                }else{   // j的容量不足以装一个i，那么只能不装i
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[length-1][amount];
    }



    /*
    方法二：多重背包：一维数组
           可从上述二维数组形式轻松得出
     */
    public int change2_1(int amount, int[] nums) {

        // 一维数组：不考虑元素i，只考虑容量j
        // dp[j]：装满容量j的背包、方案数为dp[j]

        int length = nums.length;

        int[] dp = new int[amount+1];   // 因为最终要返回dp[amount]

        // 初始化,
        // j=0时，即背包容量为0，装满背包的方式都是1种：什么都取
        dp[0] = 1;


        // 递推
        // dp[j]: (1)不使用i:  dp[j]    (2)使用i： dp[j-nums[i]]
        for(int i=0; i<nums.length; i++){
            for(int j=1; j<dp.length; j++){

                if(j >= nums[i]){
                    dp[j] = dp[j] + dp[j-nums[i]];
                }else{   // j的容量不足以装一个i，那么只能不装i
                    dp[j] = dp[j];
                }
            }
        }

        return dp[amount];
    }


    /*
    方法二优化：对上述两层for循环进行优化： j从nums[i]开始，避免冗余代码
             经过这个简单优化，效率最佳！！！
     */
    public int change2_2(int amount, int[] nums) {

        // 一维数组：不考虑元素i，只考虑容量j
        // dp[j]：装满容量j的背包、方案数为dp[j]

        int length = nums.length;

        int[] dp = new int[amount+1];   // 因为最终要返回dp[amount]

        // 初始化,
        // j=0时，即背包容量为0，装满背包的方式都是1种：什么都取
        dp[0] = 1;


        // 递推
        // dp[j]: (1)不使用i:  dp[j]    (2)使用i： dp[j-nums[i]]
        for(int i=0; i<nums.length; i++){
            for(int j=nums[i]; j<dp.length; j++){
                dp[j] = dp[j] + dp[j-nums[i]];
            }
        }

        return dp[amount];
    }


    /*
    方法三：跟leetcode416的解法二 基本一样
     */
    public int change3(int amount, int[] nums) {

        // dp[i][j]：使用物品0-i,能装满j的组合数（跟上一题01背包的定义差不多）
        // dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]] ， 不选i 或 选i，注意选i是dp[i][j-nums[i]]，而不是01背包的dp[i-1][j-nums[i]]，因为可以无限选
        // 最终返回dp[length-1][amount]

        int length = nums.length;

        int[][] dp = new int[length][amount+1];

        // 初始化：dp[0][x]:只用物品0
        for(int j=0; j<=amount; j++){

            if(j % nums[0] == 0){
                dp[0][j] = 1;
            }
        }

        // 遍历
        for(int i=1; i<dp.length; i++){
            for(int j=0; j<dp[0].length; j++){

                if(j-nums[i]>=0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }


            }
        }

        return dp[length-1][amount];
    }












}
