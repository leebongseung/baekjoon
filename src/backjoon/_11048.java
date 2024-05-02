package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 2분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - n행 m열 미로에 갇힘.
 * - 미로 왼쪽위는 (1,1)
 * - 미로 오른쪽아래는 (n, m)
 * - 시작점은 (1,1)이고 (n,m)으로 이동하려고 한다
 * - 준규가 (r,c)에 놓여 있으면, (r+1, c), (r, c+1), (r+1, c+1) 이동가능
 * - 즉 아래, 오른쪽, 오른쪽아래대각선 으로 이동가능하다!
 * - 준규는 방을 방문할때마다 사탕을 모두 가져감
 * - (n,m)으로 이동할 때 가져올 수 있는 사탕 개수의 최댓값은?
 */
public class _11048 {
    public static int n;
    public static int m;
    public static int[][] arr;
    public static int[][] res;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        res = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < m; j++) {
                // 입력되는 인덱스는 1부터 시작하기 때문에 - 1 수행
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res[0][0] = arr[0][0];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                for (Direction d : Direction.values()) {
                    int dx = x + d.getX();
                    int dy = y + d.getY();

                    if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;

                    res[dx][dy] = Math.max(res[dx][dy], res[x][y] + arr[dx][dy]);
                }
            }
        }

        System.out.println(res[n - 1][m - 1]);
    }


    enum Direction {
        bottom(1, 0),
        right(0, 1),
        ;
        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
