import java.util.*;

public class kthSmallest_230 {

    /*
    方法一 from 我, same as GPT
    中序遍历得到有序list, 返回list中指定元素
     */
    List<Integer> list = new ArrayList<>();

    // 中序遍历得到有序
    public int kthSmallest1(TreeNode root, int k) {

        // 第1小的索引为0，第k小的索引为k-1
        inorder(root);

        return list.get(k-1);
    }


    void inorder(TreeNode cur){

        if(cur==null){
            return;
        }


        if(cur.left!=null){
            inorder(cur.left);
        }

        list.add(cur.val);

        if(cur.right != null){
            inorder(cur.right);
        }
    }











}
