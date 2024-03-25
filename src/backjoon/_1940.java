package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 11시 25분
 * 종료 시간 : ???
 * */
public class _1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 재료 개수
        int m = Integer.parseInt(bf.readLine()); // 갑옷이 되는 총 합

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int idx = 0;
        while(st.hasMoreTokens()){
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        // 오름차순으로 정렬
        Arrays.sort(arr);

        // 투 포인터 이용하기
        int start = 0;
        int end = n-1;
        int res = 0;
        while(end > start){
            int sum = arr[start] + arr[end];
            if(sum == m){
                res ++;
                start++;
                end --;
            } else if (sum < m ){
                start ++;
            } else if (sum > m){
                end --;
            }
        }
        System.out.println(res);
    }
}
