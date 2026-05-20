import java.util.*;

public class Traversal_144_145_94_102 {

    /* 介绍各种二叉树的各种遍历方法
    1、

     */

    /* 递归遍历要考虑的三个要素
    1、确定递归函数的参数和返回值
    2、确定终止条件
    3、确定单层递归的逻辑
     */



    /*
    前序遍历 leet144
     */
    public List<Integer> preorderTraversal_1(TreeNode root) {

        List<Integer> resultList = new ArrayList<>();
        preOrder_1(root,resultList);

        return resultList;
    }

    void preOrder_1(TreeNode root,List<Integer> resultList){
        if(root==null){
            return;
        }

        // 前序遍历：中左右
        resultList.add(root.val);
        preOrder_1(root.left,resultList);
        preOrder_1(root.right,resultList);
    }


    /*
    中序遍历 leet94
     */
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        inorder_1(root,list);

        return list;
    }


    void inorder_1(TreeNode root,List<Integer> list){
        if(root==null){
            return;
        }

        // 和前序遍历只有顺序的区别
        inorder_1(root.left,list);
        list.add(root.val);
        inorder_1(root.right,list);
    }


    /*
    后序遍历 leet145
     */
    public List<Integer> postorderTraversal_1(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        postorder_1(root,list);

        return list;
    }

    void postorder_1(TreeNode root,List<Integer> list){
        if(root==null){
            return;
        }

        postorder_1(root.left,list);
        postorder_1(root.right,list);
        list.add(root.val);
    }



    /*
    非递归法（迭代法）实现前中后序遍历

    在编程语言中，递归是借助"栈"来实现的，所以我们用迭代法实现递归时也是用栈
     */

    /*
    迭代法实现 前序遍历
    图解见ipad leet144

     */

    public List<Integer> preorderTraversal2(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if(root==null){
            return list;
        }

        stack.push(root);  // 先让根节点入栈，让栈不为空，使得下面的循环条件满足

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();   // 栈顶出栈，并记录，后面要让它的左右子树入栈

            list.add(node.val);  //node节点不可能为null

            if (node.right != null){
                stack.push(node.right);
            }

            if (node.left != null){
                stack.push(node.left);
            }
        }

        return list;
    }


        /*
    迭代法实现 后序遍历  leet145
    这一题想要正面实现很难，见ipad分析

    我们知道这么实现前序遍历(中左右)， 而后序遍历为(左右中)
    我们可以假设遍历顺序为（中右左），然后reverse一下即可     -> 很巧妙

     */
        public List<Integer> postorderTraversal2(TreeNode root) {

            Stack<TreeNode> stack = new Stack<>();
            List<Integer> list = new ArrayList<>();

            if(root==null){
                return list;
            }

            stack.push(root);

            while (!stack.isEmpty()){
                TreeNode node = stack.pop();

                list.add(node.val);

                if (node.left != null){       // 要实现“中右左”再reverse， 所以左子树先入栈、右子树后入栈
                    stack.push(node.left);
                }

                if (node.right != null){
                    stack.push(node.right);
                }

            }

            Collections.reverse(list);

            return list;
        }


    /*
    迭代法实现 中序遍历  leet94

    在前序遍历中，访问节点的顺序 和 处理节点（将节点出栈、加入数组）的顺序是一样的
    但在中序遍历中 不一样，我们先访问的是根节点（必然要先访问根节点，不可能没访问根节点就能访问到左右子树），但先处理的应该是左子树
     */

    public List<Integer> inorderTraversal2_1(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if (root == null){
            return list;
        }

        /* 核心思想：
        （1）当前节点不为空，入栈 + 访问左节点
        （2）当前节点为空， 出栈 + 访问右节点
        （3）直到 当前节点为空 且 无元素可以出栈（栈为空）    --- 如果只是判断 栈为空 会有错误，当将root加入list时，栈也会为空，此时还未遍历root右子树
         */
        TreeNode cur = root;

        while(cur!=null || !stack.isEmpty()){   // 直到 当前节点为空 且 无元素可以出栈（栈为空），退出循环
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                list.add(cur.val);

                cur = cur.right;
            }
        }

        return list;
    }

    /*
    中序遍历
    完全我自己想到的，根据遍历结果反推可能的遍历过程，然后用代码模拟这个过程
    第一次写完代码后，验证是否正确，完善有问题的地方

    思路见ipad
     */
    public List<Integer> inorderTraversal2_2(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if (root == null){
            return list;
        }

        stack.push(root);
        TreeNode cur = root;

        while(!stack.isEmpty()){

            // 有左节点时一直入栈
            while(cur.left != null){
                cur = cur.left;
                stack.push(cur);
            }    // (1)此时[5 4 2 ，cur=2  (3)[6 3  cur=3

            // 已知左节点为空，根据"左中右"的规则，应该访问当前节点
            cur = stack.pop(); // (1)cur=2 [5 4   (2)cur=1  [5  (3)cur=3  [6   (4)cur=7  [
            list.add(cur.val);

            // 找某个节点的右子树，如果没有右子树，一直回退（可能一直回退到根节点）
            while(cur.right==null && !stack.isEmpty()){
                cur = stack.pop();  // (1)cur=4 [5    (2)cur=5  [  (3)[  cur=6
                list.add(cur.val);
            }

            // 有右子树，则入栈
            if(cur.right != null){
                stack.push(cur.right);  // (1)[5 1  (2) [6        (3) [7
                cur = cur.right;        // (1)cur=1  (2)cur=6     (3)7
            }
        }

        return list;
    }










    /*
    二叉树的层序遍历（广度优先搜索） --- 逐层地，从左到右访问所有节点   leet102

    依赖二叉树这种结构本身 无法层序地保存节点，详细分析见ipad
     */

    // 根据思路，我自己写的，跟老师写的基本一样
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if(root==null){
            return result;
        }

        Deque<TreeNode> deque = new LinkedList<>();

        deque.addLast(root);
        int size = 1;

        while(!deque.isEmpty()){

            // 一层
            List<Integer> list = new ArrayList<>();

            for(int i=0; i<size; i++){
                TreeNode cur =  deque.removeFirst();

                list.add(cur.val);

                if(cur.left!=null){
                    deque.addLast(cur.left);
                }

                if(cur.right!=null){
                    deque.addLast(cur.right);
                }
            }

            // 一层结束
            result.add(list);

            size = deque.size();
        }

        return result;
    }






}
