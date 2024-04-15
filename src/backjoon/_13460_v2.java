package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간 시간 : 7시 14분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 빨간 구슬을 구멍을 빼내는 게임
 * - n*m 크기
 * - 파란구름이 구멍에 들어가면 안됨
 *
 *  실패 조건
 *  - 파란구슬이랑 빨간구슬 동시에 빠질 경우
 *  - 파란구슬이 먼저 빠질 경우
 *
 *  성공조건
 *  - 10번 이내로 빨간구슬 탈출 시키기
 *
 *  - 보드가 주어질때, 최소 몇번만에 빨간 구슬을 구멍을 통해 뺄 수 있는지?
 *
 * */
public class _13460_v2 {

    public static int n;
    public static int m;
    public static String[][] arr;
    public static int res = -1;

    public static class BallIdx{
        int[] blue;
        int[] red;

        BallIdx(int[] blue, int[] red){
            this.blue = blue.clone();
            this.red = red.clone();
        }

        public int[] getBlue() {
            return blue;
        }
        public int[] getRed() {
            return red;
        }
    }

    enum Direction{
        top(-1,0), bottom(1, 0), left(0, -1), right(0, 1);

        private int x;
        private int y;

        Direction(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }
    }

    public static void bfs(BallIdx ballIdx){
        Queue<BallIdx> q = new LinkedList<>();
        q.add(ballIdx);

        while(!q.isEmpty()){
            BallIdx poll = q.poll();

            int[] blue = poll.getBlue();
            int[] red = poll.getRed();

            int blueX = blue[0];
            int blueY = blue[1];
            int redX = red[0];
            int redY = red[1];

            for(Direction d : Direction.values()){
                if(d.name().equals(Direction.top.name())){

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] blue = new int[2];
        int[] red = new int[2];

        arr = new String[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<m; j++){
                arr[n][m] = st.nextToken();
            }
        }

        // bfs 시작
    }
}
