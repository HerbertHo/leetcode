import java.util.*;

public class numDecodings_91 {

    /*
    本题跟leetcode93 几乎一摸一样，但是使用回溯算法会超时！！！
     */

    /*
    方法一 from 我， 分割问题，所以用回溯算法

    找出可能的分割情况，看合不合法
     */
    int count = 0;

    public int numDecodings1(String s) {

        // 返回解法方法数，  没有合法解码方法返回0

        // 合法的情况
        // 不能有前导0
        // 1到26

        // 输入s可能有前导0

        // 1、除去前导0
        // 输入为空或以0开头，无法解码
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        backtracking1(s, 0);

        return count;
    }


    // 有start ，i+1
    void backtracking1(String s,int start){
        if(start == s.length()){
            count++;
            return;
        }


        for (int i = start + 1; i <= s.length(); i++){
            String sub = s.substring(start, i);

            if(isValid(sub)){
                backtracking(s,i);
            }else{
                continue;
            }
        }
    }


    boolean isValid1(String str){
        if(str.length()==0 || str.length() >= 3){
            return false;
        }

        if(str.charAt(0)=='0'){
            return false;
        }

        int num = Integer.valueOf(str);

        if(num>=1 && num <= 26){
            return true;
        }

        return false;
    }



    /*
    方法一优化 from GPT

    在递归函数中使用 Map<Integer, Integer> 来记录每个 start 起点对应的解法数，如果之前计算过，就直接返回
     */

    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings2(String s) {
        // 无法解码的情况
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        return backtracking(s, 0);
    }

    // 回溯 + 记忆化，从 start 开始的解码方式数
    int backtracking2(String s, int start) {
        // 成功解码到末尾
        if (start == s.length()) {
            return 1;
        }

        // 如果已经计算过从 start 开始的解码方法数，直接返回
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        int ways = 0;

        // 遍历1位和2位
        for (int i = start + 1; i <= s.length() && i <= start + 2; i++) {
            String sub = s.substring(start, i);

            if (isValid(sub)) {
                ways += backtracking2(s, i);
            }
        }

        // 缓存结果
        memo.put(start, ways);
        return ways;
    }


    // 判断子串是否在1~26之间，且不能以0开头
    boolean isValid2(String str) {
        if (str.length() == 0 || str.length() > 2) return false;
        if (str.charAt(0) == '0') return false;

        int num = Integer.parseInt(str);
        return num >= 1 && num <= 26;
    }



    /*
    方法三 动态规划 from 我， 有错误
     */
    public int numDecodings3(String s) {

        // s可能有前导0，一定不合法

        // dp[i] : 到dp[i]个元素时 的解码方法  (必然包括)

        int length = s.length();

        if(s.charAt(0) == '0'){
            return 0;
        }

        if(length==1){   // 以下长度至少为2
            return 1;
        }

        int[] dp = new int[length];

        dp[0] = 1;

        for(int i=1; i<=length-1 ;i++){
            boolean jugde1 = (i-2)>=0 && isValid3(s,i-1,i+1);   // dp[i-2]
            boolean jugde2 = (i-1)>=0 && isValid3(s,i,i+1);   // dp[i-1]

            if(jugde1 && jugde2){
                dp[i] = dp[i-2] + dp[i-1];
            }else if(jugde1){
                dp[i] = dp[i-2];
            }else if(jugde2){
                dp[i] = dp[i-1];
            }else{
                dp[i] = 0;
            }
        }

        return dp[length-1];
    }

    boolean isValid3(String s, int start, int end) {   // 不包含end
        String str = s.substring(start,end);

        if (str.length() == 0 || str.length() > 2) return false;
        if (str.charAt(0) == '0') return false;

        int num = Integer.parseInt(str);
        return num >= 1 && num <= 26;
    }


    /*
    动态规划 from 我，正确

    核心：dp[i]取决于dp[i-1]+dp[i-2]，因为每次最多切2个元素，否则值就不止26了
     */
    public int numDecodings4(String s) {

        // 1到26合法
        // 不能有前导0

        // dp[i] : 切完第i个元素(包括第i个元素)时，总共的切法
        // dp[i] = dp[i-1] + dp[i-2]，不一定有dp[i-1]，因为char[i]可能是0；  不一定有dp[i-2]，因为要判断(i-2,i]是否合法
        // 最终返回dp[length-1]

        int length = s.length();

        int[] dp = new int[length];

        // 初始化：切第0个元素，只有一种切法
        if(s.charAt(0)=='0'){
            dp[0] = 0;
        }else{
            dp[0] = 1;
        }

        if(length==1){
            return dp[0];
        }

        // 初始化：第1个元素也要特殊处理，因为此时没有dp[i-2]     易错点，可以举例子验证！！！
        if(isValid1(s,1)){
            dp[1] += dp[0];
        }

        if(isValid2(s,1)){
            dp[1] += 1;
        }


        // 递归
        for(int i=2; i<=length-1; i++){
            if(isValid1(s,i)){
                dp[i] += dp[i-1];
            }

            if(isValid2(s,i)){
                dp[i] += dp[i-2];
            }
        }

        return dp[length-1];
    }


    boolean isValid1(String s, int i){   //  不一定有dp[i-1]，因为char[i]可能是0
        if(s.charAt(i)=='0'){   // 不能只切出一个0
            return false;
        }else{
            return true;
        }
    }


    boolean isValid2(String s, int i){   // 不一定有dp[i-2]，因为要判断(i-2,i]是否合法

        String sub = s.substring(i-1, i+1);   // 切出[i-1, i]

        // 不能有前导0
        if(sub.charAt(0)=='0'){
            return false;
        }

        //  1到26合法
        int value = Integer.valueOf(sub);

        if(value<1 || value>26){
            return false;
        }

        return true;
    }
    
    
    
    /*
    leetcode官方做法，其他跟我的上一个解法思路基本一样，但实现简单
     */
    public int numDecodings(String s) {
        int n = s.length();

        int[] dp = new int[n + 1];   // 跟我的区别：dp[n]才是切完第n-1个元素， 所以dp[0]切的是-1（也就是空）元素； 这样可以减少特殊情况的处理

        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {

            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }

        }

        return dp[n];
    }





}
