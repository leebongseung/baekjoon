package backjoon.DataStructrue2;

/*  시작 시간 : 9시 8분
*   종료 시간 : 9시 31분
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});

        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int x = Integer.parseInt(st.nextToken());
                pq.add(x);
            }
        }

        for(int i = 0; i <n -1; i++){
            pq.poll();
        }
        System.out.println(pq.poll());
    }
}
