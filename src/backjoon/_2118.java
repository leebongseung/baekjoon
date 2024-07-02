package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 4시 50분
 * 종료 시간 : ?
 *
 * 문제 분석
 * - 1~n의 1차원 배열 존재
 * - 각각의 지점은 차례로 그리고 원형으로 연결, 즉 n 다음은 1 인덱스임
 * - 이 지점중 두개의 탑을 세우려고 함, 두 탑의 거리가 최대가 되도록 만들려고한다.
 * - 시계 방향 또는 반시계방향이 존재합니다.
 * - 시계 방향 또는 반시계 방향 중 작은 값을 거리라고 한다.
 * - 두 지점 사이의 거리가 주어질때, 두 탑 사이의 거리의 최댓값을 계산하기
 *
 * - 전체 거리의 총합은 10억 이하 == int로 가능
 * 누적합 문제
 * */
public class _2118 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // n=5라면, 0~1, 1~2, 2~3, 3~4, 4~0의 거리를 저장
        int[] arr = new int[n];
        int[] sumArr = new int[n+1];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sumArr[i+1] = sumArr[i] + arr[i]; // 0 : 0, 1번 부터 누적합 계산함,
        }

        int res = 0; // 최댓값 배출하는 친구
        int totalDistance = sumArr[n];
        int left = 0;
        int right = 1;
        while(left < n){
            // 거리가 중간 지점이 되는 right 지점 찾기
            while(right < n
                    && sumArr[right + 1] - sumArr[left] <= totalDistance / 2){
                right++;
            }

            int clockwise = sumArr[right] - sumArr[left]; // 시계방향 계산
            int counterclockwise = totalDistance - clockwise; // 반시계방향 계산
            res = Math.max(res, Math.min(clockwise,
                    counterclockwise));

            left ++;
        }
        // 배열중 가장 큰 값 출력하면 됨
        System.out.println(res);
    }
}
