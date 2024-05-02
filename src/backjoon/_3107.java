package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 4시 4분
 * 종료 시간 : 4시 55분
 * <p>
 * 문제분석
 * - 128비트 IPV6
 * - 32자리의 16진수를 4자리씩 끊어서 나타냄.
 * - 32자리의 16진수는 사람이 읽고 쓰기 불편함.
 * <p>
 * - 각 그룹의 앞자리 0의 전체 또는 일부 생략 가능
 * - 2001:db8:85a3:0:00:8a2e:370:7334
 * - 0으로만 이루어진 그룹이 있다면, 한 개 이상의 그룹을 :: 로 대체 가능 (딱 한번만 사용가능)
 * - 2001:db8:85a3::8a2e:370:7334
 */
public class _3107 {
    public static void main(String[] args) throws IOException {
        // BufferedReader를 사용하여 입력을 받음
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        // "::"를 찾아서 ": :"으로 변경하여 처리할 수 있도록 함
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.substring(i, i + 2).equals("::")) {
                s = s.substring(0, i) + ": :" + s.substring(i + 2);
            }
        }

        // ':'를 구분자로 사용하여 StringTokenizer로 문자열을 분리
        StringTokenizer st = new StringTokenizer(s, ":");
        StringJoiner sj = new StringJoiner(":");
        int n = 8; // IPv6 주소는 총 8개의 그룹으로 이루어짐

        // StringTokenizer를 사용하여 분리된 문자열 처리
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (str.equals(" ")) { // "::"로 대체된 부분 처리
                sj.add("0000"); // 빈 부분은 "0000"으로 채움
                n--;

                // "::"로 인해 생략된 그룹의 수를 계산하여 해당 수만큼 "0000"을 추가
                int zeroCnt = n - st.countTokens(); // 전체 개수
                for (int i = 0; i < zeroCnt; i++) sj.add("0000");
                n -= zeroCnt;
            } else {
                // 각 그룹을 4자리로 맞추기 위해 앞에 "0"을 추가
                while (str.length() < 4) {
                    str = "0" + str;
                }
                sj.add(str);
                n--;
            }
        }
        // 최종적으로 조립된 IPv6 주소 출력
        System.out.println(sj);
    }
}
