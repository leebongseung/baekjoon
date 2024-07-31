package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 10시 50분
 * 종료 시간 : 11시 50분
 *
 * 문제분석
 * - 간선에는 가중치, 양방향의 임의의 루트 있는 트리가 주어짐.
 * - 정점 u 를 루트로 하는 서브 트리에 속한 정점의 수를 출력하기
 * */
public class _15681 {

    public static int n, r, q;
    public static int[] subtreeSize;
    public static boolean[] visited;
    public static List<List<Integer>> lst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 수
        r = Integer.parseInt(st.nextToken()); // 루트 번호
        q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        lst = new ArrayList<>();
        for (int i = 0; i <= n; i++) lst.add(new ArrayList<>());

        // n-1줄에 거쳐 U V 형태로 트리에 속한 간선의 정보 주어짐.
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            lst.get(u).add(v);
            lst.get(v).add(u);
        }

        subtreeSize = new int[n+1];
        visited = new boolean[n + 1];

        dfs(r);

        // 각 쿼리의 서브 트리에 속한 정점의 수를 출력하기
        for(int i=0; i<q; i++){
            int apex = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(subtreeSize[apex]) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int dfs(int node) {
        visited[node] = true;
        int res = 1;

        for(int nextNode : lst.get(node)){
            if(!visited[nextNode]){
                res += dfs(nextNode);
            }
        }

        subtreeSize[node] = res;
        return res;
    }

}
