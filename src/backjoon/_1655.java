package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringJoiner;

/**
 * 시작 시간 : 2시
 * 종료 시간 : ??
 *
 * 문제분석
 * - 백준이 정수하나씩 외침, 동생은 말한 수 중 중간값을 말해야함.
 * - 백준이가 외친 수가 짝수라면 중간에 있는 두 수 중 작은수 말하기
 *
 * - 입력은 -1만 1만 보다 작거나 같음
 * - 총 개수는 100,000만 개
 *
 * 이게 왜 pq지?.. 음
 * - 정렬을 수행하여 중간값에 해당하는 걸 반환해야함.
 * 고로 pq 로 정렬수행하면서 중간값 반환하기 가능한가? 가능할듯
 * idx 기준으로 평균 idx를 찾고 pq 공식에 넣어두자
 *
 * 결국 못품블로그 참고
 * https://gh402.tistory.com/32
 *
 * */
public class _1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringJoiner sj = new StringJoiner("\n");

        // 내림차순 정렬
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) ->{
            return o2-o1;
        });

        // 오름차순 정렬
        PriorityQueue<Integer> minPQ = new PriorityQueue<>((o1, o2) ->{
            return o1-o2;
        });

        for(int i=0; i<n; i++){
            int num = Integer.parseInt(bf.readLine());

            if(maxPQ.size() == minPQ.size()){
                maxPQ.add(num);
            } else {
                minPQ.add(num);
            }

            // 왼쪽(maxPQ)을 항상 작게 유지하기!
            if(!minPQ.isEmpty() && maxPQ.peek() > minPQ.peek()){
                // maxpq가 더 클 때 swith
                // 항상 maxpq의 peek가 더 작아야하기 때문
                minPQ.add(maxPQ.poll());
                maxPQ.add(minPQ.poll());
            }
            sj.add(String.valueOf(maxPQ.peek()));
        }
        System.out.print(sj);

    }
}
