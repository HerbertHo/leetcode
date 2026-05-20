public class invertTree_226 {


    /*
    我自己想出：
     */
    public TreeNode invertTree1(TreeNode root) {

        if(root==null){
            return root;
        }

        TreeNode temp = root.left;   // 树是用链表实现的
        root.left = root.right;
        root.right = temp;

        if(root.left!=null){
            invertTree1(root.left);
        }

        if(root.right!=null){
            invertTree1(root.right);
        }

        return root;
    }


    /*
    我自己想出的另一种写法
     */
    public TreeNode invertTree2(TreeNode root) {

        if(root==null){
            return root;
        }

        invert(root);

        return root;
    }


    void invert(TreeNode cur){
        if(cur==null){
            return;
        }

        TreeNode temp = cur.left;
        cur.left = cur.right;
        cur.right = temp;

        invert(cur.left);
        invert(cur.right);
    }












}
