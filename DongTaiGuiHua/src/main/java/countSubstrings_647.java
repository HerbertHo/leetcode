public class countSubstrings_647 {

    /*
    方法一： from 随想录

    如果定义dp[i]为：以s[i-1]结尾的、回文子串的数量，那么很难找到递推关系

    现在定义：dp[i][j]: 以s[i]开头的、s[j]结尾的子串、是不是回文串, 为boolean类型（必须包括）
            如果dp[i+1][j-1]是回文串，且s[i] == s[j]，那么dp[i][j]也是回文串，count++

    初始化： dp = 0

    dp[i][j]依赖于dp[i+1][j-1] ，也就是从左下角往右下角遍历，即从下往上、从左往右遍历（初始化i从len-1到0，j从0到len-1）

     */

    public int countSubstrings1(String s) {

        // dp[i][j] ：以s[i,j]子串、是不是回文子串

        // 当dp[i][j]取决于dp[i-1][j-1]时， i和j从0开始++
        //           取决于dp[i+1][j-1]时， i从末尾到0， j从i++

        char[] arr = s.toCharArray();
        int length = arr.length;

        int count = 0;

        boolean[][] dp = new boolean[length][length];

        for(int i=length-1; i>=0; i--){
            for(int j=i; j<length; j++){

                if(arr[i] == arr[j]){
                    if(j-i <= 1){   // 包含两种情况：（1）i和j指向同一个字母a  （2）i和j指向相邻的字母aa
                        dp[i][j] = true;
                        count++;
                    }else if(dp[i+1][j-1]){    // i和j之间至少隔了一个字母 && 中间的也是回文串
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }


    /*
    对上述写法中 for循环中判断的简化
     */
    public int countSubstrings1_2(String s) {

        int length = s.length();

        if(length==1){
            return 1;
        }

        char[] arr = s.toCharArray();

        // dp[i][j]：s[i,j]是回文子串吗？
        // dp[i][j]取决于dp[i+1][j-1]，所以i从末尾到0

        boolean[][] dp = new boolean[length][length];

        int count = 0;

        for(int i=length-1; i>=0; i--){
            for(int j=i; j<length; j++){

                if(arr[i] == arr[j] && (j-i <= 1 || dp[i+1][j-1]) ){   // 意思一样，判断全放入一个if
                    dp[i][j] = true;
                    count++;

                }
            }
        }

        return count;
    }










}
