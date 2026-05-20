import java.util.HashMap;

public class copyRandomList_138 {

    /*
    方法一 from GPT
    在原链表每个节点后插入一个复制节点    理解了思想，操作很简单
     */

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 第一步：在原链表每个节点后插入一个复制节点
        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);  // 创建当前节点的副本copy

            copy.next = cur.next;       // copy插入到cur之后
            cur.next = copy;

            cur = copy.next;                // 移动到下一个原节点
        }


        // 第二步：设置复制节点的 random 指针
        cur = head;
        while (cur != null) {
            if (cur.random != null) {     // 必须要，否则会有 null.next!!!
                cur.next.random = cur.random.next; // copy的random 由cur的random得出
            }
            cur = cur.next.next; // 移动到下一个原节点
        }


        // 第三步：将原链表和复制链表拆分
        cur = head;
        Node newHead = head.next; // 复制链表的头节点

        while (cur != null) {
            Node copy = cur.next;     // 当前复制节点
            cur.next = copy.next;     // 恢复原链表的 next 指针
            if (copy.next != null) {
                copy.next = copy.next.next; // 设置复制链表的 next 指针
            }
            cur = cur.next; // 继续向后遍历
        }

        return newHead; // 返回复制链表的头节点
    }


    /*
    方法二 from GPT
    利用哈希表, 很巧妙，只是空间复杂度为 o(n)
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        HashMap<Node, Node> map = new HashMap<>();  // 把cur和copy作为键值对存入map

        // 第一次遍历：创建所有新节点，并填入 map
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));  // 把cur和copy作为键值对存入map

            cur = cur.next;
        }

        // 第二次遍历：设置每个新节点的 next 和 random 指针
        cur = head;
        while (cur != null) {
            Node copy = map.get(cur);         // 取出copy

            copy.next = map.get(cur.next);     // 666
            copy.random = map.get(cur.random); //

            cur = cur.next; // 遍历下一个原节点
        }


        return map.get(head);   // 返回原链表头对应的新链表头
    }



    /*
    我自己的写法，跟方法一一样
     */
    public Node copyRandomList3(Node head) {

        // 复制节点
        if(head==null){
            return null;
        }

        Node cur = head;

        while(cur!=null){

            Node next = cur.next;

            // 1、构造新节点
            Node newNode = new Node(cur.val);

            newNode.next = next;
            cur.next = newNode;

            cur = next;  // 更新
        }


        // 2、设置新节点的random指针
        cur = head;

        while(cur!=null){
            Node second = cur.next;  // 复制的节点

            if(cur.random!=null){    // 易错点，不然会有null.next !!!
                second.random = cur.random.next;
            }

            cur = second.next;  // 更新
        }


        // 3、断开复制节点
        Node emptyHead = new Node(-1);
        Node temp = emptyHead;  // 遍历新链表

        cur = head;

        while(cur!=null){

            Node second = cur.next;
            Node third = second.next;

            temp.next = second;
            second.next = null;
            cur.next = third;


            cur = third; // 更新
            temp = second;
        }

        return emptyHead.next;
    }





}


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}