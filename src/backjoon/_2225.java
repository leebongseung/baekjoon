package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 8시 49분
 * 종료 시간 : ?
 * <p>
 * 문제분석
 * - 0~N까지 정수 K개를 더해서 그합이 N이 되는 경우의수를 구하는 프로그램
 * - 1+2, 2+1 은 서로 다른 경우임.
 * - 한 개의 수를 여러번 사용 가능하다.
 */
public class _2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 1 || j == 0) dp[i][j] = 1;
                else dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[k][n]);

    }
}
