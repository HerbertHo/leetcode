import java.util.*;

public class isSymmetric_101 {

    /*
    判断二叉树是否对称
     */


    /* 方法一
    我的想法：根据不同遍历方法的结果判断
    见ipad
     */
    static final int nullValue = 200;  // 题目中说节点的值在 -100和100之间，所以200会和节点值冲突

    public boolean isSymmetric1(TreeNode root) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        order1(root,list1);
        order2(root,list2);

        return list1.equals(list2);

    }

    void order1(TreeNode root,List<Integer> list1){  // 中左右 - 前序遍历
        if(root==null){
            list1.add(nullValue);
            return;
        }

        list1.add(root.val);
        order1(root.left,list1);
        order1(root.right,list1);
    }

    void order2(TreeNode root,List<Integer> list2){  // 中右左
        if(root==null){
            list2.add(nullValue);
            return;
        }

        list2.add(root.val);
        order2(root.right,list2);
        order2(root.left,list2);
    }


    /*
    方法二：核心是判断 左子树的右子树 和 右子树的左子树是否相同
     */
    public boolean isSymmetric2(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;         // 两个都为空，对称
        if (t1 == null || t2 == null) return false;        // 只有一个为空，不对称
        if (t1.val != t2.val) return false;                // 值不相等，不对称

        // 递归判断：t1的左和t2的右、t1的右和t2的左
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }







}
