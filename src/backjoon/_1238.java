package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 1시 8분
 * 종료 시간 : 1시 56분
 * <p>
 * 문제분석
 * - n개의 숫자로 구분된 마을당 1명의 학생이 살고 있음.
 * - n명의 학생이 x번 마을에 모여서 파티를 벌이기로 함.
 * - m 개의 단방향 도로 존재, i번째 길을 지나는데 T_i 시간 소비
 * - 최단 시간 오고 가길 원함.
 * - n명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구?
 * <p>
 * 즉 목표 지점 정점에서 거리가 가장 긴 학생을 골라라
 */
public class _1238 {
    public static int n; // 정점 개수
    public static int m; // 단방향 간선 개수
    public static int x; // 목표지점 정점 인덱스 번호
    public static int[][] distance; // 거리 저장하는 배열, 단방향이기 때문에 x -> y, y-> x 가는거 두가지 경로를 계산해야한다.
    public static boolean[] visited;
    public static List<List<int[]>> lst;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;

        // 다익스트라 배열 초기화, 시작지점은 거리가 0
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) distance[i][j] = 0;
                else distance[i][j] = Integer.MAX_VALUE;
            }
        }

        lst = new ArrayList<>();
        // 간선 2중 중첩 리스트 초기화
        for (int i = 0; i < n; i++) {
            lst.add(new ArrayList<>());
        }

        // 단방향 간선 가중치 짚어넣기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int startNode = Integer.parseInt(st.nextToken()) - 1; // 시작지점
            int endNode = Integer.parseInt(st.nextToken()) - 1; // 종료지점
            int weight = Integer.parseInt(st.nextToken()); // 소요시간

            lst.get(startNode).add(new int[]{endNode, weight});
        }


        // 양방향으로 계산하기위하여 i 를 선언함.
        for (int i = 0; i < n; i++) {
            // 다익스트라 방문 배열 초기화
            visited = new boolean[n];

            // 간선번호, 현재거리
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
                return o1[1] - o2[1];
            });
            q.add(new int[]{i, distance[i][i]});

            // 다익스트라 알고리즘으로 탐색 시작
            while (!q.isEmpty()) {
                int[] poll = q.poll();
                int node = poll[0];
                if (visited[node]) continue; // 방문한 노드는 다시 방문하지 않음.

                visited[node] = true;

                for (int[] edge : lst.get(node)) {
                    int nextNode = edge[0];
                    int nextWeight = edge[1];

                    if (distance[i][nextNode] > distance[i][node] + nextWeight) {
                        distance[i][nextNode] = distance[i][node] + nextWeight;
                        q.add(new int[]{nextNode, distance[i][nextNode]});
                    }
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == x) continue; // 모임하는집은 알 필요 없습니다
            res = Math.max(res, distance[i][x] + distance[x][i]);
        }

        System.out.println(res);
    }
}
