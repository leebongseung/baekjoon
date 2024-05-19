package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 시작 시간 : 11시 46분
 * 종료 시간 : ??
 * <p>
 * 풀이 내용
 * - n개의 수를 대표하는 통계값, n은 홀수
 * - 산술 평균, n개의 수의 합을 n으로 나눈값, 소수점 첫째자리에서 반올림
 * - 중앙값, n개의 수중 중앙에 위치하는 값.
 * - 최빈값, n개의 수중 가장 많이 나타나는 값
 * - 범위, n개의 수중 최댓값 최솟값의 차이
 * <p>
 * <p>
 * 중앙값, 범위는 정렬하면 됨
 * 산술평균,최빈값 1~n 까지 반복
 */
public class _2108 {
    public static int n;
    public static int[] arr;
    public static Map<Integer, Integer> m = new HashMap<>();
    public static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(bf.readLine());
            m.put(num, m.getOrDefault(num, 0) + 1);
            sum += num;
            arr[i] = num;
        }

        int max = m.keySet().stream().map(key -> m.get(key))
                        .mapToInt(val -> val)
                                .max().getAsInt();


        List<Integer> lst = new ArrayList<>(m.keySet().stream().filter(key -> m.get(key) == max).collect(Collectors.toList()));

        lst.sort((o1,o2) -> o1-o2);

        Arrays.sort(arr);
        // 산술평균
        System.out.println(Math.round((double) sum / n));
        System.out.println(arr[n / 2]);

        System.out.println(lst.size() == 1 ? lst.get(0) : lst.get(1));


        if(arr[n-1] > 0){
            if (arr[0] < 0) {
                System.out.println(arr[n-1] + Math.abs(arr[0]));
            } else {
                System.out.println(arr[n-1] - arr[0]);
            }
        } else {
            System.out.println(Math.abs(arr[n-1] - arr[0]));
        }

    }
}
