package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 17분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 어떤 n개의 수가 있음.
 * - 그런데 중간에 수의 변경이 번번히 일어남, 어떤 부분의 합을 구하려고함.
 * - 1,2,3,4,5 가 있을 때
 * - 3번째를 6으로 변경, 2~5까지 합을 구하기 -> 17 출력
 * - 5번째를 2으로 변경, 3~5번째 합을 구하기 -> 12 출력
 *
 * 입력
 * - 수의 개수 n(1_000_000)만개
 * - m 변경횟수 :최대 10_000
 * - k 구간합구하는 횟수 : 최대
 *
 * 문제 풀이
 * 타입을 long으로 두어야할 것같다.
 * */
public class _2042 {
    public static long[] num, sum;
    public static int n,m,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수의 개수
        m = Integer.parseInt(st.nextToken()); // 변경 횟수
        k = Integer.parseInt(st.nextToken()); // 구간합 구하는 횟수

        num = new long[n];
        sum = new long[n+1];
        sum[0] = 0;
        for(int i=0; i<n; i++){
            num[i] = Long.parseLong(br.readLine());
            sum[i + 1] = sum[i] + num[i];
        }

        for(int i=0; i<m + k ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switch(a){
                case 1:
                    long c = Long.parseLong(st.nextToken());
                    // b번째 수를 c로 바꾸기
                    changeNum(b - 1, c);
                    break;
                case 2:
                    // b번째 수 ~ c번째수의 합을 구하기
                    int idx = Integer.parseInt(st.nextToken());
                    System.out.println(sum[idx] - sum[b-1]);
                    break;
            }
        }
    }

    // b 번째 수를 c의 값으로 변경하는 메서드
    private static void changeNum(int b, long c) {
        long originNum = num[b];
        num[b] = c;
        for(int i = b + 1; i <= n; i++){
            sum[i] -= originNum;
            sum[i] += c;
        }
    }
}
