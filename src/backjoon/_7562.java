package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 3분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 나이트가 이동범위
 *
 * */
public class _7562 {
    private enum Direction{
        leftTop(-1, -2),
        topLeft(-2, -1),
        rightTop(-1,2),
        topRight(-2,1),
        leftBottom(1, -2),
        rightBottom(1, 2),
        bottomLeft(2, -1),
        bottomRight(2, 1);
        ;
        private final int x;
        private final int y;

        Direction(int x, int y) {
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

    public static int[][] arr;
    public static int n;
    public static boolean[][] visited;
    public static int[] currentIdx;
    public static int[] targetIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t_case = Integer.parseInt(bf.readLine()); // 테스트케이스 개수
        for(int t=0; t<t_case; t++) {
            n = Integer.parseInt(bf.readLine());
            arr = new int[n][n];
            visited = new boolean[n][n];
            currentIdx = new int[2];
            targetIdx = new int[2];

            StringTokenizer st = new StringTokenizer(bf.readLine());
            currentIdx[0] = Integer.parseInt(st.nextToken());
            currentIdx[1] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            targetIdx[0] = Integer.parseInt(st.nextToken());
            targetIdx[1] = Integer.parseInt(st.nextToken());

            System.out.println(bfs());
        }
    }

    private static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{currentIdx[0], currentIdx[1], 1});
        visited[currentIdx[0]][currentIdx[1]] = true;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int depth = poll[2];

            for(Direction d : Direction.values()){
                int dx = x + d.getX();
                int dy = y + d.getY();

                if(dx < 0 || dx >= n || dy < 0 || dy >= n) continue;

                if(!visited[dx][dy]){
                    if(dx == targetIdx[0] && dy == targetIdx[1]) return depth;

                    visited[dx][dy] = true;
                    q.add(new int[]{dx, dy, depth + 1});
                }
            }
        }
        return 0;

    }
}
