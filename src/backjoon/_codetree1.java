package backjoon;

import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제분석
 * - r,c 행,열이 존재함, 시작점은 (1,1)
 * - k명의 골렘이 마법의 숲을 탐색한다.
 * - findexit : 골렘의 중앙을 제외한 4칸을 출구다.
 * - 정령은 어떤 방향에서든 골렘에 탑승가능,
 * - i번째로 숲을 탐색하는 골렘은 가장 북쪽에서 부터
 * 시작해 골렘의 중아 c열이 되도록하는 위치에서 내려옴
 * - 초기 골렘의 출구는 d방향에 위치함(d가 어딜까?)
 * 골렘의 이동
 * 1. 남쪽으로 한칸 이동
 * 2. 1이 안될경우, 왼쪽 -> 아래 로 이동
 * 3. 2가 안될경우, 오른쪽 -> 아래 로 이동
 *
 * 정령의 이동
 * - 골렘이 더 이상 남쪽으로 이동할 수 없을 경우
 * - 정령은 골렘의 출구를 통해 가장 남쪽칸으로 이ㅗㅇ함.
 * - 골렘의 출구에 인접한 다른 골렘과 인접한다면
 * 다른 골렘으로 이동가능.
 * - 골렘이 숲에 못들어온 상황이라면? 이 경우
 * 정령의 이동을 답에 포함시키지 않는다.
 *
 * */
public class _codetree1 {
    public static int r; // x축
    public static int c; // y축
    public static int k; // 정령의 수
    public static int res; // 결과
    public static List<Gol> lst = new ArrayList<>();

    public enum Direction{
        TOP(-1, 0),
        RIGHT(0, 1),
        BOTTOM(1, 0),
        LEFT(0, -1)
        ;

        Direction(int x, int y){
            this.x = x;
            this.y = y;
        }
        private final int x, y;
    }

    public static class Gol{
        public int x, y;
        public Direction d;

        public Gol(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = Direction.values()[d];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int x = 2; // 기본 인덱스 1로 생각하기!
            int y = Integer.parseInt(st.nextToken());
            // 북, 동, 남, 서 (0~3)
            int d = Integer.parseInt(st.nextToken());

            // 0.lst에 골렘삽입
            // 0.0 정상 삽입하기
            boolean isOk = false;
            Gol gol = null;
            // 골렘이 들어갈 수 없다면? lst reset 작업하기
            if (isInGol(x, y, new Gol(x, y, d)) == true) {
                gol = new Gol(x, y, d);
                lst.add(gol);
                isOk = true;
            }

            // 왼쪽 방향 삽입하기
            if (isOk == false){
                if (isInGol(x, y - 1, new Gol(x, y-1, d)) == true) {
                    gol = new Gol(x, y -1, d);
                    lst.add(gol);
                    isOk = true;
                }
            }

            // 오른쪽 방향 삽입하기
            if (isOk == false){
                if (isInGol(x, y + 1, new Gol(x, y + 1, d)) == true) {
                    gol = new Gol(x, y  + 1, d);
                    lst.add(gol);
                    isOk = true;
                }
            }

            // 초기화 하기
            if (isOk == false || gol == null){
                System.out.println(i+ "번째 인덱스 : 초기화");
                resetLst();
                continue;
            }

            // 1. 골렘을 남쪽으로 이동
            move(gol);
            // 2. 정령을 남쪽으로 이동, 총합 누적.
            int num = getPoint(gol);
            res += num;

            // debug :
/*            System.out.println(i+ "번째 인덱스 점수 회득 : " + num);
            for (Gol gol1 : lst) {
                System.out.println("골렘 위치 ("+gol1.x + ", "+ gol1.y +" )");
            }
            System.out.println("=====");*/
        }
        System.out.println(res);
    }

