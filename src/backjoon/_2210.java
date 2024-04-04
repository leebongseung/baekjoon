package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class _2210 {
    public static int[][] arr = new int[5][5];
    public static Set<String> set = new HashSet<>();
    enum Direction  {
        top(-1,0),
        bottom(1,0),
        left(0,-1),
        right(0,1);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for(int i =0; i<5; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<5; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i =0; i<5; i++){
            for(int j=0; j<5; j++){
                makeString(String.valueOf(arr[i][j]), i, j);
            }
        }
        System.out.println(set.size());
    }
    public static String makeString(String str, int x, int y){
        if(str.length() == 6) {
            set.add(str);
            return str;
        }

        for (Direction d : Direction.values()) {
            int dx = d.getX() + x;
            int dy = d.getY() + y;

            if (dx < 0 || dx >= 5 || dy < 0 || dy >= 5) continue;

            String currentStr = String.valueOf(arr[dx][dy]);
            makeString(str+currentStr, dx, dy);
        }

        return "";
    }
}
