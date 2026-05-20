public class isMatch_10 {

    /*
    方法一： 思路 from B站
            思路可见 白板、ipad， 如果看不懂再看B站 https://www.bilibili.com/video/BV1vCo7YdEj3/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */

    public boolean isMatch(String s, String t) {

        // dp[i][j] ：遍历到 s[i-1], t[j-1]字符时，两者是否匹配
        // s的前i个字符（0 到 i-1）、p的前j个字符 是否匹配（必须包含）

        // p的最后一个不是 * ， 只要比较s[i-1]和p[j-1]是否相等

        int length1 = s.length();
        int length2 = t.length();

        boolean[][] dp = new boolean[length1+1][length2+1];  // 最后返回的是 dp[length1][length2]

        // 初始化
        // dp[i][0]，也就是s非空，p为空，不可能匹配
        dp[0][0] = true;   // 其他的为false

        // dp[0][j]，也就是s为空，p非空，只有当p第j-1个元素为*（末尾元素，可以隐藏第j-2个元素）且dp[0][j-2]==true的时候，dp[0][j]=true
        // dp[0][1] = false;   默认就是false，不用写
        for(int j=2; j<=length2; j++){
            if(t.charAt(j-1)=='*' && dp[0][j-2]==true){
                dp[0][j] = true;
            }else{
                dp[0][j] = false;
            }
        }


        // 递推公式
        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){

                char c1 = s.charAt(i-1);
                char c2 = t.charAt(j-1);

                if(c2 != '*'){
                    if(isMatch(c1,c2)){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = false;
                    }
                }else{  // c2是*

                    char c3 = t.charAt(j-2);  // *前面的那个字符

                    if(isMatch(c1,c3)){  // 匹配，那么 *可以选择使用一次或多次c3，也可能选择让c3消失
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }else{       // 不匹配，*只能让c3消失
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }


        return dp[length1][length2];
    }


    boolean isMatch(char c1, char c2){
        if(c1==c2 || c2=='.'){
            return true;
        }

        return false;
    }













}
