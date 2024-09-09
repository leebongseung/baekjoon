package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 드래곤 커브
 *
 * 문제정의
 * - 세가지 속성 존재
 * - x축은 오른쪽, y축은 아래 방향임.
 * - 시작점
 * - 시작 방향
 * - 세대?
 *
 * - 0세대 드래곤 커브는 길이가 1인 선분이다.
 *  - (0, 0) 에서 시작하고 (1, 0)인 커브
 * - 1세대 커브는 0,0 1,0 1,-1 인 점
 * - 2세대는 1세대 만든 방법 이용해서 시계방향으로 90도 회전해서 끝점에 이어 붙이기
 * - k세대 드래곤 커브는 k-1 세대 드래곤 커브를 끝점을 기준으로 90도 시계방향 회전한 거임.
 * - 크기가 100 * 100 인 드래곤 커브가 n개 있음.
 * 1*1 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부부인 정사각형의 개수를 구하기
 *
 * 풀이방법 고안
 * - 그래프에서 진행방향 저장하기
 * - stack에 방향저장하기!
 * - stack pop 하고 뒤에서 부터 진행방향 결정됨.)
 * - 시작방향이 <- 일 경우
 * (왼 -> 아래 -> 오른쪽 -> 위)
 * - 0세대 : 왼 (stack : 왼)
 * - 1세대 : 위 (stack : 왼, 아래)
 * - 2세대 : (stack : 왼, 아래, 오, 아래)
 * - 3세대 : (stack : 왼, 아래, 오, 아래, 오, 위, 오, 아래)
 *
 *
 * 시작 시간 : 2시 8분
 * 종료 시간 : 3시 8분
 * */
public class _15685 {
    public static boolean[][] map = new boolean[101][101];

    // 증가하면 회전하는거임.
    enum Direction{
        RIGHT(1, 0), // 0
        TOP(0, -1), // 1
        LEFT(-1, 0), // 2
        BOTTOM(0, 1); // 3

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x, y;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 드래곤 커브 개수
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());
            drawGeneration(x, y, direction, generation);
        }

        // 꼭짓점이 4개인 정사각형 찾기
        int cnt = 0;
        for(int i=0; i<map.length - 1; i++){
            for(int j=0; j<map[0].length - 1; j++){
                if(map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]){
                    cnt++;
                }
            }
        }
/*        for (boolean[] booleans : map) {
            for (boolean aBoolean : booleans) {
                if(aBoolean) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println("====");*/
        System.out.print(cnt);
    }

    public static void drawGeneration(int x, int y, int direction, int generation){
        Stack<Integer> s = new Stack<Integer>();

        // 시작 지점 방문 처리
        map[y][x] = true;
        // 시작 방향 추가하기
        for(int i=0; i<= generation; i++){
            Stack<Integer> tmp = new Stack<Integer>();
            tmp = (Stack<Integer>) s.clone();
            if(i == 0){
                x += Direction.values()[direction].x;
                y += Direction.values()[direction].y;
                map[y][x] = true;
                s.push(direction);
            } else {
                // 큐가 빌 때 까지 빼서 방향 전환하기!
                while(!tmp.empty()){
                    int nextDirection = (tmp.pop() + 1) % 4 ;
                    x += Direction.values()[nextDirection].x;
                    y += Direction.values()[nextDirection].y;
                    map[y][x] = true;
                    s.push(nextDirection);
                    // 증가하면서 회전하기
                }
            }
        }
    }


}
