package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 시작 시간 : 12시 55분
 * 종료 시간 : 1시 34분
 * <p>
 * 1. 문제 분석
 * - n*m 체스판 존재
 * - Q, K, P 이 위치에 주어짐.
 * - Q: 가로, 세로, 대각선을 최대치 까지 이동가능,
 * - 단 중간에 장애물 있다면 이동 못함
 * - K : 2*3 직사각형을 그렸을때 반대쪽 꼭짓점으로 이동 가능
 * - 장애물 있더라도 이동 가능,
 * - P : 장애물 역할
 */
public class _1986 {
    enum KDirection {
        firstTopLeft(-2, -1),
        secondFromTopLeft(-1, -2),
        firstTopRight(-2, 1),
        secondFromTopRight(-1, 2),
        firstOneOnTheBottomLeft(2, -1),
        secondFromBottomLeft(1, -2),
        firstOneBelowRight(2, 1),
        secondFromBottomRight(1, 2);;

        private final int x;
        private final int y;

        KDirection(int x, int y) {
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


    enum QDirection {
        top(-1, 0),
        topLeft(-1, -1),
        topRight(-1, 1),
        left(0, -1),
        right(0, 1),
        bottomLeft(1, -1),
        bottomRight(1, 1),
        bottom(1, 0);

        private final int x;
        private final int y;

        QDirection(int x, int y) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // n*m 배열만들기
        int[][] arr = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        int res = n*m;

        // q 좌표저장하기 q일경우 1이라고 지정하기!
        st = new StringTokenizer(bf.readLine());
        int qCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < qCnt; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            visited[x][y] = true;
            res--;
            arr[x][y] = 1;
        }

        // k 좌표 저장하기 - 2라고 저장
        st = new StringTokenizer(bf.readLine());
        int kCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < kCnt; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            visited[x][y] = true;
            res--;
            arr[x][y] = 2;
        }
        // p 좌표 저장하기 - -1라고 저장
        st = new StringTokenizer(bf.readLine());
        int pCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < pCnt; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            visited[x][y] = true;
            res--;
            arr[x][y] = -1;
        }

        // 안전한 칸의 개수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    for (QDirection qDirection : QDirection.values()) {
                        int dx = i;
                        int dy = j;
                        while(true) {
                            dx += qDirection.getX();
                            dy += qDirection.getY();

                            if (dx < 0 || dx >= n || dy < 0 || dy >= m) break;

                            // 벽을 만날 경우
                            if (arr[dx][dy] == -1 || arr[dx][dy] == 2) break;
                            if (!visited[dx][dy]) {
                                visited[dx][dy] = true;
                                res--;
                            }
                        }
                    }
                } else if (arr[i][j] == 2) {
                    for (KDirection kDirection : KDirection.values()) {
                        int dx = i + kDirection.getX();
                        int dy = j + kDirection.getY();

                        if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;

                        // 벽을 만날 경우
                        if (arr[dx][dy] == -1) continue;
                        if (!visited[dx][dy]) {
                            visited[dx][dy] = true;
                            res--;
                        }
                    }
                }
            }
        }

//        for (boolean[] booleans : visited) {
//            for (boolean aBoolean : booleans) {
//                System.out.print(aBoolean + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("=====");
        System.out.println(res);
    }
}
