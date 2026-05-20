public class fib_509 {

    /*
    方法一：常规 递归

    但效率很低
     */
    public int fib1(int n) {
        if(n==0){
            return 0;
        }

        if(n==1){
            return 1;
        }

        return fib1(n-1)+fib1(n-2);
    }



    /*
    方法二：用动态规划分析 from 随想录

     1、确定dp数组以及下标的含义          (下标i：第i个斐波那契数   dp[i]：第i个斐波那契数の值)
     2、确定递归公式(本题直接告诉你了)     dp[i] = dp[i-1]+dp[i-2]
     3、dp数组初始化                   本题也告诉你了，dp[0]=0   dp[1]=1
     4、确定遍历顺序                    因为 i 由 i-1 和 i-2 得到，所以从前向后遍历

     效率最高
     */
    public int fib2(int n) {
        if(n==0){       // 防止定义dp[1]时 下标越界
            return 0;
        }

        if(n==1){
            return 1;
        }

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i=2; i<=n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }



    /*
    方法三：from 华为

    分析：跟ClimbStairs问题几乎一样
         三种方法： 1、递归   2、带HashMap的递归   3、从底向上的循环（不用递归）
     */










}
