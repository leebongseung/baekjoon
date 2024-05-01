package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 5분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 1*N 크기의 미로에 갇힘.
 * - 각 칸에는 정수가 쓰여 있다
 * - i번째 칸에 쓰여 있는 수를 A_i라고 할 때, 재환이는 A_i이하 만큼 오른쪽으로 떨어진 칸으로 한번에 점프 가능.
 * - 지금 미로의 가장 왼쪽 끝에 있고, 오른쪽 끝으로 가려고 함.
 * - 만약 오른쪽 끝으로 갈 수 없는 경우 -1 출력하기
 *
 * - 4, 5, 6 번 칸 중 하나로 점프 가능하다.
 * - 최소 몇 번 점프해서 갈 수 잇을까?
 *
 *
 * */
public class _11060 {
    public static boolean[] visited;
    public static int[] arr;
    public static int n;
    public static int res = -1;
    public static void bfs(){
        Queue<int []> q = new LinkedList<>();

        q.add(new int[] {0, 0});
        visited[0] = true;
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int idx = poll[0];
            int depth = poll[1];

            if(idx == n-1) res = depth;

            for(int i=arr[idx]; i> 0; i--){
                if(idx + i >= n) continue;

                if(!visited[idx + i]){
                    visited[idx + i] = true;
                    q.add(new int[]{idx + i, depth + 1});
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bfs();
        System.out.println(res);
    }
}
