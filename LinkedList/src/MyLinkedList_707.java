public class MyLinkedList_707 {

    /*
    本题统一使用虚拟头节点的方式（空的头节点），以便增删改查
    emptyHead -> 第0个节点 -> ...
     */


    class MyLinkedList {

        class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val=val;
            }
        }

        private int size;      //size存储链表元素的个数, 节点下标为 0 ~ size-1
        private ListNode emptyHead;    //虚拟头结点，index = -1

        public MyLinkedList() {
            this.emptyHead = new ListNode();
            this.size = 0;
        }


        /*
        获取链表中下标为index的节点的值(下标从0开始)
        如果下标无效，则返回-1
        */
        public int get(int index) {
            if(index >= size || index<0){
                return -1;
            }

            ListNode tempNode = emptyHead;

            for(int i=0; i<=index;i++){   // 若index=0（查第0个节点），for中语句运行一次，tempNode = emptyNode.next（第0个节点），正确
                tempNode = tempNode.next;
            }

            return tempNode.val;
        }




        public void addAtHead(int val) {
            ListNode newNode = new ListNode(val);

            if(emptyHead.next == null){
                emptyHead.next = newNode;
            }else{
                newNode.next = emptyHead.next;
                emptyHead.next = newNode;
            }

            size++;
        }



        public void addAtTail(int val) {
            ListNode newNode = new ListNode(val);

            ListNode tempNode = emptyHead;
            while(tempNode.next != null){
                tempNode = tempNode.next;
            }

            tempNode.next = newNode;

            size++;
        }




        public void addAtIndex(int index, int val) {
            if(index == size){
                addAtTail(val);
                return;     // 记得要return！！！ 否则会重复添加
            }

            if(index > size){
                return;
            }

            // 找到第index-1个节点
            ListNode newNode = new ListNode(val);
            ListNode tempNode = emptyHead;

            for(int i=0; i<=index-1;i++){
                tempNode = tempNode.next;
            }

            newNode.next = tempNode.next;
            tempNode.next = newNode;

            size++;
        }




        public void deleteAtIndex(int index) {
            if(index >= size || index<0){     // 和get方法判断异常一样
                return;
            }

            ListNode tempNode = emptyHead;

            // 最后一个节点特殊处理： index = size-1 ，就要找index-1 = size-2    --> chatGPT没特殊处理也行
            if(index == size-1){
                for(int i=0; i<=index-1;i++){
                    tempNode = tempNode.next;
                }
                tempNode.next = null;
            }else{
                for(int i=0; i<=index-1;i++){
                    tempNode = tempNode.next;
                }
                tempNode.next = tempNode.next.next;
            }

            size--;
        }
    }







}
