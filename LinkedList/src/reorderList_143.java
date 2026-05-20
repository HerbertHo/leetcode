public class reorderList_143 {

    /*  方法一 from GPT

    因为无法找到链表末尾元素的前一个元素，所以考虑将后半部分反转  见ipad
     */
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null) return;

        // 1. 快慢指针，找到链表的中点
        ListNode slow = head, fast = head;

        while (fast.next!=null && fast.next.next!=null) {   // fast.next!=null是奇数节点时；  fast.next.next!=null是偶数节点时
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半部分链表
        ListNode prev = null, curr = slow.next;

        while (curr != null) {
            ListNode nextTemp = curr.next;

            curr.next = prev;

            prev = curr;
            curr = nextTemp;
        }

        slow.next = null; // 断开前半部分和后半部分

        // 3. 合并两个链表
        ListNode first = head, second = prev;

        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }


    /*
    我对上述写法的优化

    步骤：（1）断开后半链表  （2）反转后半链表   （3）将后半链表的节点插入前半链表
     */
    public void reorderList2(ListNode head) {

        if(head==null || head.next==null || head.next.next==null){   // 0、1、2个节点，不用操作
            return;
        }

        // 断开后半链表
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode head2 = slow.next;
        slow.next=null;

        // 反转后半链表
        ListNode pre = null;
        ListNode cur = head2;

        while(cur!=null){
            ListNode next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }

        // pre是后半链表的头

        // 头插法


        while(pre!=null){
            ListNode next1 = head.next;
            ListNode next2 = pre.next;

            pre.next = next1;
            head.next = pre;

            head = next1;
            pre = next2;
        }
    }





}
