package backjoon.math;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
* 시작 시간 : 16시 06분
* 종료 시간 :
* */
public class _1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        List<Integer> arr = new ArrayList<>();

        // 토크나이저를 이용해 모든 값을 list에 저장하고, 최댓값을 구해라.
        for(int i =0; i<n; i++){
            arr.add(Integer.valueOf(st.nextToken()));
        }

        // 모든 점수 중 (점수 / 최댓값 * 100.0) 으로 바꾸고 sum에다가 더하세요
        double max = Collections.max(arr);
        double sum = 0;
        for (Integer integer : arr) {
            sum += (integer / max * 100.0);
        }

        // n 개의 개수만 큼 나누고 결과 반환
        System.out.println(sum / n);
    }
}
