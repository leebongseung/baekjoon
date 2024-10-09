package backjoon;

import java.util.*;
import java.io.*;

public class _1365 {
    // 출력용 BufferedWriter 선언
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws IOException {
        // 입력 처리용 BufferedReader 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전깃줄의 개수 n을 입력받음
        int n = Integer.parseInt(br.readLine());

        // 전봇대 B의 연결 상태를 저장할 문자열 배열을 입력받음
        String str[] = br.readLine().split(" ");

        // LIS 계산을 위한 배열 선언
        int arr[] = new int[n];

        // 첫 번째 값을 초기화 (LIS의 첫 번째 값으로 설정)
        arr[0] = Integer.parseInt(str[0]);
        int size = 1; // 현재 LIS의 길이를 나타냄

        // 두 번째 전깃줄부터 확인하면서 LIS를 계산
        for (int i = 1; i < n; i++) {
            int h = Integer.parseInt(str[i]); // 현재 전봇대 B의 값

            // arr 배열의 마지막 값보다 h가 크면 LIS에 추가
            if (arr[size - 1] < h)
                arr[size++] = h; // LIS 배열에 값을 추가하고 길이를 1 증가
            else {
                // 이분 탐색으로 LIS 배열 내에서 h가 들어갈 위치를 찾음
                int left = 0;
                int right = size;
                int mid = 0;

                // 이분 탐색으로 h의 위치를 찾음
                while (left < right) {
                    mid = (left + right) / 2;
                    if (arr[mid] < h) left = mid + 1; // h가 더 크면 mid보다 오른쪽에 위치
                    else right = mid; // h가 더 작거나 같으면 mid 위치로 이동
                }
                arr[right] = h; // 찾은 위치에 h를 넣음
            }
        }

        // 전체 전깃줄의 개수에서 LIS의 길이를 빼서 제거해야 할 전깃줄 수를 출력
        bw.write(String.valueOf(n - size));
        bw.flush(); // 출력 결과를 버퍼에 저장한 후 내보냄
    }
}
