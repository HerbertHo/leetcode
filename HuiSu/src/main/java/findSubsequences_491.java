import java.util.*;

public class findSubsequences_491 {


    /*
    eg [4,7,6,7] 的结果是 [4,7,7] [4,7] [4,6] [4,6,7] [6,7] [7,7]
    注意：子序列不需要在原数组连续,但要保证元素间相对顺序，所以不能先sort

    本题不能used[i-1]==0 去重，因为这样去重的前提是先要对 nums进行sort，但本题不能对nums进行sort
    本题引入set去重，更加通用（不仅适用与本题，还适用于之前涉及树层去重的题目）

    要砍掉：（1）树层重复  （2）不递增

     */

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {

        if(nums.length<2){
            return result;
        }

        backtracking(nums,0);

        return result;
    }


    // 有start  back(i+1)  数层去重
    private void backtracking(int[] nums, int start) {
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }

        if (start == nums.length) {
            return;
        }

        HashSet<Integer> set = new HashSet<>();  // 类似之前的used，用于数层去重

        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)) {   // 不是递增，注意nums[i]不是和nums[i-1]比较
                continue;
            }

            if (set.contains(nums[i])) {   // 数层去重
                continue;
            }

            path.add(nums[i]);
            set.add(nums[i]);

            backtracking(nums, i + 1);
            // 不用回滚set.add操作

            path.remove(path.size() - 1);

        }

    }
}
