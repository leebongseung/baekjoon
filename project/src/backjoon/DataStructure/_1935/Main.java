
package backjoon.DataStructure._1935;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*  시작 시간 : 9시 48분
 *   종료 시간 :
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine()); // 피연산자의 개수
        String str = bf.readLine(); // 후위  표기식
        double[] number = new double[27]; //최대 26개의 피연산자를 저장할 공간

        // 피연산자 저장하는 배열.
        for(int i =0; i<n; i++){
            number[i] = Double.parseDouble(bf.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for(int i =0; i<str.length(); i++){
            // A ~ Z 일 경우
            if('A' <= str.charAt(i) && str.charAt(i) <= 'Z'){
                stack.push(number[str.charAt(i) - 'A']);
            }
            // 연산자일 경우
            else{
                double y = stack.pop();
                double x = stack.pop();
                switch (str.charAt(i)){
                    case '*':
                        stack.push(x * y);
                        break;
                    case '/':
                        stack.push(x / y);
                        break;
                    case '+':
                        stack.push(x + y);
                        break;
                    case '-':
                        stack.push(x - y);
                        break;
                }
            }
        }
        //소수점 2번째 자리 까지 출력
        System.out.printf("%.2f", stack.pop());
    }
}