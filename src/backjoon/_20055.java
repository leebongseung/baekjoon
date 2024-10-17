package backjoon;

import javax.lang.model.SourceVersion;
import java.awt.image.renderable.RenderableImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 4시 35분
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 길이가 2N인 컨베이어 벨트
 * - 위쪽 N( 1~ N번호)
 * - 아래쪽 N(2N ~ N+1)
 * 벨트가 회전함ㄴ 1~2N-1 번 까지 순서대로 회전한다.
 * - i번째 내구도는 A_i임.
 * - 올리는 위치 : 2N -> 1로 가는 과정
 * - 내리는 위치 : N -> N+1로 가는 과정
 * <p>
 * 1. 벨트가 로봇과 함께 한 칸 회전
 * 2. 가장 먼저 올라간 로봇 부터 회전 방향으로 한칸 이동, 회전 못하면 가만히
 * - 이동 조건
 * 1. 해당 위치에 로봇이 없거나, 내구도가 1 이상 남아있음.
 * 3. 올리는 위치에 내구도가 0이 아니면 로봇 올리기
 * 4. 내구도가 0인 칸의 개수가 K개 라면 과정 종료하기
 * 5. 반복하기
 * <p>
 * 이해한 내용
 * 1. 올리는 위치는 1, 2N, 2N-1, ... , 2 순으로 역방향
 * 2. 내리는 위치는 N, N-1, N-2, ... N+1 순으로 역방향
 */
public class _20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n * 2];
        boolean[] visited = new boolean[n * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        // 역방향으로 한칸씩 전진함.
        int upPoint = 0;
        int downPoint = n;

        Queue<Integer> robot = new LinkedList<>();
        int res = 0;
        while (true) {
            res++;
            // 1. 벨트 한칸씩 회전하기
            upPoint = upPoint - 1 < 0 ? ((2 * n) - 1) : upPoint - 1;
            downPoint = downPoint - 1 < 0 ? ((2 * n) - 1) : downPoint - 1;

            // 2. 로봇들을 회전 방향으로 한칸 씩 전진.
            for (int i = 0; i < robot.size(); i++) {
                int curRot = robot.poll();
                visited[curRot] = false;

                int nextRot = (curRot + 1) % (n * 2);
                visited[nextRot] = true;
                robot.add(nextRot);
                arr[nextRot]--;
            }

            // 3. 로봇들을 회전 방향으로 한칸 씩 전진.
            for (int i = 0; i < robot.size(); i++) {
                int curRot = robot.poll();
                visited[curRot] = false;

                // 로봇이 이미 떨어지는 구간에 도달 했을 경우
                if (curRot == downPoint) {
                    continue;
                }

                int nextPointIdx = (curRot + 1) % (2 * n);

                // 다음위치에 로봇이 존재하거나, 낭떨어지라면 가만히 있기
                if (visited[nextPointIdx] || nextPointIdx == downPoint || arr[nextPointIdx] <= 0) {
                    robot.add(curRot);
                    visited[curRot] = true;
                } else {
                    visited[nextPointIdx] = true;
                    robot.add(nextPointIdx);
                    arr[nextPointIdx]--;
                }
            }

            // 3. 올리는 위치에 내구도가 0이 아니고, 로봇이 없다면 올리기
            if (!visited[upPoint] && arr[upPoint] != 0) {
                robot.add(upPoint);
                visited[upPoint] = true;
                arr[upPoint]--;
            }

            // 주석 출력구문
            System.out.println("upPoint = " + upPoint);
            System.out.println("downPoint = " + downPoint);
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();

            for (Integer integer : robot) {
                System.out.println("로봇 위치 = " + integer);
            }
            System.out.println("============");

            // 4. 내구도가 0인칸이 k이상이라면 과정 종료하기
            int cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] <= 0) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                break;
            }

        }
        System.out.println(res);
    }
}
