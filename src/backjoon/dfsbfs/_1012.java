package backjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*  시작 시간: 2시 52분
 *   종료 시간 : 3시 14분
 * */
public class _1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine()); // 테스트 케이스 개수

        StringBuilder sb = new StringBuilder();

        int[] dx = {0, 0, 1, -1}; //가로가변길이
        int[] dy = {-1, 1, 0, 0}; //세로가변길이


        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int m = Integer.parseInt(st.nextToken()); //가로 길이
            int n = Integer.parseInt(st.nextToken()); //세로 길이
            int k = Integer.parseInt(st.nextToken()); //양배추 개수

            int[][] arr = new int[m][n];

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
            }

            int res = 0;
            for (int j = 0; j < m; j++) {
                for (int z = 0; z < n; z++) {
                    if(arr[j][z] == 1){
                        arr[j][z] = 0;
                        Stack<int[]> stack = new Stack<>();
                        stack.push(new int[]{j,z});

                        while(!stack.isEmpty()){
                            int[] pop = stack.pop();
                            for(int r = 0 ; r < 4; r++){
                                int dxt = pop[0] + dx[r];
                                int dyt = pop[1] + dy[r];


                                if(dxt < 0  || dxt >= m || dyt < 0 || dyt >= n ) continue;

                                if(arr[dxt][dyt] == 1){
                                    arr[dxt][dyt] = 0;
                                    stack.push(new int[]{dxt, dyt});
                                }
                            }
                        }
                        res++;
                    }
                }
            }
            sb.append(res + "\n");
        }
        System.out.println(sb);
    }
}
