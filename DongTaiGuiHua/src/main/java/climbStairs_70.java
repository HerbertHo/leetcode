import java.util.HashMap;
import java.util.Map;

public class climbStairs_70 {

        /*
    方法一：用动态规划分析 from 我

     1、确定dp数组以及下标的含义          (下标i：第i个台阶   dp[i]：走到第i个台阶の方案数)
     2、确定递归公式                    dp[i] = dp[i-1]+dp[i-2]  （从dp[3] = 3 = dp[1]+dp[2]可以验证）
     3、dp数组初始化                    dp[1]=1   dp[2]=2
     4、确定遍历顺序                    因为 i 由 i-1 和 i-2 得到，所以从前向后遍历

    经过上述分析知，本题与斐波那契几乎一模一样，连递归公式都一样
     */
        public int climbStairs1(int n) {
            if(n==1){
                return 1;
            }

            if(n==2){
                return 2;
            }

            int[] dp = new int[n+1];   // 从0到n

            dp[1] = 1;
            dp[2] = 2;

            for(int i=3; i<=n; i++){
                dp[i] = dp[i-1] + dp[i-2];
            }

            return dp[n];
        }





        /*
       以下解法 from 华为
         */
        /*
    解法一（递归，暴力解法）：
    要走到n有两种方式：（1）从n-1走一步        （2）从n-2走两步
    所以 f(n) = f(n-1) + f(n-2)  即 走到第n个的走法 = 走到第n-1个的走法 + 走到第n-2个的走法
    且 f(1)=1  f(2)=2
    评论：时间复杂度太高, 因为存在重复计算，比如n=6时，会有多次重复计算f（3），可以看视频理解
     */
        public static int climbStairs10(int n){
            if(n==1){
                return 1;
            }else if (n==2){
                return 2;
            }else {
                return climbStairs10(n-1)+climbStairs10(n-2);
            }
        }


    /*
    解法二：优化解法一（用HashMap保存过程中已求解过的f(k)值）
    这样可以简化时间复杂度到 O（n）
     */
    public static int climbStairs11(int n){
        Map<Integer,Integer> storeMap = new HashMap();

        if(n==1){
            return 1;
        }else if (n==2){
            return 2;
        }else if(null != storeMap.get((n))){   // storeMap.get((n))不为null，也就是map中已存储了n对应的f（n）值
            return storeMap.get((n));
        }else {
            int value = climbStairs11(n-1)+climbStairs11(n-2);
            storeMap.put(n,value);      // 记得存入
            return value;
        }
    }

    /*
    解法三 : 由于知道f(1)  f(2)，自底向上进行循环，不用递归
     */
    public static int climbStairs12(int n){
        if(n==1){
            return 1;
        }
        if (n==2) {
            return 2;
        }

        int f_prepre = 1;
        int f_pre = 2;
        int result = 0;

        for(int i=3; i<=n; i++){
            result = f_prepre + f_pre;

            f_prepre = f_pre;   // 为下个循环准备。更新pre和prepre的值
            f_pre = result;
        }

        return result;
    }





}
