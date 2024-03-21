package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 4시 27분
 * 종료 시간 : 4시 50분
 * */
public class _11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 전체 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 덧셈 반복 횟수

        st = new StringTokenizer(bf.readLine());
        int sum[] = new int[n];
        int idx = 0;
        while(st.hasMoreTokens()){
            if(idx == 0) sum[idx] = Integer.parseInt(st.nextToken());
            else sum[idx] = sum[idx-1] + Integer.parseInt(st.nextToken());
            idx++;
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            if(start == 0) sj.add(String.valueOf(sum[end]));
            else sj.add(String.valueOf(sum[end] - sum[start - 1]));
        }
        System.out.println(sj);
    }
}
