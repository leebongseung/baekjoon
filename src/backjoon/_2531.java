package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 5분
 * 종료 시간 : 10시 17분
 *
 * 문제분석
 * - 컨테이너 벨트에 같은 종류의 초밥이 2이상 있을 수 있음.
 *
 * 1. 벨트의 임의의 한 위치부터 k개 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 2. 각 고객에게 초밥의 종류 하나 쓰인 쿠폰을 발행, 1번 행사에 참여할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 제공한다. 만약 요리가 없다면, 요리사가 새로 만들어 손님에게 제공함.
 *
 * 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램 작성하기
 *
 * 슬라이딩 윈도우 k 로 풀어보자!
 * */
public class _2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 k개
        int c = Integer.parseInt(st.nextToken()); // 초밥 종류 쿠폰 번호

        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }

        // 슬라이딩 윈도우로 k개만큼 가능한 경우의 수를 구해보자
        // 먹을 수있는 초밥의 가짓수를 구해보자
        int res = 0;
        for(int i=0; i<n; i++){
            int size = k;
            int idx = i;
            Set<Integer> set = new HashSet<>();
            set.add(c); // 추가되는 초밥의 개수
            while(size > 0){ // 연속되는 k 개 만큼의 초밥의 종류를 확인하자
                set.add(arr[idx]);

                idx ++;
                if(idx >= n) idx = 0;
                size--;
            }
            res = Math.max(res, set.size());
        }

        System.out.println(res);
    }
}
