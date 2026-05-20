public class maxDepth_104 {

    /* 概念辨析，见ipad
    深度：任一节点到root节点的距离+1 （深度从1开始，即root节点深度为1）
    高度：任一节点到叶子节点的距离+1  （高度也从1开始，即叶子节点高度为1）

    求深度：从上往下计数，d(根节点)最小，应该使用前序遍历            d(子节点) = d(父节点)+1
    求高度，从下往上计数，h(根节点)最大，应该使用 后序遍历"左右中"    h(父节点) = h(子节点)+1
     */

    /*
    方法一：层序遍历，很容易知道最大深度 from 我的思想
     */


    /*
    方法二 from 代码随想录
     */
    public int maxDepth1(TreeNode root) {
        return getHeight(root);
    }


    int getHeight(TreeNode node){
        if(node==null){   // 叶子结点的子节点为null，高度为0
            return 0;
        }

        int leftHeight = getHeight(node.left);     // 左右子树高度
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight,rightHeight) + 1;   // node节点高度 = max(左右子树高度)+1
    }





}
