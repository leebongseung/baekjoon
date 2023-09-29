
package _2346;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*  시작 시간 : 10시 00 분
 *   종료 시간 : 14시 13분
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(bf.readLine());
        Deque<Integer> balloonLsts = new ArrayDeque<>(); // 풍선 인덱스별 이동값을 저장
        Deque<Integer> balloonLstsIdx = new ArrayDeque<>(); // 풍선 인덱스값 저장

        //풍선 채우기
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i =0; i<n; i++){
            balloonLsts.add(Integer.parseInt(st.nextToken()));
            balloonLstsIdx.add(i+1);
        }


        //풍선 터뜨리기
        int movingIndexRange = 0; // 이동해야할 범위

        while(!balloonLsts.isEmpty()){
            if(movingIndexRange > 0){ // 양수일 경우 오른쪽으로 이동
                movingIndexRange--; // 현재 인덱스위치가 오른쪽으로 한칸 이동한 위치임.

                // first값을 last로 옮김으로써, 즉 인덱스가 오른쪽으로 한칸씩 이동하는 큐형태
                while(movingIndexRange != 0) {
                    balloonLsts.addLast(balloonLsts.removeFirst());
                    balloonLstsIdx.addLast(balloonLstsIdx.removeFirst());
                    movingIndexRange--;
                }
            }
            else if(movingIndexRange < 0){ // 음수일 경우 왼쪽으로 이동
                // last값을 first로 옮김으로써, 즉 인덱스가 왼쪽으로 한칸씩 이동하는 큐 형태
                while(movingIndexRange != 0) {
                    balloonLsts.addFirst(balloonLsts.removeLast());
                    balloonLstsIdx.addFirst(balloonLstsIdx.removeLast());
                    movingIndexRange++;
                }
            }

            movingIndexRange = balloonLsts.removeFirst();
            sb.append(balloonLstsIdx.removeFirst()+ " ");
        }
        System.out.println(sb);
    }
}