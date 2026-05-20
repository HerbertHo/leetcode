import java.util.*;

public class wordBreak_139 {

    /*
    方法一：一个数组和一个target，想到背包问题  from 随想录

    完全背包
    dp[i] ：到第i个字符时(不包括第i个字母)，能否被单词组成   判断dp[s.length]

    递推公式见ipad分析

    初始化： dp[0]在题目中没有确切的含义，我们要考虑怎么将它初始化
           dp[0]要初始为true，否则递推公式推出的dp[j]全为false
     */

    public boolean wordBreak1(String s, List<String> wordDict) {

        int length = s.length();

        // 先将字典中的string装入set，便于后面判断 j到i的子串在不在字典中
        Set<String> set = new HashSet<>(wordDict);

        int[] dp = new int[length+1];

        // 初始化:
        dp[0] = 1;

        for(int i=1; i<=length; i++){   // 第1个字符才有意义
            for(int j=0; j<i; j++){   // 根据图示，j是i左边的某个位置

                if(dp[j]==1 && set.contains(s.substring(j,i))){
                    dp[i] = 1;
                }

            }
        }

        return dp[length]==1;
    }


    /*

     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);

        // dp[i]：能不能组成以i为结尾的字符串（不包含i）
        // 返回dp[length]
        // dp[i]为真：dp[j]为真 且 str[j,i]在list中

        int length = s.length();

        boolean[] dp = new boolean[length+1];

        dp[0] = true;    // 别忘了

        for(int i=1; i<dp.length; i++){  // i=1才有意义
            for(int j=0; j<i; j++){   // 只要找到一个 dp[j]为真 且 str[j,i]在list中， 则dp[i]就为真

                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                }
            }
        }

        return dp[length];
    }





}
