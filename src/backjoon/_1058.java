package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 11분
 * 종료 시간 : ?
 *
 * 문제분석
 * - A -> B 가 친구이거나
 * - A 와 친구이고, B와 친구인 C가 존재하거나, 이런경우를 2-친구라고함.
 * - 가장 유명한 사람은 2-친구가 많은 사람
 * - 가장 유명한 사람의 친구의 수를 출력하자
 * -
 * */
public class _1058 {
    public static int n ;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                switch(str.charAt(j)){
                    case 'N':
                        arr[i][j] = 0;
                        break;
                    case 'Y':
                        arr[i][j] = 1;
                        break;
                }
            }
        }
    }

}
