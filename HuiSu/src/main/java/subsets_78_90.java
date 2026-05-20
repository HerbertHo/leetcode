import java.util.*;

public class subsets_78_90 {

    /*
    leet78
     */

    /*
    方法一 from 我  same as 随想录
    分析见ipad
     */

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets1_1(int[] nums) {
        backtracking1_1(nums,0);

        return result;
    }

    // 宽度为 nums.length   有start， back(i+1)
    private void backtracking1_1(int[] nums, int start){

        result.add(new ArrayList<>(path));   // 沿路都要收集，不仅仅只有终止时收集

        if(start==nums.length){     // 终止
            return;
        }

        for(int i=start; i<nums.length; i++){
            path.add(nums[i]);

            backtracking1_1(nums,i+1);

            path.remove(path.size()-1);
        }
    }


    /*
    leet90 = 上一题 + leet40
    与上一题不同：有重复元素      要去重
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int used[] = new int[nums.length];

        backtracking2_1(nums,0,used);

        return result;
    }


    private void backtracking2_1(int[] nums, int start, int[] used){

        result.add(new ArrayList<>(path));

        if(start==nums.length){
            return;
        }

        for(int i=start; i<nums.length; i++){
            if(i>0 && nums[i]==nums[i-1] && used[i-1]==0){  // 与leet40一样，只做树层去重
                continue;
            }

            path.add(nums[i]);
            used[i] = 1;


            backtracking2_1(nums,i+1,used);

            path.remove(path.size()-1);
            used[i] = 0;
        }

    }


    /*
    类比leet491，用更通用的set实现树层去重
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        backtracking2_2(nums,0);

        return result;
    }

    private void backtracking2_2(int[] nums, int start){

        result.add(new ArrayList<>(path));

        if(start==nums.length){
            return;
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i=start; i<nums.length; i++){

            if (set.contains(nums[i])) {   // 数层去重
                continue;
            }

            path.add(nums[i]);
            set.add(nums[i]);

            backtracking2_2(nums,i+1);

            path.remove(path.size()-1);
        }
    }











}
