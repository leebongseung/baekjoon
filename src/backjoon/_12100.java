package backjoon;

import java.io.*;
import java.util.*;

/**
 * 문제분석
 * - 같은 값 두 블록이 충돌하면 하나로 합쳐짐
 * - 한번 이동에서 이미 합쳐진 블록은 다시 합쳐지지 않음
 * - 예를들어 2 2 2 2 인 상태에서 왼쪽으로 이동하면 4 4 라는 의미
 * - 똑같은 수가 3개 있는 2 2 2 인 경우 이동하게 되면 4 2 로 위쪽부터 합쳐짐
 * <p>
 * - 최대 5번 이동해서 가장 큰 블록의 값을 구하는 프로그램 만들기!
 */
public class _12100 {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int n;
    public static int max = Integer.MIN_VALUE;

    enum Direction {
        top(0, -1),
        bottom(n - 1, -1),
        left(-1, 0),
        right(-1, n - 1);

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;

        public int getX(int[][] arr, int x, int y) {
            if (this.x == -1) return -1;

            if (this.x == 0) {
                int dx = x-1;
                while (dx >= 0) {
                    // 방문안했는데 숫자가 같은 경우 또는 0 인 경우
                    if (visited[dx][y] || (arr[dx][y] != arr[x][y] && arr[dx][y] != 0)) return dx + 1;
                    dx--;
                }
            } else {
                int dx = x+1;
                while (dx < n) {
                    // 방문 했을 경우
                    if (visited[dx][y] || (arr[dx][y] != arr[x][y] && arr[dx][y] != 0)) return dx - 1;
                    dx++;
                }
            }

            return this.x;
        }

        public int getY(int[][] arr, int x, int y) {
            if (this.y == -1) return -1;

            if (this.y == 0) {
                int dy = y-1;
                while (dy >= 0) {
                    // 방문 했을 경우
                    if (visited[x][dy] || (arr[x][dy] != arr[x][y] && arr[x][dy] != 0)) return dy+1;
                    dy--;
                }
            } else {
                int dy = y+1;
                while (dy < n) {
                    // 방문 했을 경우
                    if (visited[x][dy] || (arr[x][dy] != arr[x][y] && arr[x][dy] != 0)) return dy - 1;
                    dy++;
                }
            }

            return this.y;
        }
    }

    public static void method(int[][] tmp, int i, int j, int x, int y){
        if (x != -1) {
            if (tmp[x][j] == 0 || tmp[x][j] == tmp[i][j]) {

                // 새롭게 더 할 경우
                if (tmp[x][j] != 0) visited[x][j] = true;
                tmp[x][j] += tmp[i][j];
                tmp[i][j] = 0;

                max = Math.max(tmp[x][j], max);
            }
        } else if (y != -1) {
            // 같을 경우
            if (tmp[i][y] == 0 || tmp[i][y] == tmp[i][j]) {

                // 새롭게 더 할 경우
                if (tmp[i][y] != 0) visited[i][y] = true;
                tmp[i][y] += tmp[i][j];
                tmp[i][j] = 0;

                max = Math.max(tmp[i][y], max);
            }
        }
    }
    public static void dfs(int[][] arr, int depth) {
        if (depth == 5) return;
        // for 문 돌아가면서 상 하 좌 우 계산 하기!
        for (Direction d : Direction.values()) {
            int[][] tmp = new int[n][n];
            visited = new boolean[n][n];
            for(int i =0; i < n; i++){
                for(int j =0; j<n; j++){
                    tmp[i][j] = arr[i][j];
                }
            }

            if (d.name().equals("top") || d.name().equals("left")) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int x = d.getX(tmp, i, j);
                        int y = d.getY(tmp, i, j);
                        if (x == i) continue;
                        if (y == j) continue;

                        method(tmp, i, j, x, y);
                    }
                }
            } else{ // bottom, right 일 경우
                for (int i = n-1; i >= 0; i--) {
                    for (int j = n-1; j >=0 ; j--) {
                        int x = d.getX(tmp, i, j);
                        int y = d.getY(tmp, i, j);

                        if (x == i) continue;
                        if (y == j) continue;

                        method(tmp, i, j, x, y);
                    }
                }
            }
//            for (int[] ints : tmp) {
//                for (int anInt : ints) {
//                    System.out.print(anInt + " ");
//                }
//                System.out.println();
//            }
//            System.out.println(depth + 1  + ", " + d.name() + "===============");

            dfs(tmp, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(arr[i][j], max);
            }
        }
        dfs(arr, 0);
        System.out.println(max);

    }
}
