public class minCostClimbingStairs_746 {

    /*
    方法一 from 我，大体上正确，有小错误
     */
    public int minCostClimbingStairs1(int[] cost) {

        // 在第i个台阶时支付 cost[i]，可以向上爬1个or2个台阶
        // 可以从0处or1处开始
        // 台阶数为cost.length


        // 1、确定dp数组以及下标的含义
        // 下标i：第i个台阶   dp[i]：走到第i个台阶の最小总代价 (dp[i]一般就是题目要求的)

        // 2、dp数组的初始化 + 3、递推公式
        // i=2可以由i=0跨2步，也可以由i=1跨1步    dp[2] = min(cost[0], cost[1])
        // i=3可以由i=1跨2步，也可以由i=2跨1步    dp[3] = min(dp[1]+cost[1], dp[2]+cost[2])
        // 推导出 dp[i] = min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1])

        // 4、确定遍历顺序                    因为 i 由 i-1 和 i-2 得到，所以从前向后遍历
        int length = cost.length;   // 台阶数

        if(length==0 || length==1){    // 可以从0或1开始
            return 0;
        }

        // cost数组从0到length-1， dp也是
        int[] dp = new int[length];

        dp[0]=0;
        dp[1]=0;

        for(int i=2;i<length; i++){
            dp[i] = min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1]);
        }

        return dp[length-1];
    }

    private int min(int a,int b){
        return a<b?a:b;
    }



    /*
    方法二：对方法一的改正 from 我 same as 随想录

    注意官方示例：从下标为 1 的台阶开始，向上爬2个台阶，到达楼梯顶部 （所以楼梯顶部为 3=length, 而不是2==length-1
    所以我们要求的时dp[length] 而不是dp[length-1]
     */
    public int minCostClimbingStairs2(int[] cost) {

        int length = cost.length;

        if(length==0 || length==1){
            return 0;
        }

        int[] dp = new int[length+1];   // 要计算下标length， 所以长度为 length+1

        dp[0]=0;
        dp[1]=0;

        for(int i=2;i<=length; i++){
            dp[i] = min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1]);
        }

        return dp[length];   // 返回下标length
    }


    /*
    方法三 from 我
     */
    public int minCostClimbingStairs3(int[] cost) {

        // dp[i]：走到第i个台阶 的总花费
        // dp[i] = cost[i] + Math.min(dp[i-1],dp[i-2])

        int length = cost.length;

        int[] dp = new int[length];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i=2; i<=length-1; i++){
            dp[i] = cost[i] + Math.min(dp[i-1],dp[i-2]);
        }

        return Math.min(dp[length-1], dp[length-2]);
    }





}
