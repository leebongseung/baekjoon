package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 4시 16분
 * 종료 시간 : 5시 16분
 * <p>
 * 문제분석
 * - 마을엔 n의 정점
 * - m개의 간선이 존재 간선은 양방향임, 유지비가 존재, 임의의 두집사이 경로가 항상 존재
 * - 마을 이장은 마을을 두개의 분리된 마을로 분할할 계획
 * - 분리할 때 분리된 마을안에 집들이 서로 연결되어야 함.
 * - 각 분리된 마을에는 집이 하나 이상이어야함.
 * - 분리된 두 마을 사이에 없애기 가능.
 * - 분리된 마을 안에서도 길을 더 없애기 가능
 * - 유지비 최소로 만족하도록 길을 유지하시오.
 */


public class _1647 {
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }

    }

    static int[] unionFindArr;
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(start, end, weight));
        }

        unionFindArr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            unionFindArr[i] = i;
        }

        // 유지비 순으로 오름차순 정렬
        Collections.sort(edgeList);

        // 모든 집이 N - 1개의 길로 연결되도록 만듦.
        int ans = 0;
        int bigCost = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            // 사이클이 발생하는 간선은 제외.
            if (find(edge.start) == find(edge.end)) continue;

            ans += edge.weight;
            union(edge.start, edge.end);

            bigCost = Math.max(bigCost,edge.weight);
        }

        bw.write((ans - bigCost) + "\n");
        bw.flush();
        bw.close();
        bf.close();
    }

    public static int find(int x) {
        if (x == unionFindArr[x]) {
            return x;
        }

        return unionFindArr[x] = find(unionFindArr[x]);
    }

    public static void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        if (xParent != yParent) {
            unionFindArr[yParent] = xParent;
        }
    }

}
