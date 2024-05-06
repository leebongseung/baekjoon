package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 5분
 * 종료 시간 : 10시 48분
 * <p>
 * 문제분석
 * - n*m 지도 존재
 * - 오른쪽은 동쪽, 위쪽은 북쪽 임.
 * - 주사위의 전개도는 아래와 같음. 지도의 좌표는 r,c 이고
 * - r은 북쪽으로 부터 떨어진 칸의 개수
 * - c는 서쪽으로 부터 떨어진 칸의 개수
 * <p>
 * - 주사위는 지도 위에 윗면이 1이고, 동쪽바라보는 방향이 3인 상태로 놓임.
 * - 놓여있는 좌표는 x,y 이다. 주사위 모든면에는 0으로 적혀짐
 * <p>
 * - 지도의 각칸에는 정수 하나씩
 * - 이동할 칸에 쓰여있는수가 0이면 주사위의 바닥면에 수로 복사됨.
 * - 0이 아닌경우, 칸에 쓰여있는 수가 주사위의 바닥면으로 복사됨, 칸은 0이 됨.
 * <p>
 * - 주사위를 높은 곳의 좌표와 이동시키는 명령이 주어짐
 * - 주사위가 이동할때 마다 상단에 쓰여있는 값을 구하시오
 * - 주사위는 지도 밖으로 이동 불가능, 만약에 이동 시키려고 하는 경우 해당 명령을 무시하며, 출력도 하면 안됨.
 */
public class _14499 {
    public static int[] dice = new int[6];
    public static int n;
    public static int m;
    public static int x;
    public static int y;
    public static int k;
    // 동, 서, 남, 북
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};

    public static int[][] arr;
    public static StringJoiner sj = new StringJoiner("\n");

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < k; i++) {
            move(Integer.parseInt(st.nextToken()) - 1);
        }

        System.out.println(sj);
    }

    private static void move(int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        // 범위를 벗어난 경우 스킵
        if (nx < 0 || ny < 0 || nx >= n || ny >= m) return;

        int tmp = dice[5];
        switch (d) {
            // 동
            case 0:
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = tmp;
                break;
            // 서
            case 1:
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmp;
                break;
            // 남
            case 2:
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = tmp;
                break;

            // 북
            default:
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmp;
        }

        sj.add(String.valueOf(dice[0]));

        // 좌표 업데이트
        x = nx;
        y = ny;

        // 0인경우 주사위 바닥 -> 맵
        if (arr[x][y] == 0) {
            arr[x][y] = dice[5];
        }

        // map이 0이 아닌 경우 맵 -> 주사위 복사, 맵은 0으로 된다.
        else {
            dice[5] = arr[x][y];
            arr[x][y] = 0;
        }
    }
}
