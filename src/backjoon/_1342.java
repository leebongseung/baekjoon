package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 시작 시간 : 11시 52분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 행운의 문자열 : 인접한 모든 문자가 같지 않은 문자열
 * - 준영이가 문자열 s를 분석함.
 * - 준영이는 문자열 s에 나오는 문자를 재배치 하면 서로 다른 행운의 문자열이 몇개 나오는지 궁금해짐.
 * - 만약 원래 문자열도 행운이라면 그것도 개수에 포함됨
 *
 * 문제 풀이
 * - 길이가 최대 10이기 때문에 백트래킹을 이용하면 될 거 같음.
 * */
public class _1342 {
    public static String str;
    public static boolean[] visited;
    public static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        visited = new boolean[str.length()];

        dfs("");
        System.out.print(res);
    }
    private static void dfs(String curStr){
        if(curStr.length() == str.length()){
            if(luckyStringCheck(curStr)) {
                res ++;
            }
            return;
        }

        for(int i=0; i<str.length(); i++){
            // 만약에 방문안했다면 문자열 추가해보기
            if(!visited[i]){
                visited[i] = true;
                dfs(curStr + str.charAt(i));
                visited[i] = false;
            }
        }
    }

    private static boolean luckyStringCheck(String str){
        for(int i=0; i< str.length()-1; i++){
            // 자신과 자신의 뒤의 문자가 같을 경우 false 반환
            if(str.charAt(i) == str.charAt(i+1)) return false;
        }

        return true;
    }
}
