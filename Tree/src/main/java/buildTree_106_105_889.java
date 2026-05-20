public class buildTree_106_105_889 {

    /* 106 题目：
    根据中序遍历、后序遍历的数组，构造并返回这颗二叉树
     */

    /* 思路 from 随想录
     中序：左中右    后序：左右中
     后序的最后一个节点是根节点root， 在中序数组中找到root的位置，根据root的位置"切割"中序数组，左边为左子树，右边为右子树
      分析见ipad
     */


    /*
    错误写法：错误分析见ipad
     */
    public TreeNode buildTree1_1(int[] inorder, int[] postorder) {
        return build1_1(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }


    TreeNode build1_1(int[] inorder,int inBegin,int inEnd,int[] postorder,int postBegin,int postEnd){
        if(inBegin>inEnd || postBegin>postEnd){
            return null;
        }

        int rootVal = postorder[postEnd];
        int rootIndexOfInorder = inBegin;

        // 在inorder中找root
        for(int i=inBegin;i<=inEnd;i++){
            if(inorder[i] == rootVal){
                rootIndexOfInorder = i;
                break;
            }
        }

        TreeNode root = new TreeNode();
        root.val = rootVal;

        if(rootIndexOfInorder==inBegin){   // 左子树没有了
            root.left = null;
        }else{
            // 错误点：rootIndexOfInorder用来切inorder，不能用来切 postorder
            root.left = build1_1(inorder,inBegin,rootIndexOfInorder-1,postorder,postBegin,rootIndexOfInorder-1);
        }

        if(rootIndexOfInorder == inEnd){
            root.right = null;
        }else{
            root.right = build1_1(inorder,rootIndexOfInorder+1,inEnd,postorder,rootIndexOfInorder,postEnd-1);
        }

        return root;
    }



    /*
    正确写法
     */

    public TreeNode buildTree1_2(int[] inorder, int[] postorder) {
        return build1_2(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    private TreeNode build1_2(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd){

        int rootVal = postorder[postEnd];
        int rootIndexOfInorder = inBegin;

        for(int i=inBegin;i<=inEnd;i++){
            if(inorder[i] == rootVal){
                rootIndexOfInorder = i;
                break;
            }
        }

        // 构造root节点
        TreeNode root = new TreeNode(rootVal);

        // .........
        int leftLength = rootIndexOfInorder -1 -inBegin;

        if(rootIndexOfInorder==inBegin){  // 没有左子树
            root.left = null;
        }else{
            root.left = build1_2(inorder,inBegin,rootIndexOfInorder-1,postorder,postBegin,postBegin+leftLength);
        }

        int rightLength = inEnd - rootIndexOfInorder - 1;

        if(rootIndexOfInorder==inEnd){   // 没有右子树
            root.right = null;
        }else{              // 易错，postEnd的右子树 结尾是 postEnd-1 而不是postEnd!!! (postEnd是root)
            root.right = build1_2(inorder,rootIndexOfInorder+1,inEnd,postorder,postEnd-1-rightLength,postEnd-1);
        }

        return root;
    }





    /*  105 根据前序、中序构建二叉树
        跟上一题基本一样
     */
    public TreeNode buildTree2_1(int[] preorder, int[] inorder) {
        return func2_1(preorder,0,preorder.length-1, inorder,0,inorder.length-1);
    }

    TreeNode func2_1(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd){

        int rootVal = preorder[preBegin];
        int rootIndexOfInorder = inBegin;

        for(int i=inBegin; i<=inEnd; i++){
            if(inorder[i] == rootVal){
                rootIndexOfInorder = i;
                break;
            }
        }

        // 构建root
        TreeNode root = new TreeNode(rootVal);

        // 切割inorder为(inBegin,rootIndexOfInorder-1)
        int leftSize = rootIndexOfInorder -1 -inBegin;

        int rightSize = inEnd - rootIndexOfInorder -1;

        if(rootIndexOfInorder==inBegin){
            root.left=null;
        }else{
            root.left = func2_1(preorder,preBegin+1,preBegin+1+leftSize,inorder,inBegin,rootIndexOfInorder-1);
        }

        if(rootIndexOfInorder==inEnd){
            root.right=null;
        }else{
            root.right = func2_1(preorder,preEnd-rightSize,preEnd,inorder,rootIndexOfInorder+1,inEnd);
        }

        return root;
    }







}
