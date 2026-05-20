public class deleteDuplicates_83 {


    /*
    方法一：常规
     */

    public ListNode deleteDuplicates1(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }


        // 已排序
        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        ListNode pre = emptyHead;
        ListNode cur = head;

        while(cur.next!=null){
            if(cur.val != cur.next.val){
                pre = pre.next;
                cur = cur.next;
            }else{
                cur = cur.next;
                pre.next = cur;
            }
        }

        return emptyHead.next;
    }



    /*
    方法二：由于本题可以保留一个重复元素，所以可以用emptyHead
     */
    public ListNode deleteDuplicates2(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = pre.next;

        while(cur!=null){

            while(cur!=null && cur.val==pre.val){
                cur = cur.next;
            }

            pre.next = cur;

            if(cur==null){
                return head;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }

        return head;
    }

    /*
    写法3
     */
    public ListNode deleteDuplicates3(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode cur = head;

        while(cur!=null){

            ListNode next = cur.next;

            while(next!=null && next.val==cur.val){

                next = next.next;
            }

            cur.next = next;
            cur = next;
        }

        return head;
    }








}
