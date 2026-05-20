import java.util.*;

public class sumNumbers_129 {

    /*
    方法一 from 我
    回溯，用String保存路径，最后加起来
     */
    List<String> result = new ArrayList<>();

    public int sumNumbers(TreeNode root) {

        backtracking(root,"");

        // 将result中的String取出，相加
        int sum = 0;

        for(String str:result){
            sum += Integer.valueOf(str);
        }

        return sum;
    }



    // 将可能的路径放入一个String中
    void backtracking(TreeNode cur, String path){
        if(cur==null){
            return;
        }

        String newPath = new StringBuilder(path).append(cur.val).toString();

        if(cur.left==null && cur.right==null){
            result.add(newPath);
            return;
        }

        if(cur.left!=null){
            backtracking(cur.left, newPath);
        }

        if(cur.right!=null){
            backtracking(cur.right, newPath);
        }
    }


    /*
    方法二 from 我，  7.9一次写出

    将每条路径和保存到list中，然后将list中元素相加
     */
    List<Integer> list = new ArrayList<>();

    public int sumNumbers2(TreeNode root) {
        func(root, 0);

        // 将list中数求和
        int sum = 0;

        for(int i=0; i<list.size(); i++){
            sum += list.get(i);
        }

        return sum;
    }


    void func(TreeNode cur, int num){

        if(cur==null){
            return;
        }

        num = 10*num + cur.val;

        if(cur.left==null && cur.right==null){
            list.add(num);
        }else{

            func(cur.left, num);
            func(cur.right, num);
        }










}
