public class InsertSort {

    // 要保证 index=i以及之前有序，就让index=i依次与前面的比较，自己小则交换
    public void insertSort(int[] arr){

        for(int i=1; i<=arr.length-1; i++) {

            int j=i;  // 用一个新的变量j，别让i回退，否则外层for循环又要重新加

            while(j>=1){
                if(arr[j] < arr[j-1]){
                    swap(arr,j,j-1);
                    j--;
                }else{
                    break;
                }
            }
        }
    }


    void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }














}
