import java.util.Arrays;

public class lengthOfLIS_300 {

    /*
    子序列：可以不连续，但要保证元素的相对顺序，所以不能先sort
     */


    /*
    方法一：思想from随想录，实现from我

     dp[i] ： 以nums[i]为结尾的、最长递增子序列的长度(必须包括nums[i])

     不要求连续，i应该与i前面每个元素都尝试组成 新的递增子序列
     */
    public int lengthOfLIS1_1(int[] nums) {

        // dp[i] : 以nums[i]结尾的子序列的长度（必须包括nums[i]）
        int length = nums.length;

        if(length==1){
            return 1;
        }


        int[] dp = new int[length];  // dp[i] : 以nums[i]结尾的子序列的长度（必须包括nums[i]）
        Arrays.fill(dp,1);      // 以nums[i]结尾的子序列的长度、最少为1（它自己）

        for(int i=1; i<=length-1; i++){
            int maxLen = 1;

            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){  // nums[i]可以加入以nums[j]结尾的子序列
                    int curLen = dp[j]+1;  // nums[i]加入后、新子序列的长度

                    if(curLen > maxLen){   // 看看要不要更新
                        maxLen = curLen;
                    }
                }
            }

            dp[i] = maxLen;
        }


        // 在dp数组中找出最大值dp[index]，表明以nums[index]结尾的子序列 是满足题意的最长递增子序列
        int max = dp[0];

        for(int value: dp){
            if(value > max){
                max = value;
            }
        }

        return max;
    }



    /*
    对方法一的一点优化： 不用在最后特地从for循环中找最大值
     */
    public int lengthOfLIS1_2(int[] nums) {
        // 递增子序列， 不连续
        // dp[i] : 以nums[i]结尾的、递增子序列的长度(必须包括nums[i])
        int length = nums.length;

        if(length==1){
            return 1;
        }

        int[] dp = new int[length];
        int max = 1;

        dp[0] = 1;

        for(int i=1; i<=length-1; i++){
            int curMax = 1;

            // 不要求连续，i应该与i前面每个元素都尝试组成 递增子序列
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    curMax = Math.max(curMax, dp[j]+1);
                }
            }

            dp[i] = curMax;

            max = Math.max(max, curMax);
        }


        // 在dp中找最大值（已在for循环中顺便完成）
        return max;
    }


    /*
    对上述写法的再次优化，不需要curMax，直接用dp[i]
     */
    public int lengthOfLIS1_3(int[] nums) {

        // 子序列：不连续
        // dp[i] ：以第i个元素结尾的、找到的最长递增子序列的长度

        int length = nums.length;

        if(length==1){
            return 1;
        }

        int[] dp = new int[length];

        Arrays.fill(dp,1);

        int max = 1;

        for(int i=1; i<length; i++){

            for(int j=0; j<i; j++){   // i尝试与之前的每一个组陈递增子序列

                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);   // 尝试更新dp[i]
                    max = Math.max(max, dp[i]);         // 尝试更新max
                }
            }
        }

        return max;
    }






    /*
    方法二：贪心 + 二分查找

    视频讲解，见：
    https://www.bilibili.com/video/BV18b421J7Pn/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82

    https://www.bilibili.com/video/BV1ub411Q7sB/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82

    下面的实现 from 我
     */
    public int lengthOfLIS2_1(int[] nums) {

        // tail[i] : 以长度为i+1的递增子序列、最小的结尾元素（tail[0]表示长度为1的子序列结尾的最小元素，初始化为nums[0]）

        int length = nums.length;

        if(length==1){
            return 1;
        }

        int[] tail = new int[length+1];
        tail[0] = nums[0];   // 初始化

        int max = 1;  // tail数组的真实长度（存储元素的数目）

        for(int i=1; i<length; i++){

            //在tail数组中找到nums[i]应该放的位置，由于tail[]是递增的，所以可以用二分查找
            int left = 0;         // left和right都是tail数组的下标
            int right = max-1;
            int mid = 0;
            int index = max;       // nums[i]在tail数组中应该放的位置

            while (left <= right) {   // 找到第一个 >= nums[i]的数，用nums[i]替换它
                mid = (left + right) / 2;

                if (tail[mid] >= nums[i]) {
                    index = mid;   // 记录第一个 >= nums[i] 的位置
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // nums[i]填充到index位置
            tail[index] = nums[i];

            // 要是index > tail末尾，说明找到了更长的递增子序列，更新max
            if(index == max){
                max++;
            }

        }

        return max;
    }


    /*
    我对方法二的另一种写法
     */
    public int lengthOfLIS2_2(int[] nums) {

        // tail[0]：长度为1的递增子序列 的末尾元素（最小）
        // tail[i]:长度为i+1的递增子序列 的末尾元素（最小）

        int length = nums.length;

        if(length==1){
            return 1;
        }

        int[] tail = new int[length+1];

        tail[0] = nums[0];

        int count = 1;  // tail数组实际长度（存储元素的数目）

        // 二分法找到nums[i]应该插入的位置, tail中第一个>=nums[i]的位置
        for(int i=1; i<length; i++){

            if(nums[i] > tail[count-1]){   // nums[i]加入count数组
                count++;
                tail[count-1] = nums[i];
            }else{                        // nums[i]找到tail中第一个>=nums[i]的位置，并替换
                int index = 0;            // 记录tail中第一个>=nums[i]的位置

                int left = 0;
                int right = count-1;


                while(left <= right){

                    int mid = (left + right) / 2;

                    if(tail[mid] == nums[i]){
                        index = mid;
                        break;
                    }else if(tail[mid] > nums[i]){ // 比如tail = [2,4,6] , nums[i]=3, 则记录index=1，再从[2]中尝试找>=nums[i]
                        index = mid;
                        right = mid-1;    // 这里必须要-1，否则在left==right==mid时，若tail[mid]=2, nums[i]=1, 会死循环
                    }else{
                        left = mid + 1;
                    }
                }

                tail[index] = nums[i];
            }
        }

        return count;
    }









}
