package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 10시 6분
 * 종료 시간 : 11시 3분
 *
 * 문제 분석
 * -  1~n까지 문제로 구성된 문제집 풀이
 * - 1번 쉬운문제, n번 가장 어려운 문제
 * 문제풀이 방법
 * 1. n개의 문제는 모두 풀어야 함.
 * 2. 먼저 푸는것이 좋은 문제가 있는 문제는, 반드시 먼저
 * 풀어야함.
 * 3. 가능하면 쉬운 문제부터 (pq인가?)
 *
 * */
public class _1766 {
    public static int n; // 문제의 수
    public static int m; // 문제 정보 수
    // 선행 수행되는 문제 저장하는 리스트
    public static List<List<Integer>> lst = new ArrayList<>();
    // 위상정렬 arraylist : 진입(나를 가르키는 개수를 저장한다)
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            lst.add(new ArrayList<>());
        }

        // 선행 수행 문제 저장하는 리스트
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lst.get(start).add(end);
            // 진입하는 노드개수 증가
            arr[end]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a- b);
        for(int i=1; i <= n; i++){
            if (arr[i] == 0){
                pq.add(i);
            }
        }
        // 진입개수 0인 친구를 먼저 선택하기
        while(!pq.isEmpty()){
            int node = pq.poll();

            // 현재 노드 기준으로 linked 리스트 탐방하기
            for(int next : lst.get(node)){
                arr[next]--;
            }

            // 방문 처리
            System.out.print(node + " ");
        }
    }
}
