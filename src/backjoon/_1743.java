package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 10분
 * 종료 시간 : 10시 33분
 * <p>
 * 문제분석
 * - 제일큰 음식물의 크기 구하기!
 * - bfs 탐색인듯?
 */
public class _1743 {
    public static int n, m, k, res;
    public static int[][] arr;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로길이, 행
        m = Integer.parseInt(st.nextToken()); // 가로길이, 열
        k = Integer.parseInt(st.nextToken()); // 음식물 개수

        arr = new int[n][m]; // 0 : 음식물 없음, 1: 음식물 있음.
        visited = new boolean[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            // 음식물 쓰레기 좌표 (r,c)
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            arr[r][c] = 1;
        }

        res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
//                    System.out.println("탐색시작! (x,y) = ("+ i +"," + j+")");
                    res = Math.max(res, bfs(i, j));
                }
            }
        }

        System.out.print(res);

    }

    private static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        int res = 1;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
//            System.out.println("x = " + x + ", y = " + y + ", res = "+ res);

            for (Direction d : Direction.values()) {
                int dx = d.x + x;
                int dy = d.y + y;
                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;

                // 방문하지 않았거나, 음식물이 있을 경우 bfs 탐색!
                if (!visited[dx][dy] && arr[dx][dy] == 1) {
                    visited[dx][dy] = true;
                    q.add(new int[]{dx, dy});
                    res++;
                }
            }

        }

        return res;
    }

    enum Direction {
        TOP(-1, 0),
        BOTTOM(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);
        final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
