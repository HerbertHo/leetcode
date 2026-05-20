public class multiply_43 {

    /*
    方法一 ： GPT和leet官方思路一样，可以看leet官方的视频理解思路
     */
    public String multiply1(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // 用来存放中间每一位的乘积结果，最大长度为 num1.length + num2.length
        int[] res = new int[num1.length() + num2.length()];

        // 从后往前遍历 num1 和 num2（模拟竖式乘法）
        for (int i = num1.length()-1; i>=0; i--) {

            int digit1 = num1.charAt(i) - '0';

            for (int j = num2.length() - 1; j >= 0; j--) {  // 用num1的每一位 分别乘以 num2

                int p1 = i + j;     // 进位的位置
                int p2 = i + j + 1; // 当前位的位置

                int digit2 = num2.charAt(j) - '0';

                int mul = digit1 * digit2;


                int sum = mul + res[p2]; // 加上已有的值（有进位的可能）

                res[p2] = sum % 10;     // 当前位保存个位数
                res[p1] += sum / 10;    // 向前进位
            }
        }


        // 把数组转换为字符串，注意跳过前导0
        StringBuilder result = new StringBuilder();

        for (int digit : res) {
            // 跳过前导0（第一个非0之后才开始追加）
            if (result.length() == 0 && digit == 0) continue;

            result.append(digit);
        }

        return result.toString();
    }




    /*
    写法二 from 我，跟上面思想一样
     */
    public String multiply2(String num1, String num2) {

        // 防止num1和num2都为"0"时 返回“”   （应该返回“0”）
        if(num1.equals("0") || num2.equals("0")){   // 不要用Integer.valueOf(num1)==0， 会溢出int
            return "0";
        }

        int length1 = num1.length();
        int length2 = num2.length();

        int[] arr = new int[length1 + length2];

        for(int i=length1-1; i>=0; i--){
            for(int j=length2-1; j>=0; j--){

                int x1 = num1.charAt(i) - '0';
                int x2 = num2.charAt(j) - '0';

                int p = i+j+1;  // 当前位
                int q = i+j;   // 进位

                int sum = x1 * x2 + arr[p];   // 重点1：利用之前的arr[p]  arr[p]相当于之前运算 对现在的进位  !!!

                arr[p] = sum % 10;
                arr[q] = sum / 10 +  arr[q];    // 重点2：利用之前的arr[q]    相当于更新 进位digit   !!!
            }
        }


        // 如果是 99*9 则arr中没有前导0， 否则像11*1可能有前导0
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<arr.length; i++){
            builder.append(arr[i]);
        }

        // 去除可能的前导0
        while(builder.charAt(0)=='0'){
            builder.deleteCharAt(0);
        }

        return builder.toString();
    }









}
