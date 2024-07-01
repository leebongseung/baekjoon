package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 시작 시간 : 10시 4분
 * 종료 시간 : 10시 32분
 *
 * 문제분석
 * - 빈 문자열은 안정적
 * - s가 안정적 ->  {S}도 안정적
 * - S와 T가 안정적 -> ST 도 안정적
 * - {}, {}{}, {{}{}} 안정적 -> }{, 는 불안정적
 *
 * 연산종류
 * 1. 여는괄호를 닫는괄호로 바꾸기
 * 2. 닫는괄호를 여는괄호로 바꾸기
 *
 * 무슨 공식을 써야 풀수 있나?
 * 브루트 포스는 아님 -> 2000개의 모든 경우의 수를 구하면 어마어마함
 * stack으로 문자열 풀어보기
 * */
public class _4889 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        while(true) {
            String str = br.readLine();
            if (str.contains("-")) break; // "-" 한개 이상 포함일 경우 입력 세트 종료

            Stack<String> stack = new Stack<>(); // 왼쪽 괄호를 위한 스택
            int rightRequiredCount = 0;
            int leftRequiredCount = 0;
            for(int i=0; i< str.length(); i++){
                String s = str.substring(i, i+1);

                switch (s){
                    case "{":
                        stack.add("{");
                        break;
                    case "}":
                        // 왼쪽 괄호가 없을 경우
                        if(stack.isEmpty()) rightRequiredCount++;
                        // 왼쪽 괄호가 있을 경우
                        else{
                            stack.pop();
                        }
                        break;
                }
            }
            leftRequiredCount = stack.size();

            // 왼쪽과 오른쪽 교체 필요 횟수 구하기
            System.out.print(idx + ". ");

            // +1을 수행하면서 여는 괄호와 닫는 괄호의 개수가 홀수일 때 올림 처리를 위해 사용
            // /2를 수행하면서 여는 괄호 {나 닫는 괄호 }의 개수를 짝을 맞추기 위해 필요한 최소 교체 횟수를 계산
            System.out.println((leftRequiredCount + 1) / 2 + (rightRequiredCount  +  1 ) / 2);


            idx ++;
        }
    }
}
