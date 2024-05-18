package swea;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 6시 35분
 * 종료 시간 : 7시 12분
 *
 * 문제분석
 * - 모든 정점은 하나의 트리로 연결되어 있음.
 * - 모든 정점의 가중치 합이 최소인 트리.
 *
 *
 * - 유니온 파인드, 최소 스패닝 트리 구현하기
 * */
public class _3124 {
    public static int v; //정점 개수
    public static int e; //간선 개수
    public static PriorityQueue<Node> pq;
    public static int [] unionFindArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tCase = Integer.parseInt(bf.readLine());

        for(int t = 1; t<= tCase; t++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            pq = new PriorityQueue<>();

            // 유니온파인드 배열 자기자신을 대표노드로 설정하기
            unionFindArr = new int[v];
            for(int i=0; i<v; i++){
                unionFindArr[i] = i;
            }

            // 우선순위 큐에 짚어넣기
            for(int i=0; i<e; i++){
                st = new StringTokenizer(bf.readLine());
                int startNode = Integer.parseInt(st.nextToken()) -1;
                int endNode = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());

                pq.add(new Node(startNode, endNode, weight));
            }

            long sum = 0;
            while(!pq.isEmpty()){
                Node poll = pq.poll();

                if(find(poll.startNode) == find(poll.endNode)) continue;

                union(poll.startNode, poll.endNode);
                sum += poll.weight;
            }

            bw.write("#" + t +" ") ;
            bw.write(sum + "\n");
        }

        bw.flush();
    }

    public static void union(int node1, int node2){
        int topNode1 = find(node1);
        int topNode2 = find(node2);

        if(topNode1 != topNode2){
            unionFindArr[topNode2] = topNode1;
        }
    }

    public static int find(int node1){
        if(node1 == unionFindArr[node1]){
            return node1;
        } else {
            int nextNode = unionFindArr[node1];
            int topNode = find(nextNode);
            unionFindArr[node1] = topNode;
            return topNode;
        }
    }

    public static class Node implements Comparable<Node> {
        int startNode;
        int endNode;
        int weight;

        public Node(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}


