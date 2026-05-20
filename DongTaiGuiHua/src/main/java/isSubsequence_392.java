public class isSubsequence_392 {

    /*
    方法一：双指针法
    判断s中的每个元素在不在t中
    效率很好
     */
    public boolean isSubsequence1(String s, String t) {
        // dp[i][j] : s以i-1结尾， t以j-1结尾
        if(s.length()==0){
            return true;
        }

        if(s.length() > t.length()){
            return false;
        }

        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        int length1 = arr1.length;
        int length2 = arr2.length;

        int index1 = 0;
        int index2 = 0;

        while(index1<=length1-1 && index2<=length2-1){
            if(arr1[index1] == arr2[index2]){
                index1++;
                index2++;
            }else{
                index2++;
            }
        }

        return index1==length1 ? true:false;
    }



    /*
    方法二：from 代码随想录

    用leet1143最长公共子序列的思想，如果s和t的最长公共子序列长度 == s.length(), 那么s就是t的子序列
     */
    public boolean isSubsequence(String text1, String text2) {

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        int length1 = arr1.length;
        int length2 = arr2.length;

        int[][] dp = new int[length1+1][length2+1];

        int max = 0;

        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(arr1[i-1]==arr2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;

                    max = Math.max(max, dp[i][j]);
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max==length1 ? true:false;   // 相比于eet1143，只用修改最后的return
    }


    /*
    方法三 from 我，普通做法,效率好
     */
    public boolean isSubsequence3(String s, String t) {

        // 不连续

        // s是t的子序列，也就是s比t短
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        int length1 = s.length();
        int length2 = t.length();

        if(length1 > length2){
            return false;
        }

        int i=0;   // s中索引
        int j=0;   // t中索引

        while(i < length1){    // 要找完s中所有元素

            while(j<length2 && arr2[j] != arr1[i]){   // 在t中找匹配 s[i]的元素
                j++;
            }

            if(j==length2){      // j已经超过末尾了
                return false;
            }

            // 此时有 arr1[i]==arr2[j]，判断下一个元素，注意j也要++，不然当s="aaaaa"时，一个arr2[j]='a'可能会多次匹配s中不同的a
            i++;
            j++;
        }

        return true;
    }




}
