import java.util.*;

public class generateParenthesis_22 {

    /* 方法一 回溯法 from GPT

    思路见B站：
    https://www.bilibili.com/video/BV1xV411q7wE/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */

    List<String> result = new ArrayList<>(); // 存放最终的合法括号组合结果

    public List<String> generateParenthesis1_1(int n) {
        // 调用回溯函数，初始时 path 为空，左括号和右括号都还未使用
        backtrack(new StringBuilder(), 0, 0, n);
        return result;
    }

    /**
     * 回溯函数
     * @param path 当前已经构建的括号字符串
     * @param left 当前已经使用的左括号数量
     * @param right 当前已经使用的右括号数量
     * @param max 总共括号对数
     */
    private void backtrack(StringBuilder path, int left, int right, int max) {
        // 如果构建完成（长度为2*n），将其加入结果列表
        if (path.length() == max * 2) {
            result.add(path.toString());
            return;
        }

        // 如果左括号数量小于n，可以继续添加左括号
        if (left < max) {
            path.append("(");
            backtrack(path, left + 1, right, max);
            path.deleteCharAt(path.length()-1);   // StringBuilder删除字符要用 deleteCharAt()
        }

        // 如果右括号数量小于左括号数量，才可以添加右括号（否则会不合法）
        if (right < left) {
            path.append(")");
            backtrack(path, left, right+1, max);
            path.deleteCharAt(path.length()-1);
        }
    }



    /*
    方法一修改： 用String代替StringBuilder， 但不直观，不推荐
     */

    public List<String> generateParenthesis1_2(int n) {
        // 调用回溯函数，初始时 path 为空，左括号和右括号都还未使用
        backtrack("", 0, 0, n);
        return result;
    }

    /**
     * 回溯函数
     * @param path 当前已经构建的括号字符串
     * @param left 当前已经使用的左括号数量
     * @param right 当前已经使用的右括号数量
     * @param max 总共括号对数
     */
    private void backtrack(String path, int left, int right, int max) {
        // 如果构建完成（长度为2*n），将其加入结果列表
        if (path.length() == max * 2) {
            result.add(path);
            return;
        }

        // 如果左括号数量小于n，可以继续添加左括号
        if (left < max) {
            backtrack(path + "(", left + 1, right, max);   // String默认回溯
        }

        // 如果右括号数量小于左括号数量，才可以添加右括号（否则会不合法）
        if (right < left) {
            backtrack(path + ")", left, right + 1, max);
        }
    }





    /*
    我自己思路 + 实现：
    理解本题就是 排列问题（因为讲究顺序） + 括号合法问题（左括号数量 >= 右括号数量）

    所以其实就是在 leet47的全排列问题上加上 左括号数量 >= 右括号数量 的校验

    可见ipad
     */

    List<String> result = new ArrayList<>();
    StringBuilder path = new  StringBuilder();

    int left = 0;  // 左括号数量
    int right = 0;

    public List<String> generateParenthesis(int n) {

        char[] charArr = new char[2*n];

        for(int i=0; i<=n-1; i++){
            charArr[i] = '(';
        }

        for(int i=n; i<=2*n-1; i++){
            charArr[i] = ')';
        }

        int[] used = new int[charArr.length];

        backtracking(charArr, used);

        return result;
    }


    // 左括号数量 >= 右括号数量
    // 去重

    // 本质上是一个排列问题（因为有顺序）
    void backtracking(char[] charArr, int[] used){

        if(path.length() == charArr.length){
            result.add(path.toString());
        }


        for(int i=0; i<charArr.length; i++){

            if(used[i] == 1){   // 排列问题
                continue;
            }

            if(i>0 && charArr[i]==charArr[i-1] && used[i-1]==0){  // 去重
                continue;
            }

            if(right > left || (right==left && charArr[i]==')')){  // 确保 左括号数量 >= 右括号数量
                continue;
            }

            path.append(charArr[i]);
            used[i] = 1;
            if(charArr[i]=='('){
                left++;
            }else{
                right++;
            }

            backtracking(charArr, used);

            path.deleteCharAt(path.length()-1);
            used[i] = 0;
            if(charArr[i]=='('){
                left--;
            }else{
                right--;
            }
        }
    }


    /*
    B站做法 https://www.bilibili.com/video/BV1xG4y1F7nC/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */







}
