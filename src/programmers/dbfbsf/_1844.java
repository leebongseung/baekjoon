package programmers.dbfbsf;
import java.util.*;

public class _1844 {
    public int solution(int[][] maps) {
        int[] dx = {0, 0, -1, 1}; //아래, 위, 왼쪽, 오른쪽
        int[] dy = {-1, 1, 0, 0};
        int n = maps.length;
        int m = maps[0].length;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0,0});

        while(!q.isEmpty()){
            int[] position =  q.poll(); // position[0] : x 범위, position[1] : y 범위

            for(int i=0; i<4; i++){
                int x = position[0] + dx[i];
                int y = position[1] + dy[i];
                if(x < 0 || x >= n || y < 0 || y >= m){
                    continue;
                }

                if(maps[x][y] == 1){
                    maps[x][y] = maps[position[0]][position[1]] + 1;
                    q.add(new int[]{x,y});
                    //System.out.println(maps[x][y]);
                }
            }
        }

        if(n != 1 && maps[n-1][m-1] <= 1){
            return -1;
        } else{
            return maps[n-1][m-1];
        }

    }
}
