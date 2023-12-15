package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*  시작 시간 : 9시
*   종료 시간 : 9시 40분
* */
public class _19941 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 문자열 크기
        int k = Integer.parseInt(st.nextToken()); // 자신의 위치에서 거리가 k 이하인 햄버거는 먹을 수 있음.

        String str = bf.readLine(); // P : 사람, H : 햄버거
        boolean[] checked = new boolean[str.length()]; // 먹은 사람 처리하기
        Arrays.fill(checked, false);

        int res = 0;
        for(int i =0; i<str.length(); i++){
            if(str.charAt(i)=='H'){
                // i-k ~ i+k 까지 탐색하면서 안먹은 사람이 있는지 찾기
                for(int x = i-k; x <= i+k; x++){
                    if(x <0 || x >= str.length()) continue;
                    if(str.charAt(x) == 'P' && !checked[x]){
                        checked[x] = true;
                        res++;
                        break;
                    }
                }
            }
        }
        System.out.println(res);

    }
}
