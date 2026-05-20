public class KMP {

    /**
     * 执行 KMP 匹配操作，寻找模式串 patt 在主串 string 中的首次出现位置。
     * @param string 主串
     * @param patt 模式串
     * @return 返回匹配的起始索引，若未找到返回 -1
     */
    public int kmp_search(String string, String patt) {
        if (patt == null || patt.length() == 0) return 0;
        if (string == null || string.length() < patt.length()) return -1;

        int[] next = build_next(patt); // 构建 next 数组

        int i = 0; // 主串指针
        int j = 0; // 模式串指针

        while (i < string.length()) {
            if (string.charAt(i) == patt.charAt(j)) {   // 当前字符匹配，继续比较下一个
                i++;
                j++;
            } else if (j > 0) {    // 当前字符不匹配，但之前部分匹配，模式串指针回退到 next[j - 1] 位置
                j = next[j - 1];
            } else {    // 当前字符不匹配，且 j == 0，则主串向后移
                i++;
            }


            if (j == patt.length()) {  // 匹配成功
                return i-j;
            }
        }

        return -1; // 没有匹配成功
    }


    /**
     * 构建模式串的 next 数组。
     * next[i] 表示 patt[0...i] 中，最长相等的前后缀长度。
     * @param patt 模式串
     * @return 返回 next 数组
     */
    private int[] build_next(String patt) {
        int[] next = new int[patt.length()];  // 创建 next 数组
        next[0] = 0;                          // 第一个位置永远是 0
        int prefix_len = 0;                   // 记录当前 最长相等前后缀的长度

        for (int i = 1; i < patt.length(); i++) {
            while (prefix_len > 0 && patt.charAt(prefix_len) != patt.charAt(i)) {
                prefix_len = next[prefix_len - 1];   // 当前字符不匹配，则尝试前一个最长相等前后缀的位置
            }

            if (patt.charAt(prefix_len) == patt.charAt(i)) {
                prefix_len++; // 找到匹配的前后缀
            }

            next[i] = prefix_len; // 记录到 next 数组中
        }

        return next;
    }

    // 示例 main 方法测试
    public static void main(String[] args) {
        KMP kmp = new KMP();
        String text = "ababcabcacbab";
        String pattern = "abcac";

        int index = kmp.kmp_search(text, pattern);
        System.out.println("匹配位置索引: " + index);
    }
}

