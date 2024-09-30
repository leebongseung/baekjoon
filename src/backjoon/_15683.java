package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 5시 30분
 * 종료 시간 : ?
 * <p>
 * 문제분석
 * - n * m 크기의 직사각형인 사무실이 존재
 * - 사무실에는 총 k 개의 CCTV가 설치되어있음.
 * - CCTV는 5가지 종류 존재
 * 1. 오른쪽 방향만 감시
 * 2. 왼쪽, 오른쪽 방향 감시
 * 3. 위쪽, 오른쪽 방향 감시
 * 4. 왼쪽, 위쪽, 오른쪽 방향 감시
 * 5. 상,하,좌,우 다 감시
 * <p>
 * - CCTV는 벽을 통과하여 감시 못함.
 * - CCTV는 90도 방향으로 회전 가능.
 * - 감시하려고 하는 방향이 가로 또는 세로 방향이어야함.
 * - 사각지대를 최소화 하여 크기를 구하는 프로그램 작성하기
 * <p>
 * - 결국 회전 방향 고려해서 백트래킹 구현하는 문제.
 */
public class _15683 {
    public static int n, m;
    public static int res, size;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num >= 1 && num <= 5) size++;
                arr[i][j] = num;
            }
        }

        res = Integer.MAX_VALUE;
        // 초기값 조건이 잘못되었음. -1로 해야 오류 없다.
        dfs(-1, -1, 0,arr, new boolean[n][m]);
        System.out.print(res);
    }

    private static void dfs(
            int x,
            int y,
            int depth,
            int[][] arr,
            boolean[][] visited
    ) {
        if(depth == size) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(arr[i][j] == 6) continue;
                    if (!visited[i][j]) cnt++;
                }
            }
            res = Math.min(res, cnt);
            return;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i < x) continue;
                if(i == x && j <= y) continue;


                if (arr[i][j] == 0 || arr[i][j] == 6)
                    continue;
                // 현재 위치 방문 처리
                visited[i][j] = true;

                boolean[][] tmp = new boolean[n][m];
                for (int z = 0; z < visited.length; z++) {
                    tmp[z] = visited[z].clone();
                }

                if (arr[i][j] == 1) {
                    // 상, 하 좌 우
                    int dx[] = {-1, 1, 0, 0};
                    int dy[] = {0, 0, -1, 1};
                    for (int k = 0; k < dx.length; k++) {
                        int zx = i;
                        int zy = j;

                        while (true) {
                            zx += dx[k];
                            zy += dy[k];
                            if (zx < 0 || zx >= n || zy < 0 || zy >= m)
                                break;
                            // 벽만날 경우 continue
                            if (arr[zx][zy] == 6)
                                break;
                            visited[zx][zy] = true;
                        }

                        dfs(i, j, depth + 1, arr, visited);
                        for (int z = 0; z < visited.length; z++) {
                            visited[z] = tmp[z].clone();
                        }
                    }
                } else if (arr[i][j] == 2) {
                    // 상, 하 좌 우
                    int dx[] = {-1, 1, 0, 0};
                    int dy[] = {0, 0, -1, 1};
                    for (int k = 0; k < 4; k++) {
                        int zx = i;
                        int zy = j;

                        while (true) {
                            zx += dx[k];
                            zy += dy[k];
                            if (zx < 0 || zx >= n || zy < 0 || zy >= m)
                                break;
                            // 벽만날 경우 continue
                            if (arr[zx][zy] == 6)
                                break;
                            visited[zx][zy] = true;
                        }

                        if (k == 1 || k == 3) {
                            dfs(i, j, depth + 1, arr, visited);
                            for (int z = 0; z < visited.length; z++) {
                                visited[z] = tmp[z].clone();
                            }
                        }
                    }
                } else if (arr[i][j] == 3) {
                    // 상, 하 좌 우
                    int dx[] = {-1, 0, -1, 0, 0, 1, 1, 0};
                    int dy[] = {0, 1, 0, -1, -1, 0, 0, 1};
                    for (int k = 0; k < dx.length; k++) {
                        int zx = i;
                        int zy = j;

                        while (true) {
                            zx += dx[k];
                            zy += dy[k];
                            if (zx < 0 || zx >= n || zy < 0 || zy >= m)
                                break;
                            // 벽만날 경우 continue
                            if (arr[zx][zy] == 6)
                                break;
                            visited[zx][zy] = true;
                        }

                        if (k % 2 == 1) {
                            dfs(i, j, depth + 1,arr, visited);
                            for (int z = 0; z < visited.length; z++) {
                                visited[z] = tmp[z].clone();
                            }
                        }
                    }
                } else if (arr[i][j] == 4) {
                    // 상, 하 좌 우
                    int[] dx = {0, -1, 0, 1, 0, -1, 0, 1, 0, -1, 0, 1};
                    int[] dy = {-1, 0, 1, 0, -1, 0, -1, 0, 1, 0, 1, 0};
                    for (int k = 0; k < dx.length; k++) {
                        int zx = i;
                        int zy = j;

                        while (true) {
                            zx += dx[k];
                            zy += dy[k];
                            if (zx < 0 || zx >= n || zy < 0 || zy >= m)
                                break;
                            // 벽만날 경우 continue
                            if (arr[zx][zy] == 6)
                                break;
                            visited[zx][zy] = true;
                        }

                        if (k % 3 == 2) {
                            dfs(i, j, depth + 1, arr, visited);
                            for (int z = 0; z < visited.length; z++) {
                                visited[z] = tmp[z].clone();
                            }
                        }
                    }
                } else if (arr[i][j] == 5) {
                    // 상, 하 좌 우
                    int dx[] = {-1, 1, 0, 0};
                    int dy[] = {0, 0, -1, 1};
                    for (int k = 0; k < dx.length; k++) {
                        int zx = i;
                        int zy = j;

                        while (true) {
                            zx += dx[k];
                            zy += dy[k];
                            if (zx < 0 || zx >= n || zy < 0 || zy >= m)
                                break;
                            // 벽만날 경우 continue
                            if (arr[zx][zy] == 6)
                                break;
                            visited[zx][zy] = true;
                        }
                    }

                    dfs(i, j, depth + 1, arr, visited);
                    for (int z = 0; z < visited.length; z++) {
                        visited[z] = tmp[z].clone();
                    }
                }

            }
        }
    }
}
