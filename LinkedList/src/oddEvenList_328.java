public class oddEvenList_328 {

    /*
    leet328 奇偶链表
     */

    public ListNode oddEvenList1(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode emptyNode = new ListNode();  // 偶数节点的头

        ListNode cur = head;   // 遍历原链表
        ListNode cur2 = emptyNode;  // 遍历偶数链表
        ListNode pre = null;   // 记录cur的位置，方便循环结束时找尾节点

        while(cur!=null && cur.next!=null){
            ListNode second = cur.next;
            ListNode newHead = second.next;  // 可能为null

            // 把second连接到 偶数链表尾部
            cur2.next = second;
            second.next = null;
            cur.next = newHead;

            // 更新
            cur2 = second;
            pre = cur;   // 记录cur的位置，方便循环结束时找尾节点
            cur = newHead;
        }


        if(cur==null){  // 此时pre是奇数链表的尾巴
            pre.next = emptyNode.next;
        }else{
            cur.next = emptyNode.next;
        }

        return head;
    }












}
