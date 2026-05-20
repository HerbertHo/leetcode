public class searchMatrix_240 {

    /*
    跟leetcode74一样，从右上角开始找！！！
     */



    /*
    方法一：暴力搜，遍历二维数组每一个元素
     */

    /*
    方法二：对每一行都进行二分查找
     */
    int flag = 0;

    public boolean searchMatrix1(int[][] matrix, int target) {

        // 对每一行进行一次二分查找
        for(int i=0; i<matrix.length; i++){
            search1(matrix,i,0, matrix[i].length-1 ,target);
        }

        return flag==1;
    }


    void search1(int[][] matrix, int row, int left, int right, int target){
        if(left > right){
            return;
        }

        int mid = (left+right)/2;

        if(matrix[row][mid]==target){
            flag = 1;
        }else if(matrix[row][mid] > target){
            search1(matrix, row, left, mid-1, target);
        }else{
            search1(matrix, row, mid+1, right, target);
        }
    }



    /*   跟leetcode74一样
    方法三：以右上角为初始值， 若当前值>target，向左移动一格
                         若当前值<target，向下移动一格
     */
    public boolean searchMatrix2(int[][] matrix, int target) {

        if(matrix.length==0 || matrix[0].length==0){
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int i = 0;       // 初始为右上角
        int j = col-1;

        while(i<=row-1 && j>=0){

            if(matrix[i][j]==target){
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
