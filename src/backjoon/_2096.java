package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 7시 16분
 * 종료 시간 : 7시 40분
 *
 * 문제분석
 * - n줄에 0이상 9이하 숫자가 3개 씩 적혀있음.
 * - 이게임은 첫 줄에서 시작해서 마지막 줄에서 끝남.
 *
 * - 처음에 세개의 숫자중 하나를 선택함.
 * - 다음 줄로 내려갈때 바로아래의 수로 넘어가거나,
 * - 바로 아래의 수와 붙어 있는 수로만 이동가능하다.
 *
 * - 숫자표가 주어질 때, 얻을 수 있는 최대점수, 최소점수
 * 를 구하는 프로그램 만들기, 원룡이가 위치한 곳의 수의 합
 *
 * 시간이 1초다 n은 10만인데 14억으로 될것같긴함.
 * 근데 dp로도 풀수있을것같아. 내려올때 현재위치에서 구할 수 있는 최소 값과 최대값을 저장하는 dp 2가지를 굴리면 됨 .
 * */
public class _2096 {
    public static int n;
    public static final int m = 3;
    public static final int MODE_SIZE = 2;
    public static final int MIN_MODE = 0;
    public static final int MAX_MODE = 1;
    public static int[][] arr;
    public static int[][][] dp; // 3차원 배열, [x] : 행, [y] : 열 , [z]: 0일경우 최솟값 저장, 1일경우 최댓값 저장
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        arr = new int[n][m]; // n행 3열 이다.
        dp = new int[n][m][MODE_SIZE]; // mode 가 0 : 최솟값 저장, 1 : 최댓값 저장 하는 공간

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        // dp의 0번째 행은 arr과 동일하게 설정한다.
        for(int y=0; y<m; y++){
            for(int i = 0; i< MODE_SIZE; i++) dp[0][y][i] =  arr[0][y];
        }

        //dp 로직 시작
        for(int x=1; x<n; x++){
            for(int y=0; y<m; y++){
                if(y==0){
                    // 최솟값 비교해서 짚어넣기
                    // 현재위치 + 위쪽 위치, 현재위치 + 오른쪽대각선위 위치
                    dp[x][y][MIN_MODE] = Math.min(dp[x-1][y][MIN_MODE] + arr[x][y], dp[x-1][y+1][MIN_MODE] + arr[x][y]);
                    dp[x][y][MAX_MODE] = Math.max(dp[x-1][y][MAX_MODE] + arr[x][y], dp[x-1][y+1][MAX_MODE] + arr[x][y]);
                }
                else if(y == 1){
                    // 최솟값 비교해서 짚어넣기
                    // 현재위치 + 왼쪽대각선위 , 현재위치 + 위쪽, 현재위치 + 오른쪽대각선위
                    dp[x][y][MIN_MODE] = Math.min(dp[x-1][y-1][MIN_MODE] + arr[x][y],
                            Math.min(dp[x-1][y][MIN_MODE] + arr[x][y], dp[x-1][y+1][MIN_MODE] + arr[x][y]));

                    dp[x][y][MAX_MODE] = Math.max(dp[x-1][y-1][MAX_MODE] + arr[x][y],
                            Math.max(dp[x-1][y][MAX_MODE] + arr[x][y], dp[x-1][y+1][MAX_MODE] + arr[x][y]));

                } else{ // y == 2 일 경우
                    // 최솟값 비교해서 짚어넣기
                    // 현재위치 + 왼쪽대각선위 , 현재위치 + 위쪽
                    dp[x][y][MIN_MODE] = Math.min(dp[x-1][y-1][MIN_MODE] + arr[x][y], dp[x-1][y][MIN_MODE] + arr[x][y]);
                    dp[x][y][MAX_MODE] = Math.max(dp[x-1][y-1][MAX_MODE] + arr[x][y], dp[x-1][y][MAX_MODE] + arr[x][y]);
                }
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            min = Math.min(min, dp[n-1][i][MIN_MODE]);
            max = Math.max(max, dp[n-1][i][MAX_MODE]);
        }

        System.out.println(max + " " + min);
    }
}
