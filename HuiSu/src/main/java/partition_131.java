import java.util.*;

public class partition_131 {

    /*
    跟以往的问题不一样，以往的问题是从集合中选几个元素
    这个是集合中所有元素都要，只是切割不同
     */

    /*
    方法 from 代码随想录
    分析见ipad
     */
    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    // 判断是不是回文串：reverse后仍然equal
    public List<List<String>> partition1(String s) {
        backtacking1(s,0);

        return result;

    }

    private void backtacking1(String s,int startIndex){

        // startIndex就是 切割线
        if(startIndex >= s.length()){  // 切割到末尾
            result.add(new ArrayList<>(path));   // 不在这里判断是不是回文
            return;
        }

        for(int i=startIndex; i<s.length(); i++){
            // 切割的子串 s[startIndex,i]
            if(isHuiWenChuan(s,startIndex,i)){
                path.add(s.substring(startIndex,i+1));
            }else{        // 不是回文
                continue;
            }

            backtacking1(s,i+1);

            path.remove(path.size()-1);
        }

    }


    private boolean isHuiWenChuan(String str, int startIndex, int endIndex){
        String subString = str.substring(startIndex,endIndex+1);

        return subString.equals(new StringBuilder(subString).reverse().toString());
    }



    /*
    我自己的写法
     */
    public List<List<String>> partition2(String s) {
        backtacking2(s,0);

        return result;
    }


    void backtacking2(String s, int start){
        if(start == s.length()){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<s.length(); i++){
            // 切割子串加入path
            String sub = s.substring(start,i+1);

            if(isHuiWen(sub)){
                path.add(sub);
                backtacking2(s,i+1);
                path.remove(path.size()-1);
            }else{
                continue;
            }
        }
    }

    private boolean isHuiWen(String str){
        StringBuilder sb = new StringBuilder(str);

        String str2 = sb.reverse().toString();

        return str.equals(str2);
    }




}
