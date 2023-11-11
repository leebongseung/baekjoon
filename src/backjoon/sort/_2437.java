package backjoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*  시작 시간 : 10시 15분
 *   종료 시간 : 11시 15분
 * */
public class _2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 올라갈수 있는 저울의 개수
        int[] arr = new int[n]; // 저울 무게를 나타내는
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int idx = 0;

        while (st.hasMoreTokens()) {
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = 0;//누적합

        for(int i =0; i<n; i++){
            if(sum+1 >= arr[i]){
                sum += arr[i];
            }else{
                break;
            }
        }

        System.out.println(sum+1);

    }
}
