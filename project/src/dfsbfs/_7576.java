package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*  시작 시간 : 2시 46분
*   종료시간 : 3시 18분
* */
public class _7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken()); // 행 개수
        int n = Integer.parseInt(st.nextToken()); // 열 개수

        // 2차원 배열 초기화
        List<List<Integer>> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            arr.add(new ArrayList<>());
        }

        // 토마토 짚어넣기.
        /*  1 : 익은 토마토
        *   0 : 익지 않은 토마토
        *   -1 : 토마토가 없는 칸.
        * */
        for(int i =0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            while(st.hasMoreTokens()){
                arr.get(i).add(Integer.valueOf(st.nextToken()));
            }
        }
        /* 익은 토마토는 상하좌우 안익은 토마토에게 영향을 줌. */
        // 상 하 좌 우
        int dx[] = {0, 0, -1, 1};
        int dy[] = {-1, 1, 0, 0};

        // 깊이 우선 탐색, cnt가 가장 큰 값을 구하면 됨.
        Stack<int[]> stack = new Stack<>();
        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr.get(i).get(j) == 1) stack.push(new int[]{j, i});
            }
        }

        int cnt = -1;
        while(!stack.isEmpty()){
            Stack<int[]> today = new Stack<>(); // 하루하루 날짜를 기록하기 위해 쓴 변수
            while(!stack.isEmpty()){
                today.push(stack.pop());
            }

            while(!today.isEmpty()){
                int[] pop = today.pop(); //[0] : x, [1] : y
                for(int i=0; i<4; i++){
                    int x = pop[0] + dx[i];
                    int y = pop[1] + dy[i];
                    if(x < 0 || x >= m || y <0 || y>= n) continue;

                    // 만약에 익지않은 토마토를 발견했다면.
                    if(arr.get(y).get(x) == 0){
                        arr.get(y).remove(x);
                        arr.get(y).add(x, 1); // 익은 토마토로 바꾸기
                        stack.push(new int[]{x, y});
                    }
                }
            }
/*            for (List<Integer> integers : arr) {
                for (Integer integer : integers) {
                    System.out.print(integer +" ");
                }
                System.out.println();
            }
            System.out.println("-----------------");*/

            cnt++; // 날짜 증가.
        }

        int res = cnt;

        // 0 인 값이 존재할 경우 -> 모든 익지 못하기 때문에 -1 출력하기
        for (List<Integer> integers : arr) {
            for (Integer integer : integers) {
                if(integer == 0){
                    res = -1;
                }
            }
        }
        System.out.println(res);
    }
}
