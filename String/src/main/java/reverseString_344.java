public class reverseString_344 {

    /* 题目：反转字符串
    将输入的字符串反转过来，不要给另外的数组分配额外的空间，你必须原地修改输入数组
     */

    /* 我的思路和老师一样：双指针法
     */
    public void reverseString1(char[] s) {

        // 双指针法
        int i=0;
        int j = s.length-1;

        while(i<j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }
    }




}
