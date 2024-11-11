package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 8분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 번호가 1줄로 주어짐.
 * 줄세우는 규칙
 * 1. 줄 서 있는 어린이 중 한명 선택
 * 2. 제일 앞이나 제일 뒤로 보내버림.
 * 결과
 * - 앞이나 뒤로 보내는 어린이 수의 최솟값을 기
 *
 * 입력
 * - n은 1_000_000 이하.
 *
 * */
public class _7570 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            int k = Integer.parseInt(st.nextToken());
            dp[k] = dp[k-1] + 1;
        }

        int val = 0;
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for(int i=1; i<= n; i++){
            if (val <= dp[i]){
                val = dp[i];
                cnt = 0;
            } else {

            }

            cnt++;
        }
    }

}
