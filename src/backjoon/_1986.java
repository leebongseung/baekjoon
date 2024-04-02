package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1986 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        // q의 개수, q의 위치가 출력됨.
        //Q는 가로, 세로, 대각선으로 갈 수 있는 만큼 최대한 이동 가능
        st = new StringTokenizer(bf.readLine());
        int qCnt = Integer.parseInt(st.nextToken());
        int[][] qIdx = new int[qCnt][2];
        for(int i =0; i< qCnt; i++){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            qIdx[i][0] = x;
            qIdx[i][1] = y;
        }

        st = new StringTokenizer(bf.readLine());
        int kCnt = Integer.parseInt(st.nextToken());
        int[][] kIdx = new int[kCnt][2];
        for(int i =0; i< kCnt; i++){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            kIdx[i][0] = x;
            kIdx[i][1] = y;
        }

        st = new StringTokenizer(bf.readLine());
        int pCnt = Integer.parseInt(st.nextToken());
        int[][] pIdx = new int[pCnt][2];
        for(int i =0; i< pCnt; i++){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pIdx[i][0] = x;
            pIdx[i][1] = y;
        }

        // P 먼저 저장, 

        //Knight는 x나 y 2콤마 이동하고, 반대 x나 y 1콤마 이동할 수 있음.
        //Pawn 은 그냥 벽 그자체


    }
}
