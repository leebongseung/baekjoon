package sort;
/*  시작 시간 : 11시 5분
 *   종료 시간 : 13시 30분
 * */

import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _24060 {
    static int cnt;

    public static void addCnt() {
        cnt++;
    }

    public static void mergesort(int[] tmp, int[] arr, int start, int end, int k) {
//        int result = -1;
        if (start < end) {
            int mid = (start + end) / 2;
            mergesort(tmp, arr, start, mid, k);
            mergesort(tmp, arr, mid + 1, end, k);
            merge(tmp, arr, start, mid, end, k);

        }
    }

    public static void merge(int[] tmp, int[] arr, int start, int mid, int end, int k) {
        int t = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {

            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
            addCnt();

            if (cnt == k) {
                System.out.println(tmp[t - 1]);
                System.exit(0);
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];
            addCnt();
            if (cnt == k) {
                System.out.println(tmp[t - 1]);
                System.exit(0);
            }
        }

        while (j <= end) {
            tmp[t++] = arr[j++];
            addCnt();
            if (cnt == k) {
                System.out.println(tmp[t - 1]);
                System.exit(0);
            }
        }

        i = start;
        t = 0;
        while (i <= end) {
            arr[i++] = tmp[t++];
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 배열의 크기
        int k = Integer.parseInt(st.nextToken()); // 저장횟수

        int[] arr = new int[n];
        int[] tmp = new int[n];

        st = new StringTokenizer(bf.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            arr[idx] = Integer.parseInt(st.nextToken());
            idx++;
        }
        mergesort(tmp, arr, 0, n - 1, k);
        System.out.println(-1);
    }
}
