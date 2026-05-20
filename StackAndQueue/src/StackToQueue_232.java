import java.util.Stack;

public class StackToQueue_232 {

}

class MyQueue {

    /* 总结
    在用两个栈模拟队列时，两个栈角色不一样：一个是outStack,一个是inStack

    在用两个队列模拟栈时，两个队列角色完全一样，只是看队列为不为空而已
     */


    /*
    两个栈；一个输入栈，一个输出栈
    当往queue中push时，实际上压往输入栈         当往queue中取元素时，实际上从输出栈获取（如果输出栈为空，先把输入栈中元素全部放入输出栈）
    图示详见笔记

    具体实现时可以使用链表，也可以直接使用JDK中的Stack
     */

    // java栈的用法、有无大小size

    private Stack<Integer> inStack = new Stack<>();   // 如果用java提供的栈，只能 泛型必须是引用类型
    private Stack<Integer> outStack = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {   // void push(int x) 将元素 x 推到队列的末尾
        inStack.push(x);
    }

    public int pop() {       // int pop() 从队列的开头移除并返回元素
        if(outStack.isEmpty()){      // 如果outStack有元素，直接出栈；  否则先将inStack中元素转移到outStack再出栈
            inToOut();
        }
        return outStack.pop();
    }

    public int peek() {   // int peek() 返回队列开头的元素
        if(outStack.isEmpty()){
            inToOut();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty()&&outStack.isEmpty();
    }

    public void inToOut(){   // 输入栈到输出栈
        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }



}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */