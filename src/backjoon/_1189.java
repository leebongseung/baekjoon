package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 2분
 * 종료 시간 : 10시 26분
 *
 * 문제분석
 * - 한수는 캠프를 마치고 집으로 가려고 함.
 * - 현재 왼쪽 아래점에 있고, 집은 오른쪽 위에 있음.
 * - 한수는 재방문은 안함.
 * - 한수가 집에 돌아가는 모든 경우를 나타냄, T로 표시된곳은 못가는 곳.
 * - R*C 맵에서 못가는 부분 주어지고, K가 주어지면 한수가 집까지 도착하는 경우 중 거리가 K인 가짓수를 구하기
 * */
public class _1189 {
    public static int row;
    public static int column;
    public static int k;
    public static String[][] map;
    public static boolean[][] visited;

    public static int startIdxRow, startIdxColumn, desIdxRow, desIdxColumn, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new String[row][column];
        visited = new boolean[row][column];

        startIdxRow = row-1;
        startIdxColumn = 0;
        desIdxRow = 0;
        desIdxColumn = column-1;

        for(int i=0; i<row; i++){
            String str = br.readLine();
            for(int j=0; j<column; j++){
                map[i][j] = str.substring(j, j + 1);
            }
        }

        // 이건 브루트 포스다! 완전 탐색
        // 시작 지점 방문처리 후 dfs 탐색 시작.
        visited[startIdxRow][startIdxColumn] = true;
        dfs(startIdxRow, startIdxColumn, 1);

        System.out.print(res);
    }

    private static void dfs(int x, int y, int cnt) {
        // 거리가 cnt일 경우 도착지 인지 판별하기
        if(cnt == k){
            if(x != desIdxRow || y != desIdxColumn) return;

            res ++;
            return;
        }

        // 갈 수 있는 네방향 조사하기
        for(Direction d : Direction.values()){
            int dx = x + d.x;
            int dy = y + d.y;

            if(dx < 0 || dx >= row || dy < 0 || dy >= column) continue;

            if(!visited[dx][dy] && Objects.equals(map[dx][dy], ".")){
                visited[dx][dy] = true;
                dfs(dx, dy, cnt + 1);
                visited[dx][dy] = false;
            }
        }
    }

    enum Direction{
        TOP(-1,0), LEFT(0, -1), RIGHT(0, 1), BOTTOM(1, 0);
        private int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
