
package backjoon.DataStructure._1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*  시작 시간 : 09시 00분
 *   종료 시간 : 10시 52분
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

            Queue<Integer> idxQ = new LinkedList<>(); // 중요도
            Queue<Integer> impQ = new LinkedList<>(); // 인덱스
            Integer[] nums = new Integer[n]; // 중요도가 높은 순서대로 정렬

            for(int j =0; j<n; j++){
                int imp = Integer.parseInt(strImp.split(" ")[j]) - 1;
                nums[j] = imp;
                impQ.offer(imp);
                idxQ.offer(j);
            }

            // 내림차순 정렬
            Arrays.sort(nums, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });

            int cnt = 0;

            while(!impQ.isEmpty()){
                int imp = impQ.poll();
                int idx = idxQ.poll();

                if(nums[cnt] == imp){
                    cnt ++;
                    if(idx == findDoc){
                        break;
                    }
                    continue ;
                }
                impQ.offer(imp);
                idxQ.offer(idx);
            }
            sb.append(cnt);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}