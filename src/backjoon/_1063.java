package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 10시 30분
 * 종료 시간 : 11시 10분
* */
public class _1063 {
    enum index{
        R(1,0),
        L(-1,0),
        B(0,-1),
        T(0,1),
        RT(1,1),
        LT(-1,1),
        RB(1,-1),
        LB(-1,-1);
        private final int row;
        private final int column;

        index(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow(){
            return row;
        }
        public int getColumn(){
            return column;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] kingIdx = new int[2];
        int[] stoneIdx = new int[2];

        String firstKingIdx = st.nextToken();
        kingIdx[0] = firstKingIdx.charAt(0) - 'A';
        kingIdx[1] = firstKingIdx.charAt(1) - '0' - 1;

        String firstStoneIdx = st.nextToken();
        stoneIdx[0] = firstStoneIdx.charAt(0) - 'A';
        stoneIdx[1] = firstStoneIdx.charAt(1) - '0' - 1;

        int n = Integer.parseInt(st.nextToken());

        for(int i =0; i< n; i++){
            String val = bf.readLine();
            index index = _1063.index.valueOf(val);
            int x = kingIdx[0] + index.getRow();
            int y = kingIdx[1] + index.getColumn();

            if(x < 0 || x >= 8 || y < 0 || y >= 8){
                continue;
            }

            if(stoneIdx[0] == x  && stoneIdx[1] == y){
                int dx = stoneIdx[0] + index.getRow();
                int dy = stoneIdx[1] + index.getColumn();

                if(dx < 0 || dx >= 8 || dy < 0 || dy >= 8){
                    continue;
                }
                stoneIdx[0] = dx;
                stoneIdx[1] = dy;
            }
            kingIdx[0] = x;
            kingIdx[1] = y;
        }

        // 킹 위치 출력하기
        StringJoiner sj = new StringJoiner("\n");
        StringBuilder sb = new StringBuilder("");
        sb.append(Character.toChars(kingIdx[0] + 'A'));
        sb.append(kingIdx[1] + 1);
        sj.add(sb);

        // 돌 위치 출력하기
        sb = new StringBuilder("");
        sb.append(Character.toChars(stoneIdx[0] + 'A'));
        sb.append(stoneIdx[1] + 1);
        sj.add(sb);

        System.out.println(sj);
    }
}
