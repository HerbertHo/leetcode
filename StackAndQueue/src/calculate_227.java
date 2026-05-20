import java.util.*;

public class calculate_227 {

    /*
    我尝试自己写，错了还很复杂，容易忽略”找完成数字时遇到空格的情况“
     */




    /*
    方法一 from GPT
     */

    public int calculate1(String s) {
        Deque<Integer> stack = new LinkedList<>(); // 存储操作数
        int num = 0;
        char sign = '+';          // 记录前一个符号！！！ 初始为加号

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 如果是数字，构造完整数字
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // 如果不是数字、不是空格（是符号），或者是最后一个字符
            if ((!Character.isDigit(c) && c != ' ') || i==s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {   // 符号为-，直接存储负数
                    stack.push(-num);
                } else if (sign == '*') {   // 此时num为完整的数子，乘数
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {   // 此时num为完整的数子，除数
                    stack.push(stack.pop() / num);
                }

                sign = c;  // 更新符号
                num = 0;   // 重置数字
            }
        }

        // 把栈中所有数字相加就是最终结果
        int result = 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }



    /*
    方法一 我的实现
     */
    public int calculate2(String s) {

        // 遇到数字：找完整的数字
        // 遇到字符：根据目前的oper进行运算，之后更新oper

        char oper = '+';
        int num = 0 ;  //构建完整数字

        Deque<Integer> deque = new LinkedList<>();

        for(int i=0; i<s.length(); i++){

            if(s.charAt(i)==' '){
                continue;
            }

            if(Character.isDigit(s.charAt(i))){
                num = 10 * num + s.charAt(i) - '0';
            }else{   // 符号

                // 计算上一个oper结果，更新oper

                if(oper=='+'){
                    deque.addLast(num);

                    num = 0;   // 更新
                    oper = s.charAt(i);
                }else if(oper=='-'){
                    deque.addLast(-num);

                    num = 0;
                    oper = s.charAt(i);
                }else if(oper=='*'){   // 弹出一个数，与现在的num运算
                    int temp = deque.removeLast();

                    deque.addLast(temp * num);

                    num = 0;
                    oper = s.charAt(i);
                }else{
                    int temp = deque.removeLast();

                    deque.addLast(temp / num);

                    num = 0;
                    oper = s.charAt(i);
                }
            }
        }

        // 到末尾了，处理仅存的oper和num，与之前一样，所以可以合并入循环
        if(oper=='+'){
            deque.addLast(num);
        }else if(oper=='-'){
            deque.addLast(-num);
        }else if(oper=='*'){   // 弹出一个数，与现在的num运算
            int temp = deque.removeLast();

            deque.addLast(temp * num);
        }else{
            int temp = deque.removeLast();

            deque.addLast(temp / num);
        }

        // deque中元素加起来就好
        int sum = 0;

        while(!deque.isEmpty()){
            sum += deque.removeFirst();
        }

        return sum;
    }




}
