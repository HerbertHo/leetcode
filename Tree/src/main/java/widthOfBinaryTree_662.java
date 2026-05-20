import java.util.*;

public class widthOfBinaryTree_662 {

    /*
    核心思想：层序遍历，只是给每个节点加一个索引，根据同一层最左、最右节点索引的差值 作为该层的直径
     */


    /*
    目前看到的最好的方法
    层序遍历
     */
    class MyPair{
        TreeNode node;
        int index;

        MyPair(){

        }

        MyPair(TreeNode node, int index){
            this.node = node;
            this.index = index;
        }
    }

    int maxLen = 0;  // 初始化为0，处理空树的情况

    public int widthOfBinaryTree0(TreeNode root) {
        if (root == null) return 0; // 处理空树

        Deque<MyPair> deque = new LinkedList<>();

        deque.addLast(new MyPair(root, 1));   // root入栈

        while (!deque.isEmpty()) {

            maxLen = Math.max(maxLen, deque.getLast().index - deque.getFirst().index + 1);  // 在处理当前层之前计算层宽

            int size = deque.size();

            while (size != 0) {
                MyPair pair = deque.removeFirst();   // 出队，左右子树入队

                if (pair.node.left != null) {
                    deque.addLast(new MyPair(pair.node.left, pair.index * 2));
                }

                if (pair.node.right != null) {
                    deque.addLast(new MyPair(pair.node.right, pair.index * 2 + 1));
                }

                size--;
            }
        }

        return maxLen;
    }






    /*
    方法一 from GPT
    使用 Java的标准库java.util.* 中的 AbstractMap.SimpleEntry
     */

    import java.util.*;

    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;

            Deque<AbstractMap.SimpleEntry<TreeNode, Integer>> deque = new LinkedList<>();
            deque.offerLast(new AbstractMap.SimpleEntry<>(root, 1));
            int maxWidth = 0;

            while (!deque.isEmpty()) {
                int size = deque.size();
                int start = deque.peekFirst().getValue(); // 当前层最左编号
                int end = start; // 初始化最右编号

                for (int i = 0; i < size; i++) {
                    AbstractMap.SimpleEntry<TreeNode, Integer> current = deque.pollFirst();
                    TreeNode node = current.getKey();
                    int index = current.getValue();

                    end = index;

                    if (node.left != null) {
                        deque.offerLast(new AbstractMap.SimpleEntry<>(node.left, 2 * index));
                    }
                    if (node.right != null) {
                        deque.offerLast(new AbstractMap.SimpleEntry<>(node.right, 2 * index + 1));
                    }
                }

                maxWidth = Math.max(maxWidth, end - start + 1);
            }

            return maxWidth;
        }
    }


    /*
    方法二：自定义Pair类
     */
    class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }


    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        // 用Deque代替Queue
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<>();
        deque.offerLast(new Pair<>(root, 1));
        int maxWidth = 0;

        while (!deque.isEmpty()) {
            int size = deque.size();
            int start = deque.peekFirst().getValue(); // 当前层最左编号
            int end = start; // 初始化最右编号

            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> current = deque.pollFirst();
                TreeNode node = current.getKey();
                int index = current.getValue();

                end = index; // 记录最右编号

                if (node.left != null) {
                    deque.offerLast(new Pair<>(node.left, 2 * index));
                }
                if (node.right != null) {
                    deque.offerLast(new Pair<>(node.right, 2 * index + 1));
                }
            }

            maxWidth = Math.max(maxWidth, end - start + 1);
        }

        return maxWidth;
    }


    /*
    方法三： 实现from我，思想跟上面一样 from GPT
     */
    class Pair<K,V>{   // Pair<K,V> 类比 Integer
        K index;
        V node;

        Pair(K index, V node){
            this.index = index;
            this.node = node;
        }
    }


    public int widthOfBinaryTree3(TreeNode root) {

        int maxWidth = 0;
        // 为什么要存入node：因为要通过node找它的左右孩子
        Deque<Pair<Integer,TreeNode>> deque = new LinkedList<>();  // 以前是Deque<Integer> deque

        deque.addLast(new Pair<Integer,TreeNode>(1,root));   // root索引为任意，计算宽度right-left+1，只有一个节点时left==right
        int size = 1;
        int left = 0;
        int right = 0;


        // 对于满二叉树/完全二叉树：节点cur的索引为i，它的左孩子索引为2i，右孩子索引为2i+1
        while(!deque.isEmpty()){

            // 每一层开始
            left = deque.getFirst().index;

            // 每一层处理逻辑
            while(size!=0){
                // cur出栈，左右孩子入栈
                Pair<Integer,TreeNode> curPair = deque.removeFirst();
                size--;
                int curIndex = curPair.index;
                right = curIndex;                   // 每当该层有元素出队时，更新right值
                TreeNode curNode = curPair.node;

                if(curNode.left!=null){
                    Pair<Integer,TreeNode> newPair1 = new Pair<Integer,TreeNode>(2*curIndex, curNode.left);
                    deque.addLast(newPair1);
                }

                if(curNode.right!=null){
                    Pair<Integer,TreeNode> newPair2 = new Pair<Integer,TreeNode>(2*curIndex+1, curNode.right);
                    deque.addLast(newPair2);
                }


            }

            // 每一层处理结束后
            int curWidth = right-left+1;
            maxWidth = Math.max(maxWidth,curWidth);

            size = deque.size();
        }

        return maxWidth;
    }





















}
