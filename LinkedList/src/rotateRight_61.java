public class rotateRight_61 {

    /* 我的思考过程：
       把末尾元素移动到头，重复k次

        1、但是每次找末尾元素不好找，于是反转链表
           步骤：（1）反转链表  （2）将头节点移到末尾，重复k次  （3）再反转链表
            由于将头节点移到末尾时还要找尾节点，所以放弃

        2、为了避免每次找尾节点，想到将尾节点连接到头节点（成环）
     */

    /*
    方法一 from 我，用了上述思想， 跟GPT一样
     */

    public ListNode rotateRight1(ListNode head, int k) {

        if(head==null || head.next==null || k==0){
            return head;
        }

        ListNode cur = head;
        int length = 1;

        while(cur.next!=null){
            cur = cur.next;
            length++;
        }

        // 此时cur为尾节点
        cur.next = head;  // 成环

        // 题意转换为：倒数第k个元素称为头节点
        k = k % length;   // 不要用while(k >= length){k -= length;} ， 这会非常慢！！！

        // 找到倒数第k个节点和倒数第k+1个节点（新的尾节点）
        // 倒数第k个节点：正数第length-k+1个节点（从1开始数）（新头节点）
        // 倒数第k+1个节点：正数第length-k个节点（从1开始数）（新尾节点）
        cur = head;


        for(int i=1; i<length-k; i++){  // 找到新尾节点
            cur = cur.next;
        }

        ListNode pre = cur;
        ListNode newHead = pre.next;

        pre.next = null;

        return newHead;
    }














}
