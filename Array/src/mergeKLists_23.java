import java.util.PriorityQueue;

public class mergeKLists_23 {

    /*
    方法1：暴力求解

    步骤：
    ·遍历所有链表，将所有节点的值放到一个数组中，将这个数组排序
    用数组中的值，创建一个新的有序链表

   复杂度：
   时间复杂度：O(NlogN)，其中N是节点的总数目
    遍历所有的值需花费O(N)的时间
    一个稳定的排序算法花费O(NlogN)的时间
    遍历同时创建新的有序链表花费O(M)的时间

    空间复杂度：O(N)
    排序花费O(N)空间
    创建一个链表花费O(N)的空间
     */


    /*
    方法2：逐一比较

    步骤：
    比较k个节点（每个链表的首节点），获得最小值的节点
    将选中的节点接在最终有序链表的后面

    复杂度分析：
    时间复杂度：O(kN)，其中k是链表的数目
    几乎最终有序链表中每个节点的时间开销都为O(k)(k一1次比较）
    总共有N个节点在最后的列表中

    空间复杂度：
    O(n)：创建一个新的链表空间开销为O(n)
    O(1)：重复利用原来的链表节点，每次选择节点时将它直接接在最后返回的链表后面，而不是创建一个新的节点
     */


    /*
    方法3：用PriorityQueue
     */
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> heap = new PriorityQueue<>((a,b) -> (a.val - b.val));  // 记得要重写排序规则！！！

        for(ListNode head : lists){
            if(head!=null){      // 节点不能为null
                heap.add(head);
            }
        }

        // 构造新的链表
        ListNode emptyHead = new ListNode();
        ListNode tail = emptyHead;

        while(!heap.isEmpty()){

            ListNode minNode = heap.remove();

            if(minNode.next!=null){   // 重点！！！ minNode的下一个节点入堆
                heap.add(minNode.next);
            }

            // minNode加入result链表
            tail.next = minNode;
            tail = minNode;
        }

        return emptyHead.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


















