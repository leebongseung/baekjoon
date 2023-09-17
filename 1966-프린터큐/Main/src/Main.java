import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*  시작 시간 : 10시 08분
*   종료 시간 : 10시 분
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCnt = Integer.parseInt(bf.readLine()); // 테스트 케이스의 개수

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i< testCaseCnt; i++){
            String str = bf.readLine();
            int n = Integer.parseInt(str.split(" ")[0]); // 문서의 개수
            int findDoc =  Integer.parseInt(str.split(" ")[1]); // 찾을 문서
            String strImp = bf.readLine();

            Queue<int[]> q = new LinkedList<>();
//            Queue<Integer> idxQ = new LinkedList<>(); // 중요도
//            Queue<Integer> impQ = new LinkedList<>(); // 인덱스


            for(int j =0; j<n; j++){
                int imp = Integer.parseInt(strImp.split(" ")[j]) - 1;
                q.offer(new int[]{imp, j});
//                impQ.offer(imp);
//                idxQ.offer(j);
            }


            int cnt = 0;

            while(!q.isEmpty()){
                int max = Collections.max();
                int[] impAndIdx = q.poll(); // 0:imp, 1:idx
//                int imp = impQ.poll();
//                int idx = idxQ.poll();

                if(impAndIdx[0] == max){
                    cnt ++;
                    if(impAndIdx[1] == findDoc){
                        break;
                    }
                    continue ;
                }
                q.offer(impAndIdx);
//                impQ.offer(imp);
//                idxQ.offer(idx);
            }
            sb.append(cnt);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}