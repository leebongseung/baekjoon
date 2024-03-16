package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 15시 43분
 * 종료 시간 :
* */
public class _10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // n개의 수
        int m = Integer.parseInt(st.nextToken()); // 연속된 구간의 합이 M으로 나누어 떨어지는 경우
        long res = 0; // 정답 반환

        int[] arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        int idx = 0;
        while(st.hasMoreTokens()){
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        long[] sum = new long[n];
        for(int i=0; i<n; i++){
            if(i == 0) sum[i] = arr[i];
            else sum[i] = sum[i-1] + arr[i];
        }

        // sum을 특정 수 m으로 나눗셈 연산을 하고 값을 다시 저장합니다
        HashMap<Long, Integer> newSum = new HashMap<>();
        for(int i =0; i<n; i++){
            long remainder = (sum[i] % m);
            if(remainder == 0L) res ++;

            if(newSum.containsKey(remainder)) newSum.put(remainder, newSum.get(remainder) + 1);
            else newSum.put(remainder, 1);
        }

        // 조합으로 개수 구하기
        for (Long key : newSum.keySet()) {
            long cnt = newSum.get(key);
            res += cnt * (cnt - 1) / 2;
        }

        System.out.println(res);
    }
}
