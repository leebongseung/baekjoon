package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 5시 17분
 * 종료 시간 : 7시 13분(디버깅 필요)
 *
 * 문제분석
 * - n*m 크기의 직사각형이 존재
 * - 이 직사각형이 겹치지 않는 3개의 작은 직사각형으로 나누려고함
 * - 각 칸은 단하나의 작은 직사각형에 포함되어야 함.
 * - 각각의 작은 직사각형은 적어도 하나의 숫자를 포함하기
 *
 * - 어떤 작은 직사각형의 합은 그 속에 있는 수의 합
 * - 입력으로 주어진 직사각형을 3개의 작은 직사각형으로 나눌때 각각의 작은 직사각형의 합의 곱을 최대로 하는 프로그램 만들기
 *
 * 참고한 블로그
 * 1. https://velog.io/@songheechoi/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-2%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9DP-%EB%88%84%EC%A0%81-%ED%95%A9-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%9E%90%EB%B0%94#%EC%9D%BC%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9
 *
 * 2.그림정리 굳 : https://jih3508.tistory.com/50
 * */
public class _1451 {
    public static int n,m;
    public static int[][] arr, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m+1];
        sum = new int[n+1][m+1];
        for(int i=1; i <= n; i++){
            String str = br.readLine();
            for(int j=1; j<=m; j++){
                arr[i][j] = Integer.parseInt(str.substring(j-1, j));
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
            }
        }

        int res = 0;

        // 직사각형을 나누는 기준
        // 1. 가로로 2번 나누기
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                int multiplyingSquares = 1;
                // x축기준 1 ~ i 사각형 1개
                multiplyingSquares *= findingASquare(1, 1, i,m);
                // i+1 ~ j 사각형
                multiplyingSquares *= findingASquare(i+1, 1, j, m);
                // j+1~ n 사각형
                multiplyingSquares *= findingASquare(j+1,1,n,m);

                // 최댓값 구하기
                res = Math.max(res, multiplyingSquares);
            }
        }

        // 2. 세로로 2번 나누기
        for(int i=1; i<=m; i++){
            for(int j=i+1; j<=m; j++){
                int multiplyingSquares = 1;
                // y축기준 1 ~ i 사각형 1개
                multiplyingSquares *= findingASquare(1, 1, n, i);
                // i+1 ~ j 사각형
                multiplyingSquares *= findingASquare(1, i+1, n, j);
                // j+1~ n 사각형
                multiplyingSquares *= findingASquare(1,j+1,n,m);

                // 최댓값 구하기
                res = Math.max(res, multiplyingSquares);
            }
        }

        System.out.println(res);

        // 3. 가로 1, 세로 1번 나누기
        for(int i=1; i<=n; i++){
            for(int j=1; j<= m; j++){
                // 가로먼저나누고 세로 나누기
                int multiplyingSquares = 1;
                multiplyingSquares *= findingASquare(1,1,i,m); // 가로 나누기
                multiplyingSquares *= findingASquare(i+1,1,n,j); // 세로 나누기
                multiplyingSquares *= findingASquare(i+1,j+1,n,m);
                res = Math.max(res, multiplyingSquares);

                // 세로 먼저 나누기 가로 나누기
                multiplyingSquares = 1;
                multiplyingSquares *= findingASquare(1,1,n,j); // 세로 나누기
                multiplyingSquares *= findingASquare(1,j+1,i,m); // 가로 나누기
                multiplyingSquares *= findingASquare(i+1,j+1,n,m);
                res = Math.max(res, multiplyingSquares);
            }
        }
    }

    private static int findingASquare(
            int x1, int y1,
            int x2, int y2
    ){
        return sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
    }
}
