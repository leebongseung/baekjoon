package backjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 시작 시간 : 10시 10분
 * 종료 시간 : ??
 * <p>
 * 1. 문제분석
 * - 미로의 한칸에 남쪽을 보면서 서있음.(남쪽은 아래쪽이죠)
 * - 미로는 직사각형 모양
 * - 한칸 이동하거나, 벽을 포함하고 있음.
 * - F : 한칸 움직이기
 * - L : 왼쪽으로 전환하기
 * - R : 오른쪽으로 전환하기
 * - 즉 90도씩 움직이면서 위치는 그대로임
 * <p>
 * 결론 : 구현문제인것같다.
 * <p>
 * // n개를 입력받는다 이것은 홍준이가 적은 내용의 길이다.
 * // 문자열RRFRF 이런식으로 이어서 받는다
 * // 문자열 길이만큼 반복한다.
 * <p>
 * // 바라보는 방향을 인식해야하기 때문에 TOP, LEFT, RIGHT, BOTTOM 으로 현재상태를 업데이트한다.
 * // 초기 상태는 아래를 보고 있기 때문에 BOTTOM 이라고 적어둔다.
 * // R일 경우
 * // L일 경우
 * <p>
 * // 미로의 시작부분은 어디가 되어야할까..?
 * <p>
 * //
 */
public class _1347 {

    public static List<List<String>> lst = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        lst.add(new LinkedList<>());
        lst.get(0).add(".");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String str = bf.readLine();

        Direction direction = new Direction();

        for(int i=0; i<n; i++){
            char c = str.charAt(i);

            switch (c){
                case 'R':
                    direction.turnRight();
                    break;
                case 'L':
                    direction.turnLeft();
                    break;
                case 'F':
                    direction.goStraight();
                    break;
            }
        }

        for (List<String> strings : lst) {
            for (String string : strings) {
                bw.write(string);
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public static class Direction {
        private enum currentDirection {
            BOTTOM(0, new int[]{1, 0}),
            LEFT(1, new int[]{0, -1}),
            TOP(2, new int[]{-1, 0}),
            RIGHT(3, new int[]{0, 1});

            private int idx;
            private int[] pos;

            currentDirection(int idx, int[] pos) {
                this.idx = idx;
                this.pos = pos;
            }

            public int[] getPos() {
                return pos;
            }

            public int getIdx() {
                return idx;
            }
        }

        private currentDirection direction;
        private int[] pos;

        public Direction() {
            this.direction = currentDirection.BOTTOM;
            this.pos = new int[]{0, 0};
        }

        public void turnLeft() {
            int val = (this.direction.getIdx() - 1);
            // 음수일 경우를 고려하여 순환합니다.
            if (val < 0) {
                val = currentDirection.values().length - 1;
            }

            this.direction = currentDirection.values()[val];
        }

        public void turnRight() {
            int length = currentDirection.values().length;
            int val = this.direction.getIdx()+1;
            if(val >= length) val = 0;

            this.direction =  currentDirection.values()[val];
        }

        public void goStraight() {
            int[] currentPos = this.pos;
            int[] dpos = direction.getPos();
            int dx = currentPos[0] + dpos[0];
            int dy = currentPos[1] + dpos[1];

            if(dy != currentPos[1]){
                // 왼쪽 끝으로 이동하는 경우
                if(dy < 0) {
                    lst.get(dx).add(0, ".");

                    for (List<String> strings : lst) {
                        if (strings.size() < lst.get(dx).size()) {
                            strings.add(0, "#");
                        }
                    }
                    this.pos = new int[]{dx, 0};
                } else if(dy > lst.get(0).size() - 1 ) {
                    // 오른쪽으로 이동
                    lst.get(dx).add(".");

                    for (List<String> strings : lst) {
                        if (strings.size() < lst.get(dx).size()) {
                            strings.add("#");
                        }
                    }
                    this.pos = new int[]{dx, dy};
                }else {
                    // 이미존재한다면?
                    lst.get(dx).remove(dy);
                    lst.get(dx).add(dy, ".");
                    this.pos = new int[]{dx, dy};
                }
            } else{
                if(dx < 0){
                    // 위로 움직일 경우
                    lst.add(0, new LinkedList<>());

                    // 문자열 생성하고
                    // 해당(y)번째 문자만 .으로 처리하기
                    int size = lst.get(1).size();
                    for(int i =0; i< size; i++){
                        if(i == dy) lst.get(0).add(".");
                        else lst.get(0).add("#");
                    }
                    this.pos = new int[]{0, dy};
                } else if(dx >= lst.size()){
                    // 아래로 움직일 경우
                    lst.add(new LinkedList<>());

                    // 문자열 생성하고
                    // 해당(y)번째 문자만 .으로 처리하기
                    int xSize = lst.size() - 1;
                    int ySize = lst.get(xSize-1).size();
                    for(int i =0; i< ySize; i++){
                        if(i == dy) lst.get(xSize).add(".");
                        else lst.get(xSize).add("#");
                    }
                    this.pos = new int[]{dx, dy};
                } else {
                    lst.get(dx).remove(dy);
                    lst.get(dx).add(dy, ".");
                    this.pos = new int[]{dx, dy};
                }
            }
        }
    }
}
