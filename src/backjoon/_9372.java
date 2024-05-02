package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 11분
 * 종료 시간 : 10시 30분
 * 문제분석
 * - 겨울방향 맞아 n개국 여행
 * - 최대한 적은 비행기 종류로 국가 이동
 * - 비행 스케줄 있을 때 가장 적은 종류의 비행기를 타고  모든 국가 여행 가자
 * - 한국가에서 다른국가로 이동할 때, 거쳐가도 방문한 국가가 됨.
 *
 *
 * */
public class _9372 {
    public static int n;
    public static int m;
    public static int[] unionArr;
    public static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(bf.readLine());
        for(int tCase = 0; tCase < t; tCase++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken()); // 국가의 수
            m = Integer.parseInt(st.nextToken()); // 비행기의 종류
            unionArr = new int[n+1];
            res = 0;

            for(int i=1; i<n+1; i++){
                unionArr[i] = i;
            }

            for(int i=0; i<m; i++){
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            System.out.println(res);
        }
    }
    public static void union(int a, int b){
        int aParent = find(a);
        int bParent = find(b);
        if(aParent != bParent){
            unionArr[bParent] = aParent;
            res++;
        }
    }
    public static int find(int a){
        if(a == unionArr[a]) return a;
        else{
            int parent = unionArr[a];
            int nextParent = find(parent);
            unionArr[a] = nextParent;
            return nextParent;
        }
    }
}
