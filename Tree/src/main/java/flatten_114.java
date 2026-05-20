public class flatten_114 {

    /*
    方法一：

    思路见： https://www.bilibili.com/video/BV1334y1y7qk/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82

            图解见ipad

    核心思想：如果cur有左子树，就将cur的左子树移到cur右子树之前
     */
    public void flatten(TreeNode root) {

        func(root);
    }


    private void func(TreeNode cur){

        // 如果cur有左子树，就将cur的左子树移到cur右子树之前

        if(cur==null){
            return;
        }

        if(cur.left!=null){

            TreeNode left = cur.left;
            TreeNode right = cur.right;

            // 找到左子树右下角的节点
            TreeNode temp = left;

            while(temp.right!=null){
                temp = temp.right;
            }

            //
            cur.left = null;
            cur.right = left;
            temp.right = right;
        }

        func(cur.right);
    }










}
