package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 13분
 * 종료 시간 : 10시 46분
 *
 * 문제분석
 * - 보석은 M가지의 서로다른 색상 중 하나
 * - 보석 M개를 N명의 학생에게 나눠줄려고함.
 *
 * 보석나누는 규칙
 * - 모든 보석을 다 나눠 줘야함,
 * - 보석을 받지 못하는 학생도 있음,
 * - 하지만 학생은 항상 같은 색상의 보석만 가져감
 *
 * 원하는 결과
 * - 보석을 가져가면, 다른아이들이 질투함 -> 가장 많은 보석을 가져간 학생이 질투심이 가장 많고, 질투심 == 보석의 개수임.
 * - 질투심이 최소가 되도록 보석을 나눠 줄려고함
 * -
 * */
public class _2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 보석 수

        int[] arr = new int[m]; // 각 보석의 종류 당 보석의 개수
        for(int i=0; i<m; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 오름차순 정렬
        Arrays.sort(arr);

        // 보석 나눠주기
        int left = 1; // 나눠주는 개수가 1이어야함 <- 이것때문에 7번 틀림!
        int right = arr[m-1];

        while(left <= right){
            int countToDistribute = (left + right) / 2; //분배할 개수

            int totalNumberOfPeople = 0;
            for (int jewelCount : arr) {
                int sum = jewelCount / countToDistribute;
                if(jewelCount % countToDistribute > 0) sum ++;

                totalNumberOfPeople += sum;
            }

            // 총 분배된 인원수 > 총 인원, 분배 개수를 늘려야 함.
            if(totalNumberOfPeople > n){
                left = countToDistribute + 1;
            }
            // 총 분배된 인원수 <= 총 인원, 분배 개수를 줄어야 함.
            else{
                right = countToDistribute - 1;
            }
        }

        System.out.println(left);


    }
}
