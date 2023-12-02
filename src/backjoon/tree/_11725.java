package backjoon.tree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*  시작 시간 : 10시 55분
*   종료 시간 : 11시 50분
* */
public class _11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner("\n");
        int n = Integer.parseInt(bf.readLine());
        List<List<Integer>> arr = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);
        boolean[] isInside = new boolean[n+1];
        Arrays.fill(isInside, false);

        for(int i =0; i< n+1; i++){ //0~ n개 생성하기 0번인덱스는 사용안함.
            arr.add(new ArrayList<>());
        }

        for(int i =0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            arr.get(num1).add(0,num2);
            arr.get(num2).add(0,num1);
        }


        int[] parent = new int[n+1];
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        while(!stack.isEmpty()){
            int num = stack.pop();

            for (Integer i : arr.get(num)) {
                if(!visited[i]){
                    visited[i] = true;
                    stack.add(i);
                    parent[i] = num;
                }
            }
        }
        for(int i =2; i< n+1; i++){
            sj.add(Integer.toString(parent[i]));
        }
        System.out.print(sj);
    }
}
