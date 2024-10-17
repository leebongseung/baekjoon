package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 8분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 0과 1로 이루어진 행렬 a,b 존재
 * - 행렬 a를 행렬 b로 바꾸는데 필요한 연산의 최솟값 구하기
 * - 행렬을 변환하는 연산은 3*3 크기의 부분행렬에 있는 모든 원소를 뒤짚는 것임.
 */
public class _1080 {
    public static int n, m, res = Integer.MAX_VALUE;
    public static int[][] desArr, startArr;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        desArr = new int[n][m];
        startArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                desArr[i][j] = Integer.parseInt(str.substring(j, j + 1));
            }
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                startArr[i][j] = Integer.parseInt(str.substring(j, j + 1));
            }
        }

        // 백트래킹으로 start -> des 최솟값 구해보기
        if(n >= 3 && m >= 3) {
            visited = new boolean[n-2][m-2];
            dfs(startArr, 0);
        }
        System.out.println(res == Integer.MAX_VALUE? -1 : res);
    }

    private static void dfs(int[][] arr, int cnt) {
        boolean check = true;
        loop:
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr[i][j] != desArr[i][j]){
                    check = false;
                    break loop;
                }
            }
        }

        if(check){
            res = Math.min(res, cnt);
            return;
        }

        // 최솟값보다 더 깊이 탐색할 경우 빠져 나오기
        if(cnt > res) return;

        // 3*3 배열 뒤짚어서 dfs 돌리기 시작
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if(visited[i][j]) continue;

                System.out.println("i = " + i + ", j = " + j);

                visited[i][j] = true;
                rehash(arr, i, j);
                dfs(arr, cnt+1);
                rehash(arr, i, j);
                visited[i][j] = false;
            }
        }
    }

    private static void rehash(int[][] arr, int x, int y){
        for(int i=x; i< x+3; i++){
            for(int j=y; j< y+3; j++){
                arr[i][j] = arr[i][j] == 0? 1 : 0;
            }
        }
    }
}
