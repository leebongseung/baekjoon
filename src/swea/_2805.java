package swea;

import java.io.*;
import java.util.StringJoiner;

/**
 * 시작 시간 : 11시 17분
 * 종료 시간 : 11시 50분 
 *
 * 문제분석
 * - n*n 크기의 농장이 존재
 * - 농장의 크기는 항상 홀수
 * - 정사각형의 마름모 형태로만 수확이 가능함. (음..)
 * - 농장 n크기에서 농작물의 가치가 주어질 때 규칙에 따라 얻을 수있는 수익을 계산하라
 *
 * 구현임.
 * 마름모는 어떻게 할건인가?
 * - 원점으로 부터 유클리드 거리가 n만큼이어야함.
 * -
 * */
public class _2805 {
    public static int n;
    public static int[][] arr;
    public static int mid; // 중심 좌표 저장.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringJoiner sj = new StringJoiner("\n");
        int tCase = Integer.parseInt(bf.readLine());
        for(int t=1; t<= tCase; t++){
            n = Integer.parseInt(bf.readLine());
            arr = new int[n][n];
            mid = n/2;

            for(int i=0; i<n; i++){
                String str = bf.readLine();
                for(int j=0; j<n; j++){
                    arr[i][j] = Integer.parseInt(str.substring(j, j+1));
                }
            }

            int cnt = getCount();
            sj.add("#" + t + " " + cnt);
        }
        bw.write(sj.toString());
        bw.flush();
        bw.close();
    }

    private static int getCount() {
        if(n == 1) return arr[0][0];

        int cnt = 0;
        int startY = mid;
        int endY = mid;


        for(int x=0; x<n; x++){
            if(x != 0){
                if(x <= mid) {
                    startY--;
                    endY++;
                } else {
                    startY++;
                    endY--;
                }
            }
            for(int y=startY; y<= endY; y++){
                cnt += arr[x][y];
            }
        }

        return cnt;
    }
}
