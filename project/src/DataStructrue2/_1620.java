package DataStructrue2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*  시작 시간 : 18시 3분
*   종료 시간 : 18시 50분
* */
public class _1620 {
    public static boolean isNumber(String str){
        try {
            Integer.parseInt(str);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        /*  입력 : 첫째줄 포켓몬수 N, 내가 맞춰야하는 문제 수 M,
        *       N과 M은 1보다 크거나 같고, 100,000 보다 작거나 같은 자연수
        *         둘째줄 N개의 줄에 포켓몬 번호가 1번 부터 N번에 해당하는 포켓몬이 순서대로 한줄씩 입력됨, 그이후 M개의 문제가 출제
        *         1. 포켓몬 이름 유형
        *            - 첫글자만 대문자, 나머지는 소문자
        *            - 첫글자 대문자, 마지막문자 대문자 가능
        *            - 알파벳 20, 최소 길이 2임.
        *         2. 맞춰야하는 문제 수
        *            - 알파벳으로 입력되면 포켓몬 번호를
        *            - 숫자로 입력되면 해당 포켓몬번호에 해당하는 문자를 출력
        *
        *   출력 : 첫째줄 부터 M개의 줄에 문제에 대한 답을 순서대로 출력하시오.
        * */

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, String> poketmonFindName = new HashMap<>();
        Map<String, Integer> poketmonFindNumber = new HashMap<>();
        for(int i =1; i <= n; i++){
            String name = bf.readLine();
            poketmonFindName.put(i , name);
            poketmonFindNumber.put(name, i);
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<m; i++){
            String SearchVal = bf.readLine();
            if(isNumber(SearchVal)){
                sb.append(poketmonFindName.get(Integer.parseInt(SearchVal))+"\n");
            } else{
                sb.append(poketmonFindNumber.get(SearchVal)+"\n");
            }
        }
        System.out.println(sb);
    }
}
