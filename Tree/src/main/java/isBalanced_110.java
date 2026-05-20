public class isBalanced_110 {
    /*
    判断二叉树是不是平衡二叉树
     */

    /*
    方法一：利用在 leet104中写过的 求树最大高度代码
          慢
     */
    int flag = 1;

    public boolean isBalanced1(TreeNode root) {
        if(root==null){
            return true;
        }

        judge(root);

        return flag==1?true:false;
    }

    void judge(TreeNode cur){
        if(cur==null){
            return;
        }

        int leftHeight = getHeight(cur.left);       // 对cur节点的处理
        int rightHeight = getHeight(cur.right);

        if(Math.abs(leftHeight-rightHeight)>1){
            flag = 0;
        }

        judge(cur.left);  // 对cur.left节点的处理
        judge(cur.right);
    }

    // 求高度
    int getHeight(TreeNode node){
        if(node==null){   // 叶子结点的子节点为null，高度为0
            return 0;
        }

        int leftHeight = getHeight(node.left);     // 左右子树高度
        int rightHeight = getHeight(node.right);
        return leftHeight>rightHeight?(leftHeight+1):(rightHeight+1);  // node节点高度 = max(左右子树高度)+1
    }









    /*
    方法二：from 代码随想录
    更简洁
     */
    public boolean isBalanced2(TreeNode root) {

        return getHeight(root)!=-1;
    }


    int getHeight(TreeNode node){
        if(node==null){
            return 0;
        }

        // 后序遍历求高度 左右中
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if(leftHeight==-1 || rightHeight==-1 || Math.abs(leftHeight-rightHeight)>1){   // 当有一个子树不是平衡二叉树时，整个二叉树必不是平衡二叉树
            return -1;         // 如果有一个子树为-1，会一路返回到根节点
        }else{
            return leftHeight>rightHeight?leftHeight+1:rightHeight+1;   // 正常求node高度 = max(左右子树高度)+1
        }
    }



    /*
    方法三 from 我
    类似 方法二
     */
    int flag = 1;

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }

        getHeight(root);

        return flag==1;
    }


    int getHeight(TreeNode cur){
        if(cur==null){
            return 0;
        }

        if(cur.left==null && cur.right==null){
            return 1;
        }

        int leftHeight = getHeight(cur.left);
        int rightHeight = getHeight(cur.right);

        if(Math.abs(leftHeight-rightHeight) > 1){
            flag=0;
        }

        return Math.max(leftHeight,rightHeight)  + 1;
    }









}
