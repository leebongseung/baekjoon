package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 11시 48분
 * 종료 시간 : ??
 * */
public class _12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int s_length = Integer.parseInt(st.nextToken());
        int p_length = Integer.parseInt(st.nextToken());

        String str = bf.readLine();
        st = new StringTokenizer(bf.readLine());
        int A_cnt = Integer.parseInt(st.nextToken());
        int C_cnt = Integer.parseInt(st.nextToken());
        int G_cnt = Integer.parseInt(st.nextToken());
        int T_cnt = Integer.parseInt(st.nextToken());

        int res = 0;
        int sub_A_cnt = 0;
        int sub_C_cnt = 0;
        int sub_G_cnt = 0;
        int sub_T_cnt = 0;


        for(int i=0; i<s_length; i++){
            if(i + p_length > s_length) break;

            if(i==0) {
                String substring = str.substring(i, i + p_length);
                sub_A_cnt = getSameChar(substring,'A');
                sub_C_cnt = getSameChar(substring,'C');
                sub_G_cnt = getSameChar(substring,'G');
                sub_T_cnt = getSameChar(substring,'T');
            } else{
                // 이전 문자 개수 카운팅 빼기
                char c = str.charAt(i - 1);
                switch (c){
                    case 'A':
                        sub_A_cnt--;
                        break;
                    case 'C':
                        sub_C_cnt--;
                        break;
                    case 'G':
                        sub_G_cnt--;
                        break;
                    case 'T':
                        sub_T_cnt--;
                        break;
                }

                // 추가된 문자 개수 카운팅 추가하기
                char endChar = str.charAt(i + p_length - 1);
                switch (endChar){
                    case 'A':
                        sub_A_cnt++;
                        break;
                    case 'C':
                        sub_C_cnt++;
                        break;
                    case 'G':
                        sub_G_cnt++;
                        break;
                    case 'T':
                        sub_T_cnt++;
                        break;
                }
            }

            if(A_cnt <= sub_A_cnt
                    && C_cnt <= sub_C_cnt
                    && G_cnt <= sub_G_cnt
                    && T_cnt <= sub_T_cnt) res ++;

        }

        System.out.print(res);
    }

    public static int getSameChar(String str, char ch){
        int cnt = 0;
        for(int i =0; i< str.length(); i++){
            if(str.charAt(i) == ch) cnt++;
        }
        return cnt;
    }
}
