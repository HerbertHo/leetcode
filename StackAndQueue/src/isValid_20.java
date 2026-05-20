import java.util.Stack;

public class isValid_20 {


    /*  分析问题
    本题不匹配的情况只有三个：
    1、有多余左括号
    2、有多余右括号
    3、左右括号类型不匹配

    比如 [{]}  也属于第3个情况， {与]不匹配
    比如 )(   也属于第2种情况 ， 在前面没有左括号的时候先出现右括号
     */

    /* 实现思路
    遍历题目给的字符串，遇到左括号时将对应的右括号入栈，比如遍历到 "（"  时将  "）" 入栈
                    遍历到右括号时从栈中出栈一个元素，看一不一样
    比如[()]  遍历到[，将]入栈     遍历到(，将)入栈    遍历到)，出栈一个元素，为)，匹配       遍历到]，出栈一个元素，为]匹配




    解法一

     */

    public boolean isValid1(String s) {


        Stack<Character> stack = new Stack<>();
        boolean flag;

        for(int i=0;i<s.length();i++){

            char c = s.charAt(i);

            if(c=='('){
                stack.push(')');
            }else if(c=='['){
                stack.push(']');
            }else if(c=='{'){
                stack.push('}');
            }else if(c==')' || c==']' || c=='}'){
                if(stack.isEmpty()==true){
                    return false;
                }

                char temp = stack.pop();
                if(temp != c){
                    return false;
                }
            }else{
                return false;
            }
        }

        if(stack.isEmpty()){       // 易错，只有当最后stack为空时才为true， 否则有多余左括号 （其上的代码只处理了2、3两种异常情况，这里才处理了1 ）
            return true;
        }else{
            return false;
        }
    }



    /*
    解法二

    栈中只存左括号
    遇到左括号入栈，遇到右括号看栈顶的左括号是否匹配

     */

    public boolean isValid2(String s) {

        Stack<Character> stack = new Stack<>();

        if(s.length() % 2 == 1){     // 奇数个，一定不合法
            return false;
        }


        for(int i=0; i<s.length(); i++){

            if(s.charAt(i)=='(' || s.charAt(i)=='[' || s.charAt(i)=='{'){
                stack.push(s.charAt(i));
            }else if(s.charAt(i)==')'){
                if(!stack.isEmpty() && stack.peek()=='('){
                    stack.pop();
                }else{
                    return false;
                }
            }else if(s.charAt(i)==']'){
                if(!stack.isEmpty() && stack.peek()=='['){
                    stack.pop();
                }else{
                    return false;
                }
            }else{   // '}'
                if(!stack.isEmpty() && stack.peek()=='{'){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }

        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }



}
