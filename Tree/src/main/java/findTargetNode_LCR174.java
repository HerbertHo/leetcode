import java.util.*;

public class findTargetNode_LCR174 {


    /*
    方法一 from 我
    中序遍历得到有序list,返回list中元素
     */

    List<Integer> list = new ArrayList<>();

    // 中序遍历得到有序
    public int findTargetNode(TreeNode root, int cnt) {

        // 第1大的索引为size-1
        inorder(root);

        return list.get(list.size()-cnt);
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
