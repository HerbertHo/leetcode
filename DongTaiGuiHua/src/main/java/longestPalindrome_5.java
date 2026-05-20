public class longestPalindrome_5 {


    /*
    方法一： from 我
    思想几乎跟leet647一样
    比较慢
     */
    public String longestPalindrome1(String s) {

        // dp[i][j]： s[i,j]是不是回文串

        char[] arr = s.toCharArray();
        int length = arr.length;

        boolean[][] dp = new boolean[length][length];

        int curLen = 0;
        int maxLen = 0;
        int subStart = 0;
        int subEnd = 0;

        for(int i=length-1; i>=0; i--){
            for(int j=i; j<length; j++){

                if(arr[i] == arr[j]){

                    if(j-i <= 1 || dp[i+1][j-1]){
                        dp[i][j] = true;
                        curLen = j-i+1;

                        if(curLen > maxLen){
                            subStart = i;
                            subEnd = j;

                            maxLen = curLen;
                        }
                    }
                }
            }
        }

        return s.substring(subStart, subEnd+1);
    }




    /*
    方法二：中心扩散法 from GPT， 同leetcode官方题解，  其实也好理解

    第i个元素 向左右两边遍历，判断i-1和i+1两个元素是否相等， 直到不相等时得到curLen

     */
    public String longestPalindrome2(String s) {
        int start = 0, maxLen = 1;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            // 情况一：以单个字符为中心扩展
            int len1 = expandFromCenter(s, i, i);

            // 情况二：以两个字符之间为中心扩展
            int len2 = expandFromCenter(s, i, i + 1);

            int curLen = Math.max(len1, len2);

            if (curLen > maxLen) {
                maxLen = curLen;
                start = i - (curLen - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }


    private int expandFromCenter(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }


    /*
    对于扩散法， 我的写法：
     */
    public String longestPalindrome2_2(String s) {

        // 扩散法
        char[] arr = s.toCharArray();

        int length = arr.length;

        int maxLen = 1;
        int subStart = 0;

        for(int i=0; i<length; i++){

            int len1 = expand(arr, i, i);

            if(len1 > maxLen){
                subStart = i-len1/2; // "abbbbc"  i=2时，可以扩散得到"bbb"，len1=3，len1/2=1, subStart = i-len1/2

                maxLen = len1;
            }

            int len2 = expand(arr, i, i+1);  // 不用担心i+1越界，因为在expand函数中已经处理的越界的情况

            if(len2 > maxLen){
                subStart = i-len2/2+1; // "abbbbc" i=2时，i+1=3，len2=4,len2/2=2, subStart= 1 = i-len2/2+1

                maxLen = len2;
            }
        }

        return s.substring(subStart, subStart+maxLen);
    }



    private int expand(char[] arr, int left, int right){

        while(left>=0 && right<arr.length && arr[left]==arr[right]){
            left--;
            right++;
        }

        return right-left-1;   // 比如"aa"时left=-1.right=2, right-left=3,但实际回文串的长度为2
    }













}
