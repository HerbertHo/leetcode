public class uniquePaths_62_63 {

    /*
    leet62
     */

    /*
    方法一 from 我，掉进陷阱了
     */
    public int uniquePaths1(int m, int n) {

        // 坐标为 0 ~ m-1 ,   0 ~ n-1

        // 1、 dp[i][j] : 从[0][0]位置到[i][j]的路径数量  --- 题目要求的
        // 2、初始化 dp[1][0]=1  dp[0][1]=1
        // 3、递归 dp[i][j] = dp[i][j-1] + dp[i-1][j]
        // 4、递归顺序：正常

        int[][] dp = new int[m][n];

        dp[1][0]=1;
        dp[0][1]=1;

        for(int i=1; i<=m-1; i++){
            for(int j=1; j<=n-1; j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }

        return dp[m-1][n-1];
    }


    /*
    方法二：修正方法一 from 随想录

    注意：从[0,0]走到第0行的任意一个位置，都只有一种走法（一直向右走）
         从[0,0]走到第0列的任意一个位置，都只有一种走法（一直向下走）

         只有走到其他位置，才满足dp[i][j] = dp[i][j-1] + dp[i-1][j];
     */

    public int uniquePaths2(int m, int n) {

        if(m==1 && n==1){   // 特殊情况：网格只有一格，也算一条路径
            return 1;
        }

        int[][] dp = new int[m][n];

        for(int i=1; i<=m-1; i++){
            dp[i][0] = 1;
        }

        for(int j=1; j<=n-1; j++){
            dp[0][j] = 1;
        }

        for(int i=1; i<=m-1; i++){     // 这个for循环中也没有包含第0行和第0列
            for(int j=1; j<=n-1; j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }

        return dp[m-1][n-1];
    }





    /*
    leet 63
     */

    /*
    方法一 from 我
     */
    public int uniquePathsWithObstacles1(int[][] grid) {

        // 0表示，没有障碍物

        int m = grid.length;
        int n = grid[0].length;

        // 特殊情况判断, 可以省略
//        if(m==1 && n==1){
//            return grid[0][0]==0 ? 1:0;
//        }


        int[][] dp = new int[m][n];

        // 初始化第0行
        int obtIndex = m;  // 障碍物位置，初始化为m指的是默认无障碍物

        for(int i=0; i<m; i++){   // 找障碍物位置

            if(grid[i][0] == 1){
                obtIndex = i;
                break;
            }
        }

        for(int i=0; i<obtIndex; i++){   // 根据障碍物位置赋值
            dp[i][0] = 1;
        }

        // for(int i=obtIndex; i<m; i++){  // 可以省略，因为dp元素默认为0
        //     dp[i][0] = 0;
        // }

        // 初始化第0列
        obtIndex = n;

        for(int i=0; i<n; i++){

            if(grid[0][i] == 1){
                obtIndex = i;
                break;
            }
        }

        for(int i=0; i<obtIndex; i++){
            dp[0][i] = 1;
        }


        // 递推
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){

                if(grid[i][j]==1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }


    /*
    方法二， 对上述写法进行的优化，思想一样 from 随想录
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        //如果在起点或终点出现了障碍，直接返回0
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {  // 如果第0行有一个障碍物，其后的所有都到达不了
            dp[i][0] = 1;   // 默认为0
        }

        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {  // 如果第0列有一个障碍物，其后的所有都到达不了
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 0) ? dp[i - 1][j]+dp[i][j - 1] : 0;  // 和我的写法意思一样
            }
        }
        return dp[m - 1][n - 1];
    }










}
