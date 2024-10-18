package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 13분
 * 종료 시간 : 10시 44분
 *
 * 문제분석
 * - T시간동안 스터디를 진행함.
 * - 참가자들의 만족도를 구해 시간 만족도가 최대를 구함
 * 예제
 * - 4시간 동안 스터디 진행
 * - 1번은 0~6시간에 스터디 가능
 * - 2번은 1~3, 4~6시간에 스터디 가능
 * - 3번은 4~8 시간에 스터디 가능.
 * - 2~6사이에 진행한다면 시간 만족도는?
 *      4+(1+2) + 2 = 9 임 즉 시간만족도 최대
 * 출력
 * - 시간만족도가 최대인 시간을 찾아, 여러개라면?
 * 가장 빠른 시간을 출력하기
 *
 * */
public class _23295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 참가자수
        int t = Integer.parseInt(st.nextToken()); // 스터디시간(슬라이딩 윈도우 크기)

        // 0번째 인덱스를 사용하지 않음.
        int[] arr = new int[100001];
        int end = 0;
        for(int i=0; i<n; i++){
            int k = Integer.parseInt(br.readLine()); //가능한 시간덩어리
            // 시간은 최대 0~ 100,000만 까지임.
            for(int j=0; j<k; j++){
                st = new StringTokenizer(br.readLine());
                int startTime = Integer.parseInt(st.nextToken());
                int endTime = Integer.parseInt(st.nextToken());
                end = Math.max(end, endTime);

                while(startTime < endTime){
                    arr[startTime] ++;
                    startTime++;
                }
            }
        }

/*        // debug
        for(int i=0; i<10;i++)
            System.out.print(arr[i] + " ");
        System.out.println();
        System.out.println("====");*/

        // 슬라이딩 윈도우 구현하기
        int max = Integer.MIN_VALUE;
        int startTime = -1;
        int endTime = -1;
        for(int j=0; j <= end - t; j++){
            int sum = 0;
            for(int z=j; z< j+t; z++){
                sum += arr[z];
            }
            if (max < sum){
                max = sum;
                startTime = j;
                endTime = j + t;
            }
        }

        System.out.print(startTime + " " + endTime);
    }
}
