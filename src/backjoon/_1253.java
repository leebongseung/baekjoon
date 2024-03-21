package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* 시작 시간 : 11시 2분
 * 종료 시간 : ???
* */
public class _1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        long[] arr = new long[n];
        int idx = 0 ;
        while(st.hasMoreTokens()){
            arr[idx++] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long res = 0L;
        for(int i=0; i<n; i++){
            int start = 0;
            int end = n-1;

            while(start < end){
                long num = arr[start] + arr[end];

                if(num == arr[i]){
                    if(start != i && end != i){
                        res ++;
                        break;
                    } else if(start == i){
                        start ++;
                    } else if(end == i){
                        end --;
                    }

                } else if (num <= arr[i]){
                    start++;
                } else if (num >= arr[i]){
                    end--;
                }
            }
        }
        System.out.print(res);
    }
}
