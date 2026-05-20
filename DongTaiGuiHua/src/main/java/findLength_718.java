public class findLength_718 {

    /*
    方法一 from 代码随想录

         dp[i][j] : 以下标i-1为结尾的数组A、以下标j-1为结尾的数组B的 最长重复子数组的长度(一定包含nums[i],nums[j])
         这样，如果 nums1[i-1]==nums2[j-1]， dp[i][j]=dp[i-1][j-1]+1

         为什么是i-1不是i：(1)初始化更简单  (2)防止两层for循环中，i-1可能取-1而导致非法

         初始化dp[i][0]和dp[0][j]均为0, 因为以下标-1为结尾不合法，不可能找到重复子数组 （所以本题不用额外初始化，让dp元素全为0）
     */

    public int findLength1(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        int[][] dp = new int[length1+1][length2+1];

        int max = 0;

        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;

                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        // 在dp数组中找最大值(我们在两层for循环中顺便做了)
        return max;
    }


    /*
    写法二：跟上面思想一摸一样，写法稍有不同
     */
    public int findLength2(int[] nums1, int[] nums2) {

        // 连续
        // dp[i][j]：以i-1、j-1结尾的，公共最长子数组的长度  dp[length1][length2]:以length1-1,length2-1结尾
        // nums1[i]==nums2[j]: dp[i+1][j+1] = dp[i][j]

        int length1 = nums1.length;
        int length2 = nums2.length;

        int[][] dp = new int[length1+1][length2+1];

        int max = 0;


        for(int i=0;i<length1;i++){
            for(int j=0;j<length2;j++){

                if(nums1[i]==nums2[j]){      // 比较nums[i] 而不是nums[i-1]
                    dp[i+1][j+1] = dp[i][j] + 1;   // 这里是dp[i+1]，而不是dp[i]

                    max = Math.max(max, dp[i+1][j+1]);
                }
            }
        }

        return max;
    }





}
