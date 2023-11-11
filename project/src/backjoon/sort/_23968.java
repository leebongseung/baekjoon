package backjoon.sort;
/*  시작 시간 : 10시 12분
 *   종료 시간 : 10시 33분
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _23968 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 가장 마지막 인덱스 부터 정렬하는 것이 버블.
        int[] arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            arr[idx] = Integer.parseInt(st.nextToken());
            idx++;
        }

        int cnt = 0;

        for (int last = n; last >= 0; last --) {
            for (int i = 0; i < last - 1; i++) {
                if (arr[i] > arr[i+1]) {
                    int tmp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = tmp;

                    cnt++;
                    if (cnt == k) {
                        System.out.print(arr[i] + " " + arr[i+1]);
                        return;
                    }
                }
            }
        }

        if(cnt < k){
            System.out.println(-1);
        }
    }
}
