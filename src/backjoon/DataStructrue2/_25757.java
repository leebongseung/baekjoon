package backjoon.DataStructrue2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*  시작 시간 : 9시
*   종료 시간 : 9시 11분
* */
public class _25757 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 게임 횟수 100,000개이하
        String gameType = st.nextToken();
        int size =0; // 게임 참여 사람수
        if(gameType.equals("Y")){
            size = 2-1;
        } else if(gameType.equals("F")) {
            size = 3-1;
        }else if(gameType.equals("O")) {
            size = 4-1;
        }

        Set<String> personName = new HashSet<>(); // 사람 문자열, 20이하

        int cnt = 0;
        int res = 0;
        for(int i =0; i<n; i++){
            String name = bf.readLine();
            if(personName.contains(name)){
                continue;
            }
            personName.add(name);
            cnt++;
            if(cnt == size){
                res++;
                cnt = 0;
            }
        }
        System.out.println(res);

    }
}
