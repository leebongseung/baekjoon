package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 4시 18분
 * 종료 시간 : 5시 28븐
 *
 * 문제 분석
 * - 도서관에 책 제자리로 가져다 놓기
 * - 세준이 현재위치 0
 * - 각 책들의 원래 위치가 주어질 때,
 *      책을 모두 제자리에 놔두는 최소 걸음수를 계산하시오.
 * - 원래 위치는 정수 좌표다.
 * - 책을 모두 제자리에 놔둔 후 다시 0으로 돌아올 필요는 없다.
 * - 한번에 최대 M권의 책을 들 수 있음.
 *
 * 예상가능 알고리즘?
 * - 그리디인가?
 *  -거리의 차이를 저장해서 거리차이가 큰곳부터 m개 씩 추출하기
 *
 * */

import java.util.*;
public class _1461 {
    public static int n;
    public static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken()); // 책의 수
        m = Integer.parseInt(st.nextToken()); // 한번에 들 수 있는 수


        // 입력받기
        List<Integer> plusLst = new ArrayList<>(); // 플러스 값만 저장하는 리스트
        List<Integer> minusLst = new ArrayList<>(); // 마이너스 값만 저장하는 리스트
        st = new StringTokenizer(bf.readLine());
        while(st.hasMoreTokens()){
            int num = Integer.valueOf(st.nextToken());
            if(num > 0) plusLst.add(num);
            else minusLst.add(num);
        }

        // 플러스 리스트 : 내림차순 정렬
        Collections.sort(plusLst, (o1, o2) -> {
            return o2 - o1;
        });
        // 마이너스 리스트 : 오름차순 정렬
        Collections.sort(minusLst);

        // 가장 가까운 거리 선택하기!
        int res = 0 ;

        if(plusLst.isEmpty()){
            // 마이너스 밖에 없는 경우
            res += greedy(minusLst, true);
        } else if (minusLst.isEmpty()){
            // 플러스 밖에 없는 경우
            res += greedy(plusLst, true);
        } else if( Collections.max(plusLst) > Math.abs(Collections.min(minusLst))){
            // 절댓값 크기가 작은 것 부터 그리디 알고리즘을 이용하여 선택하기
            res += greedy(minusLst, false);
            res += greedy(plusLst, true);
        } else {
            res += greedy(plusLst, false);
            res += greedy(minusLst, true);
        }

        System.out.println(res);

    }

    public static int greedy(List<Integer> lst, boolean isLastLst){
        if(lst.isEmpty()) return 0; // 비정상적인 종료

        int firstSize = lst.size(); // 처음 사이즈 체크 Why? isLastLst 즉 마지막 리스트일 경우,
                                        // 책을 위치 까지만 이동하면됨 되돌아올 필요 x
        int res = 0;
        while(!lst.isEmpty()){
            int size = lst.size();
            int num = 0;

            if(size -m >= 0) { // m 만큼 책을 가지고 이동할 수 있는 경우 m 만큼 가져오기
                for(int i=0; i < m; i++){
                    num = Math.max(num, Math.abs(lst.remove(0)));
                }
            } else if(size -m < 0){ // m만큼 못가지고 이동하는 경우 들 수 있는 만큼 갓다놓기
                while(!lst.isEmpty()){
                    num = Math.max(num, Math.abs(lst.remove(0)));
                }
            }

            if(isLastLst && size == firstSize){ // 마지막 리스트면서, 최종 도착지점일 경우
                res += num;
            } else {
                // 그 외
                res += 2 * num;
            }
        }
        return Math.abs(res);
    }
}
