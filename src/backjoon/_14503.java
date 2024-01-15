package backjoon;
/*  시작 시간 : 9시 6분
*   종료 시간 : 10시 50분 못품 이어서 풀어야할듯
*
* */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _14503 {
    // 북, 동, 남 ,서
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static int n;
    public static int m;
    public static int r;
    public static int c;
    public static int direction;
    public static int cnt = 0;
    public static List<List<Integer>> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken()); // 방 크기 n
        m = Integer.parseInt(st.nextToken()); // 방 크기 m

        // 로봇 청소기가 있는 좌표
        st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken()); // 좌표 r
        c = Integer.parseInt(st.nextToken()); // 좌표 c
        // 바라 보고 있는 방향 0 : 북쪽, 1 : 동쪽, 2: 남쪽, 3: 서쪽
        direction = Integer.parseInt(st.nextToken());


        // n *m 의 상태값 저장하기
        arr = new ArrayList<>();
        for(int i =0; i<n; i++){
            arr.add(new ArrayList<>());
        }

        // 0 인 경우 : 청소가 되지않은 칸, 1 인경우 벽이 있는 곳
        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            while(st.hasMoreTokens()) {
                arr.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        // 로봇 청소기 동작 로직
        dfs(r,c, direction);
        System.out.print(cnt);
    }
    public static void dfs(int zx, int zy, int zdirection){
        int direction = zdirection;
        int r = zx;
        int c = zy;
        if(arr.get(r).get(c).equals(0)){
            arr.get(r).remove(c);
            arr.get(r).add(c, -1);
            cnt ++;

            System.out.print("    ");
            for(int j =0; j< m; j++){
                System.out.print(j +"   ");
            }
            System.out.println();
            int k =0;
            for (List<Integer> integers : arr) {
                System.out.print(k++ + "   ");
                for (Integer integer : integers) {
                    System.out.print(integer +"   ");
                }
                System.out.println();
            }
        }

        // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
        boolean isMove = false;
        for(int i = 0; i<4; i++){
            int x = dx[i] + r;
            int y = dy[i] + c;

            if(arr.get(x).get(y).equals(0)) isMove = true;
        }

        if(!isMove) return;

        // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
        for(int i =0; i < 4; i++){
            // 3.1. 반시계 방향으로 90도 회전한다.(왼쪽으로 회전) 0 -> 3 -> 2 -> 1 -> 0
            direction = (direction + 1) % 4 ;

            // 3.2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.

            int x = r + dx[direction];
            int y = c + dy[direction];

            if(x < 0 || x >=n || y <0 || y >= m) continue;
            if(arr.get(x).get(y).equals(0)){
                System.out.println("전진, r = " + r + ", c = " +  c);
                dfs(x, y, direction);
            }
        }
    }
}
