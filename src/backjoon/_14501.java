package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

/*
* 시작 시간 : 10시 6분
* 종료 시간 :
* */
public class _14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] T_i = new int[n+2]; // 상담완료 기간
        int[] P_i = new int[n+2]; // 받을 수 있는 금액

        for(int i =1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            T_i[i] = Integer.parseInt(st.nextToken());
            P_i[i] = Integer.parseInt(st.nextToken());
        }

        // 상담을 적절히 수행했을때 얻을 수 있는 최대 수익을 구하시오
        int[] sum = new int[n+2];
        int max = 0;
        for(int i =1; i<=n; i++){
            int days = T_i[i] + i; // 소요된 날짜
            int moneys = sum[i] + P_i[i];

            if(days <= n+1 && sum[days] < moneys){
                if(moneys > max) max = moneys;
                sum[days] = moneys;
            }

            sum[i+1] = Math.max(sum[i], sum[i+1]);
        }
        System.out.println(max);


    }
}
