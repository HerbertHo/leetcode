public class trainingPlan_LCR140 {

    /*
    方法一 from 我

    倒数第k，正数第length+1-k，但链表无法知道length

   采用双指针，fast先走k步，等fast到null时slow指向要求的节点
     */

    public ListNode trainingPlan(ListNode head, int k) {



        ListNode fast = head;
        ListNode slow = head;

        // fast先走k步
        for(int i=0; i<k;i++){
            fast = fast.next;
        }

        // 同时移动fast和slow，等fast指向null时，slow为待求
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }







}
