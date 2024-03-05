package backjoon.math;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 숫자의 개수
        char[] str = bf.readLine().toCharArray();
        int sum = 0;

        for(int i =0; i<n; i++){
            sum+= str[i] - '0';
        }
        System.out.println(sum);


    }
}
