public class minDistance_72 {

    /*
    方法一 from 随想录

    dp[i][j]：以i-1为结尾的word1、以j-1为结尾的word2、最少的操作次数 让两者相同

   比较 word1[i-1] 与 word2[j-1] ，若相同，说明新指向的两个元素不用操作，dp[i][j] = dp[i-1][j-1]
   若是不同，可以 增：word1的添加操作、可以由word2的删除操作替换
               删：可以将word1[i-1]删除，dp[i][j] = dp[i-1][j]+1 ；同理可以删除word2[j-1]，dp[i][j] = dp[i][j-1]+1
             替换： dp[i][j] = dp[i-1][j-1]+1
     */

    public int minDistance1(String word1, String word2) {

        int length1 = word1.length();
        int length2 = word2.length();

        if(length1==0){
            return length2;
        }

        if(length2==0){
            return length1;
        }

        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();

        // dp[i][j]：以i-1为结尾的word1、以j-1为结尾的word2、最少的操作次数 让两者相同(一定包含)

        int[][] dp = new int[length1+1][length2+1];

        // 易错：初始化不是0！！！
        // 初始化为第0行and第0列， 因为dp[i][j]可能来自 dp[i-1][j], dp[i][j-1], dp[i-1][j-1]
        for(int i=0; i<=length1; i++){
            dp[i][0] = i;   // s有0到i-1这i个元素，t为空串，所以要操作i次
        }

        for(int j=0; j<=length2; j++){
            dp[0][j] = j;
        }


        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){

                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{     // dp[i-1][j]：删word1中元素   dp[i][j-1]：删word2中元素   dp[i-1][j-1]：修改元素
                    dp[i][j] = getMin(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
                }
            }
        }

        return dp[length1][length2];  // 本题遍历过程中相当于"继承"
    }


    int getMin(int a, int b, int c){
        a = Math.min(a,b);

        return Math.min(a,c);
    }












}
