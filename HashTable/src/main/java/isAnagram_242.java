public class isAnagram_242 {
    /*
        判断两个给定字符串 组成的字符是不是相同（数量也要相同）
     */

    /*
    题目中说了str中只有小写字母，而a-z的ASCII连续，所以可以用数组       a[0]存放字符串中a的数量 - a[25]存放z的数量
     */

    /*
    定义一个数组hash[26]，将第一个str中每个字母的出现次数放入hash【】中
    遍历第二个str时，每遇到一个字母就在对应的hash【i】上 -1
    检查最后hash【】是否全为0
     */
    public boolean isAnagram1(String s, String t) {

        int[] hashArray = new int[26];

        for(int i=0; i<s.length(); i++){     // 取字符串的length要加()
            hashArray[s.charAt(i) - 'a']++;
        }

        for(int i=0; i<t.length(); i++){
            hashArray[t.charAt(i) - 'a']--;
        }

        for(int i=0; i<hashArray.length; i++){    // 注意：这里 i<hashArray.length（26），不是 i<s.length() ！！！ s="aab"是length()== 3 != 26
            if(hashArray[i] != 0){
                return false;
            }
        }

        return true;







    }













}
