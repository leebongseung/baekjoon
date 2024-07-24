package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 4분
 * 종료 시간 : 10시 20분
 *
 * 문제분석
 * - n개의 랜선을 만들어야 함.
 * - 오영식이 k개의 랜선을 가지고 있음, 랜선은 길이가 제각각이다.
 * - k개의 랜선을 잘라서 n개의 랜선을 만들어야 함.
 * - 자른 랜선은 붙일 수 없음.
 * - n개 보다 많이 만드는 것도 n개를 만드는것에 포함됨!
 * - 이 때 만들 수 있는 최대 랜선의 길이를 구하는 프로그래밍 만들기
 *
 * 입력
 * - 1 <= k <= 10_000
 * - 1 <= n <= 1_000_000
 * - 랜선의 길이는 2^31 - 1 보다 작거나 같음
 * */
public class _1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 현재 가진 랜선 개수
        int n = Integer.parseInt(st.nextToken()); // 만들고자 하는 랜선 개수

        // 랜선 길이 입력받기
        int[] arr = new int[k];
        for(int i=0; i<k; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // 이분탐색으로 자르고자 하는 랜선길이 유추하기
        // left + right 를 하면 오버플로우가 날 수도 있음. right가 int 최대라서
        long left = 1;
        long right = arr[k-1];
        long res = 0;

        while(left <= right){
            long theLengthYouWantToCut = (left + right) / 2; // 자르고자 하는 길이

            // 자르고 남은 개수를 구하기
            int sum = 0;
            for(int i=0; i<k; i++){
                sum += arr[i] / theLengthYouWantToCut;
            }

            // 현재 총 개수가 n보다 크거나 같다 => 더 길게 잘라보자
            if(sum >= n){
                res = theLengthYouWantToCut;
                left = theLengthYouWantToCut + 1;
            }
            // 현재 총 개수가 n보다 작다 => 더 짧게 잘라보자
            else{
                right = theLengthYouWantToCut - 1;
            }
        }
        System.out.print(res);
    }
}
