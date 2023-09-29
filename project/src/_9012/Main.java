package _9012;

/*  시간 시간 : 11시 18분
 *   종료 시간 : 11시 41분
 * */
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<n; i++){
            Stack<Character> stack = new Stack<>();

            String str = sc.next();
            for(int j=0; j<str.length(); j++){
                stack.push(str.charAt(j));
            }

            int cnt = 0;
            while(!stack.isEmpty()){
                Character ch = stack.pop();
                if(ch == ')') cnt ++;
                else cnt --;

                if(cnt < 0){
                    break;
                }
            }
            if(cnt == 0) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
