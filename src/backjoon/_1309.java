package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * 시작 시간 : 7시
 * 종료 시간 : 7시 9분
 *
 * 문제분석
 * -가로 두칸, 세로 n칸인 2차원 동물원 존재
 * - 사자를 우리에 가두려고함.
 * - 가로로도 세로로도 붙어있게 배치 불가능
 * - 사자를 한마리도 배치하지 않는 경우도 있음.
 * - 2*n 배열 우리에 사자를 배치하는 모든 경우의 수를 알아내기
 *
 * 브루트포스라고 생각했는데, dp임.
 * - 어떻게 dp로 해결할까?
 * - 2*1일 경우 = 3
 * - 2*2일 경우 = 7
 * - 2*3일 경우 = 17
 * - 2*4일 경우 = 41
 *
 * dp[i] = (2 * dp[i-1]) + dp[i-2]
 * 공식 발견!!
 * */
public class _1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 3;

        for(int i=2; i<=n; i++) dp[i] = ((2*dp[i-1]) + dp[i-2]) % 9901;

        System.out.print(dp[n]);
    }
}
