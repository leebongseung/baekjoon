package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*  시작 시간 : 11시 16분
*
* */
public class _17615 {
    public static void main(String[] args) throws IOException {
        // 1. 다른색깔을 볼이 있으면 모두 뛰어넘을 수 있음.
        // 2. 옮길 수 있는 볼 색은 한개임. 빨간 볼 옮긴다면 다음도 빨간볼만 옮기고,
        //   파란볼 옮긴다면 다음 또 파란볼을 옮기는 거임.

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        String colors = bf.readLine();

        int rigthBCnt = 0; // 옮겨야하는 R 볼 개수
        int rightRCnt = 0; // 옮겨야하는 B 볼 개수

        Boolean change = false; // 기준 문자와 다른 문자가 들어왔는지?

        String PreColor = ""; // 기준 문자 담는 변수

        // 오른쪽 부터 탐색하기
        for (int i = colors.length()-1; i >= 0 ; i--) {
            if(PreColor.isEmpty()){
                PreColor = String.valueOf(colors.charAt(i)); // 첫입력되는 값은 기준 값임.
                continue;
            }
            if(!PreColor.equals(String.valueOf(colors.charAt(i)))){ // 기준값과 입력값이 같지않다면? 옮겨야하는 볼 수 계산하기
                change = true;
            }
            if(change){
                if(String.valueOf(colors.charAt(i)).equals("R")){
                    rightRCnt++;
                } else {
                    rigthBCnt++;
                }
            }
        }


        change = false;

        PreColor = "";
        int leftBCnt = 0; // 옮겨야하는 볼 수 B
        int leftRCnt = 0; // 옮겨야하는 볼 수 R

        // 왼쪽인덱스 부터 탐색하기
        for (int i = 0; i< colors.length(); i++) {
            if(PreColor.isEmpty()){
                PreColor = String.valueOf(colors.charAt(i));
                continue;
            }
            if(!PreColor.equals(String.valueOf(colors.charAt(i)))){
                change = true;
            }
            if(change){
                if(String.valueOf(colors.charAt(i)).equals("R")){
                    leftRCnt++;
                } else {
                    leftBCnt++;
                }
            }
        }


        //최종적으로 제일 작은값 출력하기
        System.out.println(Math.min(Math.min(rightRCnt, rigthBCnt), Math.min(leftRCnt,leftBCnt)));

    }
}
