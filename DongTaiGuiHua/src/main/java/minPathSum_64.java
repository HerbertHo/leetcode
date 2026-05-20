public class minPathSum_64 {

    /*
    方法一 from 我
     */

    public int minPathSum(int[][] grid) {

        // dp[i][j] : 走到[i][j]的最少数字和

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];


        // 初始化第0行、第0列
        dp[0][0] = grid[0][0];

        for(int i=1; i<m ;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for(int i=1; i<n; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        // 递归
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){

                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }








}
