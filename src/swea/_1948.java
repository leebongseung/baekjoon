package swea;

import java.io.*;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 11시
 * 종료 시간 : 11시 15분
 *
 * 문제분석
 * - 월은 1~ 12이하 의 정수
 * - 막달은 다음과 같대 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
 *
 * - 두번째 날짜가 첫번째 날짜보다 항상 크대
 *
 * - 날짜 2개를 입력받아, 두번째가 첫번째 날짜의 며칠 째인지 출력하시오
 *
 * - 단순구현
 * - 주의사항 : 항상 시작 월이 종료월보다 작다.이말은 startMonth <= endMonth
 * */
public class _1948 {
    public static int[] days = {0,
            31, 28, 31, 30, 31,
            30, 31, 31, 30, 31,
            30, 31};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tCase = Integer.parseInt(bf.readLine());
        StringJoiner sj = new StringJoiner("\n");
        for(int t=1; t <= tCase; t++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            int cnt = 0;
            for(int i= startMonth; i <= endMonth; i++){

                // 시작 월 == 종료 월 일 경우
                if(i == startMonth && i == endMonth){
                    cnt += (endDay - startDay + 1);
                }

                // 시작 월일 경우
                else if(i == startMonth){
                    cnt += (days[i] - startDay + 1);
                }

                // 종료 월일 경우
                else if( i== endMonth){
                    cnt += endDay;
                }

                // 그외 의 경우
                else {
                    cnt += days[i];
                }
            }
            sj.add("#" + t + " " + cnt);
        }
        bw.write(sj.toString());
        bw.flush();
        bw.close();

    }
}