    private static int getIdx(Gol gol){
        int idx = 0 ;
        for (Gol gol1 : lst) {
            if(gol1 == gol) return idx;
            idx ++;
        }
        return -1;
    }
    private static int getPoint(Gol gol) {
        // 현재 좌표에서 갈 수 있는 최대한 낮은 값으로 가야함.
        int max = gol.x + 1;

        // 가장 깊게 갈 수 있는 경우의 수를 구해야함.
        Queue<Gol> q = new LinkedList<>();
        boolean[] visited = new boolean[lst.size()];
        visited[getIdx(gol)] = true;

        q.add(gol);
        while(!q.isEmpty()){
            Gol poll = q.poll();
//            System.out.println("(점수) 요정 이동 (" + poll.x + ", " + poll.y + ")");
            max = Math.max(max, poll.x + 1);

            // 현재 위치에서 출구로 이동.
            int x = poll.x + poll.d.x;
            int y = poll.y + poll.d.y;
            for (Gol gol1 : lst) {
                if (visited[getIdx(gol1)]) continue;
                int distance = Math.abs(gol1.x - x) + Math.abs(gol1.y - y);
//                System.out.println("(" + gol1.x + ", " + gol1.y + ") (" +x + ", "+ y + "두 요정 사이의 간격 : " + distance);
                if (distance == 2){
                    q.add(gol1);
                    visited[getIdx(gol1)] = true;
                }
            }
        }
        return max;
    }

    private static void move(Gol gol){
        while(true) {
            // 1. 남쪽으로 이동
            if (moveBottom(gol))
                continue ;
            // 2. 왼 -> 아래 로 이동
            if (moveLeft(gol))
                continue ;
            // 3. 오른 -> 아래로 이동
            if (moveRight(gol))
                continue;

            break;
        }
    }

    private static boolean moveRight(Gol gol) {
        // 오른쪽 -> 아래 이동.
        int dx = gol.x + Direction.RIGHT.x;
        int dy = gol.y + Direction.RIGHT.y;
        int dx2 = dx + Direction.BOTTOM.x;
        int dy2 = dy + Direction.BOTTOM.y;
        if (isInArr(dx , dy) == false) return false;
        if (isInArr(dx2 , dy2) == false) return false;
        if(isInGol(dx, dy, gol) && isInGol(dx2, dy2, gol)){
            gol.x = dx2;
            gol.y = dy2;

            int idx = 0;
            for (Direction value : Direction.values()) {
                if (gol.d == value) break;
                idx ++;
            }

            if(idx == 3) gol.d = Direction.values()[0];
            else gol.d = Direction.values()[idx + 1];
            return true;
        }
        return false;
    }

    private static boolean moveLeft(Gol gol) {
        // 왼 -> 아래 로 이동
        int dx = gol.x + Direction.LEFT.x;
        int dy = gol.y + Direction.LEFT.y;
        int dx2 = dx + Direction.BOTTOM.x;
        int dy2 = dy + Direction.BOTTOM.y;
        if (isInArr(dx , dy) == false) return false;
        if (isInArr(dx2 , dy2) == false) return false;
        if(isInGol(dx, dy, gol) && isInGol(dx2, dy2, gol)){
            gol.x = dx2;
            gol.y = dy2;

            int idx = 0;
            for (Direction value : Direction.values()) {
                if (gol.d == value) break;
                idx ++;
            }

            if(idx == 0) gol.d = Direction.values()[3];
            else gol.d = Direction.values()[idx - 1];

            return true;
        }
        return false;
    }

    private static boolean moveBottom(Gol gol) {
        // 1. 남쪽으로 한칸이동
        int dx = gol.x + Direction.BOTTOM.x;
        int dy = gol.y + Direction.BOTTOM.y;
        if (isInArr(dx , dy) == false) return false;
        if(isInGol(dx, dy, gol)){
            gol.x = dx;
            gol.y = dy;
            return true;
        }
        return false;
    }
    public static boolean isInArr(int x, int y){
        if(x <= 1 || x >= r || y <= 1 || y >= c)
            return false;
        return true;
    }

    // 골렘이 lst에 들어갈 수 있는지 판별하기
    public static boolean isInGol(int x, int y, Gol g){
        if(isInArr(x, y) == false)
            return false;
        // 총 좌표 차이가 3정도 나면 가능? ㅇㅇ
        for (Gol gol : lst) {
            if(gol == g) continue;

            int distance = Math.abs(gol.x - x) + Math.abs(gol.y - y);
            if(distance < 3)
                return (false);
        }
        return (true);
    }

    private static void resetLst() {
        lst = new ArrayList<>();
    }
}
