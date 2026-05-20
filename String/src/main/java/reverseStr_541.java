public class reverseStr_541 {
    public String reverseStr1(String s, int k) {
        /* 原题
        给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。

        如果剩余字符少于 k 个，则将剩余字符全部反转。
        如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
         */

        /*
        对原题的翻译：每2k个字符，翻转前k个，不翻转后k个

        示例见ipad
         */

        char[] charArray = s.toCharArray();
        int index = 0;


        while(index <= charArray.length){
            // 判断剩余的还够不够k个
            if(charArray.length-1 - index >= k){
                reverseTemp(charArray,index,index+k-1);
            }else {
                reverseTemp(charArray,index,charArray.length-1);
            }

            index = index + 2 * k;
        }

        String str = new String(charArray);
        return str;
    }

    void reverseTemp(char[] charArray,int i,int j){
        while(i<j){
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;

            i++;
            j--;
        }
    }


    /*
    我另一次的写法
     */
    public String reverseStr2(String s, int k) {

        char[] charArr = s.toCharArray();

        // k个反转，k个不反转
        int length = s.length();

        int round = length/2/k;

        for(int i=0 ;i<round; i++){
            reverseCharArray(charArr,2*k*i, 2*k*i+k-1);
        }

        // 剩下的不足2k个，charArr[2*k*round,length-1]
        if(length-1-2*k*round >= k){
            reverseCharArray(charArr,2*k*round,2*k*round+k-1);
        }else{
            reverseCharArray(charArr,2*k*round,length-1);
        }

        String result = new String(charArr);

        return result;
    }

    void reverseCharArray(char[] charArr,int left,int right){
        while(left<=right){
            char c = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = c;

            left++;
            right--;
        }
    }



}
