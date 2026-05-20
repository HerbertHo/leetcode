import java.util.HashSet;
import java.util.Set;

public class HasCycleTest2_142 {
    // P142 判断链表中是否有环, 若有环，返回环的第一个节点；若无环，返回null



/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

    /*
    方法一：继续利用哈希表
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode temp = head;

        while(temp != null){
            if(!set.contains(temp)){
                set.add(temp);
                temp = temp.next;
            }else {
                return temp;
            }
        }

        return null;
    }



    /*
        方法二：快慢指针
        先用slow=1，fast=2判断是否有环   若无环直接返回null   若有环，进行下面步骤：
        将slow移到head处，让fast速度也变化1，可以证明当fast和slow相遇时就是环的起点(可以让chatgpt证明)
     */
    public ListNode detectCycle2(ListNode head){

        if(head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean flag = false;   // 判断是否有环

        // 先判断环是否存在，类似leetcode141
        while(fast!=null && fast.next!=null){  // 为什么是&& 而不是||  因为fast.next != null 确保 fast.next 不是 null，这样 fast.next.next 访问时不会报空指针错(防止出现null.next的情况)
            fast = fast.next.next;
            slow = slow.next;

            if(fast==slow){
                flag = true;
                break;
            }
        }

        if(fast==null || fast.next==null){
            return null;
        }

        // 有环
        slow = head;

        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }












}