package backjoon;

import java.rmi.ConnectIOException;
import java.util.*;
import java.io.*;

/**
 * 문제 분석
 * - 최소 스패닝 트리 구하는프로그램 만들기
 * - 정점 v 는 10000 이하
 * - 간선 e 는 100,000 이하
 * - e 개 줄에는 간선에 대한 세 정수 a, b, c
 *  - a 번정점과 b번 정점이 가중치 c인 간선으로 연결되어 있다는 의미
 *  - c는 절댓값 1,000,000 이하임.
 * */
public class _1197 {
    public static int v;
    public static int e;
    public static List<pEdge> lst = new ArrayList<>();
    public static List<Integer> unionFindLst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // 유니온 파인드 배열 초기화
        for(int i=0; i<v; i++){
            unionFindLst.add(i);
        }

        // 에지 리스트 만들기
        for(int i=0; i< e; i++){
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            lst.add(new pEdge(node1,node2,weight));
        }

        // 오름차순으로 가중치 정렬하기
        Collections.sort(lst);

//        for (pEdge pEdge : lst) {
//            System.out.print(pEdge.weight +" ");
//        }
//        System.out.println();
//        System.out.println("======================");

        // MST 계산하기
        int numberOfVertices = v-1;
        int sum =0;
        while(numberOfVertices > 0){
            pEdge remove = lst.remove(0);
            int node1 = find(remove.start);
            int node2 = find(remove.end);

            // 순환 참조가 되어버림
            if(node1 == node2) continue;

            union(node1, node2);
            sum += remove.weight;
            numberOfVertices--;

/*            System.out.println("remove = " + remove);
            // 순환 참조 아닐 경우
            for (Integer integer : unionFindLst) {
                System.out.print(integer+ " ");
            }
            System.out.println();*/
        }

        System.out.println(sum);
    }


    // 대표자를 찾는 메서드 재귀로 구현
    public static int find(int v){
        if(v == unionFindLst.get(v)) return v; // 대표자가 자기 자신일 경우
        else{ // 대표자가 자기자신이 아님, 그러면 그 대표자의 대표자가 누구인지 봐야지!
            Integer parent = find(unionFindLst.get(v));
            unionFindLst.remove(v);
            unionFindLst.add(v, parent);
            return parent;
        }
    }

    public static void union(int start, int end){
        if(unionFindLst.get(start).equals(unionFindLst.get(end))) return;

        // 부모 노드 짚어넣기
        unionFindLst.remove(end);
        unionFindLst.add(end, start);
    }

    public static class pEdge implements Comparable<pEdge>{
        int start;
        int end;
        int weight;

        public pEdge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(pEdge o){
            return this.weight - o.weight;
        }

        @Override
        public String toString(){
            return this.start +", " + this.end +", " + this.weight;
        }
    }
}