import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class DecodeStringTest_394 {
    /*
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

    示例 1：
    输入：s = "3[a]2[bc]"
    输出："aaabcbc"

    示例 2：
    输入：s = "3[a2[c]]"
    输出："accaccacc"
     */


    /*
    我的想法：
    这一题让我想到了逆波兰式计算器，我猜测应该要有三个栈：字母栈、符号栈、数字栈
     */

    /*
    chatGPT:
    两个栈，一个存数据，一个存字符串

    遍历原字符串：
        遇到数字 ： 拼接出完整数字
        遇到 [  ： 入栈（num和curStr）   +  重置
        遇到 ]  :  出栈 + 构建重复字符串 + 拼接完整字符串
        遇到字母 ： 追加到 curStr

     技术：
        str.toChatArray()   然后遍历每个char

     */
    public String decodeString1(String s) {

        Stack<Integer> numStack = new Stack<>(); // 存储重复次数 k
        Stack<StringBuilder> strStack = new Stack<>(); // 存储字符串

        StringBuilder currStr = new StringBuilder();
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {    // 计算完整数字，例如 "10[a]" 需要解析出 10

                num = num * 10 + (c - '0');
            } else if (c == '[') {   // 遇到 `[`，将当前 num 和 currStr 推入栈

                numStack.push(num);
                strStack.push(currStr);

                num = 0;      // 重置 num 和 currStr
                currStr = new StringBuilder();
            } else if (c == ']') {

                int repeatTimes = numStack.pop();   // 结束一个 `k[...]`，弹栈
                StringBuilder decodedStr = new StringBuilder();

                // 构造重复字符串
                for (int i = 0; i < repeatTimes; i++) {
                    decodedStr.append(currStr);
                }

                // 叠加到上一个字符串（如果有）
                currStr = strStack.pop().append(decodedStr);
            } else {
                // 普通字母，加入当前构造的字符串
                currStr.append(c);
            }
        }

        return currStr.toString();
    }




    /*
    方法二 from我， 思想跟上面的一样   见ipad
     */
    public String decodeString2(String s) {

        // 遇到[ : (1)分别入栈 （2）重置cur和num
        // 遇到 ]: (1)出数栈，重复cur得到temp   （2）出字符串栈，后面追加temp得到新cur

        Stack<StringBuilder> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(isNum(c)){
                int j=i+1;

                while(j<s.length() && isNum(s.charAt(j))){
                    j++;
                }

                String numStr = s.substring(i,j);
                num = Integer.parseInt(numStr);

                i = j-1;  // 因为马上for循环i又会+1
            }else if(c=='['){
                strStack.push(cur);
                numStack.push(num);

                cur = new StringBuilder();
                num = 0;
            }else if(c==']'){
                int count = numStack.pop();
                StringBuilder temp = new StringBuilder();

                for(int k=1; k<=count; k++){
                    temp.append(cur);
                }

                cur = strStack.pop().append(temp);
            }else{
                cur.append(c);
            }
        }

        return cur.toString();
    }


    boolean isNum(char c){
        int num = c - '0';

        if(num>=0 && num<=9){
            return true;
        }else{
            return false;
        }

    }



    /*
    解法三 from 我，更好理解

    一个数栈 numStack
    两个string栈  strStack：用于存储[和字母     tempStack：辅助，帮助得到正确地顺序

    遇到数字：      等待拼接正确地数字
    遇到 [ 和字母： 入栈
    遇到 ]      ： str一直出栈、找 [， 拼接成正确地string，并重复 numStack.pop()遍
     */
    public String decodeString3(String s) {
        Deque<Integer> numStack = new LinkedList<>();
        Deque<String> strStack = new LinkedList<>();
        Deque<String> tempStack = new LinkedList<>();

        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {       // 数字，等待拼接完整数字
                num = num * 10 + (ch - '0');
            } else if (ch == '[') {        // 放入strStrack + num入栈
                strStack.push("[");
                numStack.push(num);
                num = 0;
            } else if (ch == ']') {
                while (!strStack.peek().equals("[")) {   // 直到找到 [
                    tempStack.push(strStack.pop());
                }
                strStack.pop();    // pop "["

                StringBuilder cur = new StringBuilder();

                while (!tempStack.isEmpty()) {
                    cur.append(tempStack.pop());
                }

                int count = numStack.pop();   // 重复次数

                StringBuilder temp = new StringBuilder();

                for (int j = 0; j < count; j++) {
                    temp.append(cur);
                }

                strStack.push(temp.toString());
            } else {
                strStack.push("" + ch);
            }
        }

        // 遍历结束，最后的拼接
        StringBuilder res = new StringBuilder();

        while (!strStack.isEmpty()) {
            tempStack.push(strStack.pop());
        }

        while (!tempStack.isEmpty()) {
            res.append(tempStack.pop());
        }

        return res.toString();
    }


    /*
    与上面解法一样，只是使用Deque的方法，而不是Stack的pop
     */
    public String decodeString4(String s) {

        // 小写字母、数字、方括号
        Deque<Integer> numDeque = new LinkedList<>();
        Deque<String> strDeque = new LinkedList<>();
        Deque<String> tempDeque = new LinkedList<>();


        char[] arr = s.toCharArray();
        int length = arr.length;

        int num = 0;

        for(int i=0; i<length;i++){

            if(Character.isDigit(arr[i])){

                num = num*10 + arr[i] - '0';
            }else if(arr[i]=='['){
                numDeque.addLast(num);

                num = 0;

                strDeque.addLast("[");
            }else if(arr[i]==']'){

                while(!strDeque.getLast().equals("[")){

                    tempDeque.addLast(strDeque.removeLast());
                }

                strDeque.removeLast();  // "["出栈

                StringBuilder builder = new StringBuilder();

                while(!tempDeque.isEmpty()){
                    builder.append(tempDeque.removeLast());
                }

                // 复制
                StringBuilder now = new StringBuilder();
                int copyNum = numDeque.removeLast();

                for(int j=0; j<copyNum; j++){
                    now.append(builder);
                }

                strDeque.addLast(now.toString());
            }else{   // 字母
                strDeque.addLast(""+arr[i]);
            }
        }

        // 将strDeuqe中的String拿出来拼接
        StringBuilder result = new StringBuilder();

        while(!strDeque.isEmpty()){
            result.append(strDeque.removeFirst());
        }

        return result.toString();
    }









}


