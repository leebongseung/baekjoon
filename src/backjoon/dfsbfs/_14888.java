package backjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*  시작 시간 : 8시 55분
 *   종료 시간 : 10시 15분
 * */
public class _14888 {
    public static int Max = Integer.MIN_VALUE;
    public static int Min = Integer.MAX_VALUE;
    public static int n;
    public static int[] operator = new int[4];
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine()); // 2이상 11이하
        arr = new int[n]; // 1~n까지 arr

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //  셋째 줄에는 합이 N-1인 4개의 정수
        // 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        // 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
        dfs(arr[0], 1);
        System.out.println(Max);
        System.out.println(Min);

    }

    /**
     * depth가 n일 경우 Max와 Min을 출력하는 메서드
     * @param num   이전의 결과값
     * @param depth 깊이
     * */
    private static void dfs(int num, int depth) {
        if (depth == n) {
            Max = Math.max(Max, num);
            Min = Math.min(Min, num);
        }

        // +, -, *, / 순으로 동작
        for (int i = 0; i < 4; i++) {

            // 연산자가 존재할 경우에만 반복.
            if (operator[i] > 0) {
                operator[i]--;
                switch (i) {
                    case 0:
                        dfs(num + arr[depth], depth + 1);
                        break;
                    case 1:
                        dfs(num - arr[depth], depth + 1);
                        break;
                    case 2:
                        dfs(num * arr[depth], depth + 1);
                        break;
                    case 3:
                        dfs(num / arr[depth], depth + 1);
                        break;
                }
                operator[i]++;
            }
        }
    }
}
