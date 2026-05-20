import java.util.*;

public class findBottomLeftValue_513 {

    /*
    找二叉树 最后一行、最靠左侧的节点
     */

    /* 方法一： 迭代法/层序遍历
    由于本题是求最后一行的节点，所以适合用 迭代法/层序遍历
    以下代码由我自己完成，根据层序遍历代码修改，只有最后的return不一样
    但效率很低
     */
    public int findBottomLeftValue1(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();

        deque.addFirst(root);
        int size=1;


        while(true){
            List<Integer> list = new ArrayList<>();

            while(size!=0){
                TreeNode temp = deque.removeFirst();
                list.add(temp.val);

                if(temp.left != null){
                    deque.addLast(temp.left);
                }

                if(temp.right != null){
                    deque.addLast(temp.right);
                }

                size--;
            }

            resultList.add(list);
            size = deque.size();

            if(size==0){
                return resultList.get(resultList.size()-1).get(0);   // 跟层序遍历只有这个代码不一样
            }
        }
    }



    /*
    方法二：对上述迭代法的优化 from 代码随想录
    只记录每一层的第一个节点， 只有当最后一层时才返回
     */
    public int findBottomLeftValue2(TreeNode root) {

        Deque<TreeNode> deque = new LinkedList<>();

        deque.addFirst(root);
        int size=1;

        while(true){
            int result = deque.getFirst().val;

            while(size!=0){        // 一层内的处理
                TreeNode temp = deque.removeFirst();

                if(temp.left != null){
                    deque.addLast(temp.left);
                }

                if(temp.right != null){
                    deque.addLast(temp.right);
                }

                size--;
            }

            size = deque.size();

            if(size==0){
                return result;   // size==0说明这一层已经时最后一层，返回result-该层的第一个节点
            }
        }
    }


    /*
    方法三：递归法，效率最高 from 代码随想录思想，我自己实现
    思路见ipad
     */
    int curMaxDeep = -1;   // 目前找到的最大深度
    int result;

    public int findBottomLeftValue3(TreeNode root) {
        func(root,1);
        return result;
    }


    void func(TreeNode cur,int curDeep){        // curDeep : cur节点的深度
        if(cur.left==null && cur.right==null){  // 只有叶子节点才有可能更新curDeep
            if(curDeep>curMaxDeep){
                curMaxDeep = curDeep;
                result = cur.val;
            }
        }else{
            if(cur.left!=null){
                func(cur.left,curDeep+1);
            }

            if(cur.right!=null){
                func(cur.right,curDeep+1);
            }
        }
    }





}
