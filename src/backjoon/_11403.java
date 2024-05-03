package backjoon;

import java.io.*;
import java.util.*;

public class _11403 {
    public static int n;
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 배열 순회하면서 노드 탐색하기?
        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[n];
            for(int j=0; j<n; j++){
                if(arr[i][j] == 1 && !visited[j]){
                    visited[j] = true;
                    operationOr(visited, i, j);
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void operationOr(boolean[] visited, int currentIdx, int findIdx){
        for(int i=0; i<n; i++){
            if(arr[findIdx][i] == 1 && !visited[i]){
                visited[i] = true;
                arr[currentIdx][i] = 1;
                operationOr(visited, currentIdx, i);
            }
        }
    }
}
