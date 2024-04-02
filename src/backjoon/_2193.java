package backjoon;

import java.util.*;
import java.io.*;

/**
 * 시작 시간 : 9시 10분
 * 종료 시간 : ??
 * <p>
 * 1. 문제분석
 * 이진수 중 특별한 성질 이친수
 * - 이친수는 1로 시작한다.
 * - 이친수는 1이 연속 두번 나타나지않는다.즉 11을 부분문자열로 안가짐
 * N 90이하가 주어졌을 때 N자리 이친수의 개수를 구하는 프로그램을 만드시오
 * <p>
 * dp 문제임
 */
public class _2193 {
    public static long[] arr = new long[91];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }


        System.out.print(arr[n]);
    }
}
