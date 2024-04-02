package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 1시 15분
 * 종료 시간 : 4시 34분
 * <p>
 * 1. 문제 분석
 * - 건물에 불이 났고 상근이는 출구로 감.
 * - 매 초 마다 불은 동,서,남,북 퍼짐
 * - 벽에는 불이 안붙음
 * - 상근이는 1칸 이동가능하고, 1초 걸림
 * - 상근이는 벽 통과 불가능, 불이 옮겨진 칸 또는 불이 붙으려는 칸에는 이동 못함
 * - 상근이가 있는칸에 불이 옮겨옴과 동시에 다른칸으로 이동 가능
 * <p>
 * '.' : 빈공간
 * '#' : 벽
 * '@' : 시작위치
 * '*' : 불
 * <p>
 * 빌딩 탈출 못할 경우 IMPOSSIBLE을 외치자
 */
public class _5427 {

    public static char[][] arr;

    enum direction {
        top(-1, 0),
        bottom(1, 0),
        left(0, -1),
        right(0, 1);
        private int x;
        private int y;

        direction(int x, int y) {
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

    public static int bfs(int x, int y) {
        int xSize = arr.length;
        int ySize = arr[0].length;

        Queue<int[]> fireQ = new LinkedList<>();
        Queue<int[]> sanggeunQ = new LinkedList<>();

        // 상근이 위치 조정
        sanggeunQ.add(new int[]{x, y, 0});

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                // * 확산 하기!
                if (arr[i][j] == '*') {
                    fireQ.add(new int[]{i, j});
                }
            }
        }

        while (!sanggeunQ.isEmpty()) {
            int fireSize = fireQ.size();
            for (int i = 0; i < fireSize; i++) {
                int[] poll = fireQ.poll();
                for (direction d : direction.values()) {
                    int dx = poll[0] + d.getX();
                    int dy = poll[1] + d.getY();

                    if (dx < 0 || dx >= xSize || dy < 0 || dy >= ySize) continue;

                    if (arr[dx][dy] != '#' && arr[dx][dy] != '*') {
                        fireQ.add(new int[]{dx, dy});
                        arr[dx][dy] = '*';
                    }
                }
            }

            int sanggeunSize = sanggeunQ.size();
            for (int i = 0; i < sanggeunSize; i++) {
                int[] poll = sanggeunQ.poll();

                for (direction d : direction.values()) {
                    int dx = poll[0] + d.getX();
                    int dy = poll[1] + d.getY();

                    if (dx < 0 || dx >= xSize || dy < 0 || dy >= ySize) {
                        return poll[2] + 1;
                    }

                    if (arr[dx][dy] == '.') {
                        sanggeunQ.add(new int[]{dx, dy, poll[2] + 1});
                        arr[dx][dy] = (char) (arr[poll[0]][poll[1]] + 1);
                    }
                }

            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());


        for (int tCase = 0; tCase < t; tCase++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr = new char[x][y];
            int currentX = 0;
            int currentY = 0;
            for (int i = 0; i < x; i++) {
                String str = bf.readLine();
                for (int j = 0; j < y; j++) {
                    arr[i][j] = str.charAt(j);
                    if (arr[i][j] == '@') {
                        currentX = i;
                        currentY = j;
                    }
                }
            }

            int res = bfs(currentX, currentY);
            if (res == 0) {
                bw.write("IMPOSSIBLE\n");
            } else {
                bw.write(res + "\n");
            }
        }
        bw.flush();
    }
}
