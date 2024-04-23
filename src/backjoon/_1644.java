package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 시작 시간 : 8시 23분
 * 종료 시간 : 9시 11분
 *
 * 문제분석
 * - 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다!
 *  - 3 : 3 (한 가지)
 *  - 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
 *  - 53 : 5+7+11+13+17 = 53 (두 가지)
 *
 *  풀이방법
 *  - 연속된 소수의 합을 저장하는 배열을 만들자! dp 처럼
 *  - 그리고 이중 for 문으로 경우의 수를 계산해보자!
 * */
public class _1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> sum = new ArrayList<>(); // 연속된 소수의 합 저장하는 공간.
        sum.add(0);

        // 연속된 소수의 합 구하기
        for(int num = 2; num <= n; num++) {
            if(isPrime(num)){
                int size = sum.size();
                sum.add(sum.get(size- 1) + num);
            }
        }

        // 이중 for 문으로 경우의 수를 계산해보자
        int res = 0;
        for(int i=0; i<sum.size(); i++){
            for(int j=i+1; j<sum.size(); j++){
                if(sum.get(j) - sum.get(i) == n) res++;
                // 보다 클 경우 더이상 탐색해도 의미가 없음. break
                else if(sum.get(j) - sum.get(i) > n) break;
            }
        }

        System.out.print(res);

    }

    private static boolean isPrime(int num) {
        if(num < 2) return false;

        for (int i = 2; i * i <= num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
