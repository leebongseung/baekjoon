package backjoon.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*  시작 시간 : 9시 3분
*   종료 시간 : 9시 36분
* */
public class _1522 {
    public final static int SWAPVALUE = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        String[] split = str.split("");
        int aCnt = 0;
        // 문자열의 a 의 개수를 센다.
        for(int i =0; i<str.length(); i++){
            if(split[i].equals("a")) aCnt++;
        }


        int min = Integer.MAX_VALUE; // 최소 b 의 개수를 저장할 변수

        //문자열 길이만큼 반복한다,
        for(int i =0; i< str.length(); i++){
            int bCnt = 0;

            // 현재 인덱스 i 부터 a개의 개수만큼 세면서 b개의 최소 개수를 구한다.
            for(int j =i; j< i+aCnt; j++){
                int idx = j;
                if(j >= split.length) idx %= split.length;
                if(split[idx].equals("b")) bCnt++;
            }
            min = Math.min(min, bCnt);
        }

        // 최소 개수 반환
        System.out.println(min);
    }
}
