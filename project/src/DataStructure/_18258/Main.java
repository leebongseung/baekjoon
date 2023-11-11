package DataStructure._18258;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*  시작 시간 : 19시 03분
 *   종료 시간 : 20시 03분
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        Queue<Integer> q = new LinkedList<>();
        int backValue = -1;

        for(int i=0; i<n; i++){
            String istr = br.readLine();
            if(istr.contains("push")){
                backValue = Integer.parseInt(istr.substring(5));
                q.offer(backValue);
                continue;
            }

            switch (istr){
                case "pop":
                    sb.append(q.isEmpty()? "-1" : q.remove());
                    break;
                case "size":
                    sb.append(q.size());
                    break;
                case "empty":
                    sb.append(q.isEmpty()? "1" : "0");
                    break;
                case "front":
                    sb.append(q.isEmpty()? "-1" : q.peek());
                    break;
                case "back":
                    sb.append(q.isEmpty()? "-1" : backValue);
                    break;
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}