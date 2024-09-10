package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 4시
 * 종료 시간 : 5시 56분
 *
 * 문제분석
 * - 크기가 n*m인 지도가 존재한다.
 * - 지도에 주사위가 1개 놓여있음.
 * - 지도의 좌표는 (r, c)로 나타냄. r은 위부터 몇행인지, c는 왼쪽부터 몇행인지
 * - (1) 3, 1이 젤위에 보이게 (x, y) 좌표에 놓여있음.
 *
 * - 주사위가 가장 처음 모든면에 0이 적혀있다.
 * - 지도의 각 칸에 정수가 하나씩 쓰여있음.
 * - 지도 칸이 0일 경우, 주사위 바닥면에 있는 수가 지도에 복사됨
 * - 0이 아닌 경우 칸에 있는 수가 주사위 바닥면으로 복사되고, 지도의 그 칸은 0이 됨
 *
 * 주사위가 이동할때마다 상단에 쓰여있는 값을 구하는 프로그램을 만들어라
 * 주사위는 지도의 바깥으로 이동 불가능, 만약 바깥으로 이동하는 명령은 무시하며, 출력도 하면 안됨.
 */
public class _14499 {
    public static int[][] map;
    public static int[] dice = new int[7];
    public static int[] posDice = {4, 1, 3};
    public static int n, m, x, y;

    enum Direction{
        RIGHT(0, 1),
        LEFT(0, -1),
        TOP(-1, 0),
        BOTTOM(1, 0)
        ;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x, y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for(int i=0; i<7; i++) dice[i] = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++){
            int d = Integer.parseInt(st.nextToken()) - 1;
            boolean isMove = moveDice(d);
            if(!isMove) continue;
            copyDiceToMap();
            printResult();
        }
    }

    private static void printResult() {
/*        for (int i : posDice) {
            System.out.print(i +" ");
        }
        System.out.println();*/
        System.out.println(dice[posDice[1]]);
    }

    private static void copyDiceToMap() {
        if(map[x][y] == 0){
            // 지도 칸이 0일 경우, 주사위 바닥면에 있는 수가 지도에 복사됨
            map[x][y] = dice[bottomDiceValue(posDice[1])];
            return;
        }

        // 0이 아닌 경우 칸에 있는 수가 주사위 바닥면으로 복사되고
        dice[bottomDiceValue(posDice[1])] = map[x][y];
        // 지도의 그 칸은 0이 됨
        map[x][y] = 0;
    }
    private static int bottomDiceValue(int value){
        int res = 0;
        switch (value){
            case 1:
                res = 6;
                break;
            case 2:
                res = 5;
                break;
            case 3:
                res = 4;
                break;
            case 4:
                res = 3;
                break;
            case 5:
                res = 2;
                break;
            case 6:
                res = 1;
                break;
        }
        return res;
    }

    private static boolean moveDice(int dIdx) {
        Direction d = Direction.values()[dIdx];
        int dx = x + d.x;
        int dy = y + d.y;
        if(dx < 0 || dx >= n || dy < 0 || dy >= m)
            return false;

        x = dx;
        y = dy;
        switch (dIdx){
            case 0: //  RIGHT(0, 1)
                moveRight();
                break;
            case 1: //  LEFT(0, -1)
                moveLeft();
                break;
            case 2: //  TOP(-1, 0)
                moveTop();
                break;
            case 3: //  BOTTOM(1, 0)
                moveBottom();
                break;
        }
        return true;
    }

    private static void moveBottom() {
        int mid = posDice[1];
        int right = posDice[2];

        switch (mid){
            case 1:
                if(right == 2) mid = 4;
                else if(right == 3) mid = 2;
                else if(right == 4) mid = 5;
                else if(right == 5) mid = 3;
                break;
            case 2:
                if(right == 1) mid = 3;
                else if(right == 3) mid = 6;
                else if(right == 4) mid = 1;
                else if(right == 6) mid = 4;
                break;
            case 3:
                if(right == 1) mid = 5;
                else if(right == 2) mid = 1;
                else if(right == 5) mid = 6;
                else if(right == 6) mid = 2;
                break;
            case 4:
                if(right == 1) mid = 2;
                else if(right == 2) mid = 6;
                else if(right == 5) mid = 1;
                else if(right == 6) mid = 5;
                break;
            case 5:
                if(right == 1) mid = 4;
                else if(right == 3) mid = 1;
                else if(right == 4) mid = 6;
                else if(right == 6) mid = 3;
                break;
            case 6:
                if(right == 2) mid = 3;
                else if(right == 3) mid = 5;
                else if(right == 4) mid = 2;
                else if(right == 5) mid = 4;
                break;

        }

        posDice[1] = mid;
    }

    private static void moveTop() {
        int mid = posDice[1];
        int right = posDice[2];

        switch (mid){
            case 1:
                if(right == 2) mid = 3;
                else if(right == 3) mid = 5;
                else if(right == 4) mid = 2;
                else if(right == 5) mid = 4;
                break;
            case 2:
                if(right == 1) mid = 4;
                else if(right == 3) mid = 1;
                else if(right == 4) mid = 6;
                else if(right == 6) mid = 3;
                break;
            case 3:
                if(right == 1) mid = 2;
                else if(right == 2) mid = 6;
                else if(right == 5) mid = 1;
                else if(right == 6) mid = 5;
                break;
            case 4:
                if(right == 1) mid = 5;
                else if(right == 2) mid = 1;
                else if(right == 5) mid = 6;
                else if(right == 6) mid = 2;
                break;
            case 5:
                if(right == 1) mid = 3;
                else if(right == 3) mid = 6;
                else if(right == 4) mid = 1;
                else if(right == 6) mid = 4;
                break;
            case 6:
                if(right == 2) mid = 4;
                else if(right == 3) mid = 2;
                else if(right == 4) mid = 5;
                else if(right == 5) mid = 3;
                break;
        }

        posDice[1] = mid;
    }

    private static void moveLeft() {
        int mid = posDice[1];
        int right = posDice[2];

        posDice[0] = mid;
        posDice[1] = right;
        posDice[2] = bottomDiceValue(mid);
    }

    private static void moveRight() {
        int left = posDice[0];
        int mid = posDice[1];

        posDice[0] = bottomDiceValue(mid);
        posDice[1] = left;
        posDice[2] = mid;
    }
}
