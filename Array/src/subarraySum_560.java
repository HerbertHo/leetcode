import java.util.*;

public class subarraySum_560 {

    /*
    本题题解都 from leet官方视频
     */

    /*
    方法一：暴露枚举

    end遍历数组的每个元素，作为子数组尾
    start从end向前遍历，作为子数组头
    若存在sum==target，则count++
     */
    public int subarraySum1(int[] nums, int k) {

        int count = 0; // 统计子数组个数

        for(int end=0; end<nums.length; end++){   // end是子数组结尾
            int sum = 0;

            for(int start=end; start>=0; start--){
                sum += nums[start];

                if(sum == k){
                    count++;
                }
            }
        }

        return count;
    }



    /*
    方法二：前缀和 + 哈希表优化

    前缀和pre[i] : 从0到i的元素之和， 易知pre[i] == pre[i-1] + nums[i]

    若[j,i]这个子数组和为k，则有 pre[i] - pre[j-1] == k ，即 pre[j-1] == pre[i] - k
    所以我们用i遍历nums时，看看之前有无pre[j] == pre[i]-k，若有，说明[j,i]为和为k的子数组，满足题意，count++

    建立哈希表map，以pre[i]为key，出现的次数为value
     */
    public int subarraySum2(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);   // 初始化前缀和为0时出现1次 (用于第一次和为k的子数组)

        int count = 0;  // 统计符合条件的子数组个数
        int sum = 0;    // 当前的前缀和

        // 遍历数组
        for (int num : nums) {
            sum += num;  // 更新当前前缀和

            if (map.containsKey(sum-k)) {
                count += map.get(sum - k);
            }


            map.put(sum, map.getOrDefault(sum, 0) + 1);   // 更新当前前缀和出现的次数
        }

        return count;
    }






}
