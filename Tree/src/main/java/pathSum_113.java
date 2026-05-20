import java.util.ArrayList;
import java.util.List;

public class pathSum_113 {

    /*
    方法一 from 我， GPT修正
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        // 找到所有路径
        findPath(root, targetSum);

        return result;

    }


    void findPath(TreeNode cur, int target){

        if(cur==null){
            return;
        }

        target-= cur.val;
        path.add(cur.val);

        if(cur.left==null && cur.right==null){

            if(target==0){
                result.add(new ArrayList<>(path));
            }

        }else{
            findPath(cur.left, target);
            findPath(cur.right, target);
        }


        path.remove(path.size() - 1);   // 因为path是共享变量，所以一定要回溯， 否则在找cur的右子树时还带着cur左子树的路径； 可问GPT理解
    }

















}
