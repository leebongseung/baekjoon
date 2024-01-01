package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*  시작 시간 : 9시 50분
 *   종료 시간 : 10시 50분
 * */
public class _1205 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 0보다 크고 p보다 작거나 같은 정수
        long newScore = Integer.parseInt(st.nextToken()); //20억보다 작거나 같음.
        int p = Integer.parseInt(st.nextToken()); // 10보다 크고, 50보다 작거나 같은 정수

        if (n == 0) {
            System.out.println(1);
            System.exit(0);
        }

        // 예를 들어 랭킹 리스트가 100, 90, 90, 80일 때 각각의 등수는 1, 2, 2, 4등이 된
        int[] arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int rank = 1;
        if (n == p && newScore <= arr[n - 1]) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < n; i++) {
                if (newScore < arr[i])
                    rank++;
                else
                    break;
            }
            System.out.println(rank);
        }
    }
}
