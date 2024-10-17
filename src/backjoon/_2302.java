package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 시작 시간 : 10시 3분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 1~ n번의 번호가 매겨짐
 * - 왼쪽 또는 오른쪽 좌석으로 옮기기 가능!
 * - vip 이사람은 반드시 자기 좌석에 앉아야함.
 * - 사람들이 좌석에 앉는 서로 다른 방법의 가짓수를 구하는 프로그램만들기
 * <p>
 * 못품
 * - dp로 도전해볼 것
 */
public class _2302 {
    public static int n;
    public static int currentSize;
    public static int[] lst;
    public static boolean[] visited;
    public static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        currentSize = 0;
        lst = new int[n + 1];
        set = new HashSet<>();
        visited = new boolean[n + 1];
        int m = Integer.parseInt(bf.readLine());
        for(int i=0; i<m; i++){
            int num = Integer.parseInt(bf.readLine());
            lst[num] = num;
            visited[num] = true;
            currentSize++;
        }

        dfs(1);
        System.out.println(set.size());
    }

    public static void dfs(int num) {
        if(currentSize == n){
            StringBuilder str = new StringBuilder();
            for (int i : lst) {
                str.append(String.valueOf(i));
            }

            set.add(str.toString());
            return;
        }


        for(int i=num; i<=n; i++){
            if(visited[i]) continue;

            for(int j=-1; j<=1; j++){
                if(i+j <= 0 || i+j > n) continue;
                if(lst[i+j] != 0) continue;

                lst[i+j] = i;
                visited[i] = true;
                currentSize++;
                dfs(num + 1);
                currentSize--;
                visited[i] = false;
                lst[i+j] = 0;
            }
        }
    }
}
