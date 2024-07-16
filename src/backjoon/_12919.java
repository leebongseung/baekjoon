package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시작 시간 : 10시 10분
 * 종료 시간 : ?
 *
 * 문제분석
 * - AB, BAA, AA, ABBA로 이루어진 단어가 있대
 * - 문자열 S, T 가 주어짐, S를 T로 바꾸는 게임
 *
 * 1. 문자열 뒤에 A를 추가하기
 * 2. 문자열 뒤에 B를 추가하고 뒤짚기
 * 결과는 S를 T로 만들 수 있는지 없는지 알아내기
 * */
public class _12919 {
    private static boolean res = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strS = br.readLine();
        String strT = br.readLine();

        dfs(strS, strT);
        System.out.print(res? 1 : 0);
    }

    // T -> S 로 줄여보기
    private static void dfs(String strS, String strT) {
        if(strS.length() == strT.length()){
            if(strS.equals(strT)) res = true;
            return;
        }

        // 1번 과정 수행, 문자열 뒤에 A 빼기
        if(strT.charAt(strT.length()-1) == 'A'){
            dfs(strS, strT.substring(0, strT.length()-1));
        }

        // 2번 과정 수행,문자열 젤 앞에 B 빼고, 뒤짚기
        if(strT.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(strT.substring(1));
            dfs(strS, sb.reverse().toString());
        }
    }

}
