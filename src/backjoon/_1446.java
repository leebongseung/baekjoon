package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지름길 개수
        int d = Integer.parseInt(st.nextToken()); // 고속도로 길이
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            if(!map.containsKey(start)) map.put(start, new ArrayList<>());
            List<int[]> lst = map.get(start);
            lst.add(new int[]{end, len});
        }

        // 문제 풀이 시작
        int[] dp = new int[d + 1];
        for (int i = 0; i <= d; i++)
            dp[i] = i;

        for (int i = 0; i <= d; i++) {
            if(i > 0)
                dp[i] = Math.min(dp[i], dp[i-1] + 1);
            if(!map.containsKey(i)) continue;
            List<int[]> lst = map.get(i);
            for (int[] ints : lst) {
                int end = ints[0];
                int len = ints[1];
                if (end > d || (end - i) < len) continue;
                dp[end] = Math.min(dp[end], dp[i] + len);
            }
        }
        System.out.println(dp[d]);
    }
}
