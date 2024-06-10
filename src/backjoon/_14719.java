package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 11시 40분
 * 종료 시간 : 12시 22분
 * <p>
 * 문제 분석
 * - 비가 오면 빗물 사이에 물이 고임
 * - 고이는 빗물의 총량은 얼마?
 * <p>
 * 빡구현문제
 */
public class _14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int h = Integer.parseInt(st.nextToken()); // 가로 길이
        int w = Integer.parseInt(st.nextToken()); // 세로 길이

        int[] arr = new int[w];

        // 가장 큰 값과 큰 값의 idx 찾기
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        for(int i=1; i<w-1; i++){
            int leftVal = 0;
            int rightVal = 0;

            // 젤 좌측 벽이 있는지 확인하기
            for(int j=0; j<i; j++){
                leftVal = Math.max(leftVal, arr[j]);
            }

            // 우측 벽이 있는지 확인하기
            for(int j=i+1; j <w; j++){
                rightVal = Math.max(rightVal, arr[j]);
            }

            // 현재 칸에서 최선의 선택 구하기!
            if(rightVal < arr[i] || leftVal < arr[i]) continue;
            res += Math.min(leftVal, rightVal) - arr[i];
        }

        System.out.println(res);
    }
}
