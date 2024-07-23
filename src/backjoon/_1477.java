package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 41분
 * 종료 시간 : 11시 22분
 * <p>
 * 문제분석
 * - 고속도로에 휴게소가 n 개있음.
 * - 휴게소 위치는 시작점부터 얼마나 떨어져 있는지 주어짐.
 * - 다솜이는 m개 더 세우려고함.
 * - 이미 있는곳은 못 세움, 끝에도 못세움
 * - m개 휴게소를 지어서 휴게소가 없는 구간의 길이를 최소로 할려고함.
 * <p>
 * 풀이방법
 * - 휴게소가 없는 구간의 길이가 큰 지점을 반씩 쪼개면 되지않을까? 그리디로
 * - 만약에 200, 701, 800 순으로 휴게소가 존재한다면? 1개 더 채워서 최소가 되는 문제를 풀어보자
 * - 시작점부터 모든거리를 PQ에 짚어넣기, 200, 501, 99 로 된다.
 * - 거리가 큰 지점을 절반으로 쪼개기
 * - 200, 251, 250, 99 가 됨, 이런식으로 작업해보자
 */
public class _1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 휴게소 개수
        int m = Integer.parseInt(st.nextToken()); // 추가할 휴게소 개수
        int l = Integer.parseInt(st.nextToken()); // 총 거리

        int[] arr = new int[n + 2]; // 현재 휴게소가 존재하는 개수
        arr[0] = 0;
        arr[n + 1] = l;

        // 현재 존재하는 휴게소 위치 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println("===배열정리===");

        //내림차순으로 pq 정렬, 즉 휴게소 사이의 거리가 큰 값부터 추출됨.
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 1; i < arr.length; i++) {
            pq.add(arr[i] - arr[i - 1]);
//            System.out.print(arr[i] - arr[i - 1] + " ");
        }
//        System.out.println();
//        System.out.println("====");


        // 휴게소 m개 만큼 추가 설치하기!
        for (int i = 0; i < m; i++) {
            int distance = pq.poll();

            pq.add(distance / 2);
            pq.add(distance - (distance / 2));

//            System.out.println("currentValue : " + distance
//                    + ", div1 : " + distance / 2
//                    + ", div2 :  " + (distance - (distance / 2)));
//
//            for (Integer j : pq) {
//                System.out.print(j + " ");
//            }
//            System.out.println();
//            System.out.println("====");
        }

//        while (!pq.isEmpty()) {
//            System.out.print(pq.poll() + " ");
//        }
//        System.out.println();
        System.out.println(pq.poll());

    }
}
