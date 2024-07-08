package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 10시 6분
 * 종료 시간 : ??
 *
 * 문제분석
 * -오르막수는 수의 자리가 오름차순을 이루는 수를 말함.
 * - 인접한 수가 같아도 오름차순으로 침
 * - 2234, 3678, 11119는 오르막 수임.
 * - 2232, 3676, 91111은 오르막 수가 아님
 *
 * - 수의 길이 n이 주어질 때, 오르막 수의 개수를 구하는 프로그램 만들기, 수는 0으로 시작 가능
 *
 * - 오르막 수의 개수를 10007로 나눈 나머지 출력하기
 * 10 55 220
 * 1+1+1+1+1+1+1+1+1+1 = 10
 * 1+2+3+4+5+6+7+8+9+10 = 55
 * 1+3+6+10+15+21+28+36+45+55 = 220
 *
 * - 10 *
 * */
public class _11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in
        ));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][10];
        for(int i=0; i<n; i++) {
            for (int j = 9; j >= 0; j--) {
                if(i ==0 ||j == 9) dp[i][j] = 10 - j;
                else{
                    dp[i][j] = (dp[i][j+1] + dp[i-1][j]) % 10_007;
                }
            }
        }

        System.out.println(dp[n-1][0]);
    }
}
