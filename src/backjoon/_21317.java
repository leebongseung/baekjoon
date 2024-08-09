package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 9분
 * 종료 시간 : 10시 43분
 * <p>
 * 문제분석
 * - n개 돌이 일렬로 되어있는 돌 발견(돌, 소비에너지)
 * - 산삼 캐기위해 돌과 돌사이를 점프함.
 * - 점프는 3가지 종류 존재
 * 1. 작은 점프 : 한칸 이동
 * 2. 큰 점프: 2칸 이동
 * 3. 매우 큰 점프 : 3칸 이동 존재
 * <p>
 * - 작은 점프, 큰점프는 돌의 번호마다 소비 에너지는 다름.
 * - 매우 큰점프는 1번 소비, k만큼의 에너지 소비
 * - 에너지의 최솟값 구하기
 * <p>
 * 느낌상 dp인데
 */
public class _21317 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 돌의 개수
        int[] smallJumpArr = new int[n];
        int[] bigJumpArr = new int[n];
        int[][] dp = new int[n + 3][2]; // 2차원 배열 0 : 매우 큰점프 사용, 1 : 매우 큰점프 안사용.
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = 0;

        // 1번 돌부터 n-1번 돌까지만 주어짐. -> 0~ n-2 까지 진행.
        for (int i = 0; i <= n - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            smallJumpArr[i] = Integer.parseInt(st.nextToken());
            bigJumpArr[i] = Integer.parseInt(st.nextToken());
        }

        int k = Integer.parseInt(br.readLine());

        // 점프하면서 최소가 되는 에너지 구하기
        for (int i = 0; i <= n - 2; i++) {
            // 매우 큰 점프를 안사용 하는 경우
            dp[i + 1][0] = Math.min(dp[i + 1][0], dp[i][0] + smallJumpArr[i]);
            dp[i + 2][0] = Math.min(dp[i + 2][0], dp[i][0] + bigJumpArr[i]);

            // 매우 큰 점프를 사용하는 경우
            dp[i + 3][1] = Math.min(dp[i + 3][1], dp[i][0] + k);

            // 매우 큰 점프를 사용한 경우
            dp[i + 1][1] = Math.min(dp[i + 1][1], dp[i][1] + smallJumpArr[i]);
            dp[i + 2][1] = Math.min(dp[i + 2][1], dp[i][1] + bigJumpArr[i]);
        }

        System.out.print(Math.min(dp[n - 1][0], dp[n - 1][1]));

    }
}
