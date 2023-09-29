package _10866;

/*  시작 시간 : 19시 19분
 *   종료 시간 : 19시 40분
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        StringBuffer sb = new StringBuffer();

        outer:
        for(int i =0; i<n; i++){
            String str = bf.readLine();

            switch (str.length() > 6 ? str.substring(0,6) : str){
                case "push_b":
                    deque.addLast(Integer.parseInt(str.substring(10)));
                    continue outer;
                case "push_f":
                    deque.addFirst(Integer.parseInt(str.substring(11)));
                    continue outer;
                case "pop_fr":
                    sb.append(deque.isEmpty()? "-1" :  deque.removeFirst());
                    break;
                case "pop_ba":
                    sb.append(deque.isEmpty()? "-1" :  deque.removeLast());
                    break;
                case "size":
                    sb.append(deque.size());
                    break;
                case "empty" :
                    sb.append(deque.isEmpty()? "1" : "0");
                    break;
                case "front":
                    sb.append(deque.isEmpty()? "-1" : deque.peekFirst());
                    break;
                case "back":
                    sb.append(deque.isEmpty()? "-1" : deque.peekLast());
                    break;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}