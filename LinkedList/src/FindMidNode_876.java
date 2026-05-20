public class FindMidNode_876 {
    /*
    力扣876  找中间链表
    给你单链表的头结点 head ，请你找出并返回链表的中间结点。
    如果有两个中间结点，则返回第二个中间结点。
     */
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution7 {

    /*
    我的想法：
    统计链表节点个数，除以2，找出中间的个数，再遍历链表找出该节点
    我们发现，若链表有5个节点，中间的为第3个节点    5/2=2  2+1=3
             若链表有6个节点，中间的为第4个节点   6/2=3   3+1=4(都要加1)
     */
    public ListNode middleNode(ListNode head) {

        if(head == null){
            return null;
        }

        int count = 0;
        ListNode tempNode = head;

        while(tempNode!=null){
            count++;
            tempNode = tempNode.next;
        }

        int mid = count/2;
        tempNode = head;
        // 要找第 mid+1 个节点，要在第一个节点处移动 mid 次
        for(int i=0;i<mid;i++){
            tempNode = tempNode.next;
        }

        return tempNode;

    }


    /*
    老师：找中间，继续快慢指针
     */
    public ListNode middleNode2(ListNode head){

        if(head == null){
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;

    }











}


















