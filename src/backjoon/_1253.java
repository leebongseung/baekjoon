package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 11시 25분
 * 종료 시간 :
 *
 * 문제 분석
 * - N개의 수 중에서 어떤 수가 다른 수 두개의 합으로 나타낼 수 있으면 좋다!
 * - N개의 수가 주어지면 그중에서 좋은 수의 개수를 출력한다.
 * - 수의 위치가 다르면 값이 같아도 다른수임.
 *
 * */
public class _1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        long[] arr = new long[n];
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int res =0 ;
        for(int i=0; i<n; i++){
            int left = 0;
            int right = n-1;

            while(left < right){
                long sum = arr[left] + arr[right];
                if(sum == arr[i]){
                    if(left != i && right != i) {
                        res++;
                        break;
                    } else if(left == i){
                        left++;
                    } else {
                        right--;
                    }
                }
                else if(sum < arr[i]) left++;
                else if(sum > arr[i]) right--;
            }
        }
        System.out.println(res);
    }
}
