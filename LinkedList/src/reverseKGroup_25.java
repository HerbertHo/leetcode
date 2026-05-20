public class reverseKGroup_25 {

    /*
    方法一 from 我， 跟leet92思路一样
     */
    public ListNode reverseKGroup1(ListNode head, int k) {

        if(head==null || k==1){
            return head;
        }

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        // 先求length
        int length = 0;
        ListNode cur = head;
        while(cur!=null){
            cur = cur.next;
            length++;
        }

        int count = 0;  // 用于判断剩下的元素够不够反转

        ListNode before = emptyHead;

        while(length-count >= k){
            cur = before;
            ListNode start = before.next;

            // 从before移动k个到end
            for(int i=0;i<k;i++){
                cur = cur.next;
            }
            ListNode end = cur;
            ListNode after = cur.next;


            // 反转
            ListNode pre = start;
            cur = start.next;

            while(cur!=after){
                ListNode next = cur.next;

                cur.next = pre;

                pre = cur;
                cur = next;
            }

            before.next = end;
            start.next = after;


            // 更新数值
            count += k;
            before = start;
        }

        return emptyHead.next;
    }












}
