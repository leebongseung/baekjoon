package backjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작시간 : 8시
 * 종료 시간 : ??
 *
 * 문제분석
 * - 수빈이가 제일 좋아하는건 소수
 * - 7331이 제일 좋아~
 * - 733도 소수고
 * - 73도 소수
 * - 7 도 소수임.
 * - 모두 소수 == 신기한 소수
 * - n자리의 숫자 중 신기한 소수를 모두 찾아보자~~
 * 시간 제한 2초임 -> 2억번 연산이면 가능하다
 * 1의 자리의 소수 -> 2,3,5,7
 * 10의 자리 소수 -> 11, 13, 15, 17, 19
 *
 * 걍 백트래킹으로 모두 탐색하고 그러는게 좋아보임.
 * */
public class _2023 {
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        dfs(0,0);
    }

    // 백트래킹으로 문자열 길이가 n이 될때까지 모든 경우의수를 구함.
    private static void dfs(int num, int depth) {
        if(depth == n){
            System.out.println(num);
            return;
        }

        for(int i= 1; i<= 9; i++){
            if(depth == 0 && (i == 1 || i == 4 || i == 6 || i == 8 || i == 9)) continue;
            int newNum = num * 10 + i;
            // 소수일 경우 증가하는 로직으로 품.
            if(isPrime(newNum)) dfs(newNum, depth+1);
        }
    }

    // 에라토스테네스의 체로 구하는 알고리즘
    //  O(nlog * logn) 시간복잡도로 풀수있음.
    private static boolean isPrime(int num){

        // 0과 1은 소수가 아님.
        if(num <= 1) return false;

        for(int i=2; i<= Math.sqrt(num); i++){
            // 이미 체크된 배열이면 다음 반복문으로 넘어가기
            if(num % i == 0) return false;
        }
        return true;
    }
}
