package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 3시 47분
 * 종료 시간 : 4시 00분
 * <p>
 * 문제분석
 * - 엉우는 n개의 돌이 일렬로 놓여있는 돌다리에 있음.
 * - 돌다리의 돌에는 숫자가 하나씩 적혀있다.
 * - 숫자만큼 왼쪽 또는 오른쪽으로 점프 가능, 다리밖으로는 이동 불가
 * - 영우는 이 돌다리에서 방문 가능한 돌들의 개수를 알 =고 싶다.
 * - 방문 한다 == 현재 위치에서 다른 돌을 적절히 밟아 해당 위치로 이동 가능
 * - 영우가 방문 가능한 돌들의 개수를 출력하기
 */
public class _14248 {
    public static int n, startIdx, res; // 1차원 배열 크기, 시작 idx, 결과값
    public static int[] arr;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        startIdx = Integer.parseInt(br.readLine()) - 1;

        visited = new boolean[n];
        bfs(startIdx);
        System.out.println(res);


    }

    public static void bfs(int startIdx) {
        visited[startIdx] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(startIdx);

        while (!q.isEmpty()) {
            int poll = q.poll();

            int jumpSize = arr[poll];

            // 오른쪽으로 이동해보기
            if (poll + jumpSize < n && !visited[poll + jumpSize]) {
                q.add(poll + jumpSize);
                visited[poll + jumpSize] = true;
            }

            // 왼쪽으로 이동하기
            if (poll - jumpSize >= 0 && !visited[poll - jumpSize]) {
                q.add(poll - jumpSize);
                visited[poll - jumpSize] = true;
            }

            res++;
        }
    }
}
