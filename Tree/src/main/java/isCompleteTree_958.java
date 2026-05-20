import java.util.*;

public class isCompleteTree_958 {

    /*
    核心思想:
    类似层序遍历,找到一个null节点后,之后不能再有空节点
    和层序遍历不同的是:层序遍历中先判断cur有左右子树时再入队; 这里不判断直接入队,这样可以保证一整层都在deque中(不管是不是null)
     */

    /*
    方法一: 思想 from GPT,实现 from 我
     */

    public boolean isCompleteTree1(TreeNode root) {
        int findNull = 0;

        Deque<TreeNode> deque = new LinkedList<>();

        deque.addLast(root);
        int size = 1;

        while(!deque.isEmpty()){

            for(int i=0 ;i<size;i++){  // 出队size个元素
                TreeNode cur = deque.removeFirst();

                if(cur==null){
                    findNull = 1;
                }else{
                    if(findNull == 1){
                        return false;
                    }

                    // 左右子树入栈（不管为不为null）
                    deque.addLast(cur.left);
                    deque.addLast(cur.right);
                }
            }

            size = deque.size();
        }

        return true;
    }





}
