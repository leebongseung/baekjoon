package backjoon.binary_search;
/*  시작 시간 : 9시 21분
    종료 시간 : 10시 21분
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 나무 개수
        long m = Integer.parseInt(st.nextToken()); // 상근이가 가져올 나무 길이


        int[] arr = new int[n];
        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // 절단할 최소, 최대 높이
        int start = 0;
        int end = arr[n-1];

        while(start <= end){
            int mid = (start + end) / 2; // 절단할 길이
            long total = 0;
            //현재 나무 길이
            for (int t : arr) {
                if (t > mid) {
                    total += t - mid;
                }
            }

            if(total >= m){ //집에 더 가져가야하는 상황
                start = mid+1;
            } else{ // 집에 더 못가져 가는 상황
                end = mid -1;
            }
        }
        System.out.print(end);

    }
}
