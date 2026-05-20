public class maximalSquare_221 {

    /*
    方法一， 可以看leetcode官方视频

    易错点：本题matrix是 char数组
     */

    public int maximalSquare(char[][] matrix) {

        // dp[i][j]：以[i][j]为右下角、最大的正方形 的边长！！！
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        int max = 0;   // 记录最大正方形 的边长， 注意不一定是dp[m-1][n-1]

        // 初始化dp:第0行、第0列
        for(int i=0; i<m; i++){
            if(matrix[i][0] == '1'){
                dp[i][0] = 1;
                max = Math.max(1, max);
            }else{     // 可以省略，因为dp默认为0
                dp[i][0] = 0;
            }
        }

        for(int i=0; i<n; i++){
            if(matrix[0][i] == '1'){
                dp[0][i] = 1;
                max = Math.max(1, max);
            }
        }


        // 递归，找最大值
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){

                if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = getMin(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        return max*max;   // 别忘了返回平方！！！
    }


    int getMin(int a, int b, int c){
        a = Math.min(a,b);

        return Math.min(a,c);
    }








}
