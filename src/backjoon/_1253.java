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

        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int idx = 0;
        while(st.hasMoreTokens()){
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        // 정렬 수행하기
        Arrays.sort(arr);

        // 투포인터를 이용해서 현재 idx가 두개의 합으로 나오는지 확인
        int res = 0;
        for(int i=0; i<n; i++){
            int start = 0;
            int end = n-1;
            long num = arr[i];
            while(start < end){
                long sum = arr[start] + arr[end];
                if(sum > num){
                    end--;
                } else if(sum < num){
                    start++;
                } else if(sum == num){
                    if(start != i && end != i){
                        res++;
                        break;
                    } else if(start == i) start++;
                    else if (end == i) end--;
                }
            }
        }
        System.out.print(res);
        bf.close();
    }
}
