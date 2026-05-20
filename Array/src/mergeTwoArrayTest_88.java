import java.util.Arrays;

public class mergeTwoArrayTest_88 {

    /*
    leetcode88
    两个递增数组 nums1 和 nums2 长度为 m 和 n ，要将 nums2 合并到 nums1中，保证仍然递增
    可设置nums1初始长度为 m+n
     */


    // 解法一：调用Arrays工具类     把nums2中元素全部放入nums1尾部，再直接调用Arrays.sort
    // Arrays.sort本质上是进行快速看排序的
    // 缺点：没有利用到两个数组的有序性
    public static void merge1(int[] nums1, int m, int[] nums2, int n){

        for(int i=0; i<n; i++){
            nums1[m+i] = nums2[i];
        }

        Arrays.sort(nums1);
    }


    /*
    解法二：借助临时数组temp，用两个指针分别遍历两个数组，将较小的元素放入temp数组，最后复制temp数组到nums1
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n){
        int[] temp = new int[m+n];

        int index1=0;
        int index2=0;
        int index_temp = 0;


        while(index1<m && index2<n){                  // 直到一个数组取完
            if(nums1[index1] <= nums2[index2]){
                temp[index_temp] = nums1[index1];
                index1++;
                index_temp++;
            }else {
                temp[index_temp] = nums2[index2];
                index2++;
                index_temp++;
            }
        }

        if(index1==m){
            while(index2<n){
                temp[index_temp] = nums2[index2];
                index2++;
                index_temp++;
            }
        }

        if(index2==n){
            while(index1<m){
                temp[index_temp] = nums1[index1];
                index1++;
                index_temp++;
            }
        }

        for(int i=0; i<m+n; i++){
            nums1[i] = temp[i];
        }
    }


    // 解法三：不要额外创建temp数组,利用好nums1数组的空余位置
    // 用两个指针倒序遍历两个数组的有效元素，更大的放到nums1队尾
    public static void merge3(int[] nums1, int m, int[] nums2, int n){

        int index1 = m-1;
        int index2 = n-1;
        int index_final = m+n-1;

        while(index1>=0 && index2>=0){                         // 直到一个数组取完
            if(nums1[index1] >= nums2[index2]){
                nums1[index_final] = nums1[index1];
                index1--;
                index_final--;
            }else {
                nums1[index_final] = nums2[index2];
                index2--;
                index_final--;
            }
        }

        if(index1<0){        // 注意是 <0 ，不是=0
            while(index2>=0){
                nums1[index_final] = nums2[index2];
                index2--;
                index_final--;
            }
        }

        if(index2<0){
            while(index1>=0){
                nums1[index_final] = nums1[index1];
                index1--;
                index_final--;
            }
        }

    }



    public static void main(String[] args) {



    }

}
