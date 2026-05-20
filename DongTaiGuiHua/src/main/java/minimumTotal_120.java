import java.util.List;

public class minimumTotal_120 {

    /*
    方法一 from 我

   arr[i][j] 推出 arr[i+1][j] 或 arr[i+1][j+1]，想到动态规划（有一类路径问题）

    第i行有i+1个元素（行and元素 的下标都是 0到 i）
     */

    public int minimumTotal(List<List<Integer>> triangle) {

        int height = triangle.size();

        // dp[i][j]：到[i][j]时 最小路径和
        int[][] dp = new int[height][height];

        dp[0][0] = triangle.get(0).get(0);   // 第一个元素

        // 递推：dp[i][j] 取决于 dp[i-1][j-1]或dp[i-1][j]
        for(int i=1; i<=height-1; i++){
            for(int j=0; j<=i; j++){

                // 每一行的第一个and最后一个元素 特殊处理
                if(j==0){
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);   // 因为没有dp[i-1][j-1]
                }else if(j==i){
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);   // 因为没有dp[i-1][j]
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                }
            }
        }

        // 在dp[height-1]中找到最小的一个
        int min = dp[height-1][0];

        for(int i=1; i<=height-1; i++){
            min = Math.min(dp[height-1][i], min);
        }

        return min;
    }







}
