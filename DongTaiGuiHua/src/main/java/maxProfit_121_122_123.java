public class maxProfit_121_122_123 {

    /*
    leet121   只能买卖一次
     */

    /*
    方法一 from 我，暴力解法，两层for循环，第一层固定i，第二层从i+1开始找差值
     */


    /*
    方法二 from 随想录
    两层dp数组   dp[i][1]代表第i天持有股票时、最大累计收益   dp[i][0]代表第i天不持有股票时、最大累计收益
                第一个数字表示第i天，第二个数字表示持有与否股票
     */
    public int maxProfit1_2(int[] prices) {

        // 只能买卖一次
        // i:第i天  [i][0]:没有股票  [i][1]：有股票
        int length = prices.length;

        if(length==1){
            return 0;
        }

        int[][] dp = new int[length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i=1; i<=length-1; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);  // 第i天不持有股票：（1）i-1天就没有  （2）i-1天有，第i天卖了
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);  // 第i天有股票：(1)第i-1天就有股票   (2)第i-1天没有股票，所以只能是第i天买的（并且是用了唯一一次买股票的机会）
        }

        return Math.max(dp[length-1][0], dp[length-1][1]);
    }


    /*
    方法三：对方法二的错误写法    from 我 , 思想和方法二一样

    错误原因：
    第i天持有股票时，dp[i][1] = Math.max(dp[i-1][1], -prices[i]);  因为只能买一次，所以第i天买了，之前都不能买，所以时 -price[i]
        不能写成：          =  Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);  这么写，就允许多次买卖了！！！

     事实上，这是leet122股票问题的写法
     */

    public int maxProfit1_3(int[] prices) {

        if(prices.length==1){   // 只有一天，不能卖股票
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        dp[0][1] = -prices[0];  // 第0天，有股票
        dp[0][0] = 0;

        for(int i=1; i<=prices.length-1; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);   // 错误原因！！！
        }

        return dp[prices.length-1][0];
    }


    /* 上述解法效率太低
    方法四：因为只能买卖一次，所以找到数组中 使得 price[i] - price[j] 最大的情况 （i > j）

          分为两种情况：（1）若是第i天卖能赚钱，即 price[i]-minprice > 0，尝试更新最大利润
                     （2）         不能赚钱，                   < 0，更新目前找到的最小值，继续向后找能卖的情况
     */
    public int maxProfit1_4(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {

            if(prices[i] < minprice){
                minprice = prices[i];
            }else{
                maxProfit = Math.max(maxProfit, prices[i]-minprice);
            }
        }

        return maxProfit;
    }




    /*
    leet122 可以多次买卖，可以同天买、同天卖
     */

    /*
    方法一 from 我， same as 随想录
    同 maxProfit1_3
     */
    public int maxProfit2_1(int[] prices) {

        if(prices.length==1){
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        dp[0][1] = -prices[0];  // 第0天，有股票
        dp[0][0] = 0;

        for(int i=1; i<=prices.length-1; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }

        return dp[prices.length-1][0];
    }


    /* 上述方法效率还是差一点
    方法二：贪心 from 力扣官方题解

    可以简单理解为；因为可以买卖无数次，所以只要能赚钱就卖

    具体解释可见 力扣官方解释
     */
    public int maxProfit2_2(int[] prices) {

        int profit = 0;

        for(int i=1; i<prices.length; i++){
            profit += Math.max(0, prices[i] - prices[i-1]);
        }

        return profit;
    }




    /*
    leet123
    与121、122的区别： 121只能买卖一次    122 可以买卖多次   123 最多买卖2次
    */

    /* 思路 from 随想录
    由于至多可以两次买卖，所以状态比之前更多了：
    dp[i][0] : 第i天，还没买
    dp[i][1] : 第i天，第一次持有股票（第一次买入和第一次卖出之间） 、最大累计收益
    dp[i][2] : 第i天，第一次卖出和第二次买入之间、最大累计收益
    dp[i][3] : 第i天，第二次持有股票（第二次买入和第二次卖出之间）、最大累计收益
    dp[i][4] : 第i天，第二次卖出股票、最大累计收益
     */

    /*
    方法一： 实现from我， 思路from随想录
     */
    public int maxProfit3_1(int[] prices) {
        int length = prices.length;

        if(length==1){
            return 0;
        }

        int[][] dp = new int[length][5];

        // 易错点：第0天5个状态都要初始化，因为第1、2...天的结果都基于第0天
        dp[0][0] = 0;
        dp[0][1] = -prices[0];   // 第0天，第一次持有股票
        dp[0][2] = 0;            // 第0天，第一次卖出之后（第0天先买再卖）
        dp[0][3] = -prices[0];   // 第0天进行 买 -> 卖 -> 买
        dp[0][4] = 0;            // 第0天进行 买 -> 卖 -> 买 -> 卖

        for(int i=1; i<=length-1; i++){
            dp[i][0] = 0;   // 一直还没买股票，累计收益为0
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);  // 第i天第一次持有股票：（1）第i-1天就持有了 （2）第i天才买的
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]+prices[i]); // 第i天第一次卖出之后：（1）第i-1天就卖出了  （2）第i天才卖的
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2]-prices[i]); // 第i天第二次持有股票：（1）第i-1天就第二次持有了 （2）第i天才第二次持有
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3]+prices[i]);
        }

        return getMax(dp[length-1][0], dp[length-1][2], dp[length-1][4]);  // 从一次没买股票、买卖一次股票、买卖两次股票中取max
    }


    int getMax(int a, int b, int c){
        int temp = Math.max(a,b);
        return Math.max(temp,c);
    }



    /*
    上述解法效率低

    解法二 from leetcode解法, 其实本质是一样的 
     */
    public int maxProfit3_2(int[] prices) {
        int n = prices.length;

        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;

        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        return sell2;
    }










}
