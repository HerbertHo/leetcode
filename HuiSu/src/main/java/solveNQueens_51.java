import java.util.*;

public class solveNQueens_51 {

    /* 不同点：以前都是一维， 这个是二维

    我的思考：
    不能同行、同列、同斜线      编写函数判断棋盘是否合法
    宽、高为n， 没有start
    用used数组保证不同列, 树的不同层保证不同行
     */


        /*
    我的错误写法：
    错误原因：把path设置为全局变量
             本题path不适合设置为全局变量，因为不像其他题在for循环中有path.add和path.remove操作
             若设置为全局变量，必须手动清除path
     */

    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        char[][] map = new char[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = '.';
            }
        }


        // 不同行，不同列，不同斜线

        // 宽度、高度都为n  没有start

        backtracking(n,map,0);

        return result;

    }


    void backtracking(int n, char[][] map, int row){   // 同一行不同列：i    不同行：row
        if(row==n){
            // 把棋盘存入result
            for(int i=0; i<n; i++){
                StringBuilder builder = new StringBuilder();

                for(int j=0; j<n; j++){
                    builder.append(map[i][j]);
                }

                path.add(builder.toString());
            }

            result.add(new ArrayList<>(path));

            path.clean();   // 注意：若定义path为全局变量，必须手动更新path


            // 需要重置map：不需要，因为for循环中恢复了map[i][j]为'.'   就像之前题目中for循环中path.add和path.remove一样

            return;
        }

        for(int i=0; i<n; i++){
            if(isValid(map,row,i,n)){
                map[row][i] = 'Q';

                backtracking(n,map,row+1);

                map[row][i] = '.';
            }
        }
    }


    boolean isValid(char[][] map, int row, int col, int n){  // 想把 map[row][col]置为Q，看行不行
        // 判断是否同行、同列
        for(int i=0; i<=row-1; i++){
            if(map[i][col]=='Q'){
                return false;
            }
        }

        for(int i=0; i<=col-1; i++){   // 没必要判断在不在同一列，因为由for循环控制
            if(map[row][i]=='Q'){
                return false;
            }
        }

        // 判断是否同斜线
        for(int i=row-1, j=col-1; i>=0&&j>=0; i--,j--){
            if(map[i][j]=='Q'){
                return false;
            }
        }

        for(int i=row-1, j=col+1; i>=0&&j<n; i--,j++){
            if(map[i][j]=='Q'){
                return false;
            }

        }

        return true;
    }







    /*
    方法一 from 随想录
     */
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens1(int n) {
        char[][] chessboard = new char[n][n];

        // chessboard初始化为全为.的char[][]
        for(int i=0; i<n; i++){
            for(int j=0; j<n;j++){
                chessboard[i][j] = '.';
            }
        }

        backTrack1(n, 0, chessboard);
        return res;
    }


    public void backTrack1(int n, int row, char[][] chessboard) {
        if (row == n) {
            res.add(Array2List(chessboard,n));
            return;
        }

        for (int i = 0;i < n; i++) {       // row代表树层， i代表同一树层的不同树枝
            if (isValid (row, i, n, chessboard)) {  // 现在我们想在[row][i]放入Q，就要检查如果在此处放入Q，之前放的Q会不会跟这个冲突
                chessboard[row][i] = 'Q';

                backTrack1(n, row+1, chessboard);

                chessboard[row][i] = '.';
            }else{
                continue;
            }
        }
    }


    private boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 现在我们想在[row][i]放入Q，就要检查如果在此处放入Q，之前放的Q会不会跟这个冲突
        // 检查列
        for (int i=0; i<row; ++i) {    // 之前放的Q有没有和 现在准备放的Q 在同一列
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }


    private List Array2List(char[][] chessboard,int n) {   // 把char[][] 转成 List<String> ， 每行作为一个String
        List<String> list = new ArrayList<>();

        for(int i=0;i<n;i++){
            StringBuilder sb = new StringBuilder();  // 每行作为一个String

            for(int j=0;j<n;j++){
                sb.append(chessboard[i][j]);
            }

            list.add(sb.toString());
        }

        return list;
    }









}
