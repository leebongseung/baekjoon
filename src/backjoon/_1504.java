package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 1시 24분
 * 종료 시간 : ??
 *
 * 양방향 간선.
 *
 * v1와 v2의 최단경로를 구해라. 두정점은 최대 간선1개가 존재함.
 * */
public class _1504 {
    public static class Node implements Comparable<Node>{
        private int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static int n;
    public static int e;
    public static List<Node>[] lst;
    public static int[] dijkstraArr;
    public static int startNode;
    public static int endNode1;
    public static int endNode2;
    public static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        lst = new List[n];
        for(int i=0; i<n; i++) lst[i] = new ArrayList<>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(bf.readLine());
            int startNode = Integer.parseInt(st.nextToken()) -1;
            int endNode = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            // 양방향
            lst[startNode].add(new Node(endNode, weight));
            lst[endNode].add(new Node(startNode, weight));
        }
        st = new StringTokenizer(bf.readLine());
        startNode = Integer.parseInt(st.nextToken()) - 1;
        endNode1 = startNode;
        endNode2 = Integer.parseInt(st.nextToken()) - 1;

        // 다익스트라 시작 노드 -> 종료노드 계산하기
        dijkstraArr=  new int[n];
        for(int i=0; i<n; i++){
            dijkstraArr[i] =  i == startNode ? 0 : Integer.MAX_VALUE;
        }

        // 다익스트라 알고리즘 실행
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, dijkstraArr[startNode]));

        while(!pq.isEmpty()){
            Node poll = pq.poll();
            int currentNode = poll.node;

            if(visited[currentNode]) continue;
            visited[currentNode] = true;

            for(Node n : lst[currentNode]){
                if(!visited[n.node] && dijkstraArr[n.node] > dijkstraArr[currentNode] + n.weight){
                    dijkstraArr[n.node] = dijkstraArr[currentNode] + n.weight;
                    pq.add(new Node(n.node, dijkstraArr[n.node]));
                }
            }
        }
        for (int i : dijkstraArr) {
            System.out.print(i + " ");
        }
        System.out.println();

        if(dijkstraArr[endNode1] == Integer.MAX_VALUE || dijkstraArr[endNode2] == Integer.MAX_VALUE)
            System.out.println(-1);
        else System.out.println(dijkstraArr[endNode1] + dijkstraArr[endNode2]);

    }
}
