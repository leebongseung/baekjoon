package swea;

/**
 * 시작 시간 : 9시 30분 (못품 -> 정답봄)
 * 종료 시간 : ?
 *
 * 문제분석
 * - 햄버거를 좋아함, 정해진 칼로리를 넘지않는 햄버거를 주문할 계획
 * - 고객의 조합으로 햄버거를 만들어 줌.
 * - 햄버거 재료에 대한 맛을 점수를 매겨둠.
 * - 햄버거 재료에 대한 점수, 가게에서 제공하는 칼로리
 * 가 주어질 때
 * - 만기가 좋아하는 햄버거 먹으면서 다이어트할 수 있게
 * 정해진 칼로리 이하의 조합 중 선호하는 햄버거 조합해라
 * - 같은 재료는 중복 사용이 불가능.
 *
 *
 * dp 공식
 * - 기존 칼로리 + 현재 칼로리, 이전값 중에 큰 것 짚어넣기!
 * - 2차원 배열, i번째 재료가 포함된, 칼로리 j
 * */

import java.util.*;
import java.io.*;
public class _5215 {
    public static int n;
    public static int[][] dp;
    public static int calorieRestriction;
    public static List<Food> lst;

    public static class Food{
        public int taste;
        public int calorie;

        public Food(String taste, String calorie) {
            this.taste = Integer.parseInt(taste);
            this.calorie = Integer.parseInt(calorie);
        }

        public Food(int taste, int calorie) {
            this.taste = taste;
            this.calorie = calorie;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tCase = Integer.parseInt(bf.readLine());
        for(int t=1; t<= tCase; t++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            calorieRestriction = Integer.parseInt(st.nextToken());
            dp = new int[n+1][calorieRestriction + 1];

            // 맛 칼로리 입력 받기
            lst = new ArrayList<>();
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(bf.readLine());
                lst.add(new Food(st.nextToken(), st.nextToken()));
            }

            for(int i=0; i<n; i++){
                Food food = lst.get(i);
                int dpIdx = i+1;

                for(int j=0; j<= calorieRestriction; j++){
                    if(j >= food.calorie) dp[dpIdx][j] = Math.max(dp[dpIdx-1][j], dp[dpIdx-1][j-food.calorie] + food.taste);
                    else dp[dpIdx][j] = dp[dpIdx-1][j];
                }
            }

            bw.write("#" + t + " " + dp[n-1][calorieRestriction]);
        }

        bw.flush();
        bw.close();
    }
}
