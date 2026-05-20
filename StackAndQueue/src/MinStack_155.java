import java.util.*;

public class MinStack_155 {

    /*
    方法一 from GPT

    用两个栈：一个普通栈， 另一个栈存放目前找到的最小值
     */

    private Deque<Integer> stack;     // 主栈：存放所有元素

    private Deque<Integer> minStack;     // 辅助栈：存放每一步的最小值

    // 构造函数，初始化两个栈
    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    // 入栈操作
    public void push(int val) {
        stack.push(val); // 正常入主栈

        // 如果 minStack 为空 或 当前值小于等于栈顶最小值，也压入 minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // 出栈操作
    public void pop() {
        int top = stack.pop(); // 弹出主栈元素

        // 如果弹出的正好是最小栈栈顶，也一起弹出
        if (top == minStack.peek()) {
            minStack.pop();
        }
    }

    // 查看栈顶元素
    public int top() {
        return stack.peek();
    }

    // 获取当前栈中的最小元素
    public int getMin() {
        return minStack.peek();
    }










}
