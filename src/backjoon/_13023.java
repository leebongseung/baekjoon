package backjoon;

import java.util.*;
import java.io.*;
/**
 * 시작 시간 : 5시 38분
 * 종료 시간 :
 *
 * 1. 문제분석
 *  - 제한 시간 2초
 *  - 메모리 제한 512 MB
 *  - 알고리즘에 N명 참가(2000명 이하)
 *  - 0~ N-1 번으로 번호가 매겨짐
 *  - 고정으로 5명의 사람이 A-> B- >C ->D ->E 인 경우 결과가 1이고, 아니면 0임. 이게 핵심!
 *  - 결과로 친구관계가 존재하는지 안하는지 구하는 프로그램
 *
 * 2. 입력분석
 * - N은 5이상 2000개이하, 친구관계 수 표현하는 건 M(2000이하)
 * - 둘째줄부터 a,b가 주어지는데 a와 b 가 친구라는 뜻, 친구관계는 2번이상 주어지지 않음
 *
 * 3. 결과분석
 * - 조건에맞는 A~E가 존재한다면 1, 없다면 0을 출력
 * - 내가 이해한건 모든 사람이 친구가 있어야함.
 * */
public class _13023 {

    public static List<List<Integer>> lst = new ArrayList<>();
    public static int res = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 인덱스가 0부터 시작함.
        for(int i =0; i<n; i++){
            lst.add(new ArrayList<>());
        }

        // 짚어넣어야한당!
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lst.get(first).add(end);
            lst.get(end).add(first);
;        }

        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[n];
//            System.out.println(i);
            bfs(i, visited, 1);
            if(res == 1) break;
//            System.out.println("----------------------");
        }
        System.out.println(res);
    }
    // bfs 로 5번정도 depth가 생기면 이건 ok임.
    public static void bfs(int num,boolean[] visited, int depth){
//        System.out.println("num = " + num + ", depth = " + depth);
        visited[num] = true;
        if(depth == 5) {
            res = 1;
            return;
        }
        Queue<Integer> q = new LinkedList<>();

        for(int i : lst.get(num)){
            if(!visited[i]) q.add(i);
        }

        while(!q.isEmpty()){
            int n = q.remove();
            if(!visited[n]) bfs(n, visited.clone(), depth+1);
        }
    }
}
