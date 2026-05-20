public class diameterOfBinaryTree_543 {

    /*
    方法一 from 我， same as GPT
    类比 124最大路径和 的思想，看左右子树对cur的贡献
     */
    int maxLength = 0;

    public int diameterOfBinaryTree1(TreeNode root) {
        func(root);

        return maxLength;
    }

    // 路径长度 = 节点数-1
    // 类比最大路径和
    // （1）以cur为root的最长路径
    // （2）cur作为子树对上一层的贡献
    int func(TreeNode cur){    // 返回cur对上一层的贡献
        if(cur==null){
            return 0;
        }

        if(cur.left==null && cur.right==null){
            return 1;
        }

        int leftValue = func(cur.left);  // cur左子树对cur的贡献
        int rightValue = func(cur.right);

        // （1）以cur为root的最长路径
        int curLength = leftValue + rightValue;  // 比如对于示例中的2，4贡献1，5贡献1，以2为root的最长路径为1+1=2
        maxLength = Math.max(curLength,maxLength);

        // （2）cur作为子树对上一层的贡献
        return Math.max(leftValue+1,rightValue+1); // 为什么要+1 ：cur作为子树时，cur与cur的父节点之间也有一段路径
    }



}
