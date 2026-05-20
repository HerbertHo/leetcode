import java.util.*;

public class fourSum_18 {

    /*
    和三数之和十分类似
    三数之和用一个for循环来固定i，  这里四数之和要用两个for循环固定i与j，其余left和right效果一样
    之前对i去重，                 这里对i和j去重
     */

    public List<List<Integer>> fourSum1(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for(int i=0; i<nums.length-3; i++){

            if(i>0 && nums[i]==nums[i-1]){   // 对i去重
                continue;
            }

            for(int j=i+1;j<nums.length-2; j++){

                if(j>i+1 && nums[j]==nums[j-1]){    // 对j去重，注意是j>i+1，不是j>0 否则 eg[2,2,2,2] 目标为8，找不到[2,2,2,2]
                    continue;
                }

                int left = j+1;
                int right = nums.length-1;

                while(left<right){
                    long sum = 0L + nums[i] + nums[j] + nums[left] + nums[right];   // sum必须为long类型，否则有些案例时会溢出

                    if(sum>target){
                        right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        result.add(temp);

                        // 对left和right去重
                        while(left<right && nums[left]==nums[left+1]){
                            left++;
                        }

                        while(left<right && nums[right]==nums[right-1]){
                            right--;
                        }

                        left++;  // left和right移动到下一个与现在不同的数字
                        right--;
                    }
                }
            }
        }

        return result;
    }




}
