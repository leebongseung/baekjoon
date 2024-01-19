package programmers.string;
import java.util.*;
/*  시작 시간 : 9시
      종료 시간 : 10시 32분
*/
public class _17682 {
    class Solution {
        public int solution(String dartResult) {
            int answer = 0;

            // 총 3번 다트 던짐
            String[] chars = dartResult.split("[0-9]+"); /// S, D, T 로 1제곱, 2제곱, 3제곱으로 점수계산
            String[] number =  dartResult.replaceAll("[*|#]","").split("[A-Z]+"); // 0~ 10점 얻을 수 있음

            int idx = 1; // chars 가 0번은 아무것도 안나와서 1번부터 세팅
            List<Integer> lst = new ArrayList<>();
            lst.add(0);

            for(String s : number){
                if(s.equals("")) continue;

                int num = Integer.parseInt(s);

                for(int i =0; i < chars[idx].length(); i++){
                    char ch = chars[idx].charAt(i);

                    // 문자 SDT 처리
                    if(ch == 'S') continue;
                    else if(ch == 'D') num *= num;
                    else if(ch == 'T') num *= num * num;
                    System.out.println(num);


                    // 스타상, 아차상 처리
                    if(ch == '*') {
                        // 스타상의 경우 이전값도 * 2가 되어야함
                        num *= 2;
                        lst.add(lst.remove(idx-1) * 2);
                    } else if(ch == '#'){
                        num *= -1;
                    };
                }
                lst.add(num);
                idx++;
            }

            for(int i : lst){
                answer += i;
            }

            return answer;
        }
    }
}
