package backjoon.shortest_path;

import java.io.*;
import java.util.*;

/*  시작 시간 : 5시 20분
 *   종료 시간 : 6시 20분.. 집중력 무엇...
 * */
public class _18352 {
    static private int DISTANCE = 1;
    static private int MAX = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 도시(정점)의 개수
        int m = Integer.parseInt(st.nextToken()); // 도로(간선)의 개수
        int k = Integer.parseInt(st.nextToken()); // 거리 정보
        int x = Integer.parseInt(st.nextToken()); // 출발 도시 번호

        // Graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i =0; i<= m; i++){
            graph.add(new ArrayList<>());
        }



        // 그래프 입력받기
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());
            graph.get(startIdx).add(endIdx);
        }

        // 최단 거리 테이블
        int[] d = new int[n+1];
        Arrays.fill(d,MAX);

        // 최단거리가 k 인 도시의 정보를 한줄로 출력할것.
        PriorityQueue<Node> arr = new PriorityQueue<>();
        arr.add(new Node(x, DISTANCE));
        d[x] = 0;
        while(!arr.isEmpty()){
            Node shortestNode = arr.poll();
            int currentIdx = shortestNode.getIndex();
            int currentDistance = shortestNode.getDistance();

            // 현재 노드가 이미 처리된 적 있는 노드일 경우
            if(d[currentDistance] < d[x]) continue;

            for (int endNode : graph.get(currentIdx)) {
                int cost = d[currentDistance] + 1;
                if(cost < d[endNode]) {
                    d[endNode] = cost;
                    arr.add(new Node(endNode, 1));
                }
            }
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int i =1; i<= n; i++){
            if(d[i] == k){
                sj.add(Integer.toString(i));
            }
            System.out.print(d[i] +" ");
        }
        System.out.println();
        System.out.println(sj);

    }

    static class Node implements Comparable<Node> {
        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return index;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            if(this.distance < o.distance){
                return -1;
            }
            return 1;
        }
    }



}
