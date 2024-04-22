package backjoon;

import java.io.*;
import java.util.*;

/**
 * 시작 시간 : 10시 6분
 * 종료 시간 : ?
 * <p>
 * 문제분석
 * - F 층으로 이루어진 고층 건물
 * - 스타트링크가 존재하는곳은 G 층
 * - 현재층은 S층
 * - 엘리베이터를 타고 G층으로 이동하려고 함.
 * - 버튼 총 2가지 존재 : U,D 로 위로 아래로를 의미한다.(해당층이 없다면 안움직임)
 * - G 층에 도착하려면 버튼을 적어도 몇번 눌러야 하는지 구하시오
 * G 층에 갈 수 없다면 use the stairs 를 출력한다!
 * <p>
 * 이분탐색 가능할거같은데?
 */
public class _5014 {
    public static int totalStair;
    public static int currentStair;
    public static int targetStair;
    public static int upCnt;
    public static int downCnt;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        totalStair = Integer.parseInt(st.nextToken());  // 고층 거눔ㄹ 층수
        currentStair = Integer.parseInt(st.nextToken()); // 현재 층
        targetStair = Integer.parseInt(st.nextToken()); // 목표 층
        upCnt = Integer.parseInt(st.nextToken()); // 위로 몇번가는지
        downCnt = Integer.parseInt(st.nextToken()); // 아래로 몇 번 가는지
        visited = new boolean[totalStair + 1];

        int res = bfs();
        if(res >= 0) System.out.println(res);
        else System.out.println("use the stairs");
    }

    public static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{currentStair, 0});
        visited[currentStair] = true;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int stair = poll[0];
            int idx = poll[1];
//            System.out.print("stair = " + stair);
//            System.out.println(", idx = " + idx);
            if(stair == targetStair) return idx;

            if(stair + upCnt <= totalStair && !visited[stair+upCnt]){
                visited[stair+upCnt] = true;
                q.add(new int[]{stair + upCnt, idx+1});
            }

            if(stair - downCnt >= 1  && !visited[stair- downCnt]){
                visited[stair- downCnt] = true;
                q.offer(new int[]{stair - downCnt, idx + 1});
            }
        }


        return -1;
    }
}
