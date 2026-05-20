public class myAtoi_8 {

    /*
    方法一 from GPT
     */

    // （1）丢弃前导空格，trim 去除首尾空格
    // （2）第一个是不是正负号
    // （3）跳过前导0 ： 把0当作正常数字处理，不影响结果
    // （4）超过int范围返回int
    public int myAtoi(String s) {

        s = s.trim();  // （1）trim去除字符串两端的空格

        // 如果字符串为空，直接返回 0
        if (s.length() == 0) return 0;

        int index = 0;        // 用于扫描字符串
        int sign = 1;         // 正负号，默认为正数
        int result = 0;       // 结果初始化为 0
        int length = s.length();

        // （2）判断第一个字符是否为符号位
        if (s.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // 遍历字符串中的每个数字字符
        while (index<length && Character.isDigit(s.charAt(index))) {

            int digit = s.charAt(index) - '0'; // 将字符转换为对应的数字

            // （4）检查是否越界（溢出）
            if (result > (Integer.MAX_VALUE-digit)/10) {    // 若即将溢出，返回最大或最小值, 不能用result*10 + digit > Integer.MAX_VALUE，否则会溢出
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;    // 将当前数字加到结果中

            index++;
        }

        // 返回结果时考虑符号
        return result * sign;
    }











}
