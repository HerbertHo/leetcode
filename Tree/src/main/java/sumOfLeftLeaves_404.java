public class sumOfLeftLeaves_404 {

    /*
    左叶子 ： 叶子节点 + 是它父节点的左孩子
     */
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        findLeftLeaves(root);
        return sum;
    }


    void findLeftLeaves(TreeNode node) {
        if (node == null) return;

        findLeftLeaves(node.left);
        findLeftLeaves(node.right);

        if (node.left != null && node.left.left == null && node.left.right == null) {  // 如果node的左孩子为左叶子
            sum = sum + node.left.val;
        }
    }







}
