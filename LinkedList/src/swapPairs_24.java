public class swapPairs_24 {
    /*
    给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
     */


    /* 方法一：递归
      这一题我一开始拿到就想到了递归
      分析见ipad
     */
    public ListNode swapPairs1(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode nextNode = head.next;

        head.next = swapPairs1(nextNode.next);  // 先执行后面的，再执行前面的  这样 1.next=4，而不是1.next = 3
        nextNode.next = head;

        return nextNode;
    }


    /*
    方法二：没用头节点,但有错误
     */
    public ListNode swapPairs2(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode returnNode = head.next;
        ListNode cur = head;


        while(cur!=null && cur.next!=null){
            ListNode nextNode = cur.next;
            ListNode newHead = nextNode.next;

            nextNode.next = cur;
            cur.next = newHead;

            cur = newHead;
        }

        return returnNode;
    }


    /*
    方法三 添加空的头节点，正确
    分析见ipad
     */
    public ListNode swapPairs3(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        // 用emptyHead操作1、2两个节点，让它们交换
        ListNode cur = emptyHead;

        while(cur.next!=null && cur.next.next!=null){
            ListNode first = cur.next;
            ListNode second = cur.next.next;


            cur.next = second;  // ①
            first.next = second.next;   // ②
            second.next = first;    // ③

            cur = first;    // 为什么是first，不是second
        }

        return emptyHead.next;
    }


    /*
    方法四 from我 ， 类似方法三
     */
    public ListNode swapPairs4(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }


        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        swapTwo(emptyHead);

        return emptyHead.next;

    }


    void swapTwo(ListNode cur){
        if(cur.next!=null && cur.next.next!=null){
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            first.next = second.next;  // 这一句要在前面，不能等second.next变了再执行
            cur.next = second;
            second.next = first;

            swapTwo(first);   // 继续调用
        }
    }






}
