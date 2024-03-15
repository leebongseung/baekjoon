package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
 * 시작 시간 : 9시 15분
 * 종료 시간 : 9시 39분
 * */
public class _11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        //    n와 m을 입력받는다
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        //    n*n 매트릭스를 입력
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int j = 0;
            while (st.hasMoreTokens()) {
                matrix[i][j++] = Integer.parseInt(st.nextToken());
            }
        }
        //    sum 의 매트릭스를 만든다 n*n 크기
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) sum[i][j] = matrix[i][j];
                else sum[i][j] = sum[i][j - 1] + matrix[i][j];
            }
        }
        //    m번 x1, y1, x2, y2가 주어진다.
        StringJoiner sj = new StringJoiner("\n");
        for (int t = 0; t < m; t++) {
            int res = 0;
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            for (int i = x1; i <= x2; i++) {
                if (y1 == 0) res += sum[i][y2];
                else res += sum[i][y2] - sum[i][y1 - 1];
            }
            sj.add(String.valueOf(res));
        }
        System.out.println(sj);
    }
}
