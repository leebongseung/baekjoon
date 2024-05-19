package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 7시 36분
 * 종료 시간 : 8시 9분
 *
 * 문제분석
 * - 스타크래프트
 * - 각각 건설을 시작하여 완성할 때 까지 시간이 존재
 * - 따라서 건물을 건설하는데 이전 건물을 먼저 건설하여야하는 조건이 존재
 *
 * - 즉 건물당 걸리는 최대 소요기간을 저장함.
 * - dp[i] = Math.max(dp[j], dp[k]) + i건물 소요시간
 * 위의 그래프처럼 i에 해당하는 사전 건물중 가장 큰값 + i건물 소요시간을 더하면 된다.
 *
 * */
public class _1005 {
    public static int n;
    public static int k; // 건물간 건설순서 규칙 총 k개
    public static int[] dp; // 각건물이 걸리는 최댓값을 dp로 저장
    public static int[] arr; //건물당 걸리는 소요 시간
    public static List<Integer>[] lst;
    public static int findIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        for(int tCase = 0; tCase < t; tCase++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            dp = new int[n+1];
            arr = new int[n+1];
            lst = new ArrayList[n+1];
            Arrays.fill(dp, -1);
            for(int i=1; i<=n; i++) lst[i] = new ArrayList<>();

            st = new StringTokenizer(bf.readLine());
            for(int i=1; i<= n; i++) arr[i] = Integer.parseInt(st.nextToken());

            // 현재 빌딩 짓기 위해 이전 빌딩이 존재하는지 검사하기
            for(int i=0; i<k; i++){
                st = new StringTokenizer(bf.readLine());
                int preBuilding = Integer.parseInt(st.nextToken());
                int nextBuilding = Integer.parseInt(st.nextToken());
                lst[nextBuilding].add(preBuilding);
            }

            findIdx = Integer.parseInt(bf.readLine());

            findBuildingCnt(findIdx);
            System.out.println(dp[findIdx]);
        }
    }

    public static void findBuildingCnt(int num){
        int BuildingCnt = arr[num];
        int preCnt = 0;

        // 이전 빌딩들 중 건설 시간이 가장 긴것 찾기
        for(int val : lst[num]) {
            if(dp[val] == -1) findBuildingCnt(val);

            preCnt = Math.max(preCnt, dp[val]);
        }

        // 이전 빌딩 건설 시간 + 현재 빌딩 건설 시간 저장
        dp[num] = preCnt + BuildingCnt;
    }
}
