package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 집합 n+1개가 존재한다.
 * - 여기에 합집합 연산과, 두원소가 같은 집합인지 확인하는 연산을 수행하려고 함
 * <p>
 * - 유니온 파인드로 해결가능할듯.
 * - a와 b가 같은 대표노드를 바라보고 있는지 yes, 없다면 no를 출력하면 됨
 */
public class _1717 {
    public static int n; // 0 부터 n개의 집합이 존재
    public static int m; // 연산의 개수
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1]; // n+1개의 집합 생성.

        // 유니온 파인드 배열 대표노드 초기화 수행
        for (int i = 0; i <= n; i++) {
            arr[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int operationType = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operationType == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * 두 노드의 대표노드 연결하는 과정
     */
    public static void union(int a, int b) {
        int nodeA = find(a);
        int nodeB = find(b);
        arr[nodeB] = nodeA;
    }

    /**
     * 대표노드를 찾는 과정
     */
    public static int find(int a) {
        if (a == arr[a]) { // 만약에 내 자신이 대표노드라면? 나자신을 반환하기
            return a;
        } else {
            // 만약에 내 자신이 대표노드가 아닐경우 대표노드를 찾고 연결하는 과정 거치기!
            int num = arr[a];
            int topNode = find(num); // 대표 노드 찾기!
            arr[a] = topNode; // 연결하기!
            return topNode;// 반환하기
        }
    }
}
