package backjoon;

import javax.swing.text.Position;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

/**
 * 시작 시간 : 4시 46분
 * 종료 시간 : 5시 37분
 *
 * - m*n개의 크기의 모눈종이
 * - k개의 직사각형을 그림
 * - k개의 직사각형 내부를 제외한 나머지 부분 분리된 부분은 몇개인가?
 * - 분리된 각 영역의 넓이를 계산하라!
 * - 넓이는 오름차순으로 정렬해서 반환할 것.
 * */
public class _2583 {
    public static int x;
    public static int y;
    public static int k;
    public static int ans;
    public static int[][] arr;
    public static boolean[][] visited;

    enum Direction{
        top(-1, 0), bottom(1,0), left(0,-1), right(0, 1);

        private int x;
        private int y;

        Direction(int x, int y){
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
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[x][y];
        visited = new boolean[x][y];

        List<Integer> res = new ArrayList<>();
        for(int repeat=0; repeat<k; repeat++){
            st = new StringTokenizer(bf.readLine());
            int leftY = Integer.parseInt(st.nextToken());
            int leftX = Integer.parseInt(st.nextToken());
            int rightY = Integer.parseInt(st.nextToken()) -1;
            int rightX = Integer.parseInt(st.nextToken()) - 1;

            // 방문처리하기
            for(int i = leftX; i <= rightX; i++){
                for(int j= leftY; j <= rightY; j++){
                    if(arr[i][j] == 0) arr[i][j] = 1;
                }
            }
        }


        // bfs 수행하면서 방문안했다면 넓이 계산하기
        for(int i = 0; i < x; i++){
            for(int j= 0; j < y; j++){
                if(!visited[i][j] && arr[i][j] == 0){
                    ans = 1;
                    visited[i][j] = true;
                    bfs(i, j);
                    res.add(ans);
                }
            }
        }


        // 오른차순으로 정렬하기
        res.sort((o1, o2) -> o1-o2);

        System.out.println(res.size());
        for(int num : res){
            System.out.print(num + " ");
        }
    }

    private static void bfs(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i,j));

        while(!q.isEmpty()){
            Point poll = q.poll();

            for(Direction d : Direction.values()) {
                int dx = poll.x + d.getX();
                int dy = poll.y + d.getY();

                if( dx <0 || dx >= x || dy<0 || dy>= y) continue;

                if (arr[dx][dy] == 0 && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    q.add(new Point(dx, dy));
                    ans++;
                }
            }
        }
    }
}
