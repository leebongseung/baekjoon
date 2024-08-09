package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 시작 시간 : 10시 9분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 터널 내 차선 변경 법률로 금지
 * - 터널에 들어가는 순서대로, 나오는 순서대로 각각 차량 번호를 작성함.
 * - N개의 차량이 지나간후, 차량번호의 목록을 보고, 추월 했을 것으로 여겨지는 차의 개수 구하기
 *
 * 입력
 * - n개의 차
 * - n개의 들어가는 차 순서대로
 * - n개의 나오는 차 순서대로
 *
 *  추월한 차가 몇대인가?
 *  그냥 queue 자료구조 써서 나오는 차가 일치하는지 확인하면 될듯?
 * */
public class _2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<String> q = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());

        // 들어가는 차 순서대로 입력받기
        for(int i=0; i<n; i++){
            q.add(br.readLine());
        }

        // 나오는 차 중 추월차 개수 세기
        int cnt = 0;
        for(int i=0; i<n; i++){
            String vehicleNo = br.readLine();

            // 추월했을 경우
            if(!q.peek().equals(vehicleNo)) {
                cnt++;
                q.remove(vehicleNo);
            } else{
                q.poll();
            }
        }

        System.out.print(cnt);
    }
}
