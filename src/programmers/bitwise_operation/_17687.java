package programmers.bitwise_operation;
import java.util.*;
/**
 * 시작 시간 : 9시
 * 종료 시간 : 10시 45분
 *
 * */

public class _17687 {
    class Solution {
        public String solution(int n, int t, int m, int p) {
            // n : 진법, t : 구할 숫자 개수, m : 참가 인원, p : 튜브 순서
            String answer = "";

            List<Integer> lst = new ArrayList<>();
            int order = 0;
            int num = 0;
            while (true) {
                // 자기 순서이면 숫자를 n 진법으로 변경해서 문자열에 저장하기!
                String str = "";
                str = Integer.toString(num, n);
                str = str.toUpperCase();

                // 문자열 길이만큼 반복하면서 순서에 맞는 값 배정해주기
                for (int i = 0; i < str.length(); i++) {
                    order++;
                    // p 순서인지 확인하기
                    if (order == p) {
                        answer += Character.toString(str.charAt(i));
                        if (answer.length() >= t) break;
                    }

                    // 인원만큼 반복했을 경우 순서 초기화
                    if (order == m) order = 0;
                }
                // 문자열 길이가 t 이상일 경우 while 루프 탈출
                if (answer.length() >= t) break;

                num++;
            }

            return answer;
        }
    }
}
