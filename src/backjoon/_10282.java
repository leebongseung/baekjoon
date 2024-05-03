package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 7시 55분
 * 종료 시간 : 8시 47분
 *
 * 문제분석
 * - yum3가 네트워크 시설의 한 컴퓨터를 해킹함.
 * - 컴퓨터들이 하나 둘 전염되기 시작함,
 * - 어떤 a가 다른 b에 의존한다면, b가 감염되고 그로 부터 일정시간뒤 a도 감염
 * - b가 a에 의존하지 않는다면, b는 안전하다.
 *
 * - yum3가 해킹한 컴퓨터 번호와 의존성이 주어질 때
 * - 쵱 몇대가 컴퓨터에 감염되고 그에 따라 몇시간이 ㅇ걸리는지 구하시오
 *
 * 다익스트라인듯 최단 시간을 구해야한다. 그래프이론
 * */
public class _10282 {
    public static int n; // 노드 개수, 컴퓨터 개수
    public static int d; // 간선 개수
    public static int startNode; // 시작 노드
    public static boolean[] visited; // 방문처리
    public static int[] dijkstraArr; // 다익스트라 배열, 시작지점 빼고 다 Integer.MAX_VALUE로 초기값 설정하기!
    public static List<Node>[] lst; // 간선 리스트 집합.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        for(int test_case = 0; test_case < t; test_case++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            startNode = Integer.parseInt(st.nextToken()) - 1;
            visited = new boolean[n];
            dijkstraArr = new int[n];
            // 다익스트라 배열 초기화
            for(int i=0; i<n; i++){
                if(i == startNode) dijkstraArr[i] = 0;
                else dijkstraArr[i] = Integer.MAX_VALUE;
            }

            // 간선 리스트 초기화
            lst = new ArrayList[n];
            for(int i=0; i<n; i++) lst[i] = new ArrayList<>();

            // 간선 리스트 readline 수행
            for(int i=0; i<d; i++){
                st = new StringTokenizer(bf.readLine());
                int endNode = Integer.parseInt(st.nextToken()) - 1;
                int startNode = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());

                lst[startNode].add(new Node(endNode, weight));
            }

            // 가중치 즉 가장 가까운 연결된 노드 부터 선택
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.offer(new Node(startNode, 0));
            int cnt = 0; // 감염된 노드 개수
            int lastTime = 0; // 총 걸린 시간
            while(!q.isEmpty()){
                Node poll = q.poll();
                if(visited[poll.idx]) continue;
                visited[poll.idx] = true;
                cnt++;
                lastTime = poll.weight;

                for(Node n : lst[poll.idx]){
                    // 다익스트라 배열보다 작을 경우 q에 삽입
                     if(dijkstraArr[n.idx] > dijkstraArr[poll.idx] + n.weight){
                        dijkstraArr[n.idx] = dijkstraArr[poll.idx] + n.weight;
                        q.add(new Node(n.idx, dijkstraArr[n.idx]));
                    }
                }
            }

            // 총 몇대가 감염되었는지,
            bw.write(cnt + " " + lastTime + "\n");
        }
        bw.flush();
        bw.close();
    }
    static class Node implements Comparable<Node> {
        int idx, weight; // 현재 노드 인덱스 위치, (리스트에선 노드의 가중치 or 다익스트라Arr에선 노드까지의 거리)

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
