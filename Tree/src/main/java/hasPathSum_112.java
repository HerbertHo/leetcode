public class hasPathSum_112 {



    /*
        方法：思路from代码随想录，实现from我
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }

        return tryFind1(root,targetSum);
    }


    // 处理逻辑：保证cur不为null，遍历到cur时先减去cur的值
    // 判断是否为叶子，为叶子则返回路径结果；不为叶子继续搜索
    boolean tryFind1(TreeNode cur,int target){

        target-=cur.val;

        if(cur.left==null && cur.right==null){  // 到叶子节点了，判断这条路径合不合题意
            if(target==0){
                return true;
            }else{
//                target+=cur.val;     // 易忘，回溯
                return false;
            }
        }

        if(cur.left != null){
            if(tryFind1(cur.left,target)){   // 左节点有满足题意的路径，一路返回true到根节点
                return true;
            }
        }

        if(cur.right != null){
            if(tryFind1(cur.right,target)){
                return true;
            }
        }

        return false;
    }




    /*
    另一种写法 from 我
     */
    int flag = 0;

    public boolean hasPathSum2(TreeNode root, int targetSum) {

        if(root==null){
            return false;
        }

        judge(root,targetSum);

        return flag==1;
    }


    private void judge(TreeNode cur, int target){

        // 到叶子节点了，判断路径是否满足
        target -= cur.val;

        if(cur.left==null && cur.right==null){
            if(target==0){
                flag=1;
                return;
            }else{
                return;
            }
        }

        if(cur.left!=null){
            judge(cur.left,target);
        }

        if(cur.right!=null){
            judge(cur.right,target);
        }
    }


    /*
    另一种写法from 我
     */

    int flag = 0;


    public boolean hasPathSum3(TreeNode root, int targetSum) {

        if(root==null){
            return false;
        }


        find(root,0,targetSum);

        return flag==1;

    }


    void find(TreeNode cur, int sum, int targetSum){
        if(cur.left==null && cur.right==null){
            sum+=cur.val;

            if(sum==targetSum){
                flag = 1;
            }

            return;
        }

        // cur不是叶子节点
        sum+=cur.val;

        if(cur.left!=null){
            find(cur.left,sum,targetSum);
        }

        if(cur.right!=null){
            find(cur.right,sum,targetSum);
        }
    }





}
