package backjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 31분
 * 종료 시간 : ?
 * <p>
 * 문제분석
 * - 1, 5, 10, 50, 100, 500 원이 있음.
 * - 이 동전들로 정수의 금액을 만들 수 있다.
 * - 예를들어 30원 만들기 위해선
 * - 1원짜리 30개
 * - 10원짜리 2개, 원 2개 등 다양
 * - 동전의 종류가 주어질 때 주어진 금액을 만드는 모든 방법을 세는 프로그램 만들기
 */
public class _9084 {
    public static int n, target;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tCase = 0; tCase < t; tCase++) { // 1 ~ 10
            n = Integer.parseInt(br.readLine()); // 동전의 가짓수, 1~20
            // 동전 금액 종류
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            target = Integer.parseInt(br.readLine());
            // 나올 수 있는 모든 경우의 수 찾기
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) { // 동전 개수만큼 반복
                for (int j = arr[i]; j <= target; j++) { // 현재 동전 크기부터 target 까지 반복.
                    dp[j] += dp[j - arr[i]]; // 나올 수 없는 금액은 안거치는 유일한 방법?
                }
            }

            bw.write(String.valueOf(dp[target]));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
