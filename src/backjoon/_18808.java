package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 4분
 * 종료 시간 : ?
 * <p>
 * 문제분석
 * - 스티커는 아래와 같이 모눈 종이 위에 인쇄됨.
 * - 스티커를 붙일 거임
 * 1. 회전 시키지 않고 모눈종이에서 떼어냄
 * 2. 다른 스티커와 겹치거나 노트북을 벗어나지 않고 스티커를 붙임
 * - 노트북의 위쪽 부터 채워나감.
 * - 스티커를 붙일 수 있는 위치가 여러곳이라면 젤 위쪽 부터
 * - 위쪽 왼쪽 부터 선택하기
 * 3. 선택한위치에 스티커 붙이기, 만약 스티커를 붙일곳이 없다면 시계 방향으로 90도 회전하고 2번과정 반복하기
 * 4. 0도, 90도, 180도, 270도 회전시킴에도 못 붙이면 스티커 버리기
 */
public class _18808 {
    public static int n;
    public static int m;
    public static int k;
    public static int[][] arr;
    public static List<int[][]> lst;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        lst = new ArrayList<>();
        for (int kCase = 0; kCase < k; kCase++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[x][y];

            for (int i = 0; i < x; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < y; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            lst.add(sticker);
        }

        // 스티커 붙이기 시작
        while (!lst.isEmpty()) {

            int[][] sticker = lst.remove(0);

            roof:
            for(int rotation = 0; rotation < 4; rotation ++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!findAPlaceToPutStickers(sticker, i, j)) continue;
                        // 스티커 붙이기
                        sticker(sticker, i, j);
                        break roof;
                    }
                }

                // 스티커 회전 시키기
                sticker = rotationSticker(sticker.length, sticker[0].length ,sticker);
            }
        }

        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr[i][j] == 1) cnt ++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean findAPlaceToPutStickers(int[][] sticker, int i, int j) {
        // 1. 노트북의 맨위 부터 스티커를 붙일 수 있는지 확인해보기!
        for (int x = 0; x < sticker.length; x++) {
            for (int y = 0; y < sticker[0].length; y++) {
                int dx = x + i;
                int dy = y + j;
                // 2. 스티커가 범위를 벗어날 경우 회전 시켜야함
                if (dx >= n || dy >= m ) return false;
                if(sticker[x][y] == 1 && arr[dx][dy] == 1) return false;
            }
        }
        return true;
    }

    private static void sticker(int[][] sticker, int i, int j) {
        // 1. 노트북의 맨위 부터 스티커를 붙일 수 있는지 확인해보기!
        for (int x = 0; x < sticker.length; x++) {
            for (int y = 0; y < sticker[0].length; y++) {
                if(sticker[x][y] == 1){
                    arr[x + i][y + j] = 1;
                }
            }
        }
    }

    private static int[][] rotationSticker(int x, int y, int[][] origin) {
        int[][] arr = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                arr[i][j] = origin[x-1-j][i];
            }
        }


//        System.out.println("회전시작");
//        for (int[] ints : arr) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("=====");
        return arr;
    }
}
