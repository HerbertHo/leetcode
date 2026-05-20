public class isPalindrome_9 {

    /*
    方法一：from 我，  转为字符串

    不满足题目的进阶要求： 不使用字符串
     */
    public boolean isPalindrome1(int x) {

        if(x<0){           // 第一位是负号
            return false;
        }

        Integer y = x;

        String str = y.toString();

        int length = str.length();

        int left = 0;
        int right = length-1;

        while(left<=right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }



    /*
    方法二： 反转数字， 实现from我 ，思路from leetcode
     */
    public boolean isPalindrome2(int x) {

        if(x<0){
            return false;
        }


        // 反转数字
        long l1 = (long)x;   // 防止溢出
        long l2 = 0l;

        // 反转l1   12321
        while(l1 != 0l){

            long tail = l1 % 10;   // 取最后一位 1

            l2 = l2*10 + tail;  // 更新 l2

            l1 = l1/10;  // 将最后一位去掉
        }

        return l2 == (long)x;
    }


    /*
    方法三： 反转一半数字
     */



















}
