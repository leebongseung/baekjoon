package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 4분
 * 종료 시간 : 10시 30분
 *
 * 문제 분석
 * - 진아는 식목일을 맞아 등교 때 마다 씨앗을 심음.
 * - n*n 의 격자판중 씨앗을 한곳에 심으면 년뒤 십자가 모양처럼 만개함.
 * - 모두 꽃을 피워서 가장 싼 가격에 화단을 대여하고 싶다.
 * - 돈이 많지 않은 진아가 꽃을 심기 위한 최소비용을 구해보자
 *
 * - 화단의 한변의 길이 n
 * - n개의 지점당가격
 *
 * 제한시간은 2초인데 백트래킹으로 풀 수 있을 것같고, 음흠.. 백트래킹인듯
 * */
public class _14620 {
    public static int n;
    public static int[][] arr;
    public static List<Flower> lst;
    public static int minVal = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n][n];
        lst = new ArrayList<>();

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 꽃을 3개 심어 보자!
        bfs(0);
        System.out.println(minVal);
    }
    public static class Flower{
        public int x;
        public int y;

        public Flower(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean isFlowerArea(int x, int y){
            if(x == this.x && y == this.y) return true;
            if(x == this.x+1 && y == this.y) return true;
            if(x == this.x-1 && y == this.y) return true;
            if(x == this.x && y == this.y +1) return true;
            if(x == this.x && y == this.y -1) return true;

            return false;
        }

        public int getGroundFee(){
            int cnt = 0;
            cnt += arr[x][y];
            cnt += arr[x+1][y];
            cnt += arr[x-1][y];
            cnt += arr[x][y+1];
            cnt += arr[x][y-1];

            return cnt;
        }
    }

    private static void bfs(int depth) {
        if(depth == 3){
            int cnt = 0;
            for(Flower flower : lst){
                // 십자가 모양 땅의 가격 계산해서 더하기!
                cnt += flower.getGroundFee();
//                System.out.print("flower.x = " + flower.x);
//                System.out.print(", flower.y = " + flower.y);
//                System.out.println(", fee = " + flower.getGroundFee());
            }
//            System.out.println("cnt = " + cnt);
            minVal = Math.min(minVal, cnt);
            return;
        }

        for(int i=1; i<n-1; i++){
            for(int j=1; j<n-1; j++){
                // 꽃을 심을 수 있는 위치인지 확인하기
                if(!isOk(i, j)) continue;
                int size = lst.size();
                lst.add(new Flower(i, j));
                bfs(depth + 1);
                lst.remove(size);
            }
        }
    }

    private static boolean isOk(int x, int y) {
        for(Flower flower : lst){
            if(flower.isFlowerArea(x, y)) return false;
            if(flower.isFlowerArea(x+1, y)) return false;
            if(flower.isFlowerArea(x-1, y)) return false;
            if(flower.isFlowerArea(x, y+1)) return false;
            if(flower.isFlowerArea(x, y-1)) return false;
        }

        return true;
    }
}
