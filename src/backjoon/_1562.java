package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 17;
        dp[3] = 49;
        for(int i=4; i<=n; i++){
            dp[i] = dp[i-1] +
                    (dp[i-1] - dp[i-2]) +
                    ((dp[i-1] - dp[i-2]) - (dp[i-2] - dp[i-3])) * 2;
        }

        long cnt = 0;
        for(int i=0; i<= 40; i++)
            cnt += dp[i] % 1_000_000_000;
        System.out.println(cnt);
    }
}
