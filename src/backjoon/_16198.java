package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 8분
 * 종료 시간 : 10시 21분
 *
 * 문제분석
 * - n개의 에너지 구슬이 일렬로 놓임.
 * - 에너지 구슬을 이용해서 에너지를 모음
 *
 * 규칙
 * - i번째 구슬을 무게는 W_i임.
 * 1. 에너지구슬을 하나 고르기, 고른 구슬은 x번호임.
 *  - 주의 : 단 첫번째와 마지막 구슬은 못고름
 * 2. x번째 에너지 구슬 제거
 * 3. W_{x-1} x W_{x+1}의 에너지 모으기
 * 4. N을 1 감소 시키고, 에너지 구슬 1번~ n 번까지 다시 번호 매기기
 *
 * n과 에너지 구슬 무게가 주어질때, 모을 수 있는 에너지 양의 최댓값 구하기.
 *
 * 풀이
 * n이 10이하여서 브루트포스, 완전탐색해보기
 * */
public class _16198 {
    public static int n;
    public static long res;
    public static List<Integer> lst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lst = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) lst.add(Integer.parseInt(st.nextToken()));

        dfs(0, lst);
        System.out.print(res);
    }

    private static void dfs(long energyTotal, List<Integer> lst){
        // 제일 앞, 제일 뒤는 제거 못하기 때문에 다음과 같이 조건 설정
        if(lst.size() <= 2){
            res = Math.max(res, energyTotal);
            return;
        }

        // 중간 값 x 에너지 구슬 제거하고, dfs 탐색 시작
        for(int i=1; i<lst.size()-1; i++){
            int removedEnergyOrb = lst.remove(i);
            dfs(energyTotal + ((long) lst.get(i - 1) * lst.get(i)), lst);
            lst.add(i, removedEnergyOrb);
        }
    }
}
