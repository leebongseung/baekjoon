package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 3시 12분
 * 종료 시간 : ??
 * <p>
 * 문제 분석
 * - 미키의 뒷마당에는 특정 수의 양이 있음.
 * - 배고픈 늑대는 마당에 들어와 양을 공격
 * <p>
 * - 행과열로 이루어진 직사각형 모양
 * - . : 빈 필드
 * - # : 울타리
 * - o : 양
 * - v : 늑대
 * <p>
 * - 상,하,좌,우로 이동 가능
 * - 울타리를 지나지않고 다른 칸으로 이동할 수 있다면?
 * - 두 칸은 같은 영역안에 속해있다고 함.
 * <p>
 * - 마당에서 탈출 할 수 있는 칸은 어떤 영역에도 안속함.
 * - 양은 늑대에게 싸움을 걸 수 있고
 * - 영역 안의 양의 수가 늑대의 수 보다 많으면 이김
 * - 늑대를 우리에서 쫓아냄,
 * - 그렇지 않다면, 늑대가 그 지역의 양을 다 먹음.
 * - 아침이 도달 했을 때, 살아남은 양과 늑대의 수를 출력하라
 */
public class _3184 {
    public static int r; // 행의 수
    public static int c; // 열의 수
    public static String[][] arr;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new String[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = bf.readLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = str.substring(j, j + 1);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && !arr[i][j].equals("#")) bfs(i, j);
            }
        }

        int wolfCnt = 0;
        int yangCnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j].equals("v")) wolfCnt++;
                else if (arr[i][j].equals("o")) yangCnt++;
            }
        }

        System.out.println(yangCnt + " " + wolfCnt);
    }

    public enum Direction {
        top(-1, 0), bottom(1, 0), left(0, -1), right(0, 1);

        private int x;
        private int y;

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

    public static void bfs(int i, int j) {

        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> wolfQ = new LinkedList<>();
        Queue<int[]> yangQ = new LinkedList<>();
        visited[i][j] = true;

        q.add(new int[]{i, j});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            if (arr[x][y].equals("v")) {
                // 늑대일 경우
                wolfQ.add(new int[]{x, y});
            } else if (arr[x][y].equals("o")) {
                // 양일 경우
                yangQ.add(new int[]{x, y});
            }
            for (Direction d : Direction.values()) {
                int dx = x + d.getX();
                int dy = y + d.getY();

                if (dx < 0 || dx >= r || dy < 0 || dy >= c) continue;

                if (!visited[dx][dy] && !arr[dx][dy].equals("#")) {
                    q.add(new int[]{dx, dy});
                    visited[dx][dy] = true;
                }
            }
        }

        if (yangQ.isEmpty() || wolfQ.isEmpty()) return;

        // 만약에 양이 많다면, 늑대 죽이기
        if (yangQ.size() > wolfQ.size()) {
            while (!wolfQ.isEmpty()) {
                int[] poll = wolfQ.poll();
                int x = poll[0];
                int y = poll[1];

                arr[x][y] = ".";
            }
        } else { // 아니라면 양죽이기
            while (!yangQ.isEmpty()) {
                int[] poll = yangQ.poll();
                int x = poll[0];
                int y = poll[1];

                arr[x][y] = ".";
            }
        }
    }
}
