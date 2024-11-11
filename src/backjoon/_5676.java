package backjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 시작 시간 : 10시 10분
 * 종료 시간 : 11시 5분
 *
 * 문제 분석
 * - n개의 정수로 이루어진 수열이 존재.
 * - 게임은 총 k번 라운드로 이루어짐.
 * 명령
 * 1. 변경 : 수열의 한 값을 바꾸고 싶을 때 사용
 * 2. 곱셈 : i와 j를 말함. 그러면 X_i~ X_j 까지 곱한 값이
 * 양수인지 음수인지 0인지 대답하기
 * */

public class _5676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // n 개의 treeSet 구하기
            int treeSize = 0;
            while (n != 0){
                n /= 2;
                treeSize++;
            }
            treeSize = (int) Math.pow(2, treeSize + 1);

            // treeSet 만들기
            int[] arr = new int[treeSize];
            int leafNodeStart = treeSize/2 - 1;
            st = new StringTokenizer(br.readLine());
            for(int i=leafNodeStart + 1; i <= leafNodeStart + n; i++){
                int val = Integer.parseInt(st.nextToken());
                if(val > 0) arr[i] = 1;
                else if(val == 0) arr[i] =0;
                else arr[i] = -1;
            }
            setTree(arr, treeSize - 1);

            System.out.println();

            // 새로운 값 읽어서 토큰이 더 없으면 종료
            st = new StringTokenizer(br.readLine());
        }

    }

    public static void setTree(int[] arr, int idx){
        while (idx != 1){
            arr[idx / 2] *= arr[idx];
            idx--;
        }
    }

    public static void replace(int[] arr, int v1, int v2){
        arr[v1] = v2;
        while(v1 != 1){
            arr[v1 / 2] *= arr[v1];
            v1--;
        }
    }

/*    public static String multiplication(int[] arr, int v1, int v2){

    }*/
}
