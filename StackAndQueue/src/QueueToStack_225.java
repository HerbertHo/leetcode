import java.util.*;

public class QueueToStack_225 {

    /* 总结
    在用两个栈模拟队列时，两个栈角色不一样：一个是outStack,一个是inStack

    在用两个队列模拟栈时，两个队列角色完全一样，只是看队列为不为空而已
     */

    /* 用两个队列模拟栈
    栈中1、2、3，出栈为3
    队列A中1、2、3 ， 若直接出队列为1，所以将 1、2放入队列B，然后在A中只剩3时出队列
     */

    /*
    方法一 from 我 same as GPT
     */

    Deque<Integer> deque1 = new LinkedList<>();
    Deque<Integer> deque2 = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {   // 哪个队不为空，入哪个队（都为空随意）
        if(deque1.isEmpty()){
            deque2.addLast(x);
        }else{
            deque1.addLast(x);
        }

    }

    public int pop() {
        if(empty()){
            return -1;
        }

        if(deque1.isEmpty()){   // 说明元素在2
            while(deque2.size()!=1){
                int val = deque2.removeFirst();
                deque1.addLast(val);
            }
            return deque2.removeFirst();
        }else{
            while(deque1.size()!=1){
                int val = deque1.removeFirst();
                deque2.addLast(val);
            }
            return deque1.removeFirst();
        }
    }

    public int top() {
        if(empty()){
            return -1;
        }

        if(deque1.isEmpty()){   // 说明元素在2
            while(deque2.size()!=1){
                int val = deque2.removeFirst();
                deque1.addLast(val);
            }
            int val = deque2.removeFirst();   // 先出列，再入列
            deque1.addLast(val);

            return val;
        }else{
            while(deque1.size()!=1){
                int val = deque1.removeFirst();
                deque2.addLast(val);
            }
            int val = deque1.removeFirst();
            deque2.addLast(val);

            return val;
        }




    }

    public boolean empty() {
        if(deque1.isEmpty() && deque2.isEmpty()){
            return true;
        }else{
            return false;
        }
    }


    /*
    用一个队列模拟栈

    栈中1、2、3，出栈为3
    队列A中1、2、3 ， 若直接出队列为1 ， 所以将1、2取出后重新加入队列即可 （将前size-1个元素弹出来，再重新入队）
     */




}
