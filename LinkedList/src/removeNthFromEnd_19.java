public class removeNthFromEnd_19 {
    /*

     */


    /*
    方法一：常规法
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        // 为防止删除的是头节点带来的特殊操作，添加emptyHead
        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        // 设链表长度为length，倒数第n个节点就是正数第length-n+1个节点（2个节点，倒数第1就是正数第2）
        ListNode tempNode = head;
        int size = 0;
        while(tempNode != null){
            size++;
            tempNode = tempNode.next;
        }

        if(n > size){
            return emptyHead.next;
        }

        tempNode = emptyHead;
        // 要删除第length-n+1个节点，就要找到第length-n个节点(size=10 n=10 要找第0个节点，tempNode不用动)
        for(int count=0; count<size-n; count++){
            tempNode = tempNode.next;
        }

        tempNode.next = tempNode.next.next;

        return emptyHead.next;
    }


    /*
    方法二：双指针法
    fast和slow都先指向emptyHead，fast先走n步，之后slow、fast同时走，当fast到达尾节点rear（rear.next==null）时，slow指向要删除节点的前一个结点
    比如 1 2 3 中 n=2， fast先从empty走到2，之后slow、fast开始同时走，当fast走到尾节点3时，slow走到要删除节点的前一个结点1
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;
        ListNode fast = emptyHead;
        ListNode slow = emptyHead;

        // fast先走n步
        for(int i=0;i<n;i++){
            fast = fast.next;
        }

        // 同时走，直到fast走到尾节点
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return emptyHead.next;
    }











}
