package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 10시 7분
 * 종료 시간 : 10시 39분
 *
 * 문제분석
 * - 우리집엔 도서관이 있어
 * - 책을 매우 많이 구매함. 집엔 책장이 없어서 탑처럼 쌓아놈.
 * - 책을 알파벳 순서대로 정렬하려 고함
 * - 사전순으로 A ~ Z 까지
 * - 책을 정렬할때 책을 하나 뺀다음 가장 위에 놓는 방법으로 정려함.
 *
 * - 1~N 까지의 번호가 책 이름의 사전순으로 매겨짐
 * - 1 은 사전순으로 가장 앞서는 책
 * - 책이 어떻게 쌓여있는지 주어질 때, 몇번 만에 사전순으로 쌓을 수 있는지 구하여라!
 * */
public class _2872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> lst = new ArrayList<>();

        for(int i=0; i<n; i++){
            lst.add(Integer.valueOf(br.readLine()));
        }

        // n-1번 인덱스부터 읽어가며 0번 인덱스 까지 읽기
        int idx = lst.size()-1;
        int res = 0; // 이동 횟수
        int val = n;
        while(idx >= 0){
            // 현재 값이 원하는 val인지 확인해보기
            int curVal = lst.get(idx);

            // 순서대로 큰 값이 존재하는지 확인하면된다~
            if(curVal == val){
                res++;
                val--;
            }

            idx --;
        }

        // 이동이 필요한 idx의 개수 반환하기
        System.out.println(n - res);
    }
}
