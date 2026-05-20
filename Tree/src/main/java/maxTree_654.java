public class maxTree_654 {


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return func(nums,0,nums.length-1);

    }


    private TreeNode func(int[] nums, int begin, int end){
        int rootIndex = begin;
        int max = nums[begin];

        for(int i=begin; i<=end; i++){
            if(nums[i] > max){
                rootIndex = i;
                max = nums[i];
            }
        }

        // 构建root
        TreeNode root = new TreeNode(max);

        if(rootIndex==begin){
            root.left = null;
        }else{
            root.left = func(nums,begin,rootIndex-1);
        }

        if(rootIndex==end){
            root.right = null;
        }else{
            root.right = func(nums,rootIndex+1,end);
        }

        return root;
    }


}
