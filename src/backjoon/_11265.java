package backjoon;

import javax.lang.model.element.ModuleElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
* 시작 시간 : 10시 30분
 * 종료 시간  : ?
 * n개의 파티장을 가진 놀이동산이 됨.
 * 1. a에서 b로 가는 길이 일방통행 길임. 근데, a와 b가 아닌 다른 곳 경유하는 경우도 있음
 * 2. c만큼의 시간 뒤 b 파티장에 파티가 열리는데, 제시간안에 갈 수 있는 지 모른다, 갈 수 있는지 없는지 알아봐주는 프로그램 만들기
 *
 * 다익스트라인가?
* */
public class _11265 {
    public static int n, m;
    public static int[][] map;
    public static int[][] dijkstraArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 파티장 크기
        m = Integer.parseInt(st.nextToken()); // 구해야하는 수

        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 다익스트라 배열 초기화 하기
        dijkstraArr = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++){
                if(i == j) continue;
                dijkstraArr[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<n; i++) {
            // 다익스트라 수행하기
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> a[1] - b[1]
            );
            pq.add(new int[]{i, dijkstraArr[i][i]});
            while(!pq.isEmpty())
            {
                int[] poll = pq.poll();
                int node = poll[0];

                for(int j=0; j<n; j++){
                    // 다른 노드 거쳐 가는게 빠르지 확인하는 구간.
                    if(dijkstraArr[i][node] + map[node][j] < dijkstraArr[i][j]){
                        dijkstraArr[i][j] = dijkstraArr[i][node] + map[node][j];
                        pq.add(new int[]{j, dijkstraArr[i][j]});
                    }
                }
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int dis = Integer.parseInt(st.nextToken());

            if (dijkstraArr[node1][node2] <= dis)
                System.out.println("Enjoy other party");
            else
                System.out.println("Stay here");
        }
    }
}
