import java.util.Arrays;

public class findLengthOfLCIS_674 {

    /*
    和leet300的区别在于： 本题要求子序列连续
     */

    /*
    方法一 from 我，错误

    错误原因，固定i向前找比nums[i]小的，eg nums[i]=5, 先找到3，再找到4 ， 但其实[4,3,5]不是递增子序列
     */
    public int findLengthOfLCIS1_1(int[] nums) {

        // 递增的连续子序列
        // for循环固定i，left从i往前找
        int maxLen = 0;

        int length = nums.length;

        if(length==1){
            return 1;
        }

        for(int i=1; i<=length-1; i++){
            int curLen = 1;
            int left = i-1;
            int upper = nums[i];  // 上限，要找的数字不能超过上线

            while(left>=0){
                if(nums[left] < nums[i]){
                    curLen++;
                    left--;
                }else{
                    break;
                }
            }

            maxLen = Math.max(maxLen,curLen);
        }

        return maxLen;
    }


    /*
    方法二 from GPT
    我们只需要从左向右扫描一遍数组，每当当前元素比前一个元素大时，就把当前子序列长度加一；否则就重置为 1。整个过程中记录最大值
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums.length == 0) return 0;

        int maxLen = 1;
        int curLen = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curLen++;

                if(curLen > maxLen){
                    maxLen = curLen;
                }
            } else {   // 当前元素小于前一个元素，重置子序列(找以nums[i]开头的递增子序列)
                curLen = 1;
            }
        }

        return maxLen;
    }




    /*
    方法三 from 随想录
    用动态规划，类似leet300

    与leet300核心区别：leet300不要求子序列连续，所以nums[i]要与i之前的每一个元素 尝试组成新的递增子序列(必须包括nums[i])
                          本题要求子序列连续，所以nums[i]只要看能不能和前一个元素 组成新的连续递增子序列(必须包括nums[i])
     */
    public int findLengthOfLCIS3(int[] nums) {
        // dp[i] : 以nums[i]结尾的连续递增子序列的长度（必须包括nums[i]）（和leet300几乎一样）
        int length = nums.length;

        if(length==1){
            return 1;
        }

        int[] dp = new int[length];

        Arrays.fill(dp,1);

        // 递归：
        for(int i=1; i<=length-1; i++){
            if(nums[i] <= nums[i-1]){  // 无法和nums[i-1]组成递增子序列，所以只能为1
                dp[i] = 1;
            }else{                   // 能与nums[i-1]组成递增子序列，可将nums[i]拼到 以nums[i-1]为结尾的数组之后
                dp[i] = dp[i-1] + 1;
            }
        }

        // 和leet300一样从dp数组中找出最大值
        Arrays.sort(dp);
        return dp[length-1];
    }



    /*
    对方法三的小优化： 不用在最后特地从for循环中找最大值
     */
    public int findLengthOfLCIS3_2(int[] nums) {

        // 连续递增子序列 （连续）
        // dp[i]: 以nums[i]结尾的、连续递增子序列的长度 （必须包括nums[i]）
        int length = nums.length;

        if(length==1){
            return 1;
        }

        int[] dp = new int[length];

        int max = 1;

        dp[0] = 1;

        for(int i=1; i<=length-1; i++){
            if(nums[i] > nums[i-1]){
                dp[i] = dp[i-1] + 1;

                max = Math.max(max, dp[i]);
            }else{
                dp[i] = 1;
            }
        }

        return max;
    }





}
