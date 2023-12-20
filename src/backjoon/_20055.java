package backjoon;

import java.awt.image.renderable.RenderableImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*  시작 시간 : 9시 5분
*   종료 시간 :
* */
public class _20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()); // 2n개 주어짐.
        int k = Integer.parseInt(st.nextToken());
        List<Integer> topArr = new LinkedList<>(); // 올리는 위치 ,i 번째 칸의 내구도, 1칸씩 이동 => 마지막인덱스를 젤앞으로 이동시키면 됨
        List<Integer> bottomArr = new LinkedList<>(); // 내리는 위치, i번째 칸의 내구도, 1칸씩 이동 => 위와 동일

        List<Boolean> isTopRobotArr = new LinkedList<>();
        List<Boolean> isBottomRobotArr = new LinkedList<>();
        // index 는 한칸씩 계속 이동함.
        /*
        * 1. 올리는 위치 2N -> 1
        * 2. 내리는 위치 N -> N+1
        * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다
        *   2-1.로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
        * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        * 4.내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
        * */

        st = new StringTokenizer(bf.readLine());
        for(int i =0; i<n; i++){
            topArr.add(Integer.parseInt(st.nextToken()));
            isTopRobotArr.add(false);
        }
        for(int i =0; i<n; i++){
            bottomArr.add(Integer.parseInt(st.nextToken()));
            isBottomRobotArr.add(false);
        }

        int res = 0;
        int cnt = 0;
        int firstIdx = 0;
        int LastIdx = n-1;
        while(res < k){
            cnt++;
            // top컨베이어 벨트는 로봇을 : 내리는 과정 및 이동하는 과정
            // bottom 컨베이어 벨트는 로봇을 올리는 과정 및 이동하는 과정
        }
        System.out.println(cnt);
    }
}
