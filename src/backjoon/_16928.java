package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 9시 23분
 * 종료 시간 : 9시 38분
 *
 * 문제 분석
 * - bfs 알고리즘이라고 생각이 듦
 * */
public class _16928 {
    public static int n; // 사다리 수 -> 작은 수에서 ㅡ큰 수로 이동
    public static int m; // 뱀의 수 -> 큰 수에서 작은 수로 이동


    public static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        boolean[] visited = new boolean[101];

        int startIdx = 1;
        visited[startIdx] = true;
        Queue<int[]> q = new LinkedList<>(); // 0 : 위치, 1 : 주사위 횟수
        q.add(new int[]{startIdx, 0});

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int idx = poll[0];
            int rollCnt = poll[1];

            if(idx == 100){
                return rollCnt;
            }

            // 사다리 또는 뱀 타는 경우
            if(map.containsKey(idx)){
                idx = map.get(idx);
                visited[idx] = true;
            }

            // 주사위 굴리기
            for(int i=1; i<=6; i++){
                if(idx + i <= 100 && !visited[idx + i]){
                    visited[idx + i] = true;
                    q.add(new int[]{idx + i , rollCnt + 1});
                }
            }
        }

        return -1;
    }
}
