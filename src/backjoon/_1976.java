package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 15분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 한국에는 n개의 도시가 있고, 임의의 두 도시사이엔 길 이 있을수도 없을수도 있음..
 * - 동혁이의 여행 일정이 주어졌을 때 여행가능한지 알아보기
 * - 예를들어 a-b, b-c, a-d, b-d, e-a 에 길이 존재할때
 * - 동혁이 계획 : e -> c -> b-> c -> d 라면
 * - e-a-b-c-b-c-b-d 라는 경로를 통ㅇ해 목적 달성 가능.
 *
 * 입력 크기
 * - n은 200 이하
 * - m은 1000 이하.
 * - i도시 j도시 연결의미 : 1이면 연결됨, 0이면 연결안됨.
 * */

public class _1976 {
    public static int[] unionFind;
    public static int n, m;
    public static List<List<Integer>> lst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 도시의 수
        m = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 수

        lst = new ArrayList<>();
        for(int i=0; i<n; i++){
            lst.add(new ArrayList<>());
        }

        // 유니온 파인드 수행하기
        unionFind = new int[n];
        for(int i=0; i<n; i++){
            unionFind[i] = i;
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int isConnect = Integer.parseInt(st.nextToken());
                // 연결 안되었을 경우
                if(isConnect == 0) continue;

                // isConnect == 1 일 경우(연결 될 경우)
                union(i, j);
            }
        }


        // 여행 계획에 수립되는지 확인하기
        st = new StringTokenizer(br.readLine());
        boolean isOk = true;
        int startNode = Integer.parseInt(st.nextToken()) - 1;
        for(int i=1; i<m; i++){
            int nextNode = Integer.parseInt(st.nextToken()) - 1;

            if(find(startNode) != find(nextNode)){
                isOk = false;
                break;
            }
            startNode = nextNode;
        }

        if(isOk) System.out.print("YES");
        else System.out.print("NO");
    }

    // 최고 정점을 찾아서 리턴하는 함수
    private static int find(int x){
        if(x == unionFind[x])
            return x;

        int nextNode = unionFind[x];
        int topNode = find(nextNode);
        unionFind[x] = topNode;
        return unionFind[x];
    }

    // 두개의 정점을 하나의 정점으로 통일시키는 과정
    private static void union(int x, int y){
        int topNodeX = find(x);
        int topNodeY = find(y);

        if(topNodeX != topNodeY){
            unionFind[topNodeY] = topNodeX;
        }
    }
}
