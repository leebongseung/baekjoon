package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 분석
 * - n일동안 사용할 금액 계산, m번만 통장에서 돈 빼서 슴
 * - k원 인출하며, 모자라면 k금액 인출함.
 * - 다만 현우는 m숫자를 좋아함, 인출횟수인 m번을 맞추려고
 * 남은금액이 그날 사용할 금액보다 많더라도 짚어넣고 k원을 인출.
 *
 * 남은금액이 있더라도 k원을 다시 인출해서 m번을 맞춘다
 *
 * */
public class _6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 총 일 수
        int m = Integer.parseInt(st.nextToken()); // 인출횟수

        int[] arr = new int[n]; // 하루동안 얼마나 쓰는지 기록
        int min = 0;
        int max = 0;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(bf.readLine());
            min = Math.max(min, arr[i]);
            max += arr[i];
        }

        while(min <= max){
            int mid = (min + max) / 2;

            int sum = 0;
            int cnt = 0;
            // 남은금액이 있더라도 k원을 다시 인출해서 m번을 맞춘다
            for(int i=0; i<n; i++){
                if(sum + arr[i] > mid){
                    cnt ++;
                    sum = 0;
                }
                sum += arr[i];
            }
            if(sum != 0 ) cnt++;

            if(cnt <= m){
                max = mid-1;
            } else{
                // 인출횟수가 너무 많을 경우
                min = mid + 1;
            }
        }
        System.out.println(min);
    }
}
