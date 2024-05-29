package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 30분
 * 종료 시간 : 10시 47분
 *
 * 문제분석
 * - 가로와 세로로 이동가능한 그래프 가 존재
 * - n*m 크기의 지도가 존재
 * - 0 : 갈 수 없는 땅
 * - 1 : 갈 수 있는 땅
 * - 2 : 목표지점
 * */
public class _14940 {
    public static int n;
    public static int m;
    public static int[][] arr;
    public static int[][] res;
    public static int[] startIdx;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        res = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    startIdx = new int[]{i, j};
                } else if(arr[i][j] == 0){
                    visited[i][j] = true;
                }
            }
        }

        bfs();

        // 방문 못한 즉 벽으로 막혀있는 공간은 -1 처리 함.
        for (int i =0; i<n; i++) {
            for (int j= 0; j<m; j++) {
                System.out.print((visited[i][j]? res[i][j] : -1) + " ");
            }
            System.out.println();
        }

    }

    public static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(startIdx);
        visited[startIdx[0]][startIdx[1]] = true;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            for(Direction d : Direction.values()){
                int dx = x + d.x;
                int dy = y + d.y;

                if(dx < 0 || dx >= n || dy < 0 || dy >=m || visited[dx][dy]) continue;

                if(arr[dx][dy] == 0) continue;

                visited[dx][dy] = true;
                res[dx][dy] = res[x][y] + 1;
                q.add(new int[]{dx, dy});
            }
        }
    }

    enum Direction{
        TOP(-1, 0),
        BOTTOM(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
        ;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public final int x, y;
    }
}
