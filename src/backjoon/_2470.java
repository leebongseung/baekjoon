package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 12시 41분
 * 종료 시간 : 1시 06분
 * <p>
 * 문제 분석
 * - 산성 용액은 1부터 10억 정수
 * - 알칼리성 용액은 -1부터 -10억 정수
 * <p>
 * - 두 용액을 혼합하여 0에 가까운 용액을 만들고자 함
 * - 두 용액을 출력하시오.
 */
public class _2470 {
    public static int n;
    public static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new long[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        // 투포인터 이용하기
        int left = 0;
        int right = n - 1;
        long min = Long.MAX_VALUE;
        int[] minIdx = new int[2]; //left, right 저장
        while (left < right) {
            long sum = arr[left] + arr[right];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                minIdx[0] = left;
                minIdx[1] = right;
            }
            // 음수일 경우
            if (sum < 0) left++;
            // 양수일 경우
            else right--;
        }

        System.out.print(arr[minIdx[0]] + " " + arr[minIdx[1]]);

    }
}
