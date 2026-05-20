import java.util.*;

public class letterCombinations_17 {

    /*
    熟练使用StringBuilder的用法
     */


    List<String> list = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }

        backTracking(digits,0);
        return list;
    }




    // index表示现在正在操作digits中的第几个数字   比如digits如果为"23",index 为0，则str表示2对应的 abc
    public void backTracking(String digits,int index) {
        if (index == digits.length()) {
            list.add(temp.toString());
            return;
        }

        String str = mapping[digits.charAt(index) - '0'];   //str 表示当前num对应的字符串

        for (int i = 0; i < str.length(); i++) {  // 注意是从0开始取，不是startIndex，因为每次取都是完成的
            temp.append(str.charAt(i));

            backTracking(digits, index + 1);

            temp.deleteCharAt(temp.length() - 1);
        }
    }









}
