package backjoon;
/**
 * 시작 시간 : 5시 41분
 * 종료 시간 : 6시 40분
 *
 * 문제분석
 * - 컴퓨터와 컴퓨터 모두 연결한대, 허브가없어서 C2C 연결해야함
 * - a 와 b를 연결하는 선, b와 c를 여녁ㄹ하는 선, a와 c를 연결하는 선 존재
 * - 비용을 최소로 하여 연결하고 싶음.
 * - 각 컴퓨터 연결 비용이 주어질때 최소비용을 출력하라
 * - 모든 컴퓨터를 연결할 수 없는 경우는 없다!
 *
 * - 정점 수 n 1000 이하
 * - 연결할 수 있는 선 100,000 이하
 *
 * mst 알고리즘 사용해서
 * find 매서드, 정점을나타내는 pEdge, union메서드,
 * 각종 pEdge를 저장하는 list
 * 유니온파인드 list 를 만들자
 * */

import java.util.*;
import java.io.*;

public class _1922 {
    public static int v; // 정점 수
    public static int m; // 엣지 수
    public static PriorityQueue<pEdge> queue = new PriorityQueue<>();
    public static List<Integer> unionFindList = new ArrayList<>();

    public static class pEdge implements Comparable<pEdge>{
        int startNode;
        int endNode;
        int weight;

        pEdge(int v1, int v2, int weight){
            this.startNode = v1;
            this.endNode = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(pEdge o){
            return this.weight - o.weight;
        }

        @Override
        public String toString(){
            return (this.startNode+1) + ", " + (this.endNode+1) +", " + this.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());

        // 유니온파인드 lst 초기화
        for(int i=0; i<v; i++){
            unionFindList.add(i);
        }

        // m개의 간선 입력받기
        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int startNode = Integer.parseInt(st.nextToken()) -1;
            int endNode = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            queue.add(new pEdge(startNode, endNode, weight));
        }

//        for (pEdge pEdge : queue) {
//            System.out.println(pEdge);
//        }
//        System.out.println("===========================");

        // 유니온 파인드 시작
        int findCnt = v-1;
        int sum = 0;
        while(findCnt > 0){
            pEdge remove = queue.remove();
            int startNode = remove.startNode;
            int endNode = remove.endNode;

            // 순환 참조
            if(find(startNode) == find(endNode)) continue;

            union(startNode, endNode);
            sum += remove.weight;
            findCnt--;
        }

        System.out.println(sum);
    }
    public static int find(int v){
        // 유니온 파인드가 자기 자신일 경우
        if(unionFindList.get(v) == v) return v;
        // 대표 노드를 찾아야함!
        else{
            int node = find(unionFindList.get(v));
            unionFindList.remove(v);
            unionFindList.add(v, node);
            return node;
        }
    }

    // 대표 노드 바꿔주는 작업 수행!
    public static void union(int v1, int v2){
        int v1CaptainNode = find(v1);
        int v2CaptainNode = find(v2); // 자기자신 반환! 또는 대표노드 반환함
        if(v1CaptainNode != v2CaptainNode){
            // 현재 자기자신의 대표 노드 지우고, v1대표 노드로 변경하는 작업 수행
            unionFindList.remove(v2CaptainNode);
            unionFindList.add(v2CaptainNode, v1CaptainNode);
        }
    }
}
