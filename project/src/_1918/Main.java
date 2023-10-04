package _1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*  시작 시간 : 9시 01분.
*   죵료 시간 : 10시 20분 실패
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String sinput = bf.readLine();
        LinkedList<Character> stack = new LinkedList<>();
        LinkedList<Character> waitStack = new LinkedList<>();
        int cnt = 0;
        for(int i =0; i< sinput.length(); i++){
            if(sinput.charAt(i) == '*' || sinput.charAt(i) == '/'){
                if(!stack.isEmpty() &&(stack.peek() == '+' || stack.peek() == '-')){
                    waitStack.push(stack.pop());
                }
                waitStack.push(sinput.charAt(i));
            } else if (sinput.charAt(i) == '+' || sinput.charAt(i) == '-'){
                waitStack.push(sinput.charAt(i));
            } else if (sinput.charAt(i) == '(') {
                waitStack.push(sinput.charAt(i));
                cnt++;
            } else if (sinput.charAt(i) == ')'){
                while(cnt > 0){
                    if(!waitStack.isEmpty() && waitStack.peek() != '('){
                        stack.push(waitStack.pop());
                    } else{
                        waitStack.pop();
                        cnt--;
                    }
                }
            } else{
                stack.push(sinput.charAt(i));
                while(!waitStack.isEmpty()){
                    if(waitStack.peek() == '('){
                        break;
                    }
                    stack.push(waitStack.pop());
                }

            }
//            System.out.println(stack.peek());
        }

        while(!waitStack.isEmpty()){
            if(waitStack.peek() == '('){
                waitStack.pop();
            }
            stack.push(waitStack.pop());
        }

        while(!stack.isEmpty()){
            System.out.print(stack.removeLast());
        }
    }
}
