import java.util.*;

public class combine_77{

    // 本题如果要sout，直接 sout(result)就行，因为是list


    /*
    方法一 from 随想录


     */
    List<List<Integer>> result1 = new ArrayList<>();
    List<Integer> path1 = new ArrayList<>();

    public List<List<Integer>> combine1(int n, int k) {
        backtracking1(n,k,1);
        return result1;
    }

    // 有start， i+1
    private void backtracking1(int n, int k, int startIndex){
        // 确定终止条件， path数组的大小==k，也是叶子节点
        if(path1.size() == k){
            result1.add(new ArrayList<>(path1));        // 易错
            return;
        }

        // 单层递归逻辑
        for(int i=startIndex; i<=n; i++){   // 易错：本题是从1到n，不要默认就是从0到n-1
            path1.add(i);
            backtracking1(n,k,i+1);    // 递归
            path1.remove(path1.size()-1);  // 回溯：得到12之后把2弹出，才能加上3后得到13
        }
    }



    /*
    对上述的优化：
     */
    List<List<Integer>> result2 = new ArrayList<>();
    List<Integer> path2 = new ArrayList<>();

    public List<List<Integer>> combine2(int n, int k) {
        backtracking2(n,k,1);
        return result2;
    }

    void backtracking2(int n, int k, int startIndex){
        if(path2.size() == k){
            result2.add(new ArrayList<>(path2));
            return;
        }

        // 修改的原因： size是已选取的元素数量，还要选k-size个元素

        for(int i = startIndex; i <= n - (k - path2.size()) + 1; i++){    // 唯一修改的一处
            path2.add(i);
            backtracking2(n,k,i+1);
            path2.remove(path2.size()-1);
        }
    }










}
