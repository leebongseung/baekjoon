package backjoon;

import java.util.*;
import java.io.*;

/**
 * 시작 시간 : 5시 13분
 * 종료 시간 : 5시 37분
 *
 * 문제분석
 * - 방향없는 그래프의 연결 요소의 개수 구하기
 *
 * 입력
 * - 정점 n개(1000이하) 간선 m(n*n-1)/2개 주어짐
 * - 둘째줄부터 간선 끝점 u와 v가 주어짐(u!= v)
 * */
public class _11724 {
    public static int res = 0;
    public static int n =0;
    public static List<List<Integer>> arr = new ArrayList<>();
    public static void main(String args[]) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 그래프를 만들어야겠지
        // 정점을 연결하기!
        for(int i =0; i<=n; i++){
            arr.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr.get(first).add(end);
            arr.get(end).add(first);
        }

        // dfs 탐색을 통해 방문처리를 하고 연결된 요소의 개수 count 및 출력하기
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);
        visited[0] = true;
        for(int i =1; i<= n; i++){
            if(!visited[i]){
                res ++;
                visited[i] = true;
                dfs(visited, i);
            }
        }
        bw.write(String.valueOf(res));
        bw.flush();
    }

    public static void dfs(boolean[] visited, int index){
        Stack<Integer> stack = new Stack<>();
        List<Integer> lst = arr.get(index);
        for(int i : lst){
            // 방문 안했을 경우 stack 에 짚어넣기!
            if(!visited[i]) {
                stack.push(i);
            }
        }

        while(!stack.isEmpty()){
            int num = stack.pop();

            if(!visited[num]){
                visited[num] = true;
                for(int i : arr.get(num)){
                    if(!visited[i]) stack.push(i);
                }
            }
        }

    }
}
