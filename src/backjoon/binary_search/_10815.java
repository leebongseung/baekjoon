package backjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*  시작 시간 : 10시 5분
*   종료 시간 : 10시 20분
* */
public class _10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner(" ");
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        Set<Integer> ArrN = new HashSet<>();
        while(st.hasMoreTokens()){
            ArrN.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());

        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            boolean contains = ArrN.contains(num);
            if(contains){
                sj.add("1");
            } else{
                sj.add("0");
            }
        }
        System.out.println(sj);
    }
}
