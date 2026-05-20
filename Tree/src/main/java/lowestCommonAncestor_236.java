public class lowestCommonAncestor_236 {

    /*
    方法一 from 随想录

    核心： 从下往上找，后序遍历
         找到一个节点，它的左、右子树中分别含有p和q

    用后序遍历左右中，本质上是从下往上遍历，
    从下层开始找pq，cur节点发现自己的p、q后向上层返回； 没找到返回null

    一路返回到根节点，不为null的就是所求的
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        // 找到一个节点，它的左、右子树中分别含有p和q
        return find(root,p,q);

    }

    TreeNode find(TreeNode cur, TreeNode p, TreeNode q){
        if(cur==null){
            return null;
        }

        if(cur==p || cur==q){
            return cur;     // 没有终止，只是返回给上一层，说明找到了p或q
        }

        TreeNode left = find(cur.left, p ,q);   // 左子树中找p、q
        TreeNode right = find(cur.right,p,q);

        if(left!=null && right!=null){  // 左右子树都找到了p、q，说明cur就是最近公共祖先
            return cur;
        }else if(left!=null && right==null){  // 左子树中找到了p，那么cur的返回值也是p
            return left;
        }else if(left==null && right!=null){
            return right;
        }else{                     // 左右子树都没找到，而且最开始的if也说明cur不是p或q，所以return null
            return null;
        }
    }



    /*
    方法二：思想 from leetcode， 实现 from 我， 更好懂！！！
     */
    TreeNode res = null;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        find(root,p,q);

        return res;
    }


    boolean find(TreeNode cur, TreeNode p, TreeNode q){     // 以cur为根的树是否找到p或q（找到其中一个就是true）

        if(cur==null){
            return false;
        }

        // 从底向上找  =>  用后序遍历
        boolean findLeft = find(cur.left, p, q);
        boolean findRight = find(cur.right, p, q);

        if(findLeft && findRight){   // 情况一：p、q不是父子节点
            res = cur;
        }

        if((findLeft || findRight)  && (cur.val==p.val || cur.val == q.val)){   // 情况二：p、q是父子节点， 有一个为cur
            res = cur;
        }

        if(findLeft==false && findRight==false && cur.val!=p.val && cur.val!=q.val){   // 左右子树都没找到p或q，cur也不是
            return false;
        }else{
            return true;
        }






}
