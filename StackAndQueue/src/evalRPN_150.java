import java.util.Stack;

public class evalRPN_150 {



    /*
    遇到数字，入栈
    遇到运算符，从栈里取出元素做计算，再返回栈中

    这题相当于"三项消除 --- 两个数字、一个操作符 就进行消除"， 也符合之前总结出的 "遇到消除想到栈"
     */

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<tokens.length; i++){

            // 1-2相当于12-， 出栈顺序是先2后1， 所以顺序应该是 num2 oper num1

            if("+".equals(tokens[i])){       // 易错点，String类型不能用==比较值大小！！！
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                Integer temp = num2 + num1;
                stack.push(temp);
            }else if("-".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                Integer temp = num2 - num1;
                stack.push(temp);
            }else if("*".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                Integer temp = num2 * num1;
                stack.push(temp);
            }else if("/".equals(tokens[i])){
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                Integer temp = num2 / num1;
                stack.push(temp);
            }else{
                Integer num = Integer.valueOf(tokens[i]);    // 重点：如何将String -> Integer或int
                stack.push(num);
            }
        }

        int result = stack.pop();
        return result;



    }


}
