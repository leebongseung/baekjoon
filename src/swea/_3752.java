package swea;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 5시
 * 종료 시간 : 5시 43분
 *
 * 문제분석
 * - 문제 n 개를 만듬.
 * - 각문제마다 배점이 다르다, 틀리면 0점, 맞으면 배점만큼 획득
 * - 받을 수 있는 점수로 가능한 경우의 수는 최대 몇개?
 *
 * 백트래킹으로 점수를 매겨 가능한 경우의 수 개수의 총 합을 구하자.
 * */
public class _3752 {
    public static int n;
    public static int[] arr;
    public static boolean[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tCase = Integer.parseInt(bf.readLine());

        for(int t = 1; t <= tCase; t++){
            n = Integer.parseInt(bf.readLine());
            arr = new int[n];
            dp = new boolean[100 * 100 + 1];

            StringTokenizer st=  new StringTokenizer(bf.readLine());
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

            dp[0] = true;
            int cnt = 1;
            for(int i=0; i<n; i++){
                for(int j= n*100 ; j >= 0 ; j--){
                    if(dp[j]){
                        if(! dp[j + arr[i]]) {
                            dp[j + arr[i]] = true;
                            cnt++;
                        }
                    }
                }
            }


            bw.write("#" + t +" ") ;
            bw.write(cnt + "\n");
        }

        bw.flush();
        bw.close();
    }

}
