public class addStrings_415 {

    /*
    我的错误写法：
    (1)比如str1="123"  ，str2="45"   ，str的第0位为1.  str2的第0位为4 ， 一个是百分位、一个是十分位，不能相加
       所以应该从尾向头遍历
    (2)int可能不够大
     */
    public String addStrings1(String num1, String num2) {

        // 不能使用Integer.parseInt()等操作
        int result = 0;
        int index = 0;

        int length1 = num1.length();
        int length2 = num2.length();

        while(index<=length1-1 && index<=length2-1){
            int val = num1.charAt(index)-'0' + num2.charAt(index)-'0';

            result += (int)Math.pow(10,index) * val;

            index++;
        }


        while(index<=length1-1){
            int val = num1.charAt(index)-'0';

            result += (int)Math.pow(10,index) * val;   // 是pow、不是power

            index++;
        }


        while(index<=length2-1){
            int val = num2.charAt(index)-'0';

            result += (int)Math.pow(10,index) * val;

            index++;
        }

        Integer res = (Integer)result;

        return res.toString();
    }





    /*  方法二：正确写法 from GPT

    本题要求不能使用处理大整数的库（比如 BigInteger），就是让我们不要将string转为整数、再运算，而是直接使用String运算

    本题几乎完全模拟我们计算加法的情形，考虑了进位
     */
    public String addStrings2_1(String num1, String num2) {

        StringBuilder result = new StringBuilder();

        int i = num1.length() - 1;

        int j = num2.length() - 1;

        int carry = 0;         // carry 表示进位，每次加法后都可能更新

        int digit1;
        int digit2;

        while (i >= 0 || j >= 0 || carry != 0) {            // 当 i 或 j 未越界，或者还有进位未处理时，继续循环

            // 如果 i 没越界，取 num1 的当前位字符，转换为数字；否则为 0
            if(i>=0){
                digit1 = num1.charAt(i) - '0';
            }else{
                digit1 = 0;
            }


            if(j>=0){
                digit2 = num2.charAt(j) - '0';
            }else{
                digit2 = 0;
            }


            int sum = digit1 + digit2 + carry;   // 计算当前位相加的和，加上前一位的进位


            result.append(sum % 10);   // 当前位的结果是sum的个位数

            carry = sum / 10;        // 更新进位为sum除以10的结果

            i--;
            j--;
        }

        return result.reverse().toString();    // 因为是从低位到高位逐位添加的，需要反转结果字符串
    }



    /*
    方法二：我自己的写法
     */
    public String addStrings2_2(String num1, String num2) {

        // 非负整数

        int length1 = num1.length();
        int length2 = num2.length();

        int i = length1 -1;
        int j = length2 -1;
        int digit = 0;       // 进位

        StringBuilder builder = new StringBuilder();

        while(i>=0 || j>=0 || digit!=0){   // 两个都遍历完再结束

            int a = i>=0 ? num1.charAt(i)-'0' : 0;   // 两个加数
            int b = j>=0 ? num2.charAt(j)-'0' : 0;

            int sum = a+b+digit;

            int c = sum % 10;   // 个位数
            digit = sum / 10;  // 可能的进位

            builder.append(c);

            i--;
            j--;
        }

        return builder.reverse().toString();
    }









}
