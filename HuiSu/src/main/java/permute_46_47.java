import java.util.*;

public class permute_46_47 {

    /*
    leet46
     */

    /*
    方法一 from 随想录

    我的思考：宽度、高度为 nums.length    没有start

    核心区别：要用used数组，让一条路径上元素不重复使用 (在组合问题中用startIndex确保元素不会被重复使用)
            以前的used用于树层去重，这个used用于跳过遍历
     */

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        int[] used = new int[nums.length];

        backtracking1_1(nums,used);

        return result;

    }

    // 宽度、高度为 nums.length    没有start
    void backtracking1_1(int[] nums, int[] used){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        // 要用used数组，让一条路径上元素不重复使用 (在组合问题中用startIndex确保元素不会被重复使用)
        for(int i=0; i<nums.length; i++){    // 区别1：从0开始，而不是从start开始
            if(used[i]==1){                  // 没有去重，也要用used
                continue;
            }

            path.add(nums[i]);
            used[i] = 1;

            backtracking1_1(nums,used);

            path.remove(path.size()-1);
            used[i]=0;
        }
    }



    /*
    leet47 全排列Ⅱ
    集合有重复元素
     */

    /*
    方法一 from 我 same as 随想录，只需要在上一题的基础上加入树层去重
     */
    public List<List<Integer>> permuteUnique2_1(int[] nums) {

        Arrays.sort(nums);            // 唯二修改的地方，想用used去重，一定要看能不能sort（验证本题能sort数组，先sort）

        int[] used = new int[nums.length];

        backtracking2_1(nums,used);

        return result;

    }


    void backtracking2_1(int[] nums, int[] used){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i]==nums[i-1] && used[i-1]==0){   // 唯二修改的地方，加入树层去重
                continue;
            }

            if(used[i]==1){
                continue;
            }

            path.add(nums[i]);
            used[i] = 1;

            backtracking2_1(nums,used);

            path.remove(path.size()-1);
            used[i]=0;
        }
    }



    /*
    方法二 from 我
    用set代替used实现树层去重，但是还要used进行路径上元素的去重
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

//        Arrays.sort(nums);        // 用set不用提前sort

        int[] used = new int[nums.length];

        backtracking2_2(nums,used);

        return result;

    }


    void backtracking2_2(int[] nums, int[] used){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        Set set = new HashSet<>();

        for(int i=0; i<nums.length; i++){

            if(set.contains(nums[i])){   // 用set代替used实现树层去重
                continue;
            }

            if(used[i]==1){
                continue;
            }

            path.add(nums[i]);
            used[i] = 1;
            set.add(nums[i]);

            backtracking2_2(nums,used);

            path.remove(path.size()-1);
            used[i]=0;
        }
    }









}
