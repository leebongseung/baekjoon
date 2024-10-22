package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 12분
 * 종료 시간 : 10시 40분
 * <p>
 * 문제분석
 * - 두플레이어 번갈아가면서 진행하는 게임
 * - 선 플레이어 : 홀수번째 차례
 * - 후 플레이어 : 짝수번째 차례
 * - 0~ n-1 의 고유한 번호가 부여된 평번 점 n개
 * - 이 중 세 점도 일직선 위에 놓여 있지 않음.
 * - 두 점을 연결하는 선분 그리기
 * 선분 그리기
 * - 이전에 그린 선분 다시 그릴 수 없음
 * - 이미 그린 선분과 교차하는 것 가능
 * 게임 종료
 * - 사이클을 완성하는 순간 게임 종료
 * 사이클
 * - c는 플레이어가 그린 선분들을 부분집합.
 * - 시작점 부터 모든 선분을 한번씩만 지나서 출발점으로
 * 되돌아 오는 경우 사이클이라고 함.
 * <p>
 * 사이클 판볇 방법
 * - 게임 진행상황이 주어지면 몇 번째 차례에 사이클이
 * 완성되었는지, 호은 아직 게임이 진행중인지 판별하는 프로그램
 * <p>
 * 입력
 * 크기가 제법큼 O(n * m ) 이면 5000억
 */
class _20040 {
    public static int n; // 점의 개수, 50만
    public static int m; // 진행된 차례의 수, 100만
    public static int[] union; // 플레이어

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        union = new int[n];
        // 내가 보기엔 이건 union-find 인듯.
        for (int i = 0; i < n; i++) {
            union[i] = i;
        }

        int res = 0;
        // i번째 차례에 해당 플레이어가 선택한 두점의 번호
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            if (find(node1) != find(node2)) {
                // 선분 잇기
                union(node1, node2);
            } else {
                res = i;
                break;
            }

/*            System.out.println("====" + i + "=====");
            for (int i1 : union) {
                System.out.print(i1 + " ");
            }
            System.out.println();*/


        }
        System.out.println(res);
    }

    public static int find(int x) {
        if (union[x] == x) {
            return x;
        } else {
            int topNode = union[x];
            union[x] = find(topNode);
            return union[x];
        }
    }

    public static void union(int x, int y) {
        int topX = find(x);
        int topY = find(y);

        if (topX != topY) {
            union[topY] = x;
            union[y] = x;
        }
    }
}
