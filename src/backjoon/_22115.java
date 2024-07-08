package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][k+1]; // x : 커피 index, y 축: (key : 카페인 총 합, value : 개수)
        for(int i=0; i<n; i++){
            for(int j=0; j<=k; k++){
                if(i==0 || j==0)continue;

                if(j - arr[i] <= 0) continue;
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j - arr[i]] + 1);
            }
        }
        System.out.println(dp[n-1][k]);
    }
}
