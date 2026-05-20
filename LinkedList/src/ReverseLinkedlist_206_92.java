public class ReverseLinkedlist_206_92 {
    /*
    力扣206  反转一个给定的链表
    给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
     */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

    // 注意： 力扣的链表没有空的头节点

    /*  方法一：不添加 空的头节点       ->  推荐
    反转链表思路： 将每个节点的next从指向后一个节点改为指向前一个节点 ，所以要设置currentNode preNode nextNode
     */
    public ListNode reverseList1(ListNode head) {

        if(head==null || head.next==null){    // 没有节点or只有一个节点
            return head;
        }

        ListNode currentNode = head;   // 易忘： head的next要置为null，否则会成环！！！
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
    方法二：递归的写法
    思想和双指针的一样，但比较难懂
     */
    public ListNode reverseList(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        return reverseNode(null,head);

    }


    ListNode reverseNode(ListNode pre, ListNode cur){   // 必须要有返回值，否则在主函数reverseList中不知道return什么

        if(cur==null){
            return pre;
        }

        ListNode second = cur.next;

        cur.next = pre;

        return reverseNode(cur,second);
    }



    /*
    方法三：递归 from chatGPT
           图示见ipad
     */
        public ListNode reverseList3(ListNode head) {
            // 递归终止条件：链表为空或只有一个节点时，直接返回该节点
            if (head == null || head.next == null) {
                return head;
            }

            // 递归反转后续链表
            ListNode newHead = reverseList3(head.next);

            // 反转当前节点与下一个节点的指向
            ListNode nextNode = head.next;
            nextNode.next = head;
            head.next = null;

            return newHead;
        }




    /*
    方法四：添加空的头节点, 头插法
     */
    public ListNode reverseList4(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        // 头插法
        ListNode emptyHead = new ListNode();

        ListNode cur = head;

        while(cur != null){
            ListNode second = cur.next;

            cur.next = emptyHead.next;
            emptyHead.next = cur;

            cur = second;
        }

        return emptyHead.next;
    }







    /*
    leet 92
     */

    /*
    方法一 from 我

    找到要反转部分的前一个、后一个节点
    先反转需要反转part的中间部分，再处理part的两端
     */
    public ListNode reverseBetween1(ListNode head, int left, int right) {

        if(head.next==null){
            return head;
        }

        if(left==right){  // 等于没反转
            return head;
        }

        // 根据题意，left和right均合法, 反转索引为left-1和right-1之间的元素
        // 找到要反转部分 之前和之后的节点,即索引为left-2和right的节点（emptyhead的索引为-1）

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        // 找到要反转部分 之前
        ListNode cur = emptyHead;
        for(int i=0; i<=left-2; i++){
            cur = cur.next;
        }
        ListNode before = cur;

        // 找到反转的最后一个节点，和其之后的节点
        cur = emptyHead;
        for(int i=0; i<=right-1; i++){
            cur = cur.next;
        }
        ListNode last = cur;
        ListNode after = cur.next;

        // 处理要反转部分的中间
        ListNode pre = before.next;
        cur = pre.next;

        while(pre!=last){
            ListNode nextNode = cur.next;

            cur.next = pre;   // ①

            pre = cur;
            cur = nextNode;
        }


        // 处理要反转部分的两端
        before.next.next = after;   // ②
        before.next = last;        // ③

        return emptyHead.next;
    }



    /*
    方法二 from chatGPT
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {

        if(head.next==null){
            return head;
        }

        if(left==right){  // 等于没反转
            return head;
        }

        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        // 找到要反转部分 之前
        ListNode cur = emptyHead;
        for(int i=0; i<=left-2; i++){
            cur = cur.next;
        }
        ListNode before = cur;

        cur = before.next;
        ListNode then = cur.next;

        // 使用头插法，每次把then插入before后面
        for (int i = 0; i < right - left; i++) {
            cur.next = then.next;
            then.next = before.next;
            before.next = then;

            then = cur.next;
        }

        return emptyHead.next;
    }


    /*
    方法三 from 我，也是用头插法，但是更好理解 见ipad
     */
    public ListNode reverseBetween3(ListNode head, int left, int right) {

        if(head==null || head.next==null){
            return head;
        }


        ListNode emptyHead = new ListNode();
        emptyHead.next = head;

        // 添加空的头节点后，要反转的部分索引为 left 到 right , 要反转 right-left+1个节点

        // 找到first，start，end，second
        ListNode cur = emptyHead;
        for(int i=0; i<=left-2; i++){
            cur = cur.next;
        }
        ListNode first = cur;
        ListNode start = cur.next;

        cur = start;
        for(int i=1; i<=right-left; i++){ // 从start到end要移动right-left次
            cur = cur.next;
        }
        ListNode end = cur;
        ListNode second = end.next;   // 可能为null

        first.next = second;  // 关键!!! 这样start进行头插法后 start.next=second

        // 头插法，将cur插到first之后
        cur = start;
        while(cur!=second){
            ListNode nextNode = cur.next;

            cur.next = first.next;
            first.next = cur;

            cur = nextNode;
        }

        return emptyHead.next;
    }





}
















