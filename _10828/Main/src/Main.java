/*  시작 시간 : 14시 37분
*   종료 시간 : 15시 16분
* */
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        int n = sc.nextInt(); // 명령의 수
        StringBuilder sb = new StringBuilder();


        for(int i =0; i< n; i++){
            String cmdStr = sc.next();
            if(cmdStr.equals("push")){
                int value = sc.nextInt();
                stack.push(value);
                continue;
            }
            switch(cmdStr) {
                case "top":
                    sb.append(stack.isEmpty() ? -1 : stack.lastElement());
                    break;
                case "size":
                    sb.append(stack.size());
                    break;
                case "empty":
                    sb.append(stack.isEmpty() ? 1:0);
                    break;
                case "pop":
                    sb.append(stack.isEmpty() ? -1:stack.pop());
                    break;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}