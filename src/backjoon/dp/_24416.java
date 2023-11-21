package backjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*  시작 시간 : 10시 30분
 *   종료 시간 : 10시 48분
 * */
public class _24416 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[] arr = new long[41]; // 메모이제이션

        int cnt = 0;
        for(int i=3; i<= n; i++){
            arr[i] = arr[i-1] + arr[i-2];
            cnt++;
        }
        System.out.print(arr[n] +" " + (n-2));
    }
}