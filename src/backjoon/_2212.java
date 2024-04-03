package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 1시 16분
 * 종료 시간 : 2시 24분
 *
 * 문제분석
 * 고속도로위에 N개의 센서 설치함.
 * - 센서들을 자료를 모으고 분석할 몇개의 집중국을 세워야함
 * - 고속도로 위에 최대 K개의 집중국 설치 가능
 * - 각 집중국은 센서의 수신 가능 영역 조절 가능
 * - 수신가능 영역? 고속도로 상에서 연결된 구간
 * - N개의 센서가 적어도 하나의 집중국과 통신이 가능해야함.
 * - 수신 가능 영역의 길이의 합은 최소화 할 것
 *
 * - 수신가능 영역의 길이는 0이상
 * - 모든 센서의 좌표가 다를 필요는 없음
 *
 * 입력 분석
 * 센서 개수 N이 10000이하,
 * 집중국 개수 k는 1000이하
 * 좌표의 절댓값은 1,000,000 이하 음수일 수 도 있다.
 * 좌표는 직선이라고 생각하면 된다
 *
 * */
public class _2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 센서 개수
        int k = Integer.parseInt(bf.readLine()); // 집중국 개수

        // N개 센서만큼 SET자료형에 입력 받기
        Set<Integer> s = new HashSet<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        while(st.hasMoreTokens()){
            s.add(Integer.parseInt(st.nextToken()));
        }

        int[] arr = s.stream()
                .mapToInt(a -> a)
                .toArray();

        // 정렬하기
        Arrays.sort(arr);

        // 가까운 센서의 차이를 저장하기!
        Integer[] sensorDistanceDifference = new Integer[arr.length - 1];
        for(int i =0; i< arr.length-1; i++){
            sensorDistanceDifference[i] = arr[i + 1] - arr[i];
        }

        // 센서 차이 정렬하기
        Arrays.sort(sensorDistanceDifference, Collections.reverseOrder());

        // k-1개 만큼 최대로 큰 값 없애기!
        for(int i=0; i< k-1 && i < sensorDistanceDifference.length; i++){
            sensorDistanceDifference[i] = 0;
        }

        // 남은 차이 다 더해서 반환하기
        int res = 0;
        for (Integer integer : sensorDistanceDifference) {
            res += integer;
        }
        System.out.println(res);
    }
}
