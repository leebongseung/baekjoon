package backjoon;

import java.util.*;
import java.io.*;

/**
 * 시작 시간 : 1시 33분
 * 종료 시간 : 2시 21분
 *
 * 문제분석
 * - 어떤 지역의 높이를 파악
 * - 많은 비가 내릴때 물에 잠기지 않는 안전한 영역의 개수 구하기
 *
 * - 안전한 영역이란?
 *  - 물에 잠기지 않는 영역들의 범위의 개수
 *
 *
 *  - bfs?로 푸는게 좋아보임
 *
 *  - 물의 입력은 어디에?
 *     - 최소 부터 최대 까지 반복하면서 가장 큰 bfs 의 개수 찾기
 * */
public class _2468 {
    public static int n;
    public static int[][] arr;

    enum Direction{
        top(-1,0), bottom(1,0), left(0,-1), right(0,1);

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
    public static int bfs(int num){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        int cnt = 0;
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                // 비가 잠기지 않는 영역만 bfs로 탐색하기!
                if(arr[x][y] > num && !visited[x][y]){
                    // bfs 완전탐색하기
                    q.add(new int[]{x, y});
                    visited[x][y] = true;
                    cnt ++;

                    while(!q.isEmpty()){
                        int[] poll = q.poll();
                        for(Direction d : Direction.values()){
                            int dx = poll[0] + d.getX();
                            int dy = poll[1] + d.getY();

                            if(dx < 0 || dx >= n || dy <0 || dy >= n) continue;

                            if(arr[dx][dy] > num  && !visited[dx][dy]){
                                q.add(new int[]{dx, dy});
                                visited[dx][dy] = true;
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n][n];

        // 2차원 배열 채우기
        for(int x=0; x<n; x++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int y =0; y<n; y++){
                arr[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 1; // 노트 부분! (아무부분도 물에 안잠길 수 있다 !)
        for(int i = 1; i<= 100; i++){
            res = Math.max(bfs(i), res);
        }
        System.out.println(res);

    }
}
