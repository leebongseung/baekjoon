package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 6시 8분
 * 종료 시간 : ?
 *
 * 문제분석
 * - 두봉지 사서 양손에 하나씩 들고감
 * - n개의 과자 봉지가 있으며, a_i 그램의 무게를 가짐
 * - 최대한 많은 양의 과자 봉지를 고르고 싶음.
 * - 무게가 m그램을 초과하면 무거워서 못들고 다님.
 *
 * - 한빈이가 들고 다닐수 있는 과자들의 최대 무게 합을 ㅊ출력하시오
 * - 무조건 2개 사야함.
 * - 투포인터로 접근하면되겠다
 * */
public class _9229 {
    public static int n;
    public static int m;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tCase = Integer.parseInt(bf.readLine());

        for(int t = 1; t<= tCase; t++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            arr = new int[n];
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);

            int res = -1;
            for(int i=n-1; i> 0; i--){
                int left = 0;
                int right = i-1;

                if(arr[i] >= m ) continue;

                while(left <= right){
                    int mid = (left+right) / 2;

                    if(arr[mid] + arr[i] > m){
                        right = mid - 1;
                    } else {
                        // mid < num
                        left = mid + 1;
                        res = Math.max(res, arr[mid] + arr[i]);
                    }
                }
            }

            bw.write("#" + t +" ") ;
            bw.write(res + "\n");
        }

        bw.flush();
    }
}
