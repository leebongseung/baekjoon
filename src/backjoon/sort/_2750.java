package backjoon.sort;

import java.io.*;
import java.util.*;

/**
 * 문제분석
 * - 시간제한 1초 정렬문제
 * - n이 1000이하일때 중복되는 수는 없고 정렬하는문제
 * */

public class _2750 {
        public static void main(String[] args) throws IOException {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(bf.readLine());
            int[] arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(bf.readLine());
            }

            Arrays.sort(arr);

            for(int i : arr){
                bw.write(i + "\n");
            }
            bw.flush();
            bw.close();
            bf.close();

        }
    }
