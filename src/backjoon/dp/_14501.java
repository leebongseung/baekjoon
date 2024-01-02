package backjoon.dp;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   시작 시간 : 11시 36분
 *   종료 시간 : 12시 13분
 * */
public class _14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 1이상 15이하

        // N+1되는날 퇴사함. 최대한 많은 상담하기를 원함.
        // 기간 T 와 금액 P 로 이루어짐 N=7 인경우

        int[][] arr = new int[n][2]; // T와 P 저장하는 공간
        // 상담을 적절히 수행했을때 최대 이익을 구하여라
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int Ti = Integer.parseInt(st.nextToken()); // 기간, 1이상 5이하
            int Pi = Integer.parseInt(st.nextToken()); // 금액, 1이상 1000이하
            arr[i][0] = Ti;
            arr[i][1] = Pi;
        }

        int[] dp = new int[n+1];
        for (int i = 0; i < n; i++) {
            int T = arr[i][0]; // T 기간
            int P = arr[i][1]; // P 금액

            // 남은 일수가 퇴사일에 벗어나는 경우
            if ((T + i - 1) < n) {
                dp[i + T] = Math.max(dp[i + T], dp[i] + P);
            }

            // 현재 위치가 이전위치, 현재위치랑 비교해서 큰값을 결정하는 자리
            //  이 부분은 현재 날짜까지의 최대 수익을 계산하기 위한 것입니다
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }
        System.out.println(dp[n]);
    }
}
