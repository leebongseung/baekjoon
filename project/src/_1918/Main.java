package _1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*  시작 시간 : 9시 01분.
*   죵료 시간 : 10시 20분 실패
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String sinput = bf.readLine();
        LinkedList<Character> stack = new LinkedList<>();
        LinkedList<Character> waitStack = new LinkedList<>();


        for(int i =0; i< sinput.length(); i++){
            if(sinput.charAt(i) == '*' || sinput.charAt(i) == '/') {
                if(stack.peekFirst() == '+' || stack.peekFirst() == '-'){
                    waitStack.push(stack.pop());
                }
                waitStack.push(sinput.charAt(i));
            } else if (sinput.charAt(i) == '+' || sinput.charAt(i) == '-'){
                waitStack.push(sinput.charAt(i));
            } else if (sinput.charAt(i) == '(') {
                stack.push(sinput.charAt(i));
                waitStack.push(sinput.charAt(i));
            } else if (sinput.charAt(i) == ')') {
                stack.push(sinput.charAt(i));
                while (!waitStack.isEmpty()){
                    if(waitStack.peek() == '('){
                        waitStack.pop();
                        break;
                    }
                    stack.push(waitStack.pop());
                }
            }
            else if (sinput.charAt(i) >= 'A' && sinput.charAt(i) <= 'Z'){
                stack.push(sinput.charAt(i));
                while (!waitStack.isEmpty()){
                    if(waitStack.peek() == '('){
                        break;
                    }
                    stack.push(waitStack.pop());
                }
            }
        }

        while (!waitStack.isEmpty()){
            stack.push(waitStack.pop());
        }

        int cnt = 0;
        while(!stack.isEmpty()){
            cnt++;
            if(stack.peekLast() == '(' || stack.peekLast() == ')'){
                stack.removeLast();
                continue;
            }
            System.out.print(stack.removeLast());
        }
    }
}
