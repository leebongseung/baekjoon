package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 5시 17분
 * 종료 시간 : 7시 13분(디버깅 필요)
 * <p>
 * 문제분석
 * - n*m 크기의 직사각형이 존재
 * - 이 직사각형이 겹치지 않는 3개의 작은 직사각형으로 나누려고함
 * - 각 칸은 단하나의 작은 직사각형에 포함되어야 함.
 * - 각각의 작은 직사각형은 적어도 하나의 숫자를 포함하기
 * <p>
 * - 어떤 작은 직사각형의 합은 그 속에 있는 수의 합
 * - 입력으로 주어진 직사각형을 3개의 작은 직사각형으로 나눌때 각각의 작은 직사각형의 합의 곱을 최대로 하는 프로그램 만들기
 * <p>
 * 참고한 블로그
 * 1. https://velog.io/@songheechoi/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-2%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9DP-%EB%88%84%EC%A0%81-%ED%95%A9-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%9E%90%EB%B0%94#%EC%9D%BC%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9
 * <p>
 * 2.그림정리 굳 : https://jih3508.tistory.com/50
 * 3.참고한 코드 : https://khnemu.tistory.com/32
 */
public class _1451 {
    public static int n, m;
    public static long[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sum = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + (str.charAt(j - 1) - '0');
            }
        }

        long res = 0;

        // 직사각형을 나누는 기준
        // 1. 가로로 2번 나누기
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // x축기준 1 ~ i 사각형 1개
                long num1 = findingASquare(1, 1, i, m);
                // i+1 ~ j 사각형
                long num2 = findingASquare(i + 1, 1, j, m);
                // j+1~ n 사각형
                long num3 = findingASquare(j + 1, 1, n, m);

                // 최댓값 구하기
                res = Math.max(res, num1 * num2 * num3);
            }
        }

        // 2. 세로로 2번 나누기
        for (int i = 1; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                // y축기준 1 ~ i 사각형 1개
                long num1 = findingASquare(1, 1, n, i);
                // i+1 ~ j 사각형
                long num2 = findingASquare(1, i + 1, n, j);
                // j+1~ m 사각형
                long num3 = findingASquare(1, j + 1, n, m);

                // 최댓값 구하기
                res = Math.max(res, num1 * num2 * num3);
            }
        }

        // 3. 가로 1, 세로 1번 나누기,
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {

                //(1,1 기준으로 왼쪽 위 대각선에서)
                // 위쪽 가로먼저나누고 세로 나누기
                long num1 = findingASquare(1, 1, i, m); // 가로 나누기
                long num2 = findingASquare(i + 1, 1, n, j); // 세로 나누기
                long num3 = findingASquare(i + 1, j + 1, n, m);
                res = Math.max(res, num1 * num2 * num3);

                // 왼쪽 세로 먼저 나누기 가로 나누기
                num1 = findingASquare(1, 1, n, j); // 세로 나누기
                num2 = findingASquare(1, j + 1, i, m); // 가로 나누기
                num3 = findingASquare(i + 1, j + 1, n, m);
                res = Math.max(res, num1 * num2 * num3);

                // 아래쪽 가로먼저나누고 세로 나누기
                num1 = findingASquare(i+1, 1, n, m); // 가로 나누기
                num2 = findingASquare(1, 1, i, j); // 세로 나누기
                num3 = findingASquare(1, j+1, i, m);
                res = Math.max(res, num1 * num2 * num3);

                // 오른쪽 세로 먼저 나누기 가로 나누기
                num1 = findingASquare(1, j+1, n, m); // 세로 나누기
                num2 = findingASquare(1, 1, i, j); // 가로 나누기
                num3 = findingASquare(i + 1, 1, n, j);
                res = Math.max(res, num1 * num2 * num3);
            }
        }

        System.out.print(res);
    }

    private static long findingASquare(
            int x1, int y1,
            int x2, int y2
    ) {
        // 범위를 벗어날경우
        if(x1 <= 0 || x1 > n || y1 <= 0 || y1 > m
                || x2 <= 0 || x2 > n || y2 <= 0 || y2 > m) return 0;
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }
}
