package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작시간 : 10시 13분
 * 종료 시간 : ??
 *
 * 문제분석
 * - 부모와 자식사이 1촌
 * - 아버지와 할아버지 1촌, 나와 할아버지 2촌
 * - 아버지 형제들과 할아버지 1촌, 나와 아버지 형제를 3촌
 * - 촌수를 계산하시오
 * */
import java.util.*;
public class _2644 {
    public static Map<Integer, List<Integer>> map = new HashMap<>();
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        visited = new boolean[n];
        int[] findNum = new int[2];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for(int i=0; i<findNum.length; i++){
            findNum[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int m = Integer.parseInt(bf.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken()) -1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            if(!map.containsKey(start)){
                map.put(start, new ArrayList<>());
            }

            if(!map.containsKey(end)){
                map.put(end, new ArrayList<>());
            }

            List<Integer> arr1 = map.get(start);
            arr1.add(end);

            List<Integer> arr2 = map.get(end);
            arr2.add(start);
        }
        System.out.println(bfs(findNum[0], findNum[1]));
    }
    public static int bfs(int start, int end){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        visited[start] = true;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int val = poll[0];
            int depth = poll[1];

            for(int num : map.get(val)){
                if(num == end) return depth + 1;
                if(!visited[num]){
                    visited[num] = true;
                    q.add(new int[]{num, depth+1});
                }
            }
        }
        return -1;
    }
}
