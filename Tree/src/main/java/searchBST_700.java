public class searchBST_700 {

    /*
    方法一： from 我, same as 随想录
     */
    public TreeNode searchBST1(TreeNode root, int val) {

        if(root==null || val == root.val){
            return root;
        }else if(val > root.val){  // 去右子树找
            if(root.right==null){
                return null;
            }else{
                return searchBST1(root.right,val);
            }
        }else{
            if(root.left == null){
                return null;
            }else{
                return searchBST1(root.left,val);
            }
        }
    }


    /*
    方法二：迭代法 from 随想录
     */
    // 迭代，利用二叉搜索树特点，优化，可以不需要栈
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null)
            if (val < root.val) root = root.left;
            else if (val > root.val) root = root.right;
            else return root;
        return null;
    }






}
