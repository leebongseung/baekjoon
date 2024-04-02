package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 12분
 * 종료 시간 : ??
 */
public class _1074 {

    public static int idx = 0;
    public static int r;
    public static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 2^n크기의 배열
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열

        int arrSize = (int)Math.pow(2, n);
//        System.out.println(arrSize);
        findMaxArrSize(0, 0, arrSize - 1, arrSize - 1);

/*        for(int i =0; i < arrSize; i++){
            for(int j =0; j< arrSize; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    public static void findMaxArrSize(int startX, int startY, int endX, int endY) {
        int dx = endX - startX;
        int dy = endY - startY;
         if(dx > 2 && dy > 2){
            int midx = (endX + startX) / 2;
            int midy = (endY + startY) / 2;

            // 왼쪽 상단
             if (r > midx && c > midy) idx += ((midx - startX + 1) * (midy - startY + 1));
             else findMaxArrSize(startX, startY, midx, midy);
            // 오른쪽 상단
             if(r > midx && c < midy+1) idx += ((midx - startX + 1) * (endY- (midy+1) + 1));
            else findMaxArrSize(startX, midy+1, midx, endY);
            // 왼쪽 하단
             if(r < midx+1 && c > midy) idx += ((endX - (midx+1) +1) * (midy - startY +1));
            else findMaxArrSize(midx+1, startY, endX, midy);
            // 오른쪽 하단
             if(r < midx+1 && c < midy+1) idx += ((endX - (midx + 1) + 1) * (endY - (midy + 1) + 1));
            else findMaxArrSize(midx+1, midy+1, endX, endY);

        } else {
            for(int i =startX; i<= endX; i++){
                for(int j = startY; j<= endY; j++){
                    idx++;
                    if(i == r && j == c){
                        System.out.println(idx - 1);
                        return;
                    }
                }
            }
        }
    }

}
