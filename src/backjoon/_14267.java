package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 4시
 * 종료 시간 : 4시 28분
 *
 * 문제분석
 * - 한 상사가 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬받음
 * - 모든 칭찬에는 수치가 있는데 모두 동일하게 받는다.
 * - 부하관계 와 칭찬 정보가 주어질때 각자 얼마의 칭찬을 받았는지 쓰시오
 *
 * 그래프 이론 인것같음
 * - bfs 또는 벨만 포드로 풀어야할 것 같다.
 * - 음수간선이 존재함.
 * */
public class _14267 {
    public static int n; // 직원 수
    public static int m; // 칭찬 횟수
    public static List<Integer>[] lst; // 직원의 부하 리스트
    public static int[] arr; // 칭찬점수 계산 
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lst = new List[n];
        for (int i = 0; i < n; i++) lst[i] = new ArrayList<>();
        arr = new int[n];


        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(st.nextToken()) - 1;
            if(num < 0) continue;

            lst[num].add(i); // 상사의 부하로 나자신의 짚어넣기
        }

        // 칭찬하기
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1; // 직원이름
            int weight = Integer.parseInt(st.nextToken()); // 칭찬 크기
            arr[num] += weight; //칭찬받은 직원의 점수 증가.
        }

        
        // 젤 낮은 index 번호부터 자식들 칭찬하기! 이게왜 되지..? 
        for(int i=0; i<n; i++){
            childGoodJob(i, arr[i]);
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    // 자식들 칭찬 dfs 아닙니다
    public static void childGoodJob(int num, int weight){
        for (Integer child : lst[num]) {
            arr[child] += weight;
        }
    }
}
