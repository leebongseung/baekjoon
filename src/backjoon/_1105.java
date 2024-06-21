package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 4분
 * 종료 시간 : 10시 44분
 * <p>
 * 문제분석
 * - L과 R이 주어짐
 * - L보다 크거나 같고, R보다 작거나 같은 자연수 중
 * - 8이 가장 적게 들어있는 수에
 * - 들어있는 8의 개수를 구하시오
 */
public class _1105 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String left = st.nextToken();
        String right = st.nextToken();

        int leftNum = Integer.parseInt(String.valueOf(left));
        int rightNum = Integer.parseInt(String.valueOf(right));

        int res = 0;

        // 1의 자리 부터 ~ 1억 자리 까지 비교 하면서 8을 사이에 존재하는지 확인해보자
        for (int i = right.length(); i > 0; i--) {
            int leftRemainderOperations = (int) (leftNum % Math.pow(10, i) / Math.pow(10, i - 1));
            int rightRemainderOperations = (int) (rightNum % Math.pow(10, i) / Math.pow(10, i - 1));

//            System.out.print("i = " + i + ", ");
//            System.out.println(leftRemainderOperations + ", " + rightRemainderOperations);
            if (leftRemainderOperations == 8 && leftRemainderOperations == rightRemainderOperations) {
                res++;
            } else if(leftRemainderOperations != rightRemainderOperations) break;
        }

        System.out.println(res);

    }
}
