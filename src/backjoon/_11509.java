package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 7시 14분
 * 종료 시간 : 8시 17분
 * <p>
 * 문제분석
 * - 큰 방에는 n개의 풍선이 존재
 * - 풍선은 왼쪽부터 오른쪽까지 일렬.
 * - 진솔이는 왼쪽에서 오른쪽으로 화살을 쏨.
 * - 화살은 선택된 높이 h에서 풍선을 마주칠때까지 이동함.
 * - 마주치면 풍선은 사라지고, 높이는 h-1이 됨.
 * - 우리의 목표는 모든 풍선을 터트리되 가능한한 적은 화살을 사용하기!
 * <p>
 * n이 100만까지 존재하네.
 * h도 100만.
 * <p>
 * 음.. 100만 x 100만번이면 시간복잡도가 우케되는거징. -> 1조
 * <p>
 * <p>
 * 풀이과정
 * - 현재 높이에 맞는 화살을 기록해두고 진행하면됨.
 */
public class _11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>(); // 화살 높이 저장 공간, key: 화살 높이, value : 화살 개수

        int res = 0;
        for (int i = 0; i < n; i++) {
            int curBalloonHeight = arr[i];

            // 풍선 높이에 해당하는 화살촉이 있다면?
            if (map.getOrDefault(curBalloonHeight, 0) > 0) {
                map.put(curBalloonHeight, map.get(curBalloonHeight) - 1);
                map.put(curBalloonHeight - 1, map.getOrDefault(curBalloonHeight - 1, 0) + 1);
            }
            // 풍선 높이에 해당하는 화살촉이 없을 경우, 활을 한번 더 쏜다.
            else {
                res++;
                map.put(curBalloonHeight - 1, map.getOrDefault(curBalloonHeight -1, 0) + 1);
            }
        }

        System.out.print(res);

    }
}
