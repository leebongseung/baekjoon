package backjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*  시작 시간 : 5시 2분
*   종료 시간 :
* */
public class _12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 전체 개수
        int k = Integer.parseInt(st.nextToken()); // 구하고자 하는 무게의 가치

        int[] arr = new int[k+1];
        int[] tmp = new int[k+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());


            // 현재 무게에 대한 가치 구하기
            for(int j=weight; j<=k; j++){
                // 만약에 현재무게의가치 + (j-현재무게의 가치) > j번째의 가치일 경우 교체가 됨.
                tmp[j] = Math.max(value + arr[j - weight], arr[j]);
            }

            for (int i1 : tmp) {
                System.out.println(i1 +" ");
            }
            System.out.println();

            arr = tmp;
        }
        System.out.println(arr[k]);
    }
}
