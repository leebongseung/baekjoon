package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1245 {
    static int n, m, ans;
    static int[][] map;
    static boolean[][] topMap;

    enum Direction {
        LEFT_TOP(-1, -1),
        TOP(-1, 0),
        RIGHT_TOP(-1, 1),
        LEFT(0, -1),
        RIGHT(0, 1),
        LEFT_BOTTOM(1, -1),
        BOTTOM(1, 0),
        RIGHT_BOTTOM(1, 1);

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x, y;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        topMap = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 탐색한적 없는 봉우리 거나, map이 0이 아닐경우
                if (map[i][j] != 0 && !topMap[i][j])
                    bfs(i, j);
            }
        }
        System.out.println(ans);

    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> top = new ArrayList<>();

        boolean [][]visited = new boolean[n][m];
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (Direction d : Direction.values()) {
                int dx = cur[0] + d.x;
                int dy = cur[1] + d.y;

                if (dx < 0 || dy < 0 || dx >= n || dy >= m || visited[dx][dy]) continue;
                //현재보다 더 높은 지점 있으면 return
                if (map[dx][dy] > map[cur[0]][cur[1]])
                    return;
                // 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합
                else if (map[dx][dy] == map[cur[0]][cur[1]]) {
                    q.offer(new int[]{dx, dy});
                    top.add(new int[]{dx, dy});
                }
                visited[dx][dy] = true;
            }
        }

        for (int[] cur : top) {
            topMap[cur[0]][cur[1]] = true;
        }
        ans++;
    }
}

