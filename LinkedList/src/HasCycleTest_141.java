import java.util.HashSet;

public class HasCycleTest_141 {
    // P141 判断链表中是否有环



/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

    /*
    判断有无节点被重复遍历
    方法一：设置一个哈希表
        遍历链表时，将每个节点存入 HashSet。
        如果遍历过程中遇到已存在于 HashSet 的节点，说明链表有环，返回 true。
        如果遍历到 null，说明链表无环，返回 false。

     */
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();

        while (head != null) {
            if (visited.contains(head)) { // 如果节点已存在于哈希表，则有环
                return true;
            }
            visited.add(head); // 记录访问过的节点，注意：存入的是节点，不是值（值可能重复）
            head = head.next;
        }

        return false; // 遍历结束，无环
    }

    /*
    上述方法时间慢，且由于HashSet的存在有O(n)的空间开销
    方法二：使用  快慢指针（Floyd 判圈算法） 来检测链表是否有环。该算法使用两个指针：
        **慢指针（slow）**每次前进 1 步。
        **快指针（fast）**每次前进 2 步。
        如果链表中存在环，快指针最终会追上慢指针，返回 true。
        如果链表无环，快指针会先到 null，返回 false。
     */
    public boolean hasCycle2(ListNode head){
        if(head==null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {  // 为什么是&& 而不是||  因为fast.next != null 确保 fast.next 不是 null，这样 fast.next.next 访问时不会报空指针错(防止出现null.next的情况)
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) { // 快慢指针相遇，说明有环
                return true;
            }
        }

        return false; // 快指针到达 null，说明无环
    }














}





