package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/**
 * 시작 시간 : 8시 47분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 보석이 n개 있는 보석집 털기
 * - 무게 m 와 가격 v를 가지고 있음.
 * - 상덕이는 k개의 가방을 가지고 있고, 각 가방 수용 최대 무게는 c임.
 * - 가방 1개 당 1개의 보석만 넣을 수 있다.
 * - 상덕이가 훔칠수 있는 보석의 최대 가격을 구하는 프로그램.
 *
 * 전형적인 그리디 문제
 * - 어떻게 풀지?
 * - 가격이 높은 순서대로 정렬한뒤 가방 무게만큼 제일 가치 높은거 없애기!
 * */
public class _1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 보석 총 개수
        int k = Integer.parseInt(st.nextToken()); // 가방 총 개수
        List<int[]> jewels = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewels.add(new int[]{weight, price});
        }

        for(int i=0; i<k; i++){
            bags.add(Integer.parseInt(bf.readLine()));
        }

        // 무게 순으로 오름차순 정렬하기
        Collections.sort(jewels, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        Collections.sort(bags);

        long res = 0;
        int j = 0;
        // 가격순으로 내림차순 정렬하기
        PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2)->{
            return o2[1] - o1[1];
        });

        for(int i=0; i<k; i++){
            int bagWeight = bags.get(i);

            while(j < n && jewels.get(j)[0] <= bagWeight){
                pq.add(jewels.get(j));
                j++;
            }
            if(!pq.isEmpty()){
                res += pq.poll()[1];
            }
        }
        System.out.println(res);
    }
}
