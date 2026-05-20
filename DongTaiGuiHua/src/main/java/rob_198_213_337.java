public class rob_198_213_337 {

    /* leet 198  题目
    nums元素 >= 0
     */


    /*
    方法一：实现 from 我 , 思路 from 随想录，见下面
     */

    /* 思路 from 随想录， 跟我的思路差不多
    1、确定 i 和 dp[i] 的含义
       i：第i个房间（nums的下标）
       dp[i]： 到第i个房间(包括第i个房间) 时能偷到的最大金额， 最终的结果是dp[length-1] （不一定偷第i个房间）

    2、递推公式
       到第i个房间时，考虑 偷i 或 不偷i
       （1）偷i   ： dp[i-2] + nums[i]
       （2）不偷i ： dp[i-1]
       所以 dp[i] = max (dp[i-2]+nums[i], dp[i-1] )

    3、初始化dp
      dp[0]=nums[0]  dp[1]=max(nums[0],nums[1])

   4、确定遍历顺序
      有递推公式知， dp[i] 由 i-1 和 i-2 推导得出，所以从前往后

   5、举例推导dp数组
     */

    public int rob1_1(int[] nums) {

        if(nums.length==1){
            return nums[0];
        }

        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }

        int length = nums.length;
        int[] dp = new int[length];  // 最终要求的是 dp[length-1]

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for(int i=2; i<=length-1; i++){
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }

        return dp[length-1];
    }



    /*
    方法二：from 我
    对于每个房间i，有偷和不偷两种状态，  偷为[i]][1]   不偷为[i][0]
    dp[i][1]为 偷第i个房间时的最大金额     dp[i][0]为 不偷这个房间的最大金额
     */
    public int rob1_2(int[] nums){

        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        int[][] dp = new int[length][2];

        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for(int i=1; i<=length-1; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);  // 第i个不偷，第i-1个可偷可不偷
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        return Math.max(dp[length-1][0], dp[length-1][1]);
    }






    /*
    leet 213  房间成环了，其他的不变
     */

    /* 带来的区别
       选了尾，就不能选头了， eg[2,7,9,3,1]，不带环可以选[2,9,1]，带环了选1就不能选2了（2是1的邻居）
     */

    /* 思路 from 随想录
    环形问题不利于思考，考虑变为线性问题
    由于首、尾元素最多只能取一个，所以分三种情况：
    （1）不考虑首尾，只考虑中间（仍是线性数组）  比如 1，2，3，4，5 只考虑 2，3，4
    （2）考虑首元素，那么就不考虑尾元素        比如 1，2，3，4，5 只考虑 1，2，3，4
    （3）考虑尾元素，那么就不考虑首元素        比如 1，2，3，4，5 只考虑 2，3，4，5

    分析可知，情况(2)(3)包含了情况（1）
    情况(2)只是考虑首元素，不一定要选，具体选不选由递推公式算得较大值决定，情况(3)同理

    所以分别求出情况(2)(3)的最终值，选出较大值为结果
     */

    /*
    方法一：实现from我，遵循上述思路
     */
    public int rob2_1(int[] nums) {

        if(nums.length==1){
            return nums[0];
        }

        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }

        int length = nums.length;
        int[] dp = new int[length];  // 最终要求的是 dp[length-1]


        //  情况(2): 只考虑首元素， 即考虑下标 0到length-2的元素
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for(int i=2; i<=length-2; i++){
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }

        int result1 = dp[length-2];

        // 情况(3): 只考虑尾元素， 即考虑下标1到length-1的元素
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1],nums[2]);
        int result2 = 0;

        if(length==3){  // 此时没有dp[3]
            result2 = Math.max(nums[1],nums[2]);
        }else{
            for(int i=3; i<=length-1; i++){
                dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
            }

            result2 = dp[length-1];
        }


        return Math.max(result1,result2);
    }


    /*
    方法二 from 我
    遵循198的方法二思路
     */
    public int rob2_2(int[] nums) {

        // 两种情况：偷头不偷尾    偷尾不偷头
        int length = nums.length;

        if(length==1){
            return nums[0];
        }

        if(length==2){
            return Math.max(nums[0],nums[1]);
        }

        int[][] dp = new int[length][2];


        // 不偷尾： 只考虑0到length-2
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for(int i=1; i<=length-2; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        int result1 = Math.max(dp[length-2][0], dp[length-2][1]);

        // 不偷头，只考虑1到length-1
        dp[1][0] = 0;
        dp[1][1] = nums[1];

        for(int i=2; i<=length-1; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        int result2 = Math.max(dp[length-1][0], dp[length-1][1]);

        return Math.max(result1,result2);
    }








    /*
    leet337  房子成二叉树，树形dp
     */

    /*
    每个节点只能由两个状态：偷or不偷
    长度为2的dp数组： dp[0]不偷当前节点 获得最大金钱
                   dp[1]偷当前节点  获得最大金钱

    后序遍历 由下往上推， 最终在root节点的dp[0]和dp[1]中取较大值
     */
    public int rob3_1(TreeNode root) {
        int[] res = robAction1(root);

        return Math.max(res[0], res[1]);
    }

    int[] robAction1(TreeNode cur) {    //
        int dp[] = new int[2];

        if (cur == null)
            return dp;

        int[] left = robAction1(cur.left);
        int[] right = robAction1(cur.right);

        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);  // 不偷cur，那么可以偷cur.left和cur.right(也可以不偷)
        dp[1] = cur.val + left[0] + right[0];   // 偷cur，那么cur.left和cur.right都不能偷
        return dp;   // 不是返回dp[0]和dp[1]中的较大值，而是返回dp数组，供上一层选择
    }











}
