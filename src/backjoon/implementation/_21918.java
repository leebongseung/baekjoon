package backjoon.implementation;
/*  시작 시간 : 9시 00 분
*   종료 시간 : 9시 28분
* */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class _21918 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Boolean> bulbs = new ArrayList<>();
        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<n; i++) {
            boolean value = Objects.equals(st.nextToken(), "1");
            bulbs.add(value);
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int rule = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            switch (rule){
                case (1):
                    boolean value = y == 1;
                    bulbs.set(x-1, value);
                    break;
                case (2):
                    for(int j = x-1; j < y; j++){
                        bulbs.set(j, !bulbs.get(j));
                    }
                    break;
                case (3):
                    for(int j = x-1; j < y; j++){
                        bulbs.set(j, false);
                    }
                    break;
                case (4):
                    for(int j = x-1; j < y; j++){
                        bulbs.set(j, true);
                    }
                    break;
            }
        }

        for (Boolean bulb : bulbs) {
            if(bulb) bw.write("1 ");
            else bw.write("0 ");
        }
        bw.flush();
    }
}
