package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 20분
 * 종료 시간 : 10시 25분
 *
 * 문제분석
 * - n개의 정수로 이루어진 배열 a가 주어짐.
 * - 배열에 정수의 순서를 적절히 바꿔 다음식을 최댓값을만들자
 * */
public class _10819 {
    public static int n;
    public static int res = Integer.MIN_VALUE;
    public static int[] arr;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        dfs(new ArrayList<>());
        System.out.println(res);

    }

    public static void dfs(List<Integer> tmp){
        if(tmp.size() == arr.length){
            int sum = 0;
            for(int i=0; i<n-1; i++){
                sum += Math.abs(tmp.get(i) - tmp.get(i+1));
            }

            res = Math.max(res, sum);
            return;
        }

        for(int i=0; i<n; i++){
            if(visited[i]) continue;

            int idx = tmp.size();

            visited[i] = true;
            tmp.add(arr[i]);
            dfs(tmp);
            tmp.remove(idx);
            visited[i] = false;
        }
    }
}
