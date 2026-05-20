public class longestPalindromeSubseq_516 {


        /* 方法一 from 我
        不连续

        dp[i][j] : 以i开头、以j结尾的子串中、最长回文子序列的长度（不一定包含）

        递推公式：
        arr[i]和arr[j] 若相等：跟leet647一样，若i与j相差<=1，dp[i][j]可以直接得到长度
                                                       >1，dp[i][j] = dp[i+1][j-1] + 2;
                       不相等：继承
        */

    public int longestPalindromeSubseq1(String s) {

        char[] arr = s.toCharArray();

        int length = arr.length;

        if(length==1){
            return 1;
        }

        int[][] dp = new int[length][length];

        int max=0;

        for(int i=length-1; i>=0 ;i--){
            for(int j=i; j<=length-1; j++){

                if(arr[i]==arr[j]){
                    if(j-i<=1){
                        dp[i][j] = j-i+1;

                        max = Math.max(dp[i][j], max);
                    }else{
                        dp[i][j] = dp[i+1][j-1] + 2;

                        max = Math.max(dp[i][j], max);
                    }
                }else{    // arr[i]与arr[j]不等
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);   // 继承
                }

            }
        }

        return max;
    }













}
