package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 분석
 * - V개의 마을과 E개의 도로로 구성된 도시가 있음.
 * - 마을 :  1~ V 번까지 번호가 있다.
 * - 도로 : 일방 통행 도로임.
 * - 도로를 따라 운동하기 위한 경로를 구함, 우리는 사이클을 원한다.
 * <p>
 * - 사이클을 이루는 도로의 길이의 합이 최소
 * 플로이드 워셜이뭐지 -> 다익스트라로 풀어버리자
 */
public class _1956 {
    public static int v, e;
    public static List<List<int[]>> lst;
    public static int[][] distance;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        lst = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            lst.get(start).add(new int[]{end, weight});
        }

        // 1. 다익스트라 배열 초기화
        distance = new int[v][v];
        for(int i=0; i<v; i++){
            for(int j=0; j<v; j++){
                if(i == j) distance[i][j] = 0;
                else distance[i][j] = Integer.MAX_VALUE;
            }
        }


        // 2. 다익스트라 최단 경로 찾기
        for(int i=0; i<v; i++) {
            visited = new boolean[v];
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
                return o1[1] - o2[1];
            });
            q.add(new int[]{i, distance[i][i]});

            while(!q.isEmpty()){
                int[] poll = q.poll();
                int node = poll[0];
                if(visited[node]) continue;

                visited[node] = true;

                for (int[] ints : lst.get(node)) {
                    int nextNode = ints[0];
                    int weight = ints[1];

                    if(distance[i][nextNode] > distance[i][node] + weight){
                        distance[i][nextNode] = distance[i][node] + weight;
                        q.add(new int[]{nextNode, distance[i][nextNode]});
                    }

                }
            }
        }

        // 3. 결과값 구하기
        int res = Integer.MAX_VALUE;
        for(int i=0; i<v; i++){
            for(int j=0; j<v; j++){
                if(i == j) continue;
                if(distance[i][j] == Integer.MAX_VALUE || distance[j][i] == Integer.MAX_VALUE) continue;

                int cnt = 0;
                cnt += distance[i][j];
                cnt += distance[j][i];

                res = Math.min(res, cnt);
            }
        }

        if(res == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.print(res);
    }
}
