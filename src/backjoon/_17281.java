package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 18분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * -9명으로 이루어진 2팀
 * - 총 n이닝 동안 게임을 진행해야함.
 * - 한 게임 당 3아웃이 발생하면 이닝 종료 후 공수교대
 * - 경기 시작 전 타자 순서를 정해야함, 경기 도중 변경 불가
 * - 9번 타자 까지 공을 쳤을 때 3아웃이 발생하지 않은 상태면 다시 1번부터
 * - 이닝이 변경되어도 순서는 유지됨.
 * <p>
 * - 공격은 타자가 치는 것.
 * - 1루, 2루, 3루, 홈 다 거쳐서 도착하면 1점 득점.
 * - 타자가 홈에 도착하지 못하고 루에 머물수있음.
 * - 이닝시작될 때는 루에 머무는 주자는 없다.
 * <p>
 * - 타자 : 안타, 2루타, 3루타, 홈런, 아웃 중 하나
 * - 안타 : 모든 주자가 한루씩 진루
 * - 2루타 : 타자와 모든 주자가 두루씩 진루
 * - 3루타 : 타자와 모든 주자가 3루씩 진루
 * - 홈런 : 모든 주자가 홈까지 진루
 * - 아웃 : 모든 주자 진루 불가, 공격팀에 아웃 하나 증가
 * <p>
 * 타자 순서는
 * 1번만 4번째로 고정 나머지는 고정아님.
 */

public class _17281 {
    public static int n;
    public static int[][] arr;
    public static int res;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][9];
        visited = new boolean[9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        int[] order = new int[9];
        Arrays.fill(order, -1);
        // 1번이 4번타자여야함
        order[3] = 0;
        visited[0] = true;
        dfs(order, 0);
        System.out.println(res);
    }

    public static void dfs(int[] order, int depth) {

        // 탈출조건
        if (depth == 9) {

            // 순서가 다 맞춰 졌으니 순서에 맞게끔 반복하면됨.
            int o = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int outCnt = 0;
                boolean[] lu = new boolean[3];

                while (true) {
                    if (outCnt == 3)
                        break;
                    int idx = order[o++]; // 선수 번호
                    if (o == 9) {
                        o = 0;
                    }

                    if (arr[i][idx] == 0) {
                        outCnt++;
                    } else if (arr[i][idx] == 1) {
                        // 점수 획득
                        if (lu[2] == true) {
                            lu[2] = false;
                            sum++;
                        }
                        // 2~0루 한칸 전진
                        for (int j = 2; j >= 1; j--) {
                            lu[j] = lu[j - 1];
                            lu[j - 1] = false;
                        }
                        // 0루 true
                        lu[0] = true;
                    } else if (arr[i][idx] == 2) {
                        // 점수 획득
                        if (lu[2] == true) {
                            lu[2] = false;
                            sum++;
                        }
                        if (lu[1] == true) {
                            lu[1] = false;
                            sum++;
                        }
                        // 2~0루 두칸 전진
                        lu[2] = lu[0];
                        lu[0] = false;
                        // 0루 true
                        lu[1] = true;
                    } else if (arr[i][idx] == 3) {
                        // 점수 획득
                        for (int j = 0; j <= 2; j++) {
                            if (lu[j] == true) {
                                lu[j] = false;
                                sum++;
                            }
                        }
                        // 0루 true
                        lu[2] = true;
                    } else if (arr[i][idx] == 4) {
                        for (int j = 0; j <= 2; j++) {
                            if (lu[j] == true) {
                                lu[j] = false;
                                sum++;
                            }
                        }
                        // 타자점수
                        sum++;
                    }
                }
            }
            res = Math.max(res, sum);
            return;
        }

        // 반복 조건
        if (depth == 3) {
            dfs(order, depth + 1);
        } else {
            for (int j = 1; j < 9; j++) { // 선수 번호
                if (visited[j]) continue;
                visited[j] = true;
                order[depth] = j;
                dfs(order, depth + 1);
                order[depth] = -1;
                visited[j] = false;
            }
        }
    }
}
