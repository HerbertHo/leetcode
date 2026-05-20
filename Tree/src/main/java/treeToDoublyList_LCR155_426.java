public class treeToDoublyList_LCR155_426 {

    /*
    思路:
    本题要将一个 二叉搜索树 转为一个 有序的双向链表
    首先判断二叉树的遍历顺序, 由于二叉搜素树的中序遍历得到的就是有序的,所以采用中序遍历

    详见ipad
     */

    /*
    方法一: 实现from我 ,思想from GPT / B站
     */
    Node head;   // 用于最终作为链表的头节点返回
    Node pre;    // 指向cur的前一个节点，用于与cur相连

    public Node treeToDoublyList(Node root) {
        if(root==null){
            return null;
        }

        func(root);

        // 易忘！！！ 最终结束时，pre更新为最后一个节点，由于要求时循环链表，所以头尾相连
        head.left = pre;
        pre.right = head;

        return head;

    }


    void func(Node cur){
        if(cur==null){     // 递归的终止条件
            return;
        }

        // 1、中序遍历（左）
        func(cur.left);

        // 2、中序遍历（中）: 对cur的处理逻辑
        if(pre == null){
            head = cur;      // 头节点，没有pre，不用与pre相连
        }else{    // 有pre， cur与pre相连
            cur.left = pre;
            pre.right = cur;
        }

        pre = cur;   // 更新pre，用于找到目前cur的后序节点时，与当前cur相连

        // 3、中序遍历（右）
        func(cur.right);
    }






}
