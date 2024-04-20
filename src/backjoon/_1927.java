package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 시작 시간 : 1시 51분
 * 종료 시간 : ??
 *
 * 문제 분석
 * - 비어있는 배열을 초기화
 * - 배열에 x 를 넣는다
 * - 배열에서 가장 작은 값 출력하고, 그 값을 배열에서 제거 한다.
 *
 * -  x 가 0이라면 가장 작은 값을 출력하고 그 값을 배열에서 제거
 * - x 가 자연수라면 배열에 넣는 연산
 * - x 는 2^31 == 10^9 정도
 *
 * - 만약에 배열이 비어잇있는데 가장 작은값을 출력하라고 할땐 0 을 출력하면됨 .
 * */
public class _1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
            return o1-o2;
        });

        int n = Integer.parseInt(bf.readLine());
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(bf.readLine());

            if(num == 0){
                int res = 0;
                if(!q.isEmpty()) res = q.poll();
                System.out.println(res);
            } else {
                q.add(num);
            }
        }
    }
}
