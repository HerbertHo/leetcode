import java.util.HashSet;
import java.util.Set;

public class DetectTwoLinkedlistWhetherCross_160 {
    // Leetcode160 判断两个链表是否相交  若相交，返回相交的第一个节点  若不相交返回mull

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

    /* 方法一：
    我的想法：利用Set，先将链表A中的节点全部放入Set中，再遍历B中的节点，看是否与Set中A的节点一样
            想法对，但是很慢   时间复杂度O(m+n)  空间复杂度O(m)
     */

        Set<ListNode> set = new HashSet<>();

        ListNode temp = headA;

        while(temp != null){
            set.add(temp);
            temp = temp.next;
        }

        temp = headB;

        while(temp != null){
            if(set.contains(temp)){
                return temp;
            }else {
                temp = temp.next;
            }
        }

        return null;

    }


    /*
    方法二
     */
    // 当然还有一种更暴力的方法，将A中每个节点一一与B中的节点相比较

    /*
    优化解法：用两个指针tempA、tempB分别遍历A、B
            若tempA先到null，则让它重新指向B   之后当tempB到达null时让其指向A
            这样，若有相交，tempAB必在相交处汇合
            若无相交，tempAB必指向null
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){

        if(headA==null || headB==null){
            return null;
        }

        ListNode tempA = headA;
        ListNode tempB = headB;

        while(tempA!=null && tempB!=null){
            tempA = tempA.next;
            tempB = tempB.next;
        }

        if(tempA == null){
            tempA = headB;
            while(tempB!=null){
                tempA = tempA.next;
                tempB = tempB.next;
            }
            tempB = headA;
        }else {
            tempB = headA;
            while (tempA!=null){
                tempA = tempA.next;
                tempB = tempB.next;
            }
            tempA = headB;
        }

        while(tempA != tempB){
            tempA = tempA.next;
            tempB = tempB.next;
        }

        if(tempA != null){
            return tempA;
        }else {
            return null;
        }
    }


    /*
    方法二优化
     */
    // 上述写法对，但是可以简化
    public ListNode getIntersectionNode2_2(ListNode headA, ListNode headB){

        if(headA==null || headB==null){
            return null;
        }

        ListNode tempA = headA;
        ListNode tempB = headB;


        while(tempA != tempB){       // 简化

            if(tempA == null){       // 只有tempA为null时，即走到末尾时才重置tempA的值，否则向后移动即可
                tempA = headB;
            }else {
                tempA = tempA.next;
            }

            if(tempB == null){
                tempB = headA;
            }else {
                tempB = tempB.next;
            }
        }

        return tempA;     // 无论tempA是否为null，即无论是否相交，都返回tempA即可
    }



    /* 方法三：长度差
    另一个方法：(效率上没有优化，只是提供一个思路)
    先遍历两个链表，分别获得长度 m 和 n， 计算长度差 d = m - n
    然后从较长链表的第 d+1 个节点、较短链表的第1个节点开始 分别遍历两个链表，
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB){

        if(headA==null || headB==null){
            return null;
        }

        ListNode tempA = headA;
        ListNode tempB = headB;
        int m = 1;
        int n = 1;
        int d = 0;

        while(tempA != null){
            tempA = tempA.next;
            m++;
        }

        while(tempB != null){
            tempB = tempB.next;
            n++;
        }


        if(m>=n){
            d = m - n;

            // tempB从第1个开始遍历，tempA从第 d+1 个开始遍历
            tempA = headA;
            tempB = headB;

            for(int i=0; i<d; i++){   // 注意从 1 移动到 d+1 只需要移动 d 次
                tempA = tempA.next;
            }
        }else {
            d = n - m;

            tempA = headA;
            tempB = headB;

            for(int i=0; i<d; i++){
                tempB = tempB.next;
            }
        }

        while (tempA != null && tempB != null){
            if(tempA == tempB){
                return tempA;
            }

            tempA = tempA.next;
            tempB = tempB.next;
        }

        return tempA;
    }


    /*
    方法四：双指针  best
     */
    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {

        // 双指针，一个先遍历A，遍历完后遍历B； 另一个相反    判断两者相交的地方是不是null
        ListNode temp1 = headA;
        ListNode temp2 = headB;

        while(temp1!=null && temp2!=null){
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        if(temp1==null){  // temp1先遍历完A

            temp1 = headB;   // temp1开始遍历B

            while(temp2!=null){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }

            temp2 = headA;      // temp2开始遍历A
        }else{
            temp2 = headA;

            while(temp1!=null){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }

            temp1 = headB;
        }

        while(temp1!=temp2){  // 再次相遇即为交点，也可能为null
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return temp1;
    }






}





