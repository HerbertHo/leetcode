public class integerBreak_343 {

    // 拆掉n，乘积max （k不是给定的）



    /*
    方法一：动态规划 from 随想录
     */
    public int integerBreak1(int n) {
        //dp[i] 为正整数 i 拆分后的结果的最大乘积
        int[] dp = new int[n+1];

        dp[2] = 1;

        for(int i = 3; i <= n; i++) {
            for(int j = 1; j <= i-j; j++) {
                // 这里的 j 其实最大值为 i-j,再大只不过是重复而已，
                //并且，在本题中，我们分析 dp[0], dp[1]都是无意义的， j 最大到 i-j,就不会用到 dp[0]与dp[1]

                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                //而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
        }

        return dp[n];
    }


    /*
    针对上述思想，我的解法
     */
    public int integerBreak1_2(int n) {

        // dp[i]：将i拆开、相乘、得到的最大乘积
        // 最终求dp[n]

        int[] dp = new int[n+1];

        dp[2] = 1;

        for(int i=3; i<=n; i++){

            for(int j=1; j<=i-1; j++){   // 将i拆为 j 与 i-j， j与i-j可以再拆

                // maxJ : 固定i时，若在j处拆分，获得的最大值
                int maxJ = Math.max(j * (i-j), j*dp[i-j]);  // i-j是不拆分， dp[i-j]是强制拆分i-j为至少2个数， 这是两种情况

                dp[i] = Math.max(dp[i], maxJ);   // 根据maxJ、尝试更新dp[i]
            }
        }

        return dp[n];
    }





    /*
    方法二 from chatGPT

    可以用数学证明，当 n>=4 时，尽量将n拆分为多个 3
    数学证明可见chatGPT


     */
    public int integerBreak2(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int res = 1;

        while (n > 4) {
            res = res * 3;  // 把n拆成多个3
            n = n - 3;
        }

        return res * n;   // 剩下的n最大为4
    }













}
