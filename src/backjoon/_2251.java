package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 10시 2분
 * 종료 시간 : 10시 48분
 *
 * 문제분석
 * - A, B, C 리터인 3개의 물통 존재.
 * - 앞선 두물통은 비어있음, C는 가득 참.
 * - 다른 한통이 가득 찰 때 까지 물을 부을 수 있음, 이과정에서 손실되는 물은 없음..
 *
 * - 처음 A와 B는 비어있음, C만 가득찬 상태
 * - A가 비어있을 때, C에 담겨 있을 수 있는 물의 양을 모두 구하시오.
 *
 * 힌트봄, 너비우선탐색 OR 깊이 우선탐색이래 왜?
 * 백트래킹은 안되나 3 뭐그런식으로도 나올 수 있을 것같고 그렇군
 * */

public class _2251 {
    public static boolean [][][] visited = new boolean[201][201][201];
    public static int[] maxVal = new int[3];

    public static Set<Integer> res = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        maxVal[0] = Integer.parseInt(st.nextToken());
        maxVal[1] = Integer.parseInt(st.nextToken());
        maxVal[2] = Integer.parseInt(st.nextToken());

        bfs();

        for (int re : res.stream().mapToInt(m->m).sorted().toArray()) {
            System.out.print(re + " ");
        }
    }

    private static void bfs() {
        res.add(maxVal[2]);
        visited[0][0][maxVal[2]] = true;
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0, maxVal[2]});
        while(!q.isEmpty()){
            int[] poll = q.poll();
            // 결과 값 짚어넣기
            if(poll[0] == 0){
                res.add(poll[2]);
            }

            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    //같은곳엔 못옮김
                    if(i==j) continue;

                    // 넣을 곳이 가득 찾을 경우
                    if(poll[j] == maxVal[j]) continue;

                    // 부을 곳이 비었을 경우
                    if(poll[i] == 0) continue;

                    // 붓기 !
                    int[] newN = new int[]{poll[0],poll[1],poll[2]};

                    // 옮겨지는 양
                    int move = Math.min(poll[i], maxVal[j]-poll[j]);
                    newN[i]-=move;
                    newN[j]+=move;

                    // 기존에 계산 했던 값인 경우
                    if(visited[newN[0]][newN[1]][newN[2]]) continue;

                    q.add(new int[]{newN[0],newN[1],newN[2]});
                    visited[newN[0]][newN[1]][newN[2]] = true;

                }
            }


        }
    }
}
