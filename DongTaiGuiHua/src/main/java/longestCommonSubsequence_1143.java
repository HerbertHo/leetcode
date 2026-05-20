public class longestCommonSubsequence_1143 {

    /*

     */

    /*
    方法一 from 随想录

    本题与718不同： 本题可以不连续   这带来的不同：
    （1）dp[i][j]含义不同： 遍历到nums1[i-1], nums2[j-1]时，目前找到的最长子序列的长度（不一定都包含这两个元素，一定包含其中一个）
    （2）代码的不同：和leet718不一样，718要求连续，所以arr1[i-1]与arr2[j-1]不等时重置公共子序列为0，本题可以继承
     */

    public int longestCommonSubsequence1_1(String text1, String text2) {

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        int length1 = arr1.length;
        int length2 = arr2.length;

        int[][] dp = new int[length1+1][length2+1];

        int max = 0;

        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(arr1[i-1]==arr2[j-1]){     // 和leet718一样
                    dp[i][j] = dp[i-1][j-1] + 1;

                    max = Math.max(max, dp[i][j]);
                }else{  // 和leet718不一样，718要求连续，所以arr1[i-1]与arr2[j-1]不等时重置公共子序列为0，本题可以继承
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

                    max = Math.max(max, dp[i][j]);
                }
            }
        }


        // 在718中，应该遍历得到的dp找最大值；而本题dp[i][j]可以"继承"之前找到的长度，所以本题return dp[length1][length2]就可以
        return max;    // return dp[length1-1][length2-1]就可以
    }



    /*
    思想同上， 只是考虑到 dp[i-1][j] 或 dp[i][j-1] 会不会大于 dp[i-1][j-1] + 1？   （上一种写法也行，说明应该不会）
     */
    public int longestCommonSubsequence1_2(String text1, String text2) {

        // 不连续

        // dp[i][j]：以arr1[i-1]  arr2[j-1]结尾的、最长 公共子序列 的长度

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        int length1 = arr1.length;
        int length2 = arr2.length;

        int[][] dp = new int[length1+1][length2+1];

        for(int i=1; i<length1+1; i++){
            for(int j=1; j<length2+1; j++){

                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);  // 区别，先继承，再判断 相同子序列会不会更长

                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
            }
        }

        return dp[length1][length2];  // 由于有继承，所以不用维护max
    }









}
