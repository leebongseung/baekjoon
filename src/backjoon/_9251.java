package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 시작 시간 : 10시 8분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 두 수열 중 모두의 부분 수열이 되는 수열 중 가장 긴 것 찾는 문제.
 *
 * 입력 크기
 * str의 크기는 최대 1000자.
 * 가능한 길이 0~999자
 * */
public class _9251 {
    public static String str1, str2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        lcs(dp);
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("===");
        System.out.println(dp[str1.length()][str2.length()]);
    }
    public static void lcs(int[][] dp){
        for(int i=1; i<= str1.length(); i++){
            for(int j=1; j<=str2.length(); j++){
                if(str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
}
