package backjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*  시작 시간 : 10시 30분
*   종료 시간 : 10시 52분
* */
public class _11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 가로의 길이 범위 1~ 1000 까지임.
        int[] arr = new int[1001];

        arr[1] = 1;
        arr[2] = 3;
        for(int i =3; i<= n; i++){
            arr[i] = (arr[i-1] + 2* arr[i-2] ) % 10007; // 10007로 나눈 나머지를 출력한다.
        }

        System.out.println(arr[n]);
    }
}
