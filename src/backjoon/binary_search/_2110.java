package backjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*  시작 시간 : 21시 38분
*   종료 시간 : 00시 10분
* */
public class _2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집 개수
        int c = Integer.parseInt(st.nextToken()); // 공유기 개수
        List<Integer> arr = new ArrayList<>();

        for(int i=0; i<n; i++){
            arr.add(Integer.valueOf(bf.readLine()));
        }

        Collections.sort(arr);
        // 최소 거리
        int left = 1;
        // 최대거리 (먼집 - 가까운 인덱스집)
        int right = arr.get(arr.size()-1) - arr.get(0);
        int res = 0;
        while(left <= right) {
//            System.out.println("left = " + left);
//            System.out.println("right = " + right);
//            System.out.println();
            int mid = (left + right) / 2; // 두집사이의 간격
            int cnt = 1;
            int preHome = -1;
            for (Integer Home : arr) {
                if(preHome == -1){
                    preHome = Home;
                    continue;
                }

                if(mid <= (Home - preHome)){
                    preHome = Home;
                    cnt++;
                }
            }
//            System.out.println("cnt = " + cnt);
//            System.out.println();
            if(cnt >= c){ // 공유기가 많다는거니, 거리를 늘려서 공유기를 적게 설치해야 한다.
                left = mid+1;
                res = mid;
            } else{ // 공유기가 적다는거니, 거리를 좁혀서 공유기를 많이 설치해야 한다.
                right = mid-1;
            }
        }
        System.out.println(res);
    }
}

/*
*
*123123
9 3
1
2
3
4
5
6
7
8
100
* */
