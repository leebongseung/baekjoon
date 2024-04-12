package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 12시 7분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 백트래킹 완탐인것같음.
 * */

import java.io.*;
import java.util.*;
public class _2784 {
    public static boolean[] visited = new boolean[6];
    public static int n = 6;
    public static String[] str = new String[n];
    public static String[] res = new String[3];
    public static boolean alreadyReturn = false;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<n; i++){
            str[i] = bf.readLine();
        }

        dfs(new String[6], 0);
        for (String re : res) {
            if(re == null){
                System.out.println(0);
                break;
            }
            System.out.println(re);
        }
    }

    public static void dfs(String[] tmp, int depth){
        if(alreadyReturn) return;

        if(depth == 6){
            // 비교하기
            // 세로 : tmp[0], tmp[1], tmp[2]
            // 가로 : tmp[3], tmp[4], tmp[5]

            // 일치하는지 비교하기!
            if(tmp[0].charAt(0) != tmp[3].charAt(0)) return;
            if(tmp[0].charAt(1) != tmp[4].charAt(0)) return;
            if(tmp[0].charAt(2) != tmp[5].charAt(0)) return;

            if(tmp[1].charAt(0) != tmp[3].charAt(1)) return;
            if(tmp[1].charAt(1) != tmp[4].charAt(1)) return;
            if(tmp[1].charAt(2) != tmp[5].charAt(1)) return;

            if(tmp[2].charAt(0) != tmp[3].charAt(2)) return;
            if(tmp[2].charAt(1) != tmp[4].charAt(2)) return;
            if(tmp[2].charAt(2) != tmp[5].charAt(2)) return;

            for(int i=0; i<3; i++){
                res[i] = tmp[i];
            }
            alreadyReturn = true;
            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                tmp[depth] = str[i];
                visited[i] = true;
                dfs(tmp, depth+1);
                visited[i] = false;
            }
        }
    }
}
