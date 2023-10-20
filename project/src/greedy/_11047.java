package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*  시작 시간 : 12시 02분
*   종료 시간 : 12시 11분
* */
public class _11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전의 종류
        int k = Integer.parseInt(st.nextToken()); // 현재 금액

        List<Integer> moneys = new ArrayList<>();
        for(int i= 0; i<n; i++){
            String money = bf.readLine();
            moneys.add(Integer.valueOf(money));
        }

        moneys.sort(((o1, o2) -> o2 - o1));

        int res = 0; // 동전의 개수
        for (Integer money : moneys) {
            if(k <= 0) break;

            if(k / money != 0){
                res += k/money;
                k %= money;
            }
        }
        System.out.println(res);

    }
}
