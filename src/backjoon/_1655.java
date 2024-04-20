package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 시작 시간 : 2시
 * 종료 시간 : ??
 *
 * 문제분석
 * - 백준이 정수하나씩 외침, 동생은 말한 수 중 중간값을 말해야함.
 * - 백준이가 외친 수가 짝수라면 중간에 있는 두 수 중 작은수 말하기
 *
 * - 입력은 -1만 1만 보다 작거나 같음
 * - 총 개수는 100,000만 개
 *
 * 이게 왜 pq지?.. 음
 * - 정렬을 수행하여 중간값에 해당하는 걸 반환해야함.
 * 고로 pq 로 정렬수행하면서 중간값 반환하기 가능한가? 가능할듯
 * idx 기준으로 평균 idx를 찾고 pq 공식에 넣어두자
 * */
public class _1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n]; // 배열

        int sum = 0;
        int avg = 0;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(bf.readLine());
            sum += arr[i];
            avg = sum / (i+1);

            if(i == 0){
                System.out.println(arr[i]);
                continue;
            }
            PriorityQueue<int[]> q = new PriorityQueue<>();

            for(int j=0; j<=i; j++)  q.add(new int[]{Math.abs(arr[j] - avg), j});
            if(q.size() % 2 == 0){
                // 짝수라면
                int[] poll1 = q.poll();
                int[] poll2 = q.poll();

                if(arr[poll1[1]] < arr[poll2[1]]){
                    System.out.println(arr[poll1[1]]);
                } else{
                    System.out.println(arr[poll2[1]]);
                }

                q.add(poll1);
                q.add(poll2);
            } else {
                // 홀수라면
                int[] poll = q.poll();
                System.out.println(arr[poll[1]]);
                q.add(poll);
            }

        }

    }
}
