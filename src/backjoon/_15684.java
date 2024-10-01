package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제분석
 * - n개의 세로선, m개의 가로선 존재.
 * - 인접한 세로선 사이엔 가로선을 둘 수 있음,
 * - 총 놓을 수 있는 가로선의 개수는 H
 * - 가로선 : 두 인접한 세로선을 연결해야함.
 * - 단, 두 가로선이 연속하거나 서로 접하면안됨.
 * - 가로선은 점선 위에 있어야함.
 * <p>
 * - 사다리에 가로선을 추가해서 사다리 게임의 결과를 조작하려고 함. I번 세로선의 결과가 I번이 나와야한다.
 * - 추가해야하는 가로선 개수의 최솟값을 구하는 프로그램 만들기
 * <p>
 * 입력
 * - N : 세로선의 개수
 * - M : 존재하는 가로선 개수
 * - H : 가로선의 개수
 */
public class _15684 {
    public static int row, col, m;
    public static int res = Integer.MAX_VALUE;
    public static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        arr = new boolean[row][col - 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int curRow = Integer.parseInt(st.nextToken()) - 1;
            int curCol = Integer.parseInt(st.nextToken()) - 1;

            if (curRow >= row || curCol >= col - 1) {
                System.out.println(-1);
                return;
            }
            arr[curRow][curCol] = true;
        }

        // 백트래킹으로 가능한 모든 경우의 수 다 해보기
        dfs(-1,-1,0);
        if(res > 3) System.out.println(-1);
        else System.out.println(res);

    }

    private static void dfs(int x, int y, int depth) {

        if(depth > 3)
            return ;
        // 탈출 조건
        // j -> j 로 사다리를 탈 수 있는지 체크하기
//        printArr();
        boolean isOk = true;
        for (int j = 0; j < col; j++) {
            int curPos = j;
            for (int i = 0; i < row; i++) {
                // 왼쪽을 볼 수 있는 경우
                if (curPos != 0 && arr[i][curPos - 1]) {
                    curPos = curPos - 1;
                    continue;
                }

                // 오른쪽을 볼 수 있는 경우
                if (curPos != col - 1 && arr[i][curPos]) {
                    curPos = curPos + 1;
                    continue;
                }
            }
            if (j != curPos) {
                isOk = false;
                break;
            }
        }
        if (isOk) {
            res = Math.min(res, depth);
            return;
        }

        // 반복 조건
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 1; j++) {
                if (arr[i][j] || i < x || (i == x && j <= y)) {
                    continue;
                }

                // 좌랑 우 탐색했을 때 하나라도 가로선이 있을 경우 탈출.
                isOk = true;
                for (Direction d : Direction.values()) {
                    int dy = j + d.y;

                    if (dy < 0 || dy >= col - 1) continue;
                    if (arr[i][dy]) {
                        isOk = false;
                        break;
                    }
                }

                if (!isOk) continue;

                arr[i][j] = true;
                dfs(i, j, depth + 1);
                arr[i][j] = false;
            }
        }
    }

    enum Direction {
        LEFT(0, -1),
        RIGHT(0, 1);
        final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void printArr(){
        for (boolean[] booleans : arr) {
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("===");
    }
}
