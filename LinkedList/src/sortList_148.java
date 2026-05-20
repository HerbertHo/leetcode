public class sortList_148 {

    /*
    方法一：传统归并 in 链表
     */

    public ListNode sortList1(ListNode head) {
        return mergeSort1(head);
    }


    // 经典归并
    private ListNode mergeSort1(ListNode cur){
        if(cur==null || cur.next==null){   // 递归终止条件：为空节点or单个节点
            return cur;
        }

        // 左边有序，右边有序，合并

        // (1)找到中间节点
        ListNode slow = cur;
        ListNode fast = cur.next;

        while(fast!=null && fast.next!=null){  //  while(leftHead!=null && rightHead!=null)也行
            fast = fast.next.next;
            slow = slow.next;
        }

        // (2)断开连接，让左右成为两个链表
        ListNode rightHead = slow.next;   // 右链表的头
        slow.next = null;

        // (3)分别排序左右链表
        ListNode leftHead =  mergeSort1(cur);
        rightHead = mergeSort1(rightHead);

        // (4)合并左右链表
        return merge1(leftHead, rightHead);
    }


    ListNode merge1(ListNode leftHead, ListNode rightHead){

        ListNode emptyHead = new ListNode();
        ListNode tail = emptyHead;

        while(leftHead!=null && rightHead!=null){
            if(leftHead.val <= rightHead.val){
                tail.next = leftHead;
                tail = leftHead;

                leftHead = leftHead.next;
                tail.next = null;
            }else{
                tail.next = rightHead;
                tail = rightHead;

                rightHead = rightHead.next;
                tail.next = null;
            }
        }

        if(leftHead!=null){
            tail.next = leftHead;
        }

        if(rightHead!=null){
            tail.next = rightHead;
        }

        return emptyHead.next;
    }



    /*
    方法二：优化的归并 自底向上

    思想见：https://www.bilibili.com/video/BV1V84y1T7fW/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */








}
