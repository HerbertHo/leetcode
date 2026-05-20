import java.util.*;

public class minWindow_76 {

    /*
    方法一 from leetcode官方，GPT一样      看leetcode官方视频理解！！！

    注：t中可能有重复元素， 最小子串为1

    如何判断 s的子串 包含了t中所有字符？
    分别统计s的子串 和 t中 每个字符出现的次数，进行比对
     */

    public String minWindow(String s, String t) {

        if (s.length() < t.length()) {  // t比s长，肯定无解
            return "";
        }

        // 用来记录 t 中每个字符出现的次数
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口内部每个字符出现的次数
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;   // 滑动窗口的左右边界
        int valid = 0;             // 当前窗口中满足条件的字符种类数
        int start = 0;             // 最小覆盖子串的起始位置
        int len = Integer.MAX_VALUE; // 最小子串长度初始化为最大值

        // 开始滑动窗口
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;  // 扩大窗口

            // 如果这个字符是需要的
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (window.get(c).intValue() == need.get(c).intValue()) {   // 如果当前字符数量刚好满足要求，valid++，即window中多一种字符满足条件
                    valid++;
                }
            }

            // 当窗口已经包含了 t 的全部字符（满足要求）
            while (valid == need.size()) {

                if (right - left < len) {    // 更新最小子串
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);  // 即将移出窗口的字符
                left++;  // 缩小窗口

                // 如果移出的是需要的字符
                if (need.containsKey(d)) {
                    if (window.get(d).intValue() == need.get(d).intValue()) {
                        valid--;  // 移出后不满足要求
                    }
                    window.put(d, window.get(d) - 1); // 更新窗口中的计数
                }
            }
        }

        // 返回最小子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }





}
