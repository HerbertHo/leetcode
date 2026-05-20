public class largestNumber_179 {

    /*
    方法一 from 我， 没仔细看案例， 错误理解题意
    根据leet示例2可知：一个数字顺序不能变  比如34在resultStr中必须保持 "34"的顺序，不能变成 “43“
     */

    public String largestNumber1(int[] nums) {

        // 将每个数字存到0-9的数组
        int[] arr = new int[10];

        StringBuilder builder = new StringBuilder();

        for(int num:nums){
            builder.append(num);
        }

        // 统计每个数字出现的次数
        for(int i=builder.length()-1; i>=0; i--){
            char c = builder.charAt(i);

            arr[c-'0']++;
        }


        // 将数组中的元素存入字符串
        StringBuilder result = new StringBuilder();

        for(int i=9; i>=0 ;i--){

            for(int count=0; count<arr[i]; count++){
                result.append(i);
            }
        }

        return result.toString();
    }











}
