package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 10분
 * 종료 시간 : 10시 40분
 *
 * 문제분석
 * - k 길이가 9 인데 부등호 순서열과 일치할 경우 각 그룹의 최솟값, 최댓값을 출력해라.
 * - 각 숫자는 중복 되면 안되고 0~ 9 까지의 임의의 숫자여야함.
 * */
public class _2529 {
    private static int k;
    private static String min, max;
    private static boolean[] visited;
    private static Map<Integer, String> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 변수 초기화
        k = Integer.parseInt(bf.readLine());
        min = String.valueOf(Long.MAX_VALUE);
        max = String.valueOf(Long.MIN_VALUE);
        visited = new boolean[10]; // 0~9 까지 방문처리

        // 부등호 입력받기
        StringTokenizer st= new StringTokenizer(bf.readLine());
        for(int i=1; i<=k; i++) map.put(i, st.nextToken());
        dfs("", 0);
        System.out.println(max);
        System.out.println(min);

    }

    private static void dfs(String num, int depth){
        if(depth == k+1){
            min = Long.parseLong(min) > Long.parseLong(num) ? num : min;
            max = Long.parseLong(max) < Long.parseLong(num) ? num : max;
            return;
        }

        String code = map.get(depth);
        int preNum = num.isEmpty() ? -1 : Integer.parseInt(num.substring(depth-1, depth));
        for(int i=0; i<=9; i++){
            // 이미 방문한 숫자인 경우 탈락 처리
            if(visited[i]) continue;

            // depth == 0 일 경우 숫자가 1개도 없기 때문에 방문만 하고 continue
            if(depth == 0){
                visited[i] = true;
                num += i;
                dfs(num, depth + 1);
                num = num.substring(0, depth);
                visited[i] = false;
                continue;
            }

            // > 부등호일 경우
            if(code.equals(">") && preNum > i){
                num += i;
                visited[i] = true;
                dfs(num, depth + 1);
                visited[i] = false;
                num = num.substring(0, depth);
            }

            // < 부등호일 경우
            else if (code.equals("<") && preNum < i){
                num += i;
                visited[i] = true;
                dfs(num, depth + 1);
                visited[i] = false;
                num = num.substring(0, depth);
            }
        }
    }
}
