public class addTwoNumbers_2 {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*
    方法一：用数字运算，得到结果数字，然后取出每个数字作为Node.val连上链表    有错误：链表很长时超出 int甚至是long的边界
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        // 读取两个链表的数字
        StringBuilder builder1 = new StringBuilder();

        while(l1!=null){
            builder1.append(l1.val);

            l1 = l1.next;
        }

        StringBuilder builder2 = new StringBuilder();

        while(l2!=null){
            builder2.append(l2.val);

            l2 = l2.next;
        }

        // builder1 =
        String num1Str = builder1.reverse().toString();
        long num1 = Long.valueOf(num1Str);

        String num2Str = builder2.reverse().toString();
        long num2 = Long.valueOf(num2Str);

        long num = num1+num2;  // 807
        String numStr = new StringBuilder().append(num).reverse().toString();   // "708"

        ListNode emptyHead = new ListNode();
        ListNode cur = emptyHead;

        for(int i=0; i<numStr.length(); i++){
            int val = numStr.charAt(i)-'0';

            ListNode newNode = new ListNode(val);

            cur.next = newNode;

            cur = newNode;
        }

        return emptyHead.next;
    }






    /*
    方法二：
    类似leet415字符串加法，考虑进位
     */

    // 链表不一样长, 至少有一个节点
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);

        ListNode current = dummyHead;  // 遍历新链表的指针

        int carry = 0;    // 上一组相加的进位


        while (l1 != null || l2 != null || carry != 0) {   // 当l1或l2不为空，或者还有进位要处理时，继续循环

            int x = (l1 != null) ? l1.val : 0;   // 取出当前位的值，如果对应链表节点为空则记为0
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + carry;

            carry = sum / 10;

            current.next = new ListNode(sum % 10);

            // 更新指针
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 返回结果链表的头节点（去除虚拟头）
        return dummyHead.next;
    }



    /*
    方法三：我的错误解法： 将两个链表reverse后，从头节点遍历、相加
             错误原因：当两个链表长度不一样时，容易导致错位相加
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {

        // 因为加法有进位，所以还是要从个位数加起

        // 找到两个链表的末尾
        ListNode cur = l1;

        while(cur.next!=null){
            cur = cur.next;
        }

        ListNode t1 = cur;

        cur = l2;

        while(cur.next!=null){
            cur = cur.next;
        }

        ListNode t2 = cur;

        // 为了从个位数开始计算，反转两个链表，这时t1、t2成了头
        reverse(l1);
        reverse(l2);

        // 进行加法计算，每次把计算结果放到resultList尾
        ListNode emptyHead = new ListNode();  // 结果链表
        cur = emptyHead;  // 指向结果链表的尾部

        int digit = 0;  // 进位

        while(t1!=null || t2!=null || digit!=0){
            int num1 = t1!=null ? t1.val : 0;
            int num2 = t2!=null ? t2.val : 0;

            int sum = num1+num2+digit;

            int value = sum % 10;    // 新节点的值
            digit = sum / 10;

            ListNode newNode = new ListNode(value);

            cur.next = newNode;
            cur = newNode;

            if(t1 != null){
                t1 = t1.next;   // 不要忘了
            }

            if(t2!=null){
                t2 = t2.next;
            }

        }

        return emptyHead.next;
    }


    void reverse(ListNode head){

        ListNode pre = null;
        ListNode cur = head;

        while(cur!=null){
            ListNode next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }
    }






}
