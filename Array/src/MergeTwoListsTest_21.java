
    //leetcode 21 合并有序列表


public class MergeTwoListsTest_21 {
    // 我的解法：把list2的每个节点依次连接到list1上（不引入新的节点）   -> 但是有错误
//    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
//
//        if(list1==null){
//            return list2;
//        }
//
//        if(list2==null){
//            return list1;
//        }
//
//        ListNode temp1 = list1;
//        ListNode temp2 = list2;
//
//        while(temp1.next != null && temp2.next!=null){
//            if(temp1.val<temp2.val && temp1.next.val >= temp2.val){
//                list2.next = list2.next.next;
//                temp2.next = temp1.next;
//                temp1.next = temp2;
//                temp2 = list2.next;
//            }else {
//                temp1 = temp1.next;
//            }
//        }
//
//        if(temp1.next == null){   // list1完了，直接连接到list2上
//            temp1.next = list2.next;
//        }
//
//        return list1;
//    }





        /*
        方法二

        引入一个新节点head作为头节点（题目给的list1和list2没有 空的头节点）

        注意：题目给的链表是不带空的头节点的，也就是list1和list2都指向实际的第一个元素
         这时list1和list2本身就可以作为遍历两个链表的指针

         构造新链表
         */

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {

        ListNode emptyHead = new ListNode();

        ListNode tail = emptyHead;    // result链表的末尾

        while(list1!=null && list2!=null){

            if(list1.val <= list2.val){   // list1加入result链表

                tail.next = list1;
                tail = list1;

                list1 = list1.next;
                tail.next = null;
            }else{

                tail.next = list2;
                tail = list2;

                list2 = list2.next;
                tail.next = null;
            }
        }

        if(list1==null){
            tail.next = list2;
        }

        if(list2==null){
            tail.next = list1;
        }

        return emptyHead.next;
    }



    /*
    方法三：递归
     */





}
