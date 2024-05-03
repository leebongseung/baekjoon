package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 12시 24분
 * 종료 시간 : ??
 *
 * 문제분석
 * - w*h 크기의 지도가 존재함.
 * - 각 칸은 빈칸이거나, 벽이며
 * - 두개는 'c'로 표시되어있는 칸임
 *  - 레이저로 통신하기 위해 설치해야하는 거울 개수의 최솟값을 구하시오.
 * - 거울 / \ 를 통해서 90도로 회전 시킬 수 있다.
 * */
public class _6087 {
    public static int n;
    public static int m;
    public static String[][] arr;
    public static int[][] visited;
    public static int[] startIdx;
    public static int[] endIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new String[n][m];
        visited = new int[n][m];
        for(int i=0; i<n; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);

        for(int i=0; i<n; i++){
            String str = bf.readLine();
            for(int j=0; j<m; j++){
                arr[i][j] = str.substring(j, j+1);
                if(arr[i][j].equals("C")){
                    if(startIdx == null){
                        startIdx = new int[2];
                        startIdx[0] = i;
                        startIdx[1] = j;
                    } else if(endIdx == null){
                        endIdx = new int[2];
                        endIdx[0] = i;
                        endIdx[1] = j;
                    }
                }
            }
        }

        bfs();
        System.out.println(visited[endIdx[0]][endIdx[1]]);
    }

    enum Direction{
        TOP(-1, 0),
        BOTTOM(1, 0),
        LEFT(0, -1),
        RIGHT(0,1);

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void bfs(){
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) ->{
            return o1[2]- o2[2];
        }); // x축, y축, 거울개수, 현재방향(0,1,2,3)

        q.add(new int[]{startIdx[0], startIdx[1], 0, -1});

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int mirrorCnt = poll[2];
            int directionVal = poll[3];
            if(visited[x][y] < mirrorCnt) continue;
            visited[x][y] = mirrorCnt;

            if(x == endIdx[0] && y == endIdx[1]) return;

            Direction[] d = Direction.values();
            for(int i=0; i<d.length; i++){
                int dx = x + d[i].getX();
                int dy = y + d[i].getY();

                if(dx < 0 || dx >=n || dy<0 || dy>=m) continue;
                if(arr[dx][dy].equals("*")) continue;

                if(directionVal == -1){
                    q.offer(new int[]{dx, dy, 0, i});
                } else if(directionVal == i){
                    if(visited[dx][dy] < mirrorCnt) continue;
                    // 같은방향일 경우 미러 이동할 필요 없음
                    q.offer(new int[]{dx, dy, mirrorCnt, i});
                } else {
                    if(visited[dx][dy] < mirrorCnt) continue;
                    // 수직 방향일 경우 미러 이동
                    q.offer(new int[]{dx, dy, mirrorCnt + 1, i});
                }
            }
        }

    }
}
