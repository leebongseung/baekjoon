package backjoon.DataStructrue2;

/*  시작 시간 : 12시 39분
 *   종료 시간 : 12시 52분
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stInput = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(stInput.nextToken()); // 문자열의 개수
        int m = Integer.parseInt(stInput.nextToken()); // 집합 S에 포함되어 있는 문자열

        Map<String, String> strMap = new HashMap<>();
        for(int i =0; i<n; i++){
            String StrSet = bf.readLine();
            strMap.put(StrSet, StrSet);
        }
        int cnt = 0 ;
        for(int i=0; i<m; i++){
            String findStr = bf.readLine();
            if(strMap.get(findStr) != null){
                cnt ++;
            }
        }
        System.out.println(cnt);

    }
}
