package _11279;
<<<<<<< HEAD
/*  시작시간 : 1시 00분
*  종료 시간 : 1시 30분
* */
=======

>>>>>>> 7b49c82c436b49acd1932a54db2ee982bfc456d9

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 연산의 개수

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i =0; i<n; i++){
            int x = Integer.parseInt(bf.readLine());

            if( x > 0){ // 정수 x 라면 값을 넣는 연산
                maxHeap.add(x);
            } else{ // x가 0이라면 배열에서 가장 큰 값을 출력하고 제거하기
                //Heap이 비어있다면 0 출력
                if(maxHeap.isEmpty()){
                    bw.write("0\n");
                }else {
                    bw.write(maxHeap.poll() + "\n");
                }
            }
            // 입력되는 수는 2^31 보다 작음.
        }
        bw.flush();
    }
}
