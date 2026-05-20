public class maxProduct_152 {

    /*
    方法一 from 我

    要分开考虑 i为正数 和 负数的情况
     */
    public int maxProduct1(int[] nums) {

        // 乘积最大，   连续

        // dp[i][1]: 以i结尾的、乘积最大的正数    dp[i][0]: 以i结尾的、乘积绝对值最大的负数 (必须包含)

        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        int[][] dp = new int[length][2];

        // 初始化
        for(int i=0; i<length; i++){
            if(nums[i] >= 0){
                dp[i][1] = nums[i];
            }else{
                dp[i][0] = nums[i];
            }
        }

        int result = Math.max(dp[0][0], dp[0][1]);


        for(int i=1; i<length; i++){
            if(nums[i] >=0 ){
                dp[i][1] = Math.max(dp[i-1][1] * nums[i], nums[i]);
                dp[i][0] = dp[i-1][0] * nums[i];

                result =  Math.max(result, dp[i][1]);
            }else{   // nums[i]为负数
                dp[i][1] = dp[i-1][0] * nums[i];
                dp[i][0] = Math.min(dp[i-1][1] * nums[i], nums[i]);

                result =  Math.max(result, dp[i][1]);
            }
        }

        return result;
    }

    /*
    对方法一的改进 不用特地初始化
     */
    public int maxProduct(int[] nums) {

        // dp[i][1]:到第i个元素的最大值  一定包含
        // dp[i][0]:到第i个元素的最小值

        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        int[][] dp = new int[length][2];

        dp[0][1] = nums[0];
        dp[0][0] = nums[0];

        int max = nums[0];

        for(int i=1; i<length; i++){

            dp[i][1] = getMax(nums[i], dp[i-1][1]*nums[i], dp[i-1][0]*nums[i]);
            dp[i][0] = getMin(nums[i], dp[i-1][1]*nums[i], dp[i-1][0]*nums[i]);

            max = Math.max(max, dp[i][1]);
        }

        return max;
    }
    


    /*
    方法二 from 我，思路对，但运行时会超出内存限制
     */
    public int maxProduct2(int[] nums) {

        // 连续

        // dp[i][j] ：子数组nums[i,j]的乘积

        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        int max = nums[0];

        int[][] dp = new int[length][length];

        // 初始化 dp[i][i] = nums[i]
        for(int i=0; i<length; i++){
            dp[i][i] = nums[i];
            max = Math.max(max, dp[i][i]);
        }

        // 递推
        for(int i=0; i<length; i++){
            for(int j=i+1; j<length; j++){

                dp[i][j] = dp[i][j-1] * nums[j];

                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }


    /*
    方法三 from GPT， 不用dp[]
     */
    public int maxProduct3(int[] nums) {

        int length = nums.length;
        int ans = nums[0];
        int curMax = nums[0];  // 当前最大乘积
        int curMin = nums[0];  // 当前最小乘积（处理负数）

        for (int i = 1; i < length; i++) {
            int temp = curMax;  // 保存旧值用于计算curMin

            curMax = Math.max(nums[i], Math.max(curMax * nums[i], curMin * nums[i]));  // 因为nums[i]可能为正数，可能为负数
            curMin = Math.min(nums[i], Math.min(temp * nums[i], curMin * nums[i]));

            ans = Math.max(ans, curMax);
        }

        return ans;
    }


    /*
    我对方法三的改进，更好理解
     */
    public int maxProduct3_2(int[] nums) {

        int max = nums[0];   // 是必须包括nums[i]的局部最大值
        int min = nums[0];
        int result = nums[0];  // 全局最大值，不一定包括nums[i]

        for (int i = 1; i < nums.length; i++) {

            int cur1 = max * nums[i];
            int cur2 = min * nums[i];

            max = getMax(nums[i], cur1, cur2);  // 关键：nums[i] 本身也要参与比较
            min = getMin(nums[i], cur1, cur2);

            result = Math.max(result, max);  // 更新全局最大值
        }

        return result;
    }

    int getMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    int getMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }







}
