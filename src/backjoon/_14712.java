package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간 : 1시
 * 종료 시간 : ??
 * <p>
 * 문제분석
 * - 비어있는 칸을 임의로 골라 "넴모"를 하나 올려 놓거나
 * - "넴모"가 올라간 칸 4개가 2*2 사각형 이루는 부분을 찾아서 그 위에 "넴모"들을 모두 없애는 것을 질릴 때 까지 반복하기
 * <p>
 * - 네모가 격자판 위에 없앨 수 있는 "넴모"가 없으면 게임을 그만 두기로 함
 * - 네모가 게임을 그만두었을 때 나올 수 있는 “넴모”의 배치의 가짓수를 구하여라.
 * <p>
 * 모든 경우의 수를 구하고 2*2 사각형을 이루는 부분을 없애고 Set에 짚어넣으면 되는 거같음.
 * 2*2도 없애는 경우가 다양하니 모든 경우를 다 해봐야함!
 */
public class _14712 {
    public static int n, m;
    public static char[] ch;
    public static int res = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ch = new char[n * m];
        Arrays.fill(ch, '0');


        dfs(0);
//        for (String set : sets) {
//            System.out.print(set);
//            System.out.println("===========");
//        }
        System.out.println(res);

    }

    public static void dfs(int depth) {

        // 2*2 격자판이 존재하는 모든 경우를 없애기
        if (depth == n * m) {
            res++;
            return;
        }

        int i = depth / m;
        int j = depth % m;

        if(i-1 >= 0 && j-1 >= 0 && ch[i * m + j - 1] == '1' && ch[(i-1) * m + j] == '1' && ch[(i-1) *m + (j-1)] == '1'){
            // "넴모"를 놓지 않는 경우
            dfs(depth + 1);
        } else{
            // "넴모"를 놓지 않는 경우
            dfs(depth + 1);
            // "넴모"를 놓는 경우
            ch[m * i + j] = '1';
            dfs(depth + 1);
            ch[m * i + j] = '0';

        }
    }


}
