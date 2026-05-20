public class rotate_48 {

    /*
    方法一
     */

    public void rotate(int[][] matrix) {

        int length = matrix.length;

        for(int round=0; round<length/2; round++){   // round表示length/2个整圈

            for(int j=round; j<=length-2-round; j++){   // 表示一个整圈中的四数交换

                // [round][j] -> [j][length-1-round] -> [length-1-round][length-1-j] -> [length-1-j][round]
                // 注意：在每个[ ]中， j与round有且仅有一个！！！
                int temp1 = matrix[round][j];
                int temp2 = matrix[j][length-1-round];
                int temp3 = matrix[length-1-round][length-1-j];
                int temp4 = matrix[length-1-j][round];

                matrix[round][j] = temp4;
                matrix [j][length-1-round] = temp1;
                matrix[length-1-round][length-1-j] = temp2;
                matrix [length-1-j][round] = temp3;
            }
        }
    }



    /*
    写法二 from 我， 思想与上面一样

    见ipad
     */
    public void rotate2(int[][] matrix) {

        int length = matrix.length;

        int round = (length+1) / 2;   // 要转round圈

        for(int i=0; i<round; i++){   //  要转round圈

            for(int j=i; j<=length-2-i; j++){

                // 四数交换, 按照ipad顺序1 2 3 4
                int temp = matrix[i][j];

                matrix[i][j] = matrix[length-1-j][i];   // 由于已记录下matrix[i][j]的值，所以先更新它
                matrix[length-1-j][i] = matrix[length-1-i][length-1-j];  // 由于上一行已移动matrix[length-1-j][i]， 所以更新它
                matrix[length-1-i][length-1-j] = matrix[j][length-1-i];
                matrix[j][length-1-i] = temp;
            }
        }
    }













}
