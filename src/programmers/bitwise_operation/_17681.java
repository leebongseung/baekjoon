package programmers.bitwise_operation;
import java.lang.*;

public class _17681 {
    class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];
            for(int i =0; i< n; i++){
                int x = arr1[i] | arr2[i];
                char[] ch = new char[n];
                int idx = 0;
                for(int j= x; j > 0; j /= 2){
                    if(j % 2 != 0) ch[idx++] = '#';
                    else ch[idx++] = ' ';
                }

                StringBuffer sb = new StringBuffer();
                for(int j =n-1; j >=0; j--){
                    if(ch[j] == '#') sb.append("#");
                    else sb.append(" ");
                }
                answer[i] = sb.toString();
            }
            return answer;
        }
    }
}
