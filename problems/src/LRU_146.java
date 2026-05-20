import java.util.*;

public class LRU_146 {

    /*
    核心：
    两个数据结构： 双向链表 + map        ！！！算法题中：head节点 代表链表！！！

    双向链表作用：模拟缓存，维护节点顺序
    map的作用： 可以快速根据key判断对应的Node在不在 双向链表（缓存）中

    易错点：
    添加、删除 节点的时候 别忘了操作map！！！


     */


    /*
    方法一
     */
    class LRUCache {

        class Node{       // 内部类，用双向链表实现，这是节点
            int key;      // 缓存的元素是key-value结构，所以节点的值也是key-value
            int value;
            Node pre;     // 双向链表
            Node next;

            public Node(){

            }

            public Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }


        // 链表的属性
        int capacity;

        Node emptyHead = new Node();    // 定义空的头节点、尾节点
        Node emptyTail = new Node();

        Map<Integer,Node> map = new HashMap<>();  // Interger就是Node中的key，作为索引找到Node


        public LRUCache(int capacity) {   // 构造器，相当于初始化
            this.capacity = capacity;

            emptyHead.next = emptyTail;   // head.pre 和 tail.next不重要，就让它指向null
            emptyTail.pre = emptyHead;
        }


        public int get(int key) {    // 相当于访问了这个元素
            if(!map.containsKey(key)){
                return -1;
            }

            // map中有这个node,那么就通过key拿到这个node，并放到最前面(emptyHead之后)
            Node cur = map.get(key);

            moveToHead(cur);

            return cur.value;
        }


        public void put(int key, int value) {    // put也算一次访问！！！
            // 如果map中存在，则修改，并移到最前面
            if(map.containsKey(key)){
                Node cur = map.get(key);
                cur.value = value;  // 取出node，并将它的value值修改

                moveToHead(cur);
            }else{   // 不存在，则创建新节点加入链表和map， 判断是否超过容量
                Node newNode = new Node(key,value);

                addToHead(newNode);
                map.put(key,newNode);

                if(map.size() > capacity){   // map的容量就是链表中元素的数量
                    Node deleteNode = removeLast();

                    map.remove(deleteNode.key);  // 容易忘记！！！map中也要删除，因为map的容量就是链表中元素的数量
                }
            }
        }


        void moveToHead(Node cur){
            // 从原链表中断开cur
            cur.pre.next = cur.next;   // (1)
            cur.next.pre = cur.pre;    // (2)

            // 把cur插入到emptyHead的后面
            cur.next = emptyHead.next;  // (3)
            cur.pre = emptyHead;        // (4)
            emptyHead.next.pre = cur;  // (5) 易漏
            emptyHead.next = cur;  //  (6) emptyHead.pre不重要
        }


        void addToHead(Node cur){
            cur.next = emptyHead.next;  // 同(3)-(6)
            cur.pre = emptyHead;
            emptyHead.next.pre = cur;  // 易漏
            emptyHead.next = cur;
        }


        Node removeLast(){
            Node deleteNode = emptyTail.pre;

            deleteNode.pre.next = emptyTail;
            emptyTail.pre = deleteNode.pre;

            return deleteNode;
        }
    }
















}
