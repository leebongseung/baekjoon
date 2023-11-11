package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*  시작 시간: 12시 13분
*   종료 시간 : 1시 1분 완료
* */
public class _1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine()); // 회의의 수
        List<List<Integer>> arrs = new ArrayList<>();

        for(int i =0; i<n; i++){
            arrs.add(new ArrayList<>());

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            arrs.get(arrs.size()-1).add(startTime);
            arrs.get(arrs.size()-1).add(endTime);
        }

        // (실패) 그러면.. 음... 회의시간이 짧은 순서대로 정렬을 해볼까
        // (성공) 끝나는 시간이 짧은 순서대로 정렬 ? 만약에 종료시간이 같다면 ? 시작시간 순으로 내림차순 : 종료시간 순으로 내림차순
        arrs = arrs.stream()
                .sorted((o1, o2) -> o1.get(1) == o2.get(1)? o1.get(0)- o2.get(0) : o1.get(1)- o2.get(1))
                .collect(Collectors.toList());

        for (List<Integer> arr : arrs) {
            System.out.println(arr.get(0) +" " + arr.get(1));
        }


        // 회의가 겹치지 않게 회의실을 사용할 수 있는 최대 개수
        int currentTime = -1; // 현재시간
        int res = 0; // 종료시간
        for (List<Integer> mettingTime : arrs) {
            int startTime = mettingTime.get(0);
            int endTime = mettingTime.get(1);

            if(startTime < currentTime) continue;

            currentTime = endTime;
            res ++;

        }
        System.out.println(res);
    }
}
