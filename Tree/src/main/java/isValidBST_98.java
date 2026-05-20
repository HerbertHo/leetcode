import java.util.*;

public class isValidBST_98 {


    /*
    错误代码 from 我
    错误原因：只做了“当前节点 vs 左右子节点”的比较，没有判断当前节点 是不是 大于左子树的所有节点（而不是只有左子树的根节点）
     */
    int flag1 = 1;

    public boolean isValidBST1(TreeNode root) {

        // 空树and只有一个节点的树也是二叉搜索树
        judge(root);

        return flag1==1;

    }

    private void judge(TreeNode cur){
        if(cur==null || (cur.left==null && cur.right==null)){
            return;
        }

        if(cur.left!=null){
            if(cur.left.val >= cur.val){
                flag1 = 0;
                return;
            }else{
                judge(cur.left);
            }
        }

        if(cur.right!=null){
            if(cur.right.val <= cur.val){
                flag1 = 0;
                return;
            }else{
                judge(cur.right);
            }
        }
    }



    /*
    对上述代码的改进
    对lower和upper的理解见ipad
     */
    int flag2 = 1;

    public boolean isValidBST2(TreeNode root) {

        // 空树and只有一个节点的树也是二叉搜索树
        judge2(root,Long.MIN_VALUE, Long.MAX_VALUE);  // 用 Long.MAX_VALUE防止Integer.MAX_VALUE不够大

        return flag2==1;

    }

    private void judge2(TreeNode cur,long lower, long upper){
        if(cur==null){
            return;
        }

        if(cur.val<=lower || cur.val>=upper){
            flag2=0;
            return;
        }

        if(cur.left!=null){
            judge2(cur.left,lower,cur.val);
        }

        if(cur.right!=null){
            judge2(cur.right,cur.val,upper);
        }
    }


    /*
    方法二：利用二叉搜索树的特性："中序遍历（左中右）是有序的"
    将中序遍历的结果保存到数组上，判断数组是否为递增 from 随想录
    很慢
     */
    public boolean isValidBST3(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        if(root==null){
            return true;
        }

        inorder(list,root);

        // 判断list是否有序
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i)>=list.get(i+1)){
                return false;
            }
        }

        return true;
    }


    private void inorder(List<Integer> list,TreeNode cur){
        if(cur.left!=null){
            inorder(list,cur.left);
        }

        list.add(cur.val);

        if(cur.right!=null){
            inorder(list,cur.right);
        }
    }







}
