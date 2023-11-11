
package backjoon.DataStructure._1010;

/* 다리 놓기
 * 문제 설명 : 서쪽에는 N개의 사이트, 동쪽에는 M개의 사이트가 존재.
 * 서쪽 사이트와 동쪽 사이트를 다리로 연결하려고 함(한 사이트에는 최대 한개의 다리만 연결 가능)
 * 서족의 개수만큼 다리를 지으려고 함.
 * 다리는 겹칠수없고, 이 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램 작성해라
 *
 * 입력 : T개, 정수 N, M 이 주어짐(0 < N ≤ M < 30), N은 서쪽 사이트 개수, M은 동쪽 사이트 개수
 * 출력 : 다리를 지을 수 있는 경우의 수를 출력
 *
 * */


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] N = new int[T];
        int[] M = new int[T];
        long[] res = new long[T];
        Arrays.fill(res, 1);
        for(int i =0; i<T; i++){
            N[i] = sc.nextInt();
            M[i] = sc.nextInt();

            int maxValue = Math.max(N[i],(M[i]-N[i]));
            int minValue = Math.min(N[i],(M[i]-N[i]));

            for(int j =maxValue + 1; j<= M[i]; j++){
                res[i] *= j;
            }
            for(int j = 2; j <= minValue; j++){
                res[i] /= j;
            }

            sc.nextLine();
        }

        for(int i = 0; i<T; i++){
            System.out.println(res[i]);
        }
    }
}