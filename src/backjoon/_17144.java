package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 6시 5분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 공기청정기 R*C 2차원 배열
 * - 각 칸마다 미세먼지 양이 정해짐
 * <p>
 * - 공기청정기는 항상 1번열에 설치되어있음.(Y축)
 * - 크기는 두행을 차지함.(X축 2개 차지)
 * <p>
 * - 공기청정기가 설치되지 않은 칸은 미세먼지 존재;.
 * <p>
 * 1초동안 아래 적힌 일이 순서대로 일어남
 * - 미세먼지 확산, 모든칸에서 동시에 확산됨
 * - 인접한 방향에 공기청정기 존재하거나,
 * 칸이 없다면 그 방향으로 학산되지 않음.
 * - 확산 되는 양은 A/5, 소수점은 버림
 * - 남은 미세먼지 양은 A - A/5 * 확산된 방향개수
 * - 확산 : 상, 하, 좌, 우로 확산됨(최소2~최대4개)
 * <p>
 * <공기청정기 동작>
 * - 바람이 나옴
 * - 위쪽 : 반시계방향 순환
 * - 아래쪽 : 시계방향 순환
 * <p>
 * - 바람이 불면 바람 방향대로 한칸씩 이동
 * - 공기청정기에 도달한 바람은 0으로 정화됨
 */
public class _17144 {
    public static int[][] arr;
    public static int r; // x축 최대 길이
    public static int c; // y축 최대 길이
    public static int t; // t초 후 결과는?

    enum Direction {
        top(-1, 0), bottom(1, 0), left(0, -1), right(0, 1);

        private int x;
        private int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    public static void spreadingAir(int topX, int bottomX) {
        int[][] tmp = new int[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                tmp[i][j] = arr[i][j];
            }
        }

        // 공기 확산하기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int x = i;
                int y = j;
                if (arr[x][y] == 0) continue;

                int spreadSize = (int) Math.floor(tmp[x][y] / 5);
                int cnt = 0; // 확산된 개수
                for (Direction d : Direction.values()) {
                    int dx = x + d.getX();
                    int dy = y + d.getY();

                    if (dx < 0 || dx >= r || dy < 0 || dy >= c) continue;
                    if ((dx == topX || dx == bottomX) && dy == 0) continue;

                    // 현재위치로 확산 시키기
                    arr[dx][dy] += spreadSize;
                    cnt++;
                }

                // 현재 위치 공기 확산한만큼 빼기
                arr[x][y] -= (spreadSize * cnt);
            }
        }
    }

    // 위일 경우 top
    // 아래일 경우 bottom
    public static void AirCleaner(int topX, int bottomX) {

        int[][] tmp = new int[r][c];

        // top 방향일 경우
        for (int i = 0; i <= topX; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 && j != 0) {
                    tmp[i][j - 1] = arr[i][j];
                } else if (i != topX && j == 0) {
                    tmp[i + 1][j] = arr[i][j];
                } else if (i == topX && j != c - 1) {
                    tmp[i][j + 1] = arr[i][j];
                } else if (i != 0 && j == c - 1) {
                    tmp[i - 1][j] = arr[i][j];
                } else {
                    tmp[i][j] = arr[i][j];
                }

            }
        }

        // 바텀 방향일 경우
        for (int i = bottomX; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == bottomX && j != c - 1) {
                    tmp[i][j + 1] = arr[i][j];
                } else if (i != r - 1 && j == c - 1) {
                    tmp[i + 1][j] = arr[i][j];
                } else if (i != bottomX && j == 0) {
                    tmp[i - 1][j] = arr[i][j];
                } else if (i == r - 1 && j != 0) {
                    tmp[i][j - 1] = arr[i][j];
                } else tmp[i][j] = arr[i][j];
            }
        }

        // 공기청정기 위치의 공기를 0으로 만들기
        tmp[topX][0] = 0;
        tmp[bottomX][0] = 0;

        arr = tmp;
    }

    public static int bfs(int topX, int bottomX) {

//        Queue<Air> q = new LinkedList<>();
//        // 모든 먼지 우선 짚어넣기!
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                if (arr[i][j] > 0) q.add(new Air(i, j, arr[i][j]));
//            }
//        }

        for (int repeat = 0; repeat < t; repeat++) {

            spreadingAir(topX, bottomX);

            // 공기 정화하기! 및 q에 짚어넣기!
            AirCleaner(topX, bottomX);

            /*for(int i=0; i<r; i++){
                for(int j=0; j<c; j++){
                    System.out.print(arr[i][j] +" ");
                }
                System.out.println();
            }
            System.out.println("=====================");*/
        }

        // 남은 먼지 총양 세기
        int res = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] > 0) res += arr[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        int[] airCleanerIndex = new int[2];
        int idx = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    airCleanerIndex[idx++] = i;
                    arr[i][j] = 0;
                }
            }
        }

        System.out.println(bfs(airCleanerIndex[0], airCleanerIndex[1]));
    }
}
