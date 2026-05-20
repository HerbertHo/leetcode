import java.util.*;

public class spiralOrder_54 {

    /*
    易错点： 不一定是正方形，可能是长方形！！！
     */


    /*
    方法一 from 我， 见ipad，  有错误！！！ 会越界
     */
    public List<Integer> spiralOrder1(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> result = new ArrayList<>();

        int bigger = Math.max(row,col);  // 较长的一边
        int count = 0;

        if(bigger % 2 == 0){          // 长边为偶数
            count = bigger/2;     // 圈数
        }else{                    // 长边为奇数
            count = bigger/2+1;
        }


        for(int round=0; round<count; round++){

            for(int j=round; j<=col-1-2*round; j++){
                result.add(matrix[round][j]);
            }

            for(int j=round; j<=row-1-2*round; j++){
                result.add(matrix[j][col-round]);
            }

            for(int j=col-round; j>=1+2*round; j--){
                result.add(matrix[row-round][j]);
            }

            for(int j=row-round; j>=1+2*round; j--){
                result.add(matrix[j][round]);
            }
        }

        return result;
    }



    /*
    方法二 from GPT
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // top row
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;

            // right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // bottom row (if not the same as top)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }

            // left column (if not the same as right)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }



    /*
    对 方法二的规范写法 from 我，更容易理解
     */
    public List<Integer> spiralOrder3(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int left = 0;
        int right = col-1;
        int top = 0;
        int bot = row-1;

        List<Integer> result = new ArrayList<>();

        while(left<=right && top<=bot){

            // 上边 matrix[top][i]
            if(left<=right && top<=bot){

                for(int i=left; i<=right; i++){
                    result.add(matrix[top][i]);
                }

                top++;
            }

            // 右边 matrix[i][right]
            if(left<=right && top<=bot){    // 都要加这个判断，因为遍历完一条边后top、right等都会变，就不一定满足合法条件了

                for(int i=top; i<=bot; i++){
                    result.add(matrix[i][right]);
                }

                right--;
            }


            // 下边 matrix[bot][i]
            if(left<=right && top<=bot){

                for(int i=right; i>=left; i--){
                    result.add(matrix[bot][i]);
                }

                bot--;
            }


            // 左边 matrix[i][left]
            if(left<=right && top<=bot){

                for(int i=bot; i>=top;i--){
                    result.add(matrix[i][left]);
                }

                left++;
            }
        }

        return result;
    }













}
