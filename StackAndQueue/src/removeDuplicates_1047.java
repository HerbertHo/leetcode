import java.util.Stack;

public class removeDuplicates_1047 {


    /*
    这题关键在于 能否想到用 栈 解决    --- 有"消除"，特别是"相邻消除"，想到"栈"
    想到用"栈"以后，其他的就跟 "括号匹配"基本一样了
     */

    // 方法一：我的写法
    public String removeDuplicates1(String s) {

        StringBuilder resultStringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(stack.size()!=0 && stack.peek()==c){
                stack.pop();
            }else{
                stack.push(c);
            }
        }

        // 将stack中剩余字符输出
        while(stack.size()!=0){
            resultStringBuilder.append(stack.pop());
        }

        resultStringBuilder.reverse();  // 注意：String是不可变类型，没有reverse()方法        但StringBuilder有

        return resultStringBuilder.toString();
    }




    // 方法二：直接用StringBuilder模拟栈，不需要真用栈   更快 from 代码随想录
    public String removeDuplicates2(String s) {

        StringBuffer res = new StringBuffer();
        int top = -1;  // top为 res 的长度
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (top >= 0 && res.charAt(top) == c) {
                res.deleteCharAt(top);
                top--;
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }







}
