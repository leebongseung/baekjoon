package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _11967 {
    public static int n, m;
    public static boolean[][] arr;
    public static Map<Integer, Map<Integer, List<int[]>>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new boolean[n+1][n+1];
        arr[1][1] = true;
        // 1,1 부터 출발 하면서 불이켜져있는 방만 드감.
        // 상하좌우 인접한 방으로 이동

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!map.containsKey(x))
                map.put(x, new HashMap<>());
            Map<Integer, List<int[]>> xMap = map.get(x);
            if (!xMap.containsKey(y))
                xMap.put(y, new ArrayList<>());
            List<int[]> lst = xMap.get(y);
            lst.add(new int[]{a, b});
        }

        // bfs 탐색 시작.
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];
        q.add(new int[]{1, 1});
        visited[1][1] = true;
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] poll = q.poll();

            // 현재 위치에서 킬 수 있는 조명 다 켜기
            if(map.containsKey(poll[0])){
                Map<Integer, List<int[]>> xMap = map.get(poll[0]);
                if (xMap.containsKey(poll[1])){
                    List<int[]> lst = xMap.get(poll[1]);
                    for (int[] dd : lst) {
                        if(!arr[dd[0]][dd[1]]){
                            arr[dd[0]][dd[1]] = true;
                            visited = new boolean[n+1][n+1];
                            cnt ++;
                        }
                    }
                }
            }

            for(Direction d : Direction.values()){
                int dx = poll[0] + d.x;
                int dy = poll[1] + d.y;

                if(dx <= 0 || dx > n || dy <= 0 || dy > n) continue;
                if(!arr[dx][dy]) continue;
                if(visited[dx][dy]) continue;
                q.add(new int[]{dx, dy});
                visited[dx][dy] = true;
            }
        }
        System.out.println(cnt);
    }
    enum Direction{
        TOP(-1, 0),
        BOTTOM(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);
        ;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public final int x, y;
    }
}
