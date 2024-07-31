package backjoon;

import java.io.*;

/**
 * 시작 시간 : 10시 4분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 연속한 소수 p와 p+n 이 있따.
 * - 그 사이에 있는 n-1개의 합성수(소수나 1이 아닌 양수)는 길이가 n인 소수 사이 수열 이라고 한다.
 * - 양의 정수 k가 있을 때, k를 포함하는 소수 사이 수열의 길이를 구하자
 * - 23과 29 의 소수 사이 수열은 24, 25,26,27,28이고 길이는 6임
 *
 * */
public class _3896 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int tCase = 0; tCase < t; tCase++){
            int k = Integer.parseInt(br.readLine());

            // k가 합성수가 아니라면 0을 출력하기
            if(isDecimal(k)){
                bw.write(String.valueOf(0) +"\n");
                continue;
            }

            // 어떤수 k가 주어지면
            int res = 1;
            // k를 포함해서 앞으로 몇번가야 소수가 있는지 검사하고
            int left = k;
            while(true){
                left--;
                res++;
                if(isDecimal(left)) break;
            }

            // 뒤로 몇번가야 소수가 있는지 검사하면 끝?
            int right = k;
            while(true){
                right++;
                res++;
                if(isDecimal(right)) break;
            }

            bw.write(String.valueOf(right - left) + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 소수 인지 검사하는 메서드
    // true : 소수임, false : 소수 아님
    private static boolean isDecimal(int num){
        for(int i=2; i<= num/2; i++){
            if(num % i == 0) return false;
        }

        return true;
    }
}
