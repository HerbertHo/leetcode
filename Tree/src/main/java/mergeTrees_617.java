public class mergeTrees_617 {


    /*
    方法一 from 代码随想录
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        // 前序遍历，先处理中间，再处理左右子树
        // 将tree2加入到tree1

        // 处理中间节点
        if(root1==null){
            return root2;
        }

        if(root2==null){
            return root1;
        }

        // root1和root2均不为空
        root1.val = root1.val + root2.val;

        // 左子树
        if(root1.left!=null || root2.left!=null){
            root1.left = mergeTrees(root1.left,root2.left);
        }

        // 右子树
        if(root1.right!=null || root2.right!=null){
            root1.right = mergeTrees(root1.right,root2.right);
        }

        return root1;
    }



}
