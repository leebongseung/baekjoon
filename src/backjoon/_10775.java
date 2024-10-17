package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 10시 5분
 * 종료 시간 : 10시 52분
 *
 * 문제분석
 * - 신승원의 생일임
 * - 박승원은 인천공항에서 신승원에게 선물로 인천국제공항을 줌.
 * - 공항에는 G개의 게이트 존재(1~ G개)
 * - 공항에는 P개의 비행기가 순서대로 도착할 예쩡.
 * - i번째 비행기를 1번~ g_i번째 게이트 중 영구적으로 도킹하려고 함.
 * - 비행기가 어느 게이트에도 도킹할 수 없다면 공항이 폐쇄됨, 이후 비행기가 도착못한다.
 * -
 * */
public class _10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] arr = new int[p];
        boolean[] visited = new boolean[g + 1]; // 공항 도킹 현황
        int lastIdx = g;

        for(int i=0; i<p; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }


        int res = 0;
        for(int i=0; i<p; i++){
            // arr[i]값을 현재 공항 위치에 값을 짚어넣기!
            int g_i = arr[i];

            boolean isOk = false;
            for(int j = Math.min(g_i, lastIdx); j >= 1; j--){
                // 1. 만약에 그 위치에 값이 있다면? 앞으로 이동 (값이 없다면?)
                if(visited[j]) continue;
                if(j == lastIdx){
                    lastIdx = j-1;
                }

                isOk = true;
                visited[j] = true;
                break;
            }

            if(!isOk) break;
            res++;
        }
        System.out.print(res);
    }
}
