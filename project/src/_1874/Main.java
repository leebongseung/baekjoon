
package _1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*  시작 시간 : 16시 34분
 *   종료 시간 : 17시 12분
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Stack<Integer> stack = new Stack<>();

        StringBuffer sb = new StringBuffer();

        int nextNum = 0; // stack에 넣을 정수값.
        boolean isFind = false; // 찾았는지 여부

        for(int i =0; i<n; i++){
            isFind = false;
            int findNum = Integer.parseInt(bf.readLine());

            while(!isFind){
                if(!stack.isEmpty()){
                    // stack에 값이 있따면 peek값이 findNum값일 경우 빼주기
                    if(stack.peek() == findNum){
                        stack.pop();
                        sb.append("-\n");
                        isFind = true;
                        break;
                    }
                }
                // nextNum이 작을경우 계속 push 해주기
                if(nextNum < n) {
                    nextNum++;
                    stack.push(nextNum);
                    sb.append("+\n");
                }

                // 만약에 못찾았다 nextNum 이 findNum 보다 클경우.
                if(nextNum > findNum){
                    break;
                }
            }

            // 만약에 못찾았다면 isFind가 false임
            if(!isFind) {
                sb = new StringBuffer("NO\n");
                break;
            }
        }
        System.out.println(sb);
    }
}