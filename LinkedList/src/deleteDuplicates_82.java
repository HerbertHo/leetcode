public class deleteDuplicates_82 {

    /*
    方法一 from 我， 见ipad
     */
    public ListNode deleteDuplicates1(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        ListNode pre = emptyHead;
        ListNode cur = head;

        while(cur.next!=null){  // 因为有 cur.next.val， 所以cur.next不能为null

            ListNode nextNode = cur.next;

            if(cur.val != nextNode.val){  // 这种情况pre才动！！！
                pre = cur;
                cur = cur.next;
            }else{          // 这种情况pre不动！！！

                while(cur.next!=null && cur.val==nextNode.val){  // 一直找到末尾or不是重复元素 为止
                    cur = cur.next;
                    nextNode = nextNode.next;
                }

                pre.next = nextNode;

                if(nextNode==null){   // 到了末尾
                    break;
                }else{                // 没到末尾
                    cur = nextNode;
                    nextNode = nextNode.next;
                }
            }
        }


        return emptyHead.next;
    }



    /*
    解法二 ： from B站， 好理解
    https://www.bilibili.com/video/BV17T411W741/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */

    public ListNode deleteDuplicates2(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        ListNode cur = emptyHead;   // cur表示不重复元素（不会被删除的元素）

        // 比较cur的下一个节点 和 下下一个节点的值
        while(cur.next!=null && cur.next.next!=null){

            if(cur.next.val == cur.next.next.val){

                int value = cur.next.val;   // 记录要删除的值(十分好用！！！)

                // 删除所有值为value的节点
                while(cur.next!=null && cur.next.val==value){
                    cur.next = cur.next.next;  // 跳过cur.next
                }
            }else{
                cur = cur.next;
            }
        }

        return emptyHead.next;
    }


    /*
    解法二的不同写法， 好理解
     */
    public ListNode deleteDuplicates2_2(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        ListNode pre = emptyHead;
        ListNode cur = head;

        while(cur!=null){

            ListNode next = cur.next;

            if(next!=null && next.val==cur.val){    // cur.next与cur一样

                int value = cur.val;

                while(next!=null && next.val==value){   // 找到所有值为value的

                    next = next.next;
                }

                pre.next = next;
                cur = next;
            }else{               // cur.next与cur不一样 或 cur.next==null， pre和cur移动一格

                pre = cur;
                cur = next;
            }
        }

        return emptyHead.next;
    }










}
