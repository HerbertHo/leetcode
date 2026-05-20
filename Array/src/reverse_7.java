public class reverse_7 {

    /*
    方法一：我想到了 用字符串处理， 但不知道怎么判断是否超出Integer范围
          GPT提供思路：用try catch 抓异常

          慢
     */
    public int reverse1(int x) {

        // 有符号 ， 超过integer范围返回0

        // 怎么判断是否超过范围？

        StringBuilder builder = new StringBuilder();

        builder.append(x);

        int oper = 1;    // 初始化为正数

        if(builder.charAt(0)=='-'){
            oper = 0;
            builder.deleteCharAt(0);
        }

        builder.reverse();

        // 去除前导0
        while(builder.length()!=0 && builder.charAt(0) == '0'){
            builder.deleteCharAt(0);
        }

        if(builder==null || builder.length()==0){
            return 0;
        }

        String resStr = builder.toString();


        try{
            int result = Integer.parseInt(resStr);

            if(oper==1){
                return result;
            }else{
                return result * -1;
            }
        }catch(Exception e){
            return 0;
        }

    }



    /*
    方法二 from GPT

    进行数字操作：每次取出input中的最后一位，拼接到curResult（拼接之前要考虑是否会溢出）
     */
    public int reverse2(int x) {

        // 去除前导0, 其实不用特地去除，为0是digit为0，等于没加
        if(x==0){
            return 0;
        }

        int sign = 1;  // 正负号

        if(x<0){
            sign = 0;
            x = -x;
        }

        int newNum = 0;
        int digit = 0;

        while(x!=0){
            digit = x % 10;   // 取出x的最后一位
            x = x/10;

            // 判断newNum是否越界  newNum*10 + digit > Integer.MAX_VALUE
            if(newNum > (Integer.MAX_VALUE - digit)/10){
                return 0;
            }

            newNum = newNum*10 + digit;
        }

        if(sign==0){
            newNum *= -1;
        }

        return newNum;
    }










}
