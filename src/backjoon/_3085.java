package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 1시 26분
 * 종료 시간 : 1시 57분
 * <p>
 * 문제분석
 * - n*n 배열에 사탕을 채워넣음
 * - 사탕 색은 모두 같지 않음
 * - 1. 사탕 색이 다른 인접한 2칸을 고름
 * - 2. 사탕을 서로 교환 함.
 * - 3. 모두 같은 색으로 이루어진 가장 긴 연속 부분(행 또는 열 )을 고른 다음 먹음
 * <p>
 * 사탕이 채워진 상태가 주어질때 상근이가 먹을 수 있는 사탕의 최대 개수를 구해라
 * <p>
 * 시간은 1초고 n이 50이라서 그냥 완탐해도 될듯
 */
public class _3085 {
    enum Direction {
        TOP(-1, 0),
        LEFT(0, -1),
        BOTTOM(1, 0),
        RIGHT(0, 1);
        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int maxVal = 0;
    public static int n;
    public static String[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new String[n][n];

        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.substring(j, j + 1);
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (Direction d : Direction.values()) {
                    // x와 y와 교환할 좌표 값
                    int dx = x + d.x;
                    int dy = y + d.y;

                    if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

                    switchIdx(x, y, dx, dy);
                    findSameRowLength(x, y);
                    findSameColumnLength(x, y);
                    switchIdx(x, y, dx, dy);
//                    System.out.print("x = " + x);
//                    System.out.print(", y = " + y);
//                    System.out.println(", maxVal = " + maxVal);
                }
            }
        }
        System.out.println(maxVal);
    }

    private static void switchIdx(int x, int y, int dx, int dy) {
        String tmp = arr[x][y];
        arr[x][y] = arr[dx][dy];
        arr[dx][dy] = tmp;
    }

    private static void findSameColumnLength(int x, int y) {
        int length = 1;

        int zy = y - 1;
        while (true) {
            if (zy < 0 || !arr[x][y].equals(arr[x][zy--])) break;
            length++;
        }

        // Bottom 탐색
        zy = y + 1;
        while (true) {
            if (zy >= n || !arr[x][y].equals(arr[x][zy++])) break;
            length++;
        }

        maxVal = Math.max(maxVal, length);
    }

    private static void findSameRowLength(int x, int y) {
        // 행 탐색하기
        int length = 1;

        // top 탐색
        int zx = x - 1;
        while (true) {
            if (zx < 0 || !arr[x][y].equals(arr[zx--][y])) break;
            length++;
        }

        // Bottom 탐색
        zx = x + 1;
        while (true) {
            if (zx >= n || !arr[x][y].equals(arr[zx++][y])) break;
            length++;
        }

        maxVal = Math.max(maxVal, length);
    }
}
