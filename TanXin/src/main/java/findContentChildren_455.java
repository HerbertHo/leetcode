import java.util.Arrays;

public class findContentChildren_455 {

    public int findContentChildren(int[] g, int[] s) {

        /*
            我的想法：对孩子和饼干排序，把最大的饼干分给 能满足的、胃口最大的孩子 same as 随想录
         */

        /*
        方法一 from 我
         */

        Arrays.sort(g);  // g：孩子    s：饼干
        Arrays.sort(s);

        int count = 0;

        int i=g.length-1;
        int j=s.length-1;

        while(i>=0 && j>=0){
            if(s[j] >= g[i]){
                count++;
                i--;
                j--;
            }else{
                i--;  // 找胃口更小的孩子
            }
        }

        return count;
    }






}
