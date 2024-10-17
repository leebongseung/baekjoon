package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 7시 26분
 * 종료 시간 : ??
 * <p>
 * 문제 분석
 * - n * m 크기의 2차원 배열
 * - 인접한 세로선 사이에는 가로선 놓을 수 있음.
 * - 세로선 마다 가로선을 놓을 수 있는 위치의 개수 h 개
 * - 모든 세로선이 같은 위치를 가짐.
 * - i번째 세로선의 결과가 i번 나와야 함.
 * - 그러기 위해 추가해야하는 가로선 개수의 최솟값을 구해라
 * <p>
 * 주의 사항
 * - 가로선이 연하거나, 접하면 안됨
 * - 가로선은 점선 위에 존재ㅑ
 */
public class _15684 {
    public static int n, m, h, res = Integer.MAX_VALUE;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로선의 개수
        m = Integer.parseInt(st.nextToken()); // 존재하는 가로선의 개수
        h = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치 개수

        arr = new int[h][n-1]; // x : 가로선 위치, y : 세로선 위치

        // 가로선의 정보가 주어짐
        // a와 b, b의 세로선과 b+1세로선을 a번째 점선 위치에 연결
        // 1 <= a <= h, 1 <= b <= n-1

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int depth = Integer.parseInt(st.nextToken()) - 1; // 점선 위치
            int b = Integer.parseInt(st.nextToken()) - 1; // b ~ b+1 세로선 연결
            h--;

            arr[depth][b] = 1;
        }

        // dfs
        dfs(0);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    public static void dfs(int depth) {
        if (depth > 4) {
            return;
        }

        // true 일 경우 -> i번째 세로선이 i번이 나오도록 조작된 경우
        if (check()) {
            res = Math.min(res, depth);

            for (int[] ints : arr) {
                for (int anInt : ints) {
                    System.out.print(anInt +" ");
                }
                System.out.println();
            }
            System.out.println("res = " + res);
            System.out.println("======");
        }

        for (int i = 0; i < n - 1; i++) {
            for (int d = 0; d < h; d++) {
                // 연속하거나 서로 인접한 경우 가로선을 추가 불가능
                if (arr[d][i] == 1
                        || (i - 1 >= 0 && arr[d][i-1] == 1)
                        || (i + 1 < n - 1 && arr[d][i+1] == 1)) continue;

                arr[d][i] = 1;
                dfs(depth + 1);
                arr[d][i] = 0;
            }
        }
    }

    public static boolean check() {
        for (int i = 0; i < n; i++) { // i 의 결과가 i가 나와야함.
            int idx = i; // 시작 인덱스
            for(int depth = 0; depth < h; depth++){
                // 현재 idx 이동 방향 찾기
                if(idx == 0){
                    if(arr[depth][idx] == 1) idx++;
                } else if(idx == n-1){
                    if(arr[depth][idx-1] == 1) idx--;
                } else{
                    if(arr[depth][idx] == 1) idx = idx -1;
                    else if(arr[depth][idx+1] == 1) idx = idx+1;
                }
            }
            if(idx != i) return false;
        }

        return true;
    }
}
