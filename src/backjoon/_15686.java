package backjoon;
/**
 * 시작 시간 : 2시
 * 종료 시간:
 * <p>
 * 문제분석
 * - n*n 배열
 * - 도시의 각칸은 빈 칸, 치킨집, 집 중 하나임.
 * - 도시의 칸은 (r,c)의 형태
 * - r행 c 열(1부터 인덱싱 시작)
 * <p>
 * - 치킨 거리 = 집과 가장 가까운 치킨집사이의 거리
 * - 집 기준 으로 정해짐
 * - 각각의 집은 치킨 거리를 가짐
 * - 도시의 치킨 거리는 모든 집의 치킨 거리의 합
 * <p>
 * - (r1, c1) 과 (r2, c2)사이의 거리는 |r1-r2| + |c1- c2|로 구함.
 * <p>
 * - 0은 빈칸
 * - 1은 집
 * - 2는 치킨집
 * <p>
 * - 수익 증가를 위해 일부 치킨집 폐업함
 * - 수익을 많이 낼 수 있는 치킨 집의 개수는 최대 M
 * - 도시의 치킨집중 최대 M을 고르고, 나머지 모두 폐업
 * - 도시의 치킨 거리가 가장 작게 될 지 구하시오.
 * <p>
 * <p>
 * 추천 풀이방법 백트래킹
 * 모든 조합을 다 계산하는 dfs 방식으로 하면 된다?! 는데
 * 0 -> 1 -> 2
 */

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _15686 {
    public static int[][] arr;
    public static List<int[]> chickenList = new ArrayList<>(); // 0: x축, 1: y축
    public static List<int[]> homeList = new ArrayList<>();// 0: x축, 1: y축
    public static boolean[] visited;
    public static int n; // 2차원 배열 크기
    public static int m; // 치킨집 선택 개수
    public static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 입력받기
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 1) homeList.add(new int[]{i, j});
                else if (arr[i][j] == 2) chickenList.add(new int[]{i, j});
            }
        }

        visited = new boolean[chickenList.size()];
        dfs(0, 0);

        System.out.println(res);
    }

    public static void dfs(int idx, int depth) {
        if (depth == m) {
            int ans = 0;
            for (int[] homeIdx : homeList) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < chickenList.size(); i++) {
                    // 선택받지 못한 치킨집의 경우
                    if (!visited[i]) continue;

                    int[] chickenIdx = chickenList.get(i);
                    int dx = Math.abs(homeIdx[0] - chickenIdx[0]);
                    int dy = Math.abs(homeIdx[1] - chickenIdx[1]);
                    min = Math.min(min, dx + dy);
                }
                ans += min;
            }
            res = Math.min(res, ans);
            return;
        }

        for(int i= idx; i< chickenList.size(); i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

}
