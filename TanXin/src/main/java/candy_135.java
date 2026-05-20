public class candy_135 {

    /*
    易错：相邻 既包括左、也包括右， 所以要和两边的小孩都要比较
     */

    /*
    方法一 from 随想录

    要考虑两边的情况，一定要确定好一边、再确定另一边：
     1、只看右边比左边高的情况
     2、再看左边比右边高的情况
     */
    // 相邻孩子，分高的糖果多（不包括同分）

    public int candy1(int[] ratings) {

        // 相邻孩子，分高的糖果多（不包括同分）

        int length = ratings.length;

        if(length==1){
            return 1;
        }

        int[] count = new int[length];   // 每个小孩的糖果数
        int sum = 0;    // 一共的糖果数

        // 1、只考虑右边比左边大的情况
        count[0] = 1;   // 先初始化为1，最终不一定为1
        for(int i=1; i<length; i++){
            if(ratings[i] > ratings[i-1]){
                count[i] = count[i-1] + 1;
            }else{
                count[i] = 1;
            }
        }

        // 2、考虑左边比右边大的情况  i 与 i+1 比较    注意：每个元素不能比现在要小（此次遍历时，元素只能增、不能减）
        for(int i=length-2; i>=0; i--){
            if(ratings[i] > ratings[i+1]){
                count[i] = Math.max(count[i], count[i+1] + 1);
            }
        }

        // 统计一共要用的糖果数
        for(int num: count){
            sum += num;
        }

        return sum;
    }










}
