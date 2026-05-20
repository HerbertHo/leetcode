public class longestCommonPrefix_14 {

    /*
    方法一 from 我
    比较每个str的第0、1...个字符
    注意：为0或1的特殊情况    for循环遍历char时 i < 每个str的最短长度
     */
    public String longestCommonPrefix1(String[] strs) {

        int length = strs.length;  // str的个数

        int minLen = Integer.MAX_VALUE;   // 获取最短str的长度

        if(length==1){    // 只有一个str
            return strs[0];
        }

        // 检查有没有空的字符串
        for(int i=0; i<length; i++){
            if(strs[i].length()==0 || strs[i]==null){
                return "";
            }else{
                minLen = Math.min(minLen, strs[i].length());
            }
        }

        StringBuilder res = new StringBuilder();

        // 两层循环，一层遍历str的元素，一层遍历
        for(int i=0; i<minLen; i++){   // char下标 ， 注意取所有str中的最短长度
            int flag = 1;

            for(int j=0; j<length-1; j++){  // strs中str下标
                if(strs[j].charAt(i) == strs[j+1].charAt(i)){

                }else{
                    flag = 0;
                }
            }

            if(flag==1){
                res.append(strs[0].charAt(i));
            }else{
                break;
            }
        }


        return res.toString();
    }


    /*
    方法二 from 我

    依次查看每个str的第index个元素是否相同
     */
    public String longestCommonPrefix2(String[] strs) {

        if(strs.length==1){   // 就一个单词
            return strs[0];
        }

        int index = 0;
        StringBuilder result = new StringBuilder();

        while(true){   // while循环控制index++

            // 依次查看每个str的元素是否相同
            for(int i=1; i<strs.length; i++){  // strs[i]表示第i个单词

                if(index>=strs[0].length() || index>=strs[i].length()){   // 出界
                    return result.toString();
                }

                if(strs[i].charAt(index) == strs[i-1].charAt(index)){

                }else{    // 不相等，结束
                    return result.toString();  // 就是公共最长前缀
                }
            }

            result.append(strs[0].charAt(index));

            index++;
        }

    }








}
