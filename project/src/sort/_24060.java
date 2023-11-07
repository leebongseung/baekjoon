package sort;
/*  시작 시간 : 11시 5분
*   종료 시간 :
* */
import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _24060 {
    public static int mergesort(int[] arr, int start, int end, int cnt, int k){
        if(start < end){
            int mid = (start + end) / 2;
            mergesort(arr,start,mid, cnt,k);
            mergesort(arr, mid+1, end, cnt,k);
            int result = merge(arr, start, mid, end, cnt, k);
            return result;
        }
        return -1;
    }

    public static int merge(int[] arr, int start, int mid, int end, int cnt, int k){
        int[] tmp = new int[end+1];
        int originstart = start;
        int t = 0;
        int i = start;
        int j = mid+1;
        while(start <= mid && mid+1 <= end ){
            System.out.println("start = " + start);
            System.out.println("mid = " + mid);
            System.out.println("end = " + end);
            System.out.println("t = " + t);

            if(arr[i] <= arr[j]){
                tmp[t++] = arr[i++];
            } else{
                tmp[t++] = arr[j++];
            } cnt++;
            if(cnt == k){
                return tmp[t];
            }

        }
        while(i <= mid){
            tmp[t++] = arr[i++];
        }

        while(j <= end){
            tmp[t++] = arr[j++];
        }

        t = 0;
        while(start <= end){
            arr[i++] = tmp[t++];
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 배열의 크기
        int k = Integer.parseInt(st.nextToken()); // 저장횟수

        int[] arr = new int[n];

        st = new StringTokenizer(bf.readLine());
        int idx = 0;
        while(st.hasMoreTokens()){
            arr[idx] = Integer.parseInt(st.nextToken());
            idx++;
        }

        int cnt =0;
        int result = mergesort(arr, 0, n-1, cnt, k);
        System.out.println(result);

        for (int i : arr) {
            System.out.print(i +" ");
        }

    }
}
