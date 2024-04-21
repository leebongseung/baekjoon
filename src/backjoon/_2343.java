package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 43분
 * 종료 시간 :??
 * <p>
 * 문제분석
 * - 총 n개의 강의가 들어감, 강의의 순서가 바뀌면안됨
 * - i 번째 강의와 j번째 강의를 녹화하려면
 * 그 사이 모든 강의도 같이 녹화해야함.
 * - m개의 블루레이에 모든 기타 강의를 녹화하기로 함
 * - 블루레이의 크기를 최소화 할려고 함.
 * - m개의 블루레이 모두 같은 크기다.
 * <p>
 * 각 강의의 길이가 분 단위로 주어짐, 이때 블루레이의
 * 크기 중 최소를 구하는 프로그램을 구하시오.
 * <p>
 * 문제 결론
 * - 이문제는 MIN, MAX의 초기값 설정이 상당히 중요하다.
 * - 또한 res 출력하는 과정에서 둘이 크로스 될때 min을 선택하는 기준을 잘 고려해야한다.
 */
public class _2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 강의의 수
        int m = Integer.parseInt(st.nextToken()); // 블루레이 크기
        int[] arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        int max = Integer.MAX_VALUE;
        int min = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min = Math.max(arr[i], min);
            max += arr[i];
        }

        while (min <= max) {
            int mid = (max + min) / 2;
            int sum = 0;
            int cnt = 0; // 블루레이 개수
            for (int i = 0; i < n; i++) {
                if (sum + arr[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum += arr[i];
            }

            if (sum != 0) cnt++;

            if (cnt <= m) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
/*
            System.out.print("cnt = " + cnt);
            System.out.print(", mid = " + mid);
            System.out.print(", max = " + max);
            System.out.println(", min = " + min);*/
        }
        System.out.println(min);
    }
}
