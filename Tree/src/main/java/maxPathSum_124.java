public class maxPathSum_124 {

    /*
    视频理解：https://www.bilibili.com/video/BV16a4y1T7Wv/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=0701e0840c36fbcd4ceefffeef800a82

    核心思路：
    1、对于每一个节点cur，有两个作用：(1)cur作为root，计算路径和，判断是否大于maxPath（此时cur左右子树贡献值都可以取）
                              (2)cur作为上一层的子树，return自己对上一层的贡献（此时左右子树贡献值只可以取一个，也可以都不取(0)）  可以看视频理解
    2、也是根据子树信息，所以后序遍历

     */

    /*
    方法一 实现from我，思路fromB站
     */
    int maxPath = Integer.MIN_VALUE;

    public int maxPathSum1(TreeNode root) {
        func(root);

        return maxPath;

    }


    private int func(TreeNode cur) {
        // cur有两个作用：(1)cur作为root时，计算路径和，是否大于maxPath
        // (2)cur作为上一层的子树，return自己对上一层的贡献

        if (cur == null) {
            return 0;
        }

        int leftValue = func(cur.left);  // cur.left作为cur的子树，return自己对cur的贡献 (func返回的一定是>=0的，可以见下面cur的处理逻辑)
        int rightValue = func(cur.right);

        // (1)cur作为root时，计算路径和，是否大于maxPath （此时左右子树贡献值都可以取）
        int curPath = cur.val + leftValue + rightValue;

        if (curPath >= maxPath) {
            maxPath = curPath;
        }

        // (2)cur作为上一层的子树，return自己对上一层的贡献（此时左右子树贡献值只可以取一个，也可以都不取(0)）
        int curValue = leftValue > rightValue ? cur.val + leftValue : cur.val + rightValue;

        if (curValue >= 0) {    // (2)cur作为上一层的子树，return自己对上一层的贡献(若贡献为负数，就不贡献(返回0))
            return curValue;
        } else {
            return 0;
        }
    }



    /*
    实现二：我在7.9的写法
     */
    int max = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {

        // 以cur为根时，左右子树都能取；   以cur为子树时，左右子树最多取一个

        gainToRoot(root);

        return max;
    }


    int gainToRoot(TreeNode cur){   // 返回cur对父节点的贡献值

        if(cur==null){
            return 0;
        }

        int leftGain = gainToRoot(cur.left);   // cur.left对cur的贡献值（ 已保证leftGain>=0 ）
        int rightGain = gainToRoot(cur.right);

        // 1、以cur为根的路径，尝试更新max
        max = Math.max(max, cur.val + leftGain + rightGain);

        // 2、cur向父节点返回自己的贡献值
        int curGain = cur.val + Math.max(leftGain,rightGain);   // 只能算一个子树

        return Math.max(curGain, 0);
    }









}
