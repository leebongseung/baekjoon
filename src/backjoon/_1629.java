package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 5분
 * 종료 시간 : ?????????
 * <p>
 * 문제분석
 * - 자연수 a를 b번 곱한 수를 알고 싶다.
 */
public class _1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.print(dfs(a, b, c));
    }

    private static long dfs(int a, int b, int c) {

        if (b == 1) return a % c;
        long tmp = dfs(a, b / 2, c);
        if (b % 2 == 0) {
            // 짝수 일 경우 작은문제로 b/2, b/2 로 분리가 가능하다.
            return (tmp * tmp) % c;
        } else {
            // 홀수 일 경우 b/2 b/2 a
            return (((tmp * tmp) % c) * a) % c;
        }
    }
}
