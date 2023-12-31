package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*  시작 시간 : 9시 55분
*   종료 시간 : 10시 11분
* */
public class _1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner(" ");
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i =0; i<n; i++){
            //  자기보다 키가 큰 사람이 왼쪽에 몇 명이 있었는지 주어진다
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 사람들이 줄 서는 위치를 기록해 둠.
        // 아침에 자기가 기록한것과 같은지 확인한다.
        // 사람들은 자기보다 큰 사람이 왼쪽에 몇명있는지만 기억하고,
        // n명의 사람이 있고, 사람들의 키는 1부터 n 만큼 다름.

        List<Integer> res = new LinkedList<>();
        for(int i =n-1; i >= 0; i--){
            // 마지막 인덱스 부터 짚어넣기

            // 왼쪽에 나보다 큰게 몇개 있는지 세는 과정
            int idx = arr[i];
            int value = i+1;

            res.add(idx, value);
        }

        // 출력 하기
        for (Integer i : res) {
            sj.add(Integer.toString(i));
        }

        System.out.println(sj);
    }
}
