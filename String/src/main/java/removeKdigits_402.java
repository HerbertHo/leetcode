import java.util.*;

public class removeKdigits_402 {

    /*
    方法一：思想 from leetcode， 见ipad

    若前一个数字大于后一个数字，则删除前一个数字， 比如 425 -> 删除4

    于是我们可以将数据遍历放入栈中，若栈顶元素 > 当前元素，栈顶元素一直出栈

     */

    public String removeKdigits1(String num, int k) {

        Deque<Integer> stack = new LinkedList<>();
        int length = num.length();

        for(int i=0; i<length; i++){
            int cur = num.charAt(i) - '0';

            while(!stack.isEmpty() && stack.getLast()>cur && k>0){
                stack.removeLast();
                k--;
            }

            stack.addLast(cur);
        }


        StringBuilder path = new StringBuilder();


        // 没删满k个，删栈顶元素，比如 425在遍历中删除了4，栈中有[2 5，如果再删除一个，应该删5
        while(k>0){
            stack.removeLast();
            k--;
        }

        while(!stack.isEmpty()){
            path.append(stack.removeFirst());
        }

        // 去除结果中的前导0
        while(path.length()>0 && path.charAt(0)=='0'){
            path.deleteCharAt(0);
        }

        String res2 = path.toString();

        if(res2.equals("")){
            return "0";
        }else{
            return res2;
        }
    }








}
