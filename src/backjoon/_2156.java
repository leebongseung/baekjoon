package backjoon;
/**
 * 시작 시간 : 10시 3분
 * 종료 시간 : 10시 55분
 * <p>
 * 문제 분석
 * - 테이블 당 포도주가 일렬로 배치됨
 * - 규칙 1. 포도주 선택하면 그 잔에 있는 포도주 다 마시고, 마신 후 원래 위치로
 * - 규칙 2. 연속으로 놓여있는 3잔 모두 마실 수 없음
 * <p>
 * - 효주를 도와 가장 많은 포도주를 마실 수 있도록 하는 프로그램 만들기!
 * <p>
 * - 6, 10, 13, 9, 8, 1
 * - 1,2, 4,5 => 33 으로 가장 많음.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _2156 {
    public static int[] arr = new int[10000];
    public static int n = 0;
    public static int[] dp = new int[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        dp[0] = 0;
        dp[1] = arr[0];
        dp[2] = arr[0] + arr[1];

        List<Integer> lst = new ArrayList<>();
        for(int i = 3; i <= n; i++){
            lst = new ArrayList<>();
            lst.add(dp[i-1]);
            lst.add(dp[i-2] + arr[i-1]);
            lst.add(dp[i-3] + arr[i-2] + arr[i-1]);

//            System.out.println("i = " + i);
//            for (Integer integer : lst) {
//                System.out.print(integer + " ");
//            }
//            System.out.println();

            dp[i] = Collections.max(lst);
        }

        System.out.println(dp[n]);
    }

}
