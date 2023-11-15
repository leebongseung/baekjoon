package backjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*  시작 시간 : 10시 3분.
*   종료 시간 : 12시
* */
public class _2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());


        List<Integer> arr = new ArrayList<>(); // 각 요청된 예싼.

        int max = 0; // 요청된 예산 총합
        while(st.hasMoreTokens()){  // 각 지방을 나타낸다.
            int num = Integer.parseInt(st.nextToken());
            arr.add(num);
            max += num;
        }
        Collections.sort(arr);

        int m = Integer.parseInt(bf.readLine()); // 총 예산을 나타냄

        if(m >= max){ // 예산의 총합 >= 요청된 예산 총합
            Integer max22 = arr.get(n - 1);
            System.out.println(max22);
        } else {
            int res = 0;
            int left = 0;
            int right = arr.get(n - 1);

            // 0 ~ 요청된 예산최대크기 사이를 이진탐색함.
            while(left <=right){
                int mid = (left+right) /2; // 0~요청된 예산크기 사이의 중간값.

                int total = 0;
                for (int number : arr) {
                    total += Math.min(number, mid); // 중간값이랑 요청된 예산중 작은 값을 저장한다.
                }

                if(total > m){  // 총합 > 예산 인 경우 총합을 줄여야함.
                    right = mid-1;
                    res = right;
                } else{ // 총합 < 예산 인 경우 넉넉하다는 의미로 총합을 늘려야함.
                    left = mid+1;
                }
            }
            System.out.println(res);
        }
    }
}
