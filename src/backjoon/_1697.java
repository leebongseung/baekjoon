package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 7 분
 * 종료 시간 : ??
 *
 * 1.문제분석
 * - 현재점 N에 수빈이 있음
 * - 동생은 K점에 있음
 * - 수빈이는 걷거나 순간이동 가능
 *  - 수빈이가 위치가 X일 때 걷는다면 1초후 X-1 또는 X+1로 이동가능
 *  - 순간이동 : 1초 후 2*X의 위치로 이동가능
 *
 * - 수빈이와 동생의 위치가 있다면, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간을
 * 구하시오!
 *
 *
 * */
public class _1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int num = n;
        int cnt = 0;
        while(num != k){

            System.out.println(num);
            cnt++;

            int abs = k - num;
            int tmp = 0;
            if(abs > k - num - 1) {
                abs = k - num - 1;
                tmp = num - 1;
            }

            if(abs > k - num + 1) {
                abs = k - num + 1;
                tmp = num + 1;
            }

            if(abs > k - (num * 2)) {
                abs = k - (num * 2);
                tmp = num * 2;
            }

            num = tmp;
        }

        System.out.println(cnt);
    }
}
