import java.util.*;

public class zigzagLevelOrder_103 {

    /*
    方法一：想法from我，修改fromGPT
    用一个flag控制顺序
     */
    int flag1 = 1; // 1: 从左到右；0: 从右到左
    List<List<Integer>> result = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return result;
        }

        deque.addLast(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> path = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (flag1 == 1) {
                    TreeNode cur = deque.removeFirst();   // 不一样
                    path.add(cur.val);

                    // 从左往右：左孩子先入队
                    if (cur.left != null) {
                        deque.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addLast(cur.right);
                    }
                } else {
                    TreeNode cur = deque.removeLast();   // 不一样
                    path.add(cur.val);

                    // 从右往左：右孩子先入队（注意加到队头）
                    if (cur.right != null) {
                        deque.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        deque.addFirst(cur.left);
                    }
                }
            }

            result.add(new ArrayList<>(path));
            flag1 = 1 - flag1; // 切换方向
        }

        return result;
    }



    /*
    方法二：巧妙 from 我

    先进行正常的层序遍历，reverse 部分path
     */
    boolean flag = true;   // 正向

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if(root==null){
            return result;
        }

        Deque<TreeNode> deque = new LinkedList<>();

        deque.addLast(root);
        TreeNode cur = root;
        int size = 1;

        while(size!=0){

            List<Integer> path = new ArrayList<>();     // 收集一层的数据

            while(size!=0){

                cur = deque.removeFirst();
                path.add(cur.val);

                size--;

                if(cur.left!=null){
                    deque.addLast(cur.left);
                }

                if(cur.right!=null){
                    deque.addLast(cur.right);
                }
            }

            // 一层结束
            if(flag==true){                // 不一样的地方
                result.add(path);
            }else{
                Collections.reverse(path);

                result.add(path);
            }

            flag = !flag;              // 每层换一次

            size = deque.size();
        }

        return result;
    }












}
