import java.util.*;

public class countNodes_222 {

    // 方法一：遍历每个元素，遇到一个元素count++ （这种方法易错）
    public int countNodes1(TreeNode root) {
        return getSize(root);
    }

    int getSize(TreeNode node){
        if(node==null){
            return 0;
        }

        return getSize(node.left) + getSize(node.right) + 1;   // 别忘了+1（没+1就是忘了自己）
    }


    // 方法二：完全按照遍历的写法，只是最后返回的不是list   而是list.size()
    public int countNodes2(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        preOrder_1(root,list);

        return list.size();
    }

    void preOrder_1(TreeNode root,List<Integer> resultList){
        if(root==null){
            return;
        }

        resultList.add(root.val);
        preOrder_1(root.left,resultList);
        preOrder_1(root.right,resultList);
    }


    // 方法三：利用满二叉树的节点个数特性
    public int countNodes3(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0;

        while (left != null) {  // 求左子树深度
            left = left.left;
            leftDepth++;
        }

        while (right != null) { // 求右子树深度
            right = right.right;
            rightDepth++;
        }

        if (leftDepth == rightDepth) {   // 完全二叉树
            return (int)Math.pow(2, leftDepth+1) -1;   // 注意Math.pow()返回的是double，要强转
        }

        return countNodes(root.left) + countNodes(root.right) + 1;  // 递归去向下找满二叉树
    }










}
