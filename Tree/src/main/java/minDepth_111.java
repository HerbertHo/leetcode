import java.util.*;

public class minDepth_111 {

    /*
    方法一 from 我
    在 层序遍历中 找叶子节点，要求的就是找到叶子节点时 resultList的size+1
    在层序遍历代码基础上，基本上只改 加了注释的那一处

     */

    public int minDepth1(TreeNode root) {

        return levelOrder(root);
    }

    int levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();

        if(root==null){
            return 0;
        }

        deque.addFirst(root);
        int size=1;


        while(true){
            List<Integer> list = new ArrayList<>();

            while(size!=0){
                TreeNode temp = deque.removeFirst();
                list.add(temp.val);

                if(temp.left==null && temp.right==null){   // 如果找到叶子节点，结束
                    return resultList.size()+1;
                }

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

//            if(size==0){
//                return resultList.size();     // 本题根本到不了这里，注释了也没关系
//            }
        }
    }





    /*
    方法二 from 代码随想录
     */

    public int minDepth2(TreeNode root) {
        return getHeight(root);
    }


    int getHeight(TreeNode node){
        // 以下代码跟"最大深度"的基本一样
        if(node==null){
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        // 以下代码不一样，不能将 1+max(leftHeight,rightHeight)中max改成min就完事了
        // 因为那只是以下的第一种情况，可见官方例子2
        if(node.left!=null && node.right!=null){
            return Math.min(leftHeight,rightHeight) + 1;
        }else if(node.left==null && node.right!=null){  // 一个节点的左子树为空，那么它的高度为 右子树高度+1
            return rightHeight+1;
        }else if(node.left!=null && node.right==null){
            return leftHeight+1;
        }else{              // 左右子树都为空，当前节点高度为1
            return 1;
        }
    }











}
