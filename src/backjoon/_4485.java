package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 12 시 14분
 * 종료 시간 : 12 시 59분
 * <p>
 * 문제분석
 * - 젤다 전설 화폐단위 루피
 * - 도둑 루피 : 검정색 루피, 이건 루피가 감소함
 * - 도둑루피만 가득한 n*n 크기의 동굴의 제일 왼쪽 위에 존재
 * - 녹색옷은 주인공 이름은 링크, 젤다는 그냥 잡혀있는 공주
 * -동굴의 출구는 n-1 n-1 임. 각 칸마다 도둑루피가 있는데
 * - 링크를 읽는 금액을 최소로 이동하기, 한번에 상하좌우 한칸씩 이동 가능.
 * - 링크가 잃을 수 밖에 없는 최소금액은?
 * <p>
 * DFS로 최솟값을 계산하면서 저장하기! => 실패
 * 다익스트라라는 힌트를 얻음
 */
public class _4485 {
    public static int n;
    public static int[][] arr;
    public static int[][] dijkstraArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tCase = 0;
        while (true) {
            tCase++;
            n = Integer.parseInt(bf.readLine());
            if (n == 0) break;

            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 다익스트라 배열 초기화
            dijkstraArr = new int[n][n];
            for (int i = 0; i < n; i++){
                    Arrays.fill(dijkstraArr[i], Integer.MAX_VALUE);
            }
            dijkstraArr[0][0] = arr[0][0];

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                    o1[2] - o2[2]); //현재노드, 가중치 저장해서 가중치 순으로 반환.
            pq.add(new int[]{0, 0, dijkstraArr[0][0]});

            // 다익스트라 시작쿠~
            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                int x = poll[0];
                int y = poll[1];

                // 현재 방향에서 네방향 다 확인하면서 최솟값인 위치로 이동하기!
                for (Direction d : Direction.values()) {
                    int dx = x + d.x;
                    int dy = y + d.y;

                    if (dx < 0 || dx >= n || dy < 0 || dy >= n) continue;

                    // 다익스트라 조건 현재 값보다 클경우 짚어넣고 pq에 가중치 낮은것부터 돌리기~
                    if(dijkstraArr[dx][dy] > dijkstraArr[x][y] + arr[dx][dy]){
                        dijkstraArr[dx][dy] = dijkstraArr[x][y] + arr[dx][dy];
                        pq.add(new int[]{dx, dy, dijkstraArr[dx][dy]});
                    }
                }

            }


            bw.write("Problem " + tCase + ": " + dijkstraArr[n-1][n-1] + "\n");
        }

        bw.flush();
        bw.close();
    }

    enum Direction {
        TOP(-1, 0),
        BOTTOM(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);

        public final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }



}
