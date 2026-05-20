public class HuiWenLianBiao_234 {
    /*
    力扣234   回文链表 ： 从前往后读 and 从后往前读都一样的链表   eg  1->2->2->1   [1,2,2,1]
    给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
     */


    public boolean isPalindrome(ListNode head) {

        // 我的想法，可以先得到它的反转链表，再一一比较反转链表与原链表的元素


        // 老师解法一：将链表的数据全部复制到一个数组中，然后用两个指针从头到尾、从尾到头遍历这个数组，一一比较两个指针所指向的值是否一样
        // 但这种解法时间和空间复杂度都是O(n)  不符合进阶要求中O(1)空间复杂度的要求


        /*
        老师解法二：利用反转链表
        将原链表从中间一分为二，将后半部分反转，然后与前半部分比较
         */

        if(head == null){
            return false;
        }

        if (head.next == null){
            return true;
        }


        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        if(fast != null){   // 如果是奇数节点，再往后移动一次
            slow = slow.next;
        }

        slow = reverse(slow);  // 从slow处反转链表
        fast = head;

        while(slow != null){   // 后半链表长度 <= 前半链表长度
            if(slow.val != fast.val){   // 这里注意是比较节点的值
                return false;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }

        return true;
    }


    ListNode reverse(ListNode head) {

        if(head==null || head.next==null){    // 没有节点or只有一个节点
            return head;
        }

        ListNode currentNode = head;
        ListNode preNode = null;

        while(currentNode != null){
            ListNode nextNode = currentNode.next;

            currentNode.next = preNode;

            preNode = currentNode;
            currentNode = nextNode;
        }

        return preNode;   // 注意return的是preNode  因为currentNode已经是null
    }



    /*
    我对解法二的优化
     */
    public boolean isPalindrome3(ListNode head) {

        // 断开并反转后部分

        if(head==null || head.next==null){
            return true;
        }

        if(head.next.next==null){   // 2个节点
            return head.val == head.next.val;
        }

        ListNode slow = head;
        ListNode fast = head;


        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        // 反转head2
        ListNode pre = null;
        ListNode cur = head2;

        while(cur!=null){
            ListNode next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }

        // pre是后半部分反转后链表的头
        while(pre!=null){
            if(head.val == pre.val){
                head = head.next;
                pre = pre.next;
            }else{
                return false;
            }
        }

        return true;
    }










}








