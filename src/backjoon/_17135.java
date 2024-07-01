package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 4시 3분
 * 종료 시간 : 5시 6분
 *
 * 문제분석
 * - 크기가 N*M 크기의 격자판 존재
 * - 각 칸에는 포함된 적의 수는 최대 하나
 * - 격자판의 N번행의 바로 아래 (N+1)의 모든 칸에는 성이 있음.
 *
 * - 성을 지키기위해 궁수 3명을 배치하려 함.
 * - 궁수는 성이 있는 칸에 배치 가능.
 * - 하나의 칸에는 1명만 궁수 존재 가능.
 *
 * - 궁수가 공격하려는 적은 거리가 D 이하인 적 중 가장 가까운 적을 공격
 * - 적이 여럿이라면 가장 왼쪽에 적을 공격함.
 * - 같은 적이 여러궁수에게 공격당할 수 있음
 * - 공격받은 적은 게임에서 제외
 *
 * - 궁수의 공격 후 -> 적이 이동함
 * - 적은 아래로 한칸 이동하며, 성이 있는 칸으로 이동한 경우 게임에서 제외 됨.
 * - 모든 적이 격자판에서 제외되면 게임 끝.
 *
 * - 궁수를 배치하고 공격으로 제거할 수 있는 적의 수가 최대가 되는 수를 반환할것.
 * - 격자판의 두 거리는 유클리드 거리법으로 계산
 * - |r1- r2| + |c1 - c2|
 * */
public class _17135 {
    public static int n; // x축 개수
    public static int m; // y축 개수
    public static int d; // 궁수의 공격 거리 제한
    public static int res = Integer.MIN_VALUE;
    public static int[][] arr; // 격자판, 0 : 빈 칸, 1 : 적이 있는 칸
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m]; // n+1 은 궁수 배치하는 곳
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(res);
    }

    public static void dfs(int depth){

        if(depth == 3){
            int numberOfTargetsRemoved = 0;
            int[][] tmpArr = new int[n+1][m];
            for(int i=0; i<=n; i++){
                for(int j=0; j<m; j++) tmpArr[i][j] = arr[i][j];
            }

            while(true){
                // 타깃이 없다면 종료하기
                boolean isEnd = true;
                roof:
                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        if(tmpArr[i][j] == 1){
                            isEnd = false;
                            break roof;
                        }
                    }
                }
                if(isEnd) break;

                List<int[]> targets = new ArrayList<>();
                for(int i=0; i<m; i++) {
                    // 궁수가 있다면?
                    if(tmpArr[n][i] == 1){
                        // 공격 타깃 찾기
                        Optional<int[]> theTarget = findTheTarget(tmpArr, new int[]{n, i});
                        theTarget.ifPresent(targets::add);
                    }
                }

                // 가까운 적 공격하기
                for (int[] target : targets) {
                    // 만약 타겟이 공격 받을 수 있는 상황이라면 공격하기
                    if(tmpArr[target[0]][target[1]] == 1) {
                        tmpArr[target[0]][target[1]] = 0;
                        numberOfTargetsRemoved ++;
                    }
                }

                // 적 아래로 이동하기
                int[][] tmp = new int[n][m];
                for(int i=0; i<n -1; i++){
                    for(int j=0; j<m; j++){
                        if(tmpArr[i][j] == 1){
                            tmp[i+1][j] = 1;
                        }
                    }
                }


                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++) tmpArr[i][j] = tmp[i][j];
                }
            }

            res = Math.max(res, numberOfTargetsRemoved);
            return;
        }

        for(int i=0; i<m; i++){
            if(arr[n][i] == 1) continue;
            arr[n][i] = 1;
            dfs(depth+1);
            arr[n][i] = 0;
        }
    }

    public static Optional<int[]> findTheTarget(int[][] tmpArr, int[] archerPosition){
        // 가장 가까운 거리의 표적 찾기
        // 여러개일 경우 왼쪽 표적 찾기 y축이 적은 값!
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 표적 가능한 값이면 pq 에 짚어넣기
                int distance = distanceMeasurement(archerPosition, new int[]{i, j});
                if(tmpArr[i][j] == 1 && distance <= d){
                    pq.add(new Node(i, j, distance));
                }
            }
        }
        Node poll = pq.poll();
        if(poll == null ) return Optional.empty();

        return Optional.of(new int[]{poll.x, poll.y});
    }

    public static class Node implements Comparable<Node>{
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }


        // 거리 순으로 정렬하기, 아닐 경우 가장 왼쪽 값 먼저 출력되게 하기
        @Override
        public int compareTo(Node o) {
            return distance == o.distance ? y - o.y : distance - o.distance;
        }
    }

    public static int distanceMeasurement(int[] archerPosition, int[] targetLocation){
        return Math.abs(archerPosition[0] - targetLocation[0]) + Math.abs(archerPosition[1] - targetLocation[1]);
    }

}
