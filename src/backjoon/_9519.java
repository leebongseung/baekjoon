package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 시작 시간 : 4시 20분
 * 종료 시간 : 5시 55분
 *
 * 문제분석
 * - 졸려?
 * - COCI에 사용할 데이터를 만드느라 3일동안 깨어있음.
 * - 선영이가 무엇인가를 읽다가 눈을 깜밖하면 단어의 뒷부분 절반이 앞부분과 섞임
 *
 * - 섞임?
 *  - 마지막 글자가  1~2번째의 사이로 이동
 *  - 뒤에서 두번째 글자가 2~3번째의 사이로 이동
 *  - 뒤에서 K번째 글자는 앞에서부터 k~k+1 사이로 이동
 *
 *  - ex) abcdef -> afbecd -> adfcbe
 *      - 이분탐색 같음 왼쪽한번 오른쪽한번 이런식으로 섞이네

 * - 복원하려면?
 *  ex) afbecd -> abcdef, 딱 두개의 string을 두어 한번씩 + 하면 됨 abc + def(뒤에껀 반대로 더해줘야함)
 * - 주의사항
 * - 길이가 홀수면 뒷부분의 길이가 짧다
 * - X번 깜밖인 후에 선영이가 보고 있는 단어가 주어짐 -> 원래단어 찾기
 * 0-> (idx(1) + x) ->
 * 시간 초과 발생
 * */
public class _9519 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        String str = br.readLine();

        // 반복되는 사이클이기 때문에 Set이용
        // 문자열 길이가 1000이기 때문에 이걸로 풀이
        List<String> lst = new ArrayList<>();
        String curStr = str;
        lst.add(curStr);
        while(true){
            curStr = stringRestoration(curStr);
            if(lst.contains(curStr)) break;
            lst.add(curStr);
        }
//        System.out.println("set.size() = " + lst.size());
//        for (String s : lst) {
//            System.out.println(s);
//        }
//        System.out.println("====");

        System.out.println(lst.get(x % lst.size()));
    }

    private static String stringRestoration(String str){
        StringBuffer forepart = new StringBuffer();
        StringBuffer backPart = new StringBuffer();
        for(int i=0; i<str.length(); i++){
            int idx = i % 2; // 0: 앞부분, 1: 뒷부분
            switch (idx){
                case 0:
                    forepart.append(str.charAt(i));
                    break;
                case 1:
                    backPart.insert(0, str.charAt(i));
                    break;
            }
        }
        return forepart.toString() + backPart.toString();
    }
}
