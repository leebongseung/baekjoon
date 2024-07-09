package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 5시 37분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 회사에는 n개의 커피가 하나씩 있음.
 * - 각 커피에는 카페인 함유량 C_i 존재.
 * - n개의 커피 중 몇개를 골라서 정확히 K만큼의 카페인을 섭취하려고함.
 * - 창영이가 최소 몇개의 커피를 마셔야하나?
 * */

public class _22115 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 커피 - 카페인 개수
        int k = Integer.parseInt(st.nextToken()); // 목표 카페인 수

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][k+1]; // x : 커피 index, y 축: (key : 카페인 총 합, value : 개수)

        // x = 0 : 커피를 하나도 선택하지 않을 경우
        // 카페인 0을 제외한 1~ k 까지는 커피를 선택할 수 없음 -> 무한대로 표기하기
        for(int i=1; i<=k; i++){
                dp[0][i] = 1000;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=k; j++){
                // 현재 index의 커피를 선택할 수 있을 경우
                if(j - arr[i] >= 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], (dp[i - 1][j - arr[i]] + 1));
                } else{
                    // 현재 커피 선택못하는 경우
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.print(dp[n][k] == 0? -1 : dp[n][k]);
    }
}
