import java.util.*;

public class rightSideView_199 {


    /*
    方法一 from 我
    层序遍历，将每一层遍历结果的最后一个放入list返回
     */

    public List<Integer> rightSideView1(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();

        if(root==null){
            return result;
        }

        deque.addLast(root);
        int size = 1;

        while(!deque.isEmpty()){
            List<Integer> path = new ArrayList<>();

            while(size!=0){
                TreeNode cur = deque.removeFirst();
                size--;

                path.add(cur.val);

                if(cur.left!=null){
                    deque.addLast(cur.left);
                }

                if(cur.right!=null){
                    deque.addLast(cur.right);
                }
            }

            // 一层结束了，把path的最后一个放入result
            result.add(path.get(path.size()-1));

            size = deque.size();
        }

        return result;
    }






}
