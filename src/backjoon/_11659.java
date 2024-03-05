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

//        모든 입력을 받는다.
        st = new StringTokenizer(bf.readLine());
        int[] num = new int[n];
        int[] sum = new int[n];
        for(int i = 0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
                //S[0] = N[0]
            if(i == 0) sum[i] = num[i];
                //S[i] = S[i-1] + A[i]로 S 연산하기
            else sum[i] = sum[i-1] + num[i];
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int i =0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            // i부터 j 까지 합구하기
            // i~j 의 합은?  S[j] - S[i-1]
            // 만약에 i == 0 일 경우
            if(start == 0) sj.add(String.valueOf(sum[end]));
            else sj.add(String.valueOf(sum[end] - sum[start-1]));
        }

        System.out.println(sj);
    }
}
