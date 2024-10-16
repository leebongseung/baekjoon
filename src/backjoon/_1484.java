package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 10시 19분
 * 종료 시간 : 1시간
 * <p>
 * 문제분석
 * - G 킬로그램이나 더 쩟어~~ 라고 말함.
 * - G 킬로그램 : (성원이의 현재 무게) ^ 2 - 성원이가 기억하고 있던 무게
 * 성원이의 현재 몸무게로 가능한 것을 모두 출력하시오.
 * <p>
 * G = (X ^ 2) - (Y ^ 2)
 * X > Y
 * X를 지정하고 가능한 Y가 있다면 현재값 X를  출력하기
 * 가능한 무게가 없다면 -1을 출력하기
 */

public class _1484 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        boolean isOk = false;



/*        // 시간 초과 발생 : 100억 연산하게 되어버림.
        for(int x= 1; x <= g; x++){
            for(int y = 1; y < x; y++){
                if( Math.pow(x, 2) - Math.pow(y, 2) == g) {
                    isOk = true;
                    System.out.println(x);
                }
            }
        }*/

        int x = 2; // 현재 무게
        int y = 1; // 이전 무게
        while (y < x) {
            double guess = Math.pow(x, 2) - Math.pow(y, 2);
            if (g < guess) {
                y++;
            } else if (g > guess) {
                x++;
            } else if (g == guess) {
                System.out.println(x);
                isOk = true;
                x++;
            }
        }
        if (isOk == false) {
            System.out.println(-1);
        }

    }
}
