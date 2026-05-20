public class searchMatrix_74 {

    /*
    方法一
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        // 以右上角为初始值， 若当前值>target，向左移动一格
        // 若当前值<target，向下移动一格

        int row = matrix.length;
        int col = matrix[0].length;

        if(row==0 || col==0){
            return false;
        }

        int i = 0;
        int j = col-1;

        while(i<=row-1 && j>=0){
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] > target){
                j--;
            }else{
                i++;
            }
        }

        return false;
    }







}
