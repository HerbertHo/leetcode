import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class dailyTemperatures_739 {

    /*
    方法一：暴力

    两层for循环：一层i=0遍历数组，另一层j=i+1找比当前更大的数
     */


    /*
    方法二：单调栈 from 随想录

    单调栈：保证栈内元素是递增or递减的
          适合解决：找cur左边or右边 第一个比cur大or小的元素
   栈内应该递增还是递减：递增 - 求cur左边or右边第一个比cur大的元素（栈底元素最大）
                    递减 - 求            第一个比cur小的

    本题单调栈里应该存下标，因为要求的是下标的差值
     */
    public int[] dailyTemperatures2(int[] tem) {

        int length = tem.length;
        int[] result = new int[length];
        Deque<Integer> stack = new LinkedList<>();  // 存放下标

        if(length==1){
            return result;
        }

        stack.push(0);

        for(int i=1; i<=length-1; i++){
            while(!stack.isEmpty() && tem[i] > tem[stack.peek()]){  // 大于栈顶元素，栈顶元素一直出栈
                int index = stack.pop();

                result[index] = i-index;
            }

            stack.push(i);   // 别忘了i入栈！！！
        }

        // 如果结束后栈内还有元素，说明后面没找到更高的温度，根据题意置为0
        while(!stack.isEmpty()){
            result[stack.pop()] = 0;
        }

        return result;
    }










}
