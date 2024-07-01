package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 10시 3분
 * 종료 시간 : 10시 28분
 *
 * 문제분석
 * - 농장에는 헛간이 많이 널려있고 재서기는 그 중에 하나에 숨어야 한다
 * - 헛간의 개수는 N(2 <= N <= 20,000)개
 * - 재서기는 수혀니가 1번 헛간부터 찾을 것을 알고 있다
 * - 모든 헛간은 M(1<= M <= 50,000)개의 양방향 길로 이어져 있음
 * - 냄새는 1번 헛간에서 멀어질수록 감소한다.
 * - 재서기의 발냄새를 최대한 숨길 수 있는 헛간을 찾자
 *
 * 다익스트라 문제임.
 * 1번에서 n 까지의 거리를 찾고
 * 가장 먼 거리의 index를 출력
 * 그 후 거리를 출력
 * 같은 거리가 존재할 경우 총 index의 개수를 출력하기
 * */
public class _6118 {
    public static int n,m;
    public static int[] dijkstraArr;
    public static List<List<Integer>> lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            lst.add(new ArrayList<>());
        }

        // 다익스트라 초기화 하기
        dijkstraArr = new int[n];
        Arrays.fill(dijkstraArr, Integer.MAX_VALUE);
        dijkstraArr[0] = 0;

        // 간선 연결하기
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) -1;
            int b = Integer.parseInt(st.nextToken()) -1;
            lst.get(a).add(b);
            lst.get(b).add(a);

        }

        int maxDistance = Integer.MIN_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        while(!pq.isEmpty()){
            Node poll = pq.poll();

            for(int num : lst.get(poll.idx)){
                if(dijkstraArr[num] > dijkstraArr[poll.idx] + 1) {
                    dijkstraArr[num] = dijkstraArr[poll.idx] + 1;
                    pq.add(new Node(num, dijkstraArr[num]));
                    maxDistance = Math.max(maxDistance, dijkstraArr[num]);
                }
            }
        }

        int totalCnt = 0;
        int minIdx = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            if(dijkstraArr[i] == maxDistance){
                totalCnt++;
                minIdx = Math.min(minIdx, i+1);
            }
        }

        System.out.println(minIdx + " " + maxDistance + " " + totalCnt);
    }
    public static class Node implements Comparable<Node>{
        public int idx, distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
