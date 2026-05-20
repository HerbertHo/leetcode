public class removeElements_203 {
    /* 题目描述：删除链表值为val的节点 from 代码随想录
    给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     */

    /*
    对比27题 数组中删除值为val的元素，用 双指针fast和slow法
     */

    /*
       若要删除头节点，有两种方式
       （1）添加一个空的头节点，这样原来的head就不是头节点了，可以作为普通节点处理
       （2）不添加空的头节点，将head作为特殊节点处理
            其他节点要删除的话找前一个节点pre，头节点要删除的话直接 head = head.next
     */

    /*
    解法一：我的想法，添加一个空的头节点防止 原链表的第一个节点就是val  -> 就是代码随想录中推荐的方法
     */
    public ListNode removeElements1(ListNode head, int val) {

        ListNode emptyHead = new ListNode();  // 定义一个空节点作为头节点
        emptyHead.next = head;

        ListNode pre = emptyHead;
        ListNode current = head;

        while(current != null){         // 注意一种情况：连续几个节点都为val
            if(current.val == val){
                current = current.next;
                pre.next = current;
            }else{
                pre = pre.next;
                current = current.next;
            }
        }

        return emptyHead.next;

    }


    /*
    解法二：不用空的头节点  -> 麻烦
     */
    public ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {   // 注意是while，head为val时一直删
            head = head.next;
        }

        if (head == null) {    // 已经为null，提前退出
            return head;
        }

        ListNode pre = head;     // 已确定当前head.val != val
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }






}
