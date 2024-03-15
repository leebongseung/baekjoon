package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 9 시
 * 종료 시간 : 9시 20분
 * */
public class _2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] S = new int[n];
        S[0] = 0;
        for(int i =1; i< n; i++){
            S[i] = S[i-1] + i;
        }

        int res = 1;
        for(int i =1; i<= n/2; i++){
            int sum = 0;
            int num = i;
            while(sum <= n && num < n){
                sum = S[num++] - S[i-1];
                if(sum == n) {
//                    System.out.println("i = " + i);
//                    System.out.println("num = " + num);
                    res++;
                    break;
                }
            }
        }

        System.out.print(res);
    }
}
