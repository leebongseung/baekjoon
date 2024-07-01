package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 3분
 * 종료 시간 : 10시 41분
 *
 * 문제분석
 * - Dummy : 뱀이 나와서 기어다님, 사과 먹으면 뱀의 길이가 늘어남.
 * - 뱀이 벽 또는 자기자신의 몸과 부딪친다면 게임 끝!
 *
 * - n*n 정사각형 보드위에서 진행
 * - 몇몇칸에는 사과가 놓임.
 * - 보드의 상하좌우 끝에는 벽이 있다.
 * - 뱀의 맨좌측위에서 시작하고 뱀의길이는 1이다. 뱀은 처음에 오른쪽을 바라봄.
 *
 * - 규칙
 *  - 뱀은 몸길이를 늘려 머리를 다음칸에 위치 시킴.
 *  - 벽이나 자기자신과 부딪치면 게임 끝
 *  - 이동칸에 사과가 있다면, 사과가 없어지고 꼬리는 움직이지 않음
 *  - 사과가 없다면? 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌, 즉 몸길이는 변하지 않음.
 *
 *  사과의 위치와 뱀의 이동경로가 주어질때 이 게임은 몇초에 끝나는지 계산하기
 *
 *  구현인것같다.
 * */
public class _3190 {
    public static final int APPLE_VALUE = 1;
    public static final int DUMMY_VALUE = -1;


    enum Direction{
        TOP(-1, 0),
        RIGHT(0, 1),
        BOTTOM(1, 0),
        LEFT(0, -1)
        ;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public final int x, y;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] arr= new int[n][n];

        int k = Integer.parseInt(bf.readLine()); // 사과의 개의
        for(int i=0; i<k; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            arr[x][y] = APPLE_VALUE; // 사과를 나타내는 값
        }

        int direction = 1; // 현재 오른쪽으로 세팅됨.

        int l = Integer.parseInt(bf.readLine()); // 뱀의 방향 변환 횟수
        int[] turnTime = new int[l];
        String[] transitionDirection = new String[l]; // 'L' : 왼쪽, 'D' : 오른쪽

        for(int i=0; i<l; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            turnTime[i] = Integer.parseInt(st.nextToken());
            transitionDirection[i] = st.nextToken();
        }

        int idx = 0; // 방향 전환을 가르키는 idx
        int time = 0; // 게임 끝나는 시간.

        // 머리 위치를 나타내는 인덱스
        int x = 0;
        int y = 0;
        arr[0][0] = -1;

        Queue<int[]> q = new LinkedList<>(); // 꼬리 자르기용 q
        q.add(new int[]{x, y});
        while(true){
            time++;

            // 다음 이동 위치 계산하기
            int dx = x + Direction.values()[direction].x;
            int dy = y + Direction.values()[direction].y;

            if(dx <0 || dx >=n || dy <0 || dy>=n) break;

            // 꼬리 남기고, 전진
            if(arr[dx][dy] == 1){
                arr[dx][dy] = DUMMY_VALUE;
            }
            // 꼬리 지우고, 전진
            else if(arr[dx][dy] == 0){
                arr[dx][dy] = DUMMY_VALUE;
                int[] poll = q.poll();
                arr[poll[0]][poll[1]] = 0;
            }
            // 자기자신일 경우
            else{
                break;
            }

            // 뱀의 머리 추가하기~
            q.add(new int[]{dx, dy});

            // 머리 위치 갱신하기
            x = dx;
            y = dy;

            // n초가 지난뒤 방향 회전 해야함.
            if(idx < l && turnTime[idx] == time){
                if(transitionDirection[idx].equals("L")){
                    // 왼쪽으로 90도 회전
                    direction = direction - 1 < 0 ? 3 : direction - 1;
                } else {
                    // 오른쪽으로 90도 회전
                    direction = direction + 1 >= 4 ? 0 : direction + 1;
                }
                idx++;
            }

        }

        System.out.println(time);
    }
}
