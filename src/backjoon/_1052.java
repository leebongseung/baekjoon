package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 5시 40분
 * 종료 시간 :?
 *
 * 문제 분석
 * - n 개 물병 존재, 무한대로 붇기 가능
 * - 초기값 1L 들어있음.
 * - 한번에 K 번 옮기기 가능,물낭비 싫음, 한번 보다 많이 이동 싫음
 *  - 물병의 물을 적절히 배분해서, 빈물병을 K개 만드려고함.
 *
 * - 분배 규칙
 *  - 같은 양의 물이 있는 2개를 고름,
 *  - 한개의 물병에 다른 한쪽에 물을 모두 부음, 반복
 *  - 새로운 물병 구매 가능, 상점에서 사는 물병도 1리터 들어있음.
 *
 * - 아 결론은 같은 양의 물을 들어야하네!
 * 그리디인것같은데
 *  가장 작은 문제가 무엇일까?
 *  - 1이 1개있을때?
 *  - 같은 수가 2개 있을 때?
 *  - 같은 수가 1개 있을 때?
 *  - PQ 문제인가? 그런거같기도 하고 ..
 *  - 작은 값을 가진 물병 부터 없어지는게 최소한의 물병을 구매하는 횟수일 듯.
 *
 * */
public class _1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 총 물병 개수
        int k = Integer.parseInt(st.nextToken()); // 목표하는 물병 개수
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2)-> {
            if(map.getOrDefault(o1, 0) > map.getOrDefault(o2, 0)){
                return -1;
            } else if(Objects.equals(map.getOrDefault(o1, 0), map.getOrDefault(o2, 0))){
                return o1-o2;
            } else {
                return 1;
            }
        });
        for(int i=0; i<n; i++){
            map.put(1, map.getOrDefault(1, 0) + 1);
            q.add(1);
        }

        int cnt = 0; // 물병 구매 횟수
        while(q.size() > k){
            int curSize = q.poll(); // 현재 물병 크기
            if(q.peek() != curSize){
                // 같은 수가 1개 있는 경우 - 상점에서 물병 구매하기!
                q.add(curSize);
                map.put(1, map.getOrDefault(1, 0) + 1);
                q.add(1);
                cnt++;
            } else {
                // 같은 수가 2개 있는 경우
                q.poll();
                map.put(curSize, map.getOrDefault(curSize, 0) - 2);

                q.add(curSize * 2);
                map.put(curSize * 2, map.getOrDefault(curSize * 2, 0) + 1);
            }

            for (Integer integer : q) {
                System.out.print(integer +" ");
            }
            System.out.println();
            System.out.println("=============");
        }
        System.out.println(cnt);
    }
}
