package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            q.add(Integer.valueOf(bf.readLine()));
        }
        int sum = 0;
        int a = 0;
        int b = 0;

        while (!q.isEmpty()) {
            if (a == 0) { // 최소값 꺼내기 1
                a = q.poll();
                continue;
            }
            b = q.poll(); // 최소값 꺼내기 2

            sum += (a+b);
            q.add(Integer.sum(a,b)); // a+b 합친 카드개수 짚어넣기
            a = 0;
        }
        System.out.println(sum);
    }
}
