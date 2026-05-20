import java.util.*;

public class combinationSum_216_39_40 {

    /*
    leet 216
     */

    // from 我
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum1_1(int k, int n) {
        backtracking1_1(k,n,1,sum);
        return result;
    }


    private void backtracking1_1(int k, int n, int startIndex, int sum){

        if(path.size()==k){
            if(sum == n){
                result.add(new ArrayList<>(path));
                return;
            }else{
                return;
            }
        }

        for(int i=startIndex; i<=9; i++){
            path.add(i);
            sum+=i;

            backtracking1_1(k,n,i+1,sum);

            sum -= i;
            path.remove(path.size()-1);
        }
    }



    /*
    加入 剪枝 from 随想录
     */
    public List<List<Integer>> combinationSum1_2(int k, int n) {
        backTracking1_2(n, k, 1, 0);
        return result;
    }

    private void backTracking1_2(int targetSum, int k, int startIndex, int sum) {

        if (sum > targetSum) {          // 减枝
            return;
        }

        if (path.size() == k) {
            if (sum == targetSum) result.add(new ArrayList<>(path));
            return;
        }


        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {     // 减枝 9 - (k - path.size()) + 1
            path.add(i);
            sum += i;

            backTracking1_2(targetSum, k, i + 1, sum);

            path.remove(path.size()-1);
            sum -= i;
        }
    }


    /*
    leet 39

    candidates中取任意数量的数字，和为target
    每个数字可以取多次
    的深度不确定，宽度为 candidates.length
     */

    /*
    方法一 from 我，same as 随想录
     */
    List<List<Integer>> result2_1 = new ArrayList<>();
    List<Integer> path2_1 = new ArrayList<>();


    public List<List<Integer>> combinationSum2_1(int[] candidates, int target) {

        backtracking2_1(candidates,target,0,0);

        return result;
    }


    void backtracking2_1(int[] candidates, int target, int start, int sum){

        if(sum==target){   // 深度不确定
            result2_1.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            return;
        }


        for(int i=start; i<candidates.length;i++){
            path2_1.add(candidates[i]);
            sum+=candidates[i];

            backtracking2_1(candidates,target,i,sum);   // 注意不是 i+1，因为本题允许一个元素被多次选取

            path2_1.remove(path.size()-1);
            sum-=candidates[i];
        }
    }


    /*
    leet 40
     */

    /*
    错误写法：没有去重 from 我
    结果会出现[1,2,5]和[2,1,5这样的重复数据]
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        backtracking(candidates,target,0,0);

        return result;
    }


    void backtracking(int[] candidates, int target, int start, int sum){

        if(sum==target){
            result.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            return;
        }


        for(int i=start; i<candidates.length;i++){
            path.add(candidates[i]);
            sum+=candidates[i];

            backtracking(candidates,target,i+1,sum);  // 和leet39相比，只把 i 变成 i+1，实现每个元素只取一次

            path.remove(path.size()-1);
            sum-=candidates[i];
        }
    }

    /*
    错误2：错误去重 from 我
    应该是后一个发现与前一个重复，再去重
    而不是前一个看后一个，否则 [1,1,6] tar=8 会跳过第一个1，导致只有[1,6]，找不到[1,1,6]
     */

    public List<List<Integer>> combinationSum3_2(int[] candidates, int target) {

        Arrays.sort(candidates);

        backtracking3_2(candidates,target,0,0);

        return result;
    }


    void backtracking3_2(int[] candidates, int target, int start, int sum){

        if(sum==target){
            result.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            return;
        }


        for(int i=start; i<candidates.length;i++){
            if(i+1<candidates.length && candidates[i]==candidates[i+1]){   // 错误，应该是后一个发现与前一个重复，再去重
                continue;
            }

            path.add(candidates[i]);
            sum+=candidates[i];

            backtracking3_2(candidates,target,i+1,sum);

            path.remove(path.size()-1);
            sum-=candidates[i];
        }
    }


    /*
    改正
    本质是用start去重，会抽象一点
     */
    public List<List<Integer>> combinationSum3_3(int[] candidates, int target) {

        Arrays.sort(candidates);

        backtracking3_3(candidates,target,0,0);

        return result;
    }


    void backtracking3_3(int[] candidates, int target, int start, int sum){

        if(sum==target){
            result.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            return;
        }


        for(int i=start; i<candidates.length;i++){
            if(i>start && candidates[i]==candidates[i-1]){
                continue;
            }

            path.add(candidates[i]);
            sum+=candidates[i];

            backtracking3_2(candidates,target,i+1,sum);

            path.remove(path.size()-1);
            sum-=candidates[i];
        }
    }




    /*
    正确 from 随想录      比上述方法就多了个used数组，更好理解
    添加了used数组，用于标识
     */
    public List<List<Integer>> combinationSum3_4(int[] candidates, int target) {

        Arrays.sort(candidates);

        int[] used = new int[candidates.length];

        backtracking3_4(candidates,target,0,0,used);

        return result;
    }


    // used记录哪些用过了，用过的记为1(用于区分树枝还是树层)
    void backtracking3_4(int[] candidates, int target, int start, int sum, int[] used){

        if(sum==target){
            result.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            return;
        }


        for(int i=start; i<candidates.length;i++){
            if(i>0 && candidates[i]==candidates[i-1] && used[i-1]==0){  // used[i-1]==1，说明是树枝，不用去重，可以见老师的分析
                continue;
            }

            path.add(candidates[i]);
            sum+=candidates[i];
            used[i] = 1;

            backtracking3_4(candidates,target,i+1,sum,used);

            path.remove(path.size()-1);
            sum-=candidates[i];
            used[i] = 0;
        }
    }










}
