public class generateMatrix_59 {
    /* 题目：螺旋矩阵II  from 代码随想录
    给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix
     */

    /* 方法一：
    笔记：见ipad
     */


    public int[][] generateMatrix1(int n) {

        int[][] matrix = new int[n][n];
        int count = 1;  // 给每个格子赋值，从1到n²

        for(int round=0; round<=n/2-1; round++){   // round：正在转第几圈(第0圈到第n/2-1圈)
            // 上边(第round行) [round][round] -> [round][n-round-2]  每条边的最后一个节点留给下一条边处理  -> 每条边都是处理 n-2round-2个元素
            for(int i=round; i<=n-round-2; i++){
                matrix[round][i] = count;
                count++;
            }

            // 右边（第n-round-1列）, [round][n-round-1] -> [n-round-2][n-round-1]
            for(int i=round; i<=n-round-2; i++){
                matrix[i][n-round-1] = count;
                count++;
            }

            // 下边（第n-round-1行）， [n-round-1][n-round-1] -> [n-round-1][round+1]
            for(int i=n-round-1; i>=round+1; i--){
                matrix[n-round-1][i] = count;
                count++;
            }

            // 左边(第round列)， [n-round-1][round] -> [round+1][round]
            for(int i=n-round-1; i>=round+1; i--){
                matrix[i][round] = count;
                count++;
            }
        }

        if (n % 2 == 1) { // n 为奇数时，单独处理矩阵中心的值
            matrix[n/2][n/2] = n*n;
        }

        return matrix;
    }




    /*
    方法二：与 leet54几乎一样的写法
     */
    public int[][] generateMatrix2(int n) {

        int count = 1;
        int max = n*n;
        int[][] nums = new int[n][n];

        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom = n-1;


        while(count <= max){

            // 上边 nums[top][i]
            if(top<=bottom && left<=right){

                for(int i=left; i<=right; i++){
                    nums[top][i] = count;
                    count++;
                }

                top++;
            }


            // 右边 nums[i][right]
            if(top<=bottom && left<=right){

                for(int i=top; i<=bottom; i++){
                    nums[i][right] = count;
                    count++;
                }

                right--;
            }


            // 下边 nums[bottom][i]
            if(top<=bottom && left<=right){

                for(int i=right; i>=left; i--){
                    nums[bottom][i] = count;
                    count++;
                }

                bottom--;
            }


            // 左边 nums[i][left]
            if(top<=bottom && left<=right){

                for(int i=bottom; i>=top; i--){
                    nums[i][left] = count;
                    count++;
                }

                left++;
            }
        }

        return nums;
    }










}
