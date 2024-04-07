package backjoon;

import javax.swing.plaf.basic.BasicToggleButtonUI;
import java.io.*;
import java.rmi.ConnectIOException;
import java.util.*;

/**
 * 문제 분석
 * - 직사각형 보드
 * - 구슬 탈출 빨간 구슬 하나, 파란구슬 하나 있음
 * - 빨간 구슬을 통해 내빼는 게임
 * <p>
 * - 보드에는 구멍이 하나 있다.
 * - 파란구슬이 구멍에 들어가면 안됨
 * <p>
 * - 구슬은 상 하 좌 우 굴려야함
 * - 왼쪽, 오른쪽, 위쪽, 아래쪽으로 굴리기 가능
 * <p>
 * - 공은 동시에 움직인다.
 * - 파란구슬이 구멍에 빠지거나, 동시에 구멍에 빠지면 실패
 * - 동시에 같은칸에 있을 수 없음
 * -
 * <p>
 * bfs 이고 공 B 또는 R이 0과 만나면 그 즉시 게임종료
 * 움직이는 순서는??
 * top 일경우 x좌표 비교해서 작은거 부터
 * right일 경우 x 좌표 큰 것 부터
 * left일 경우 y 좌표 작은 것 부터
 * right일 경우 y 좌표 큰 것 부터
 */
public class _13460 {

    public static int n;
    public static int m;
    public static String[][] arr;
    public static int min = Integer.MAX_VALUE;

    enum Direction {
        top(-1, 0), bottom(1, 0), left(0, -1), right(0, 1);

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    public static int bfs(int[] Bpos, int[] Rpos) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, Bpos[0], Bpos[1], Rpos[0], Rpos[1]});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int depth = poll[0];
            int B_x = poll[1];
            int B_y = poll[2];
            int R_x = poll[3];
            int R_y = poll[4];

            if (depth == 10) continue;

            for (Direction d : Direction.values()) {
                Queue<int[]> q2 = new LinkedList<>();
                if (d.name().equals("top")) {
                    // x 가 작은 것 부터 실행
                    if (B_x < R_x) {
                        q2.add(new int[]{B_x, B_y, 0});
                        q2.add(new int[]{R_x, R_y, 1});
                    } else {
                        q2.add(new int[]{R_x, R_y, 1});
                        q2.add(new int[]{B_x, B_y, 0});
                    }
                } else if (d.name().equals("bottom")) {
                    // x 가 큰 것 부터 실행
                    if (B_x > R_x) {
                        q2.add(new int[]{B_x, B_y, 0});
                        q2.add(new int[]{R_x, R_y, 1});
                    } else {
                        q2.add(new int[]{R_x, R_y, 1});
                        q2.add(new int[]{B_x, B_y, 0});
                    }
                } else if (d.name().equals("left")) {
                    // y가 작은 것 부터 실행
                    if (B_y < R_y) {
                        q2.add(new int[]{B_x, B_y, 0});
                        q2.add(new int[]{R_x, R_y, 1});
                    } else {
                        q2.add(new int[]{R_x, R_y, 1});
                        q2.add(new int[]{B_x, B_y, 0});
                    }
                } else if (d.name().equals("right")) {
                    // y가 작은 것 부터 실행
                    if (B_y > R_y) {
                        q2.add(new int[]{B_x, B_y, 0});
                        q2.add(new int[]{R_x, R_y, 1});
                    } else {
                        q2.add(new int[]{R_x, R_y, 1});
                        q2.add(new int[]{B_x, B_y, 0});
                    }
                }

                int[] BTmpPos = new int[2];
                int[] RTmpPos = new int[2];

                // 공을 굴려보장!
                while (!q2.isEmpty()) {
                    int[] poll1 = q2.poll();
                    int x = poll1[0];
                    int y = poll1[1];
                    int ballColor = poll1[2];
                    int dx = x;
                    int dy = y;
                    while (true) {
                        dx += d.getX();
                        dy += d.getY();
                        int qx = dx;
                        int qy = dy;

                        if (dx < 0 || dx >= n || dy < 0 || dy >= m) break;

                        if (arr[dx][dy].equals("#") || (BTmpPos != null && dx == BTmpPos[0] && dy == BTmpPos[1]) || (RTmpPos != null && dx == RTmpPos[0] && dy == RTmpPos[1])) {
                            if (d.name().equals("top")) {
                                qx = dx + 1;
                            } else if (d.name().equals("bottom")) {
                                qx = dx - 1;
                            } else if (d.name().equals("left")) {
                                qy = dy + 1;
                            } else if (d.name().equals("right")) {
                                qy = dy - 1;
                            }

                            if (ballColor == 1) {
                                RTmpPos = new int[]{qx, qy};
                            } else {
                                BTmpPos = new int[]{qx, qy};
                            }
                        } else if (arr[dx][dy].equals("O")) {
                            if (ballColor == 1) {
                                RTmpPos = null;
                            } else {
                                BTmpPos = null;
                            }
                            break;
                        }
                    }
                } // while 루프 끝


                // 두개 다 널일경우 실패!
                // 두개 다 널이 아닐경우 계쏙 굴려보자!
                if (BTmpPos == null) {
                    continue;
                }

                // 만약에 한개가 널일경우 성공적이므로 depth 반환
                if (RTmpPos == null) {
                    return depth;
                }

                q.add(new int[]{depth + 1, BTmpPos[0], BTmpPos[1], RTmpPos[0], RTmpPos[1]});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new String[n][m];


        int[] Rpos = new int[2];
        int[] Bpos = new int[2];
        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            for (int j = 0; j < m; j++) {
                if (String.valueOf(str.charAt(j)).equals("B")) {
                    Bpos = new int[]{i, j};
                    arr[i][j] = ".";
                } else if (String.valueOf(str.charAt(j)).equals("R")) {
                    Rpos = new int[]{i, j};
                    arr[i][j] = ".";
                } else {
                    arr[i][j] = String.valueOf(str.charAt(j));
                }
            }
        }

        System.out.println(bfs(Bpos, Rpos));
    }
}
