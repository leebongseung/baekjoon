
package backjoon.DataStructure._2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*  시작 시간 : 10시 33분
 *   종료 시간 : 11시 10분
 * */
public class Main {
    // 문자열이 숫자인지 확인하는 함수
    public static boolean isInteger(String strValue) {
        try {
            Integer.parseInt(strValue);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();

        Stack<String> smallBrackets = new Stack<>(); // 소괄호 저장하는 스택
        Stack<String> largeBrackets = new Stack<>(); // 대괄호 저장하는 스택
        Stack<String> bracketsAndNumbers = new Stack<>(); // 소괄호, 대괄호, 숫자 전체를 저장하는 스택

        boolean isError = false; // 에러가 발생했는지?

        outer:
        for(int i =0; i<str.length(); i++){
            char Ccurrent = str.charAt(i); // 현재 문자를 저장하는 변수
            int sum = 0; // 계산하기 위해 만든거
            switch(Ccurrent){
                case '(':
                    smallBrackets.push("(");
                    bracketsAndNumbers.push("(");
                    break;
                case ')':
                    if(smallBrackets.isEmpty()){ // 에러 조건1. 왼쪽 소괄호 저장하는 스택이 비어있을 경우
                        isError = true;
                        break outer;
                    }
                    smallBrackets.pop(); // 안비었다면 pop

                    while(!bracketsAndNumbers.isEmpty()){ // 전체(소대괄호, 숫자) 스택 탐색
                        String SearchSmallBracket = bracketsAndNumbers.pop();
                        if(isInteger(SearchSmallBracket)){ // 조건1. 숫자인 경우 : 괄호 안에 값은 더함
                            sum += Integer.parseInt(SearchSmallBracket);
                            continue ;
                        }
                        if(SearchSmallBracket.equals("(")){ //조건2. 왼쪽 소괄호 인경우 : 괄호 안에 더한 값 * 2 수행.
                            if(sum != 0){
                                bracketsAndNumbers.push(String.valueOf((2 * sum)));
                            } else{
                                bracketsAndNumbers.push("2");
                            }
                            break;
                        }
                        // 에러 조건 2. 숫자도 왼쪽소괄호도 아닌 경우
                        isError = true;
                        break outer;
                    }
                    break;
                case '[':
                    largeBrackets.push("[");
                    bracketsAndNumbers.push("[");
                    break;
                case ']':
                    if(largeBrackets.isEmpty()){ // 에러 조건3. 왼쪽 대괄호 저장하는 스택이 비어있을 경우
                        isError = true;
                        break outer;
                    }
                    largeBrackets.pop(); // 안비었다면 pop

                    while(!bracketsAndNumbers.isEmpty()){
                        String SearchlargeBracket = bracketsAndNumbers.pop();
                        if(isInteger(SearchlargeBracket)){ // 조건3. 숫자인 경우 : 괄호 안에 값은 더함
                            sum += Integer.parseInt(SearchlargeBracket);
                            continue ;
                        }
                        if(SearchlargeBracket.equals("[")){ //조건4. 왼쪽 대괄호 인경우 : 괄호 안에 더한 값 * 3 수행.
                            if(sum != 0){
                                bracketsAndNumbers.push(String.valueOf((3 * sum)));
                            } else{
                                bracketsAndNumbers.push("3");
                            }
                            break;
                        }
                        // 에러 조건 2. 숫자도 왼쪽 대괄호도 아닌 경우
                        isError = true;
                        break outer;
                    }
                    break;
            }
        }

        // 에러조건3. 소괄호가 남아있거나, 대괄호가 남아있을 경우
        if(isError || !smallBrackets.isEmpty() || !largeBrackets.isEmpty()){
            System.out.println(0);
        } else{
            int res = 0;
            while(!bracketsAndNumbers.isEmpty()){ // bracketsAndNumbers에 저장된 숫자들을 다 더해줌.
                res += Integer.parseInt(bracketsAndNumbers.pop());
            }
            System.out.println(res);
        }
    }
}