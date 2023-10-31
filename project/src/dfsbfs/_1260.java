package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*  시작 시간 : 2시 20분
*   종료 시간 : 2시 46분
* */
public class _1260 {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수
        int v = Integer.parseInt(st.nextToken())-1; // 시작 정점

        // 2차원 리스트
        List<List<Integer>> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            arr.add(new ArrayList<Integer>());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken())-1;
            int addNum = Integer.parseInt(st.nextToken())-1;

            arr.get(num).add(addNum);
            arr.get(addNum).add(num);
        }

        dfs(arr, v);
        bfs(arr, v);
    }

    private static void dfs(List<List<Integer>> arr, int v) {
        StringJoiner sb = new StringJoiner(" ");
        boolean[] visited = new boolean[arr.size()];
        Arrays.fill(visited, false);

        Stack<Integer> stack = new Stack();
        stack.push(v);

        while(!stack.isEmpty()){
            int num = stack.pop();
            if(visited[num]) continue;
            visited[num] = true;

            for (Integer i : arr.get(num).stream().sorted((o1, o2) -> o2-o1).collect(Collectors.toList())) {
                stack.push(i);
            }

            sb.add(Integer.toString(num+1));
        }
        System.out.println(sb);
    }
    public static void bfs(List<List<Integer>> arr, int v){
        StringJoiner sb = new StringJoiner(" ");
        boolean[] visited = new boolean[arr.size()];
        Arrays.fill(visited, false);

        Queue<Integer> q = new LinkedList<>();
        q.add(v);

        while(!q.isEmpty()){
            int num = q.poll();
            if(visited[num]) continue;
            visited[num] = true;

            for (Integer i : arr.get(num).stream().sorted().collect(Collectors.toList())) {
                q.add(i);
            }

            sb.add(Integer.toString(num+1));
        }
        System.out.print(sb);
    }
}
