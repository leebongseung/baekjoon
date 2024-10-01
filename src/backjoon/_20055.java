package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 12시 58분
 * 종료 시간 :
 * 문제분석
 * - 길이가 N인 컨베이어 벨트
 * - 길이가 2N인 벨트가 위아래로 감싸며 돈다.
 * - 올리는 위치 2N -> 1 로
 * - 내리는 위치 N -> N+1 로
 *
 * 로봇은 올리는 위치에만 올릴 수 있음.
 * 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내림.
 * - 로봇은 컨베이어 벨트 위에 스스로 이동함.
 * - 로봇 올리는 위치에 올리거나 이동하면 그 즉시 내구도 1 감소
 *
 * 1. 벨트가 각 칸위에 있는 로봇과 함께 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 회전하는 방향으로 한칸 이동, 이동 하지 못한다면 가만히 있음.
 *  - 로봇이 이동하기 위해서 이동하려는 칸에 로봇이 없어야하고, 그 칸의 내구도가 1 이상 남아있어야함.
 *  3. 올리는 위치에 칸이 0이 아니면 올리는 위치에 로봇을 올린다
 *  4. 내구도가 0인 칸의 개수가 K 개 이상이라면 과정을 종료, 그렇지 않다면 1번 으로
 *
 */
public class _20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 2n개 주어짐.
        int k = Integer.parseInt(st.nextToken()); // 종료 되는 시점.

        PriorityQueue<Robot> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[2*n];
        LinkedList<Integer> durability = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*n; i++){
            durability.add(Integer.parseInt(st.nextToken()));
        }


        int phase = 1;
        int order = 1;
        while(true){
            // 1. 컨베이어 벨트 회전하기
            durability.add(0, durability.removeLast());

            // 1.1 로봇도 컨베이어 벨트 따라서 이동
            


            // 2. 가장 먼저 올라간 로봇부터 한칸 이동하기
            for(Robot r : pq){
                // 2-1. 이동 못한다면 가만히 있기

                // 2-2. 이동 하려면 이동하는 칸에 로봇이 없어야 함, 내구도가 1이상 이어야 함
                int nextIdx = (r.index + 1) % (2 * n);
                if(durability.get(nextIdx) <= 0) continue;

                // 3. 올리는 위치에 내구도가 0이 아니면 로봇 올리기

                // 3-1. 내리는 위치에 있다면 로봇 내리기!
                visited[nextIdx] = true;
                durability.set(nextIdx, durability.get(nextIdx) - 1);
                r.index = nextIdx;
            }

            // 4. 내구도가 0인 칸이 k개 이상이라면 과정 종료
            int cnt = 0;
            for (Integer i : durability) {
                if(i == 0) cnt++;
            }

            if(cnt >= k) break;

            // 5. 로봇 올리고 내구도 1 감소 하기
            if(!visited[0] && durability.get(0) > 0){ // 올리는 위치에 로봇이 없고 내구도가 존재하는 경우 로봇 올리기!
                durability.set(0, durability.get(0) - 1);
                visited[0] = true;
                pq.add(new Robot(0, order++));

                if(durability.get(0) == 0) k --;
            }


            // 6. 단계 증가하기
            phase++;
        }

        System.out.println(phase);
    }

    static class Robot implements Comparable<Robot>{
        int index, order;

        public Robot(int index, int order) {
            this.index = index;
            this.order = order;
        }

        @Override
        public int compareTo(Robot o) {
            return this.order - o.order;
        }
    }
}
