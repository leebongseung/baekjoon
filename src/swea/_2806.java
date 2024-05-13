package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 시작 시간 : 2시
 * 종료 시간 : 4시 47분
 * <p>
 * 문제분석
 * - n queen 대표적인 백트래킹으로 풀 수 있는 문제
 * 즉 모든 경우의 수를 시도 해보자
 * - n*n 보드가 주어짐
 * - n은 임의의 수 10이하임
 */
public class _2806 {
    public static int n;
    public static List<int[]> lst;
    public static int maxVal;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner("\n");
        int tCase = Integer.parseInt(bf.readLine());
        for (int t = 0; t < tCase; t++) {
            n = Integer.parseInt(bf.readLine());
            lst = new ArrayList<>();
            maxVal = 0;

            dfs(0, 0);
            sj.add("#" + (t + 1) + " " + maxVal);
        }
        System.out.println(sj);
    }

    public static void dfs(int row, int size) {
        if (size == n) {
            maxVal++;
            return;
        }

        for (int y = 0; y < n; y++) {
            if (!isOk(row, y)) continue;
            int idx = lst.size();

            // 가로 세로 대각선 계산하기
            lst.add(new int[]{row, y});
            dfs(row + 1, size + 1);
            lst.remove(idx);
        }

    }

    public static boolean isOk(int x, int y) {
        for (int[] queen : lst) {
            int qx = queen[0];
            int qy = queen[1];

            if (x == qx || y == qy) return false;
            if (Math.abs(x - qx) == Math.abs(y - qy)) return false;
        }
        return true;
    }
}
