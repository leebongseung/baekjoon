package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringJoiner;

/**
 * 시작 시간 : 7시
 * 종료 시간 : ??
 *
 *
 * 문제분석
 * - 숫자순서대로 말하기, 3,6,9는 말 안함.
 * - 박수는 해당 숫자가 들어간 만큼 치기 개수 세서
 * */
public class _1926 {
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringJoiner sj = new StringJoiner(" ");
        for(int i=1; i<=n; i++){
            int cnt = clapCount(String.valueOf(i));
            if(cnt == 0) sj.add(String.valueOf(i));
            else{
                String str = "";
                for(int j=0; j<cnt; j++) str += "-";
                sj.add(str);
            }
        }
        System.out.println(sj);
    }

    private static int clapCount(String str) {
        int cnt = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '3' || str.charAt(i) == '6' || str.charAt(i) == '9') cnt ++;
        }
        return cnt;
    }
}
