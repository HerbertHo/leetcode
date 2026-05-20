import java.util.*;

public class longestValidParentheses_32 {

    /*
    易错1：本题要求的是 连续的、格式正确的子串， 要连续！！！

    易错2：连续两个((不合法
          (())合法，长度为4

    易错3：遇到 (就cur=0
          ()()长度应为4

    易错4：非法情况就是：遇到)且栈中没有左括号
         ()(() 左括号多了也非法，正确答案为2，容易错输出为4

     */

    /*
    看B站里 leetcode官方题解
     */


    /*
    方法一：动态规划 from GPT， 思想见ipad 或 B站
     */
    // dp[i] : 以s[i]结尾的合法子串长度(必须包含s[i])
    // 递推公式 dp[i] = 2 + dp[i-1] + dp[i-dp[i-1]-2] = 新匹配的括号长度2 + 内部合法子串长度 + 外部合法子串长度
    public int longestValidParentheses1(String s) {

        if (s.length() == 0) {
            return 0;
        }

        int length = s.length();
        int[] dp = new int[length]; // dp[i] 表示以 i 结尾的最长有效括号长度
        int max = 0;

        for (int i = 1; i < length; i++) {  // 从1开始，dp[0]必为0，一个字符无法组成合法子串

            if (s.charAt(i) == '(') {   // 以左括号结尾的必然不是合法子串，dp[i]=0
                continue;
            }

            // 情况1：前一个字符是'('，构成 "()", dp[i] = 2 + dp[i-2]
            if (s.charAt(i - 1) == '(') {
                dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
            }

            // 情况2：前一个是')'，看前一个有效括号前面是否还有'('
            else if (i-dp[i-1]-1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {  // i-dp[i-1]-1：以i-1为结尾的合法子串的前一个元素
                dp[i] = dp[i - 1] + 2;

                if (i - dp[i - 1] - 2 >= 0) {   // 加上更前面的有效长度
                    dp[i] += dp[i - dp[i - 1] - 2];
                }
            }

            // 更新最大长度
            max = Math.max(max, dp[i]);

        }

        return max;
    }


    /*
    方法二：栈  from leetcode官方

    栈中放的是索引，便于计算合法子串的长度

    栈中存储 -1 和 未匹配成功的右括号下标i：表示不合法的元素，下一个合法子串只能从i+1开始
    栈中存储左括号的下标： 等待匹配（如果没有匹配，也相当于非法元素）

    遇到( ： 直接入栈
    遇到): 直接出栈一个元素：（1）出栈后栈中还有元素：出栈的是(，尝试更新maxLen
                        （2）出栈后栈中没有元素，说明刚才出栈的是非法标识(-1或右括号)，本次右括号入栈、更新非法标识
     */
    public int longestValidParentheses2(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new LinkedList<>();  // 存储索引，便于计算合法子串的长度
        stack.push(-1);  // 不合法的元素，下一个合法子串只能从0开始

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); // 左括号索引入栈，等待匹配（如果没有匹配，也相当于非法元素）
            } else {    // 是右括号

                stack.pop(); //  栈中必有元素（左括号or非法元素）

                if (stack.isEmpty()) {  // 没有匹配，右括号索引入栈，表示不合法的元素，下一个合法子串只能从i+1开始
                    stack.push(i);
                } else {   // 匹配成功，更新最大长度
                    maxLen = Math.max(maxLen, i - stack.peek());  // curLen为 当前匹配成功的元素-上一个不合法元素的下标（栈顶元素）
                }
            }
        }

        return maxLen;
    }


    /*
    方法三：正向、逆向相结合   思想见leetcode官方

    为什么还要逆向遍历一遍：
    正向遍历时，只以多余的 )作为非法条件清空cur ， 但正如我们在"易错"中说的，非法情况不仅仅可能由多余的)导致，多余的(也会导致非法情况
    所以为了考虑多余的(的非法情况，我们进行逆序遍历，这样多余(的情况 = 正序时多余)的情况，就都考虑到了
     */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxLen = 0;

        // 第一次从左往右扫描
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {   // 匹配成功
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {  // 此时不合法，重新开始合法子串curLen
                left = right = 0;
            }
        }

        // 第二次从右往左扫描，防止漏掉左多右少的情况
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {   // 匹配成功
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {  // 此时不合法，重新开始合法子串curLen （ 比如((())时，遇到第3个(应该将全部清空  ）
                left = right = 0;
            }
        }

        return maxLen;
    }









}
