import java.util.*;

public class binaryTreePaths_257 {

    /*
    输出遍历顺序时，先输出根节点，所以是前序遍历
     */

    /*
    方法一：利用list进行回溯
     */


    /*
    方法二 from 代码随想录
    例子见ipad
     */

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> resultList = new ArrayList<>();
        deal(root, "",resultList);
        return resultList;
    }

    public void deal(TreeNode node, String currentPath,List<String> resultList) {      // 注意：不能直接定义StringBuilder，否则无法回溯
        if (node == null)
            return;
        if (node.left == null && node.right == null) {     // 叶子节点：把当前节点加入到str后，加入resultList
            resultList.add(new StringBuilder(currentPath).append(node.val).toString());
            return;
        }else {
            String newPath = new StringBuilder(currentPath).append(node.val).append("->").toString();
            deal(node.left, newPath,resultList);    // 给一个节点的左右子树传同一个路径，避免了回溯
            deal(node.right, newPath,resultList);
        }




        /*
        方法二 from 代码随想录的思想，我的实现
         */
        List<String> result = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {

            findPath(root,"");

            return result;
        }


        void findPath(TreeNode cur, String curPath){

            if(cur==null){
                return;
            }


            if(cur.left==null && cur.right==null){  // 是叶子，返回结果
                StringBuilder path = new StringBuilder().append(curPath).append(cur.val);

                result.add(path.toString());
            }else{     // 不是叶子，子节点继续

                StringBuilder path = new StringBuilder().append(curPath).append(cur.val).append("->");

                String pathStr = path.toString();

                if(cur.left!=null){
                    findPath(cur.left, pathStr);
                }

                if(cur.right!=null){
                    findPath(cur.right, pathStr);
                }
            }
        }













    }










}
