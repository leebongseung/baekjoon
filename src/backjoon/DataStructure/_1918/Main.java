package backjoon.DataStructure._1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*  하루에 2시간씩 2일동안 품.. 
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String sinput = bf.readLine();
        LinkedList<Character> stack = new LinkedList<>();
        LinkedList<Character> waitStack = new LinkedList<>();


        for (int i = 0; i < sinput.length(); i++) {
            // *, / 일 경우 이전에 값중 +, - 가 존재하는 경우
            if (sinput.charAt(i) == '*' || sinput.charAt(i) == '/') {
                if (stack.peekLast() == '+' || stack.peekLast() == '-') {
                    waitStack.addLast(stack.removeLast());
                }
                waitStack.addLast(sinput.charAt(i));
            } else if (sinput.charAt(i) == '+' || sinput.charAt(i) == '-') {
                waitStack.addLast(sinput.charAt(i));
            } else if (sinput.charAt(i) == '(') {
                stack.addLast(sinput.charAt(i));
                waitStack.addLast(sinput.charAt(i));
            } else if (sinput.charAt(i) == ')') {
                while (!waitStack.isEmpty()) {
                    if (waitStack.peekLast() == '(') {
                        waitStack.removeLast();
                        break;
                    }
                    stack.addLast(waitStack.removeLast());
                }
                stack.addLast(sinput.charAt(i));
                while (!waitStack.isEmpty()) {
                    if (waitStack.peekLast() == '(') {
                        break;
                    }
                    stack.addLast(waitStack.removeLast());
                }
            } else if (sinput.charAt(i) >= 'A' && sinput.charAt(i) <= 'Z') {
                stack.addLast(sinput.charAt(i));
                while (!waitStack.isEmpty()) {
                    if (waitStack.peekLast() == '(') {
                        break;
                    }
                    stack.addLast(waitStack.removeLast());
                }
            }

//            System.out.println(i + "번째 stack:");
//            for (var ch : stack) {
//                System.out.print(ch);
//            }
//            System.out.println();
//            System.out.println(i + "번째 wait스택:");
//            for (var ch : waitStack) {
//                System.out.print(ch);
//            }
//            System.out.println();
//            System.out.println("-------------");
        }

        while (!waitStack.isEmpty()) {
            stack.addLast(waitStack.removeLast());
        }

        while (!stack.isEmpty()) {
            if (stack.peekFirst() == '(' || stack.peekFirst() == ')') {
                stack.removeFirst();
                continue;
            }
            System.out.print(stack.removeFirst());
        }
    }
}
